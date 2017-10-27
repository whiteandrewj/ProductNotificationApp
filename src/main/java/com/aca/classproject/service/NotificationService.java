package com.aca.classproject.service;

import com.aca.classproject.dao.AmazonSnsDao;
import com.aca.classproject.dao.AwsCreds;
import com.aca.classproject.dao.MariaDbDao;
import com.aca.classproject.model.Notification;
import com.aca.classproject.model.Subscription;
import com.aca.classproject.model.Topic;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

public class NotificationService {
	
	//first make call to AWS to add new sub to topic
	//second insert new sub into DB

	public String insertSubscription(Subscription subscription) {
		
		AmazonSnsDao awsSubscription = new AmazonSnsDao();
		MariaDbDao dbSubscription = new MariaDbDao();
		
		String dbReturnMessage = null;
		String amazonReturnMessage = null;
		
		if (subscription.getIsComputerSub()) {
			//add new subscription in AWS SNS
			dbReturnMessage = awsSubscription.createNewComputerSubscription(subscription);
			
			//inserts new subscription into database
			amazonReturnMessage = dbSubscription.insertNewComputerSubscription(subscription);
		}
		String returnMessage;
		returnMessage = dbReturnMessage + amazonReturnMessage;			
		System.out.println(returnMessage);	
		return returnMessage;
	}

	public void newNotification(Notification notification){
		//call aws api
		//insert into database
		
		AmazonSnsDao awsDao = new AmazonSnsDao();
		awsDao.publishToTopic(notification);
	}
}
