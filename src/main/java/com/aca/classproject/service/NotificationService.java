package com.aca.classproject.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.aca.classproject.dao.AmazonSnsDao;
import com.aca.classproject.dao.AwsCreds;
import com.aca.classproject.dao.MariaDbDao;
import com.aca.classproject.model.Notification;
import com.aca.classproject.model.Person;
import com.aca.classproject.model.Subscription;
import com.aca.classproject.model.Topic;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

public class NotificationService {
	
	//first make call to AWS to add new sub to topic
	//second insert new sub into DB

	public String newSubscription(Person person) {
		
		
		MariaDbDao db = new MariaDbDao();
		AmazonSnsDao awsSubscription = new AmazonSnsDao();
		
		//inserts new person into db and assigns primaryKey value to sqlPrimaryKey
		person.setSqlPrimaryKey(db.newPerson(person));
			
		//iterates through each Topic enum
		for(Topic topic : Topic.values()) {
			
			//iterates through each subscription
			for (Subscription subscription : person.getSubscriptions()) {
				
				//checks if the "Subscription" topic is the same as our current iteration of the Topic enum && isSubscribed contains a value of true
				if (subscription.getTopic().equals(topic) && subscription.getIsSubscribed()) {
					
					//adds new subscription in AWS SNS and assigns ARN value to subscription
					subscription.setStatus(awsSubscription.newSubscription(person, topic));
					
					//inserts new subscriptions into database
					db.newSubscription(person.getSqlPrimaryKey(), topic, subscription.getStatus());
				}
			}
		}		
		
		return "inserts successful";
	}

	public void updateArn() {
		//get subscriptions from database
		//check aws sns for updates to status (pending, confirmed)
		//update tables in database
		
		AmazonSnsDao amazon = new AmazonSnsDao();
		
	}
	
	public void newNotification(Notification notification){
		//call aws api
		//insert into database
		
		AmazonSnsDao awsDao = new AmazonSnsDao();
		awsDao.publishToTopic(notification);
	}
}
