package com.aca.classproject.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aca.classproject.model.Notification;
import com.aca.classproject.model.Subscription;
import com.aca.classproject.service.*;

@Path("/notification")
public class NotificationController {
		
	@POST
	@Path("/subscribe")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertSubscription(Subscription subscription) {
		/* creates an instance of the Subscription object, then Jersey works behind the scenes 
		 * of the javax.ws.rs imports to map the variables of the 
		 * JSON object to the variables in the Subscription object
		*/
		
		//validate values coming from client
		System.out.println("Values received from frontend: ");
		System.out.println(subscription.getFirstName());
		System.out.println(subscription.getLastName());
		System.out.println(subscription.getEmailAddress());
		System.out.println(subscription.getIsComputerSub());
		System.out.println(subscription.getIsConsoleSub());
		System.out.println(subscription.getIsHeaterSub());
		System.out.println(subscription.getIsLawnSub());
		System.out.println(subscription.getIsToolSub());
		System.out.println(subscription.getIsTelevisionSub());
		
		NotificationService service = new NotificationService();
		
		String returnMessage;
		
		returnMessage = service.insertSubscription(subscription);
		
		return returnMessage;
	}
	
	/* working on a getSubscriptions functions
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public <List>Subscription getSubscriptions(Subscription subscription) {
		
	return null;
	}
	
	*/
	@POST
	@Path("/new")
	@Produces(MediaType.APPLICATION_JSON)
	public int newNotification(Notification notification) {
		
		System.out.println(notification.getDescription());
		System.out.println(notification.getProductName());
		System.out.println(notification.getTopic());
		System.out.println(notification.getPrice());
		
		NotificationService service = new NotificationService();
		service.newNotification(notification);
		
		return 1;
	}
	
}
