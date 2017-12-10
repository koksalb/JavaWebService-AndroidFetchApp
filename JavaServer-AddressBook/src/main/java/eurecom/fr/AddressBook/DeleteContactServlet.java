package eurecom.fr.AddressBook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;

public class DeleteContactServlet extends HttpServlet {

	
public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.setContentType("text/html");
		// Let's output the basic HTML headers
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>Delete a contact</title></head><body>");
		// Get the datastore
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		// Get the entity by key
		Entity contact = null;
		String name = "";
		
		try {
			contact = datastore.get(KeyFactory.stringToKey(req.getParameter("id")));
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		name = (contact.getProperty("name") != null) ? (String) contact.getProperty("name") : "";
		
		datastore.delete(KeyFactory.stringToKey(req.getParameter("id")));
		
		// Let's delete the person!
		out.println("Contact " + name +" deleted from the list!</body></html>");
		}
	
	
	
}
