package com.aca.classproject.controller;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aca.classproject.model.Notification;
import com.aca.classproject.model.Person;
import com.aca.classproject.model.Subscription;
import com.aca.classproject.service.*;

@Path("/notification")
public class NotificationController {
		
	@POST
	@Path("/subscribe")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertSubscription(Person person) {
		/* creates an instance of the Subscription object, then Jersey works behind the scenes 
		 * of the javax.ws.rs imports to map the variables of the 
		 * JSON object to the variables in the Subscription object
		*/
		
		//validate values coming from client
		System.out.println("Values received from client: ");
		System.out.println(person.getFirstName());
		System.out.println(person.getLastName());
		System.out.println(person.getEmailAddress());
		for (Subscription subscription : person.getSubscriptions()) {
			System.out.println(subscription.getTopic() + ", " + subscription.getIsSubscribed() + ", " + subscription.getStatus());
		}
			 
				
		/*
		System.out.println(subscription.getIsConsoleSub());
		System.out.println(subscription.getIsHeaterSub());
		System.out.println(subscription.getIsLawnSub());
		System.out.println(subscription.getIsToolSub());
		System.out.println(subscription.getIsTelevisionSub());
		*/
				
		NotificationService service = new NotificationService();
		
		String returnMessage;
		
		returnMessage = service.newSubscription(person);
		
		return returnMessage;
	}
	
	
	@GET
	@Path("/subscribe")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getPersons() {
		NotificationService service = new NotificationService();
		
		List<Person> list = new ArrayList();
		list = service.getCurrentPersons();
		
		return list; 
	}
	
	
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
