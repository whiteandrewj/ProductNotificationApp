package com.aca.classproject.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
	
	public List<Person> getCurrentPersons() {
				
		MariaDbDao db = new MariaDbDao();
		List<Person> personList = db.getPersons();
		personList = db.getSubscriptions(personList);		
		
		for (Person person : personList) {
			System.out.println(person.getSqlPrimaryKey());
			System.out.println(person.getFirstName());
			System.out.println(person.getLastName());
			System.out.println(person.getEmailAddress());
			for (Subscription subscription : person.getSubscriptions()) {
				System.out.println(subscription.getTopic() + ": " + subscription.getIsSubscribed() + ", " + subscription.getStatus());
			}			
		}		
		
		AmazonSnsDao dao = new AmazonSnsDao();
		List<com.amazonaws.services.sns.model.Subscription> snsList = dao.getSubscriptions();
		
		for(com.amazonaws.services.sns.model.Subscription snsSubscription : snsList) {
			System.out.println("");
			System.out.println(snsSubscription.getEndpoint());
			System.out.println(snsSubscription.getTopicArn());
			System.out.println(snsSubscription.getSubscriptionArn());
		};
		
		boolean isMatchFound;
		String awsArn;
		
		for (Person person : personList) {			
			
			for (Subscription subscription : person.getSubscriptions()) {	
				
				isMatchFound = false;
				awsArn = null;
				
				for (com.amazonaws.services.sns.model.Subscription snsSubscription : snsList) {					
					
					//checks if match is found b/w subscription object and AWS subscriptions
					if (person.getEmailAddress().equals(snsSubscription.getEndpoint()) && snsSubscription.getTopicArn().equals(subscription.getTopic().awsSnsArn())) {
							isMatchFound = true;	
							awsArn = snsSubscription.getSubscriptionArn();
					} 					
				}
				if (isMatchFound) {
					if (subscription.getIsSubscribed()) {
						if (!awsArn.equals(subscription.getStatus())) {
							db.updateSubscription(awsArn, person.getSqlPrimaryKey(), subscription.getTopic());
							subscription.setStatus(awsArn);							
						}
					} else {
						//TODO sql insert row
						subscription.setIsSubscribed(true);
						subscription.setStatus(awsArn);
					}
				} else {
					if (subscription.getIsSubscribed()) {
						db.deleteSubscription(person.getSqlPrimaryKey(), subscription.getTopic());
						subscription.setIsSubscribed(false);
						subscription.setStatus(null);
					}
				}				
				
				//if email not in AWS and isSubscribed = true
					//sql delete row
					//set values of current subscription to false, null
				
				//if email not in AWS and isSubscribed = false
					//do nothing
				
				//if email is in AWS and isSubscribed = true
						//if status values match 
							//do nothing
						//if status values don't match
							//sql update row
							//set values of current subscription to AWS values
				
				//if email is in AWS and isSubscribed = false
					//sql insert row
					//set current subscription values to AWS values				
			}
		}
		for (Person person : personList) {
			System.out.println(person.getSqlPrimaryKey());
			System.out.println(person.getFirstName());
			System.out.println(person.getLastName());
			System.out.println(person.getEmailAddress());
			for (Subscription subscription : person.getSubscriptions()) {
				System.out.println(subscription.getTopic() + ": " + subscription.getIsSubscribed() + ", " + subscription.getStatus());
			}
		}
	return personList;
	}	
	
	public void newNotification(Notification notification){
		//call aws api
		//insert into database
		
		AmazonSnsDao awsDao = new AmazonSnsDao();
		awsDao.publishToTopic(notification);
	}
}
