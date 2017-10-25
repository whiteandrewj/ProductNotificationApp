package com.aca.classproject.service;

import com.aca.classproject.dao.AmazonSubscriptionDao;
import com.aca.classproject.dao.AwsCreds;
import com.aca.classproject.dao.DbSubscriptionDao;
import com.aca.classproject.model.Subscript;
import com.aca.classproject.model.Topic;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

public class SubscriptionService {
	
	//first make call to AWS to add new sub to topic
	//second insert new sub into DB
	
	private String returnMessage;
	
	public String insertSubscription(Subscript subscription) {
		
		AmazonSubscriptionDao awsSubscription = new AmazonSubscriptionDao();
		DbSubscriptionDao dbSubscription = new DbSubscriptionDao();
		
		String dbReturnMessage = null;
		String amazonReturnMessage = null;
		
		if (subscription.getIsComputerSub()) {
			//add new subscription in AWS SNS
			dbReturnMessage = awsSubscription.createNewComputerSubscription(subscription);
			
			//inserts new subscription into database
			amazonReturnMessage = dbSubscription.insertNewComputerSubscription(subscription);
		}
		
		returnMessage = dbReturnMessage + amazonReturnMessage;
				
		System.out.println(returnMessage);
		
		return returnMessage;
	}

}
