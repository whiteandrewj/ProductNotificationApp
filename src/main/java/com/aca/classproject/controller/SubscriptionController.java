package com.aca.classproject.controller;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aca.classproject.model.Subscription;
import com.aca.classproject.service.SubscriptionService;


@Path("/subscribe")
public class SubscriptionController {
	
	@POST
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
		
		SubscriptionService service = new SubscriptionService();
		
		service.insertSubscription(subscription);
		
		return null;
	}
}
