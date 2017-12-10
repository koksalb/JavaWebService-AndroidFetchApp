package eurecom.fr.AddressBook;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;

public class SaveContactServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Save contact servlet. GET method doesn't do anything.");
		}
		/**
		* Save a contact in the DB. The contact can be new or already existent.
		*/
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Retrieve informations from the request
		String contactName = req.getParameter("name");
		String contactPhone = req.getParameter("phone");
		String contactEmail = req.getParameter("email");
		String contactPict = req.getParameter("pict");
		// Take a reference of the datastore
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		// Generate or retrieve the key associated with an existent contact
		// Create or modify the entity associated with the contact
		Entity contact;
		contact = new Entity("Contact", contactName);
		contact.setProperty("name", contactName);
		
		contact.setProperty("phone", contactPhone);
		contact.setProperty("email", contactEmail);
		contact.setProperty("pict", contactPict);
		// Save in the Datastore
		datastore.put(contact);
		resp.getWriter().println("Contact " + contactName + " saved with key " +
		KeyFactory.keyToString(contact.getKey()) + "!");
		}
	
	
}
