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

public class ContactDetailsServlet extends HttpServlet {

	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		/*
		 * To check a contact's details. Edited the modify function from the PDF
		 * 
		*/
		
		resp.setContentType("text/html");
		// Let's output the basic HTML headers
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>Modify a contact</title></head><body>");
		// Get the datastore
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		// Get the entity by key
		Entity contact = null;
		String name = "", phone = "", pict = "", email = "";
		try {
		contact = datastore.get(KeyFactory.stringToKey(req.getParameter("id")));
		name = (contact.getProperty("name") != null) ? (String) contact.getProperty("name") : "";
		phone = (contact.getProperty("phone") != null) ? (String) contact.getProperty("phone") : "";
		email = (contact.getProperty("email") != null) ? (String) contact.getProperty("email") : "";
		pict = (contact.getProperty("pict") != null) ? (String) contact.getProperty("pict") : "";
		} catch (EntityNotFoundException e) {
		resp.getWriter().println("<p>Creating a new contact</p>");
		} catch (NullPointerException e) {
		// id paramenter not present il the URL
		resp.getWriter().println("<p>Creating a new contact</p>");
		}
		out.println("<form action=\"save\" method=\"post\" name=\"contact\">");
		
		
		// Let's shot the image as well!
		out.println(			
			"<img src=" + pict + "><br/>"
			
			+"<label>Name: </label><input name=\"name\" value=\"" + name + "\"/><br/>"
				
		+ "<label>Phone: </label><input name=\"phone\" value=\"" + phone + "\"/><br/>"
		+ "<label>Email: </label><input name=\"email\" value=\"" + email + "\"/><br/>"
		+ "<label>Picture: </label><input name=\"pict\" value=\"" + pict + "\"/><br/>");
		out.println("<br/><input type=\"submit\" value=\"Continue\"/></form></body></html>");
		}

		
		
		
	}

