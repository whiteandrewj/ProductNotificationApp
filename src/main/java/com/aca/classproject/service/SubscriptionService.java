package com.aca.classproject.service;

import com.aca.classproject.dao.AmazonSubscriptionDao;
import com.aca.classproject.dao.AwsCreds;
import com.aca.classproject.dao.DbSubscriptionDao;
import com.aca.classproject.model.Subscription;
import com.aca.classproject.model.Topic;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

public class SubscriptionService {
	
	//first make call to AWS to add new sub to topic
	//second insert new sub into DB
	
	public void insertSubscription(Subscription subscription) {
		
		
		AmazonSubscriptionDao awsSubscription = new AmazonSubscriptionDao();
		DbSubscriptionDao dbSubscription = new DbSubscriptionDao();
		
		if (subscription.getIsComputerSub()) {
			//creates new subscription in AWS SNS
			awsSubscription.createNewComputerSubscription(subscription);
			
			//inserts new subscription into database
			dbSubscription.insertNewComputerSubscription(subscription);
		}
	}

}
