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
	public int insertSubscription(Subscription subscription) {
		/* creates an instance of the Subscription object, then Jersey works behind the scenes 
		 * of the javax.ws.rs imports to map the variables of the 
		 * JSON object to the variables in the Subscription object
		*/
		
		SubscriptionService service = new SubscriptionService();
		int rowsChanged = service.insertSubscription(subscription);
		return rowsChanged;
	}
}
