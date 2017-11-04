package com.aca.classproject.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.aca.classproject.dao.AmazonSnsDao;
import com.aca.classproject.dao.AwsCreds;
import com.aca.classproject.dao.MariaDbDao;
import com.aca.classproject.dao.MariaDbUtilities;
import com.aca.classproject.model.Notification;
import com.aca.classproject.model.Person;
import com.aca.classproject.model.Topic;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

public class NotificationService {
	
	//first make call to AWS to add new sub to topic
	//second insert new sub into DB

	public String newSubscription(Person subscription) {
		
		Connection conn = MariaDbUtilities.getConnection();
		MariaDbDao db = new MariaDbDao();
		
		//inserts new person into db and returns connection
		conn = db.newPerson(subscription, conn);
		
		//gets primary key of person record inserted
		int personKey = MariaDbUtilities.getRecordKey(conn);
		
		
		//iterates over each displayName in the enum "Topic"
		for(Topic topic : Topic.values()) {
			
			//checks if the "Subscriptions" HashMap contains a value of true for the current topicName (eg "Computer")  
			if (subscription.getSubscriptions().get(topic.displayName())) {
			
				//adds new subscription in AWS SNS
				AmazonSnsDao awsSubscription = new AmazonSnsDao();
				awsSubscription.newSubscription(subscription, topic);
				
				//inserts new subscriptions into database
				conn = db.newSubscription(conn, personKey, topic);
			}
		}
		
		try {
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
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
