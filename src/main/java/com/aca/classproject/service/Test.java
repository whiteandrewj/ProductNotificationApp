package com.aca.classproject.service;

import java.util.List;
import java.util.Map;

import com.aca.classproject.dao.Credentials;
import com.aca.classproject.dao.FacebookDao;
import com.aca.classproject.dao.MariaDbDao;
import com.aca.classproject.model.Notification;
import com.aca.classproject.model.Person;
import com.aca.classproject.model.Subscription;
import com.aca.classproject.model.Topic;
import com.aca.classproject.dao.AmazonSnsDao;
import com.amazonaws.services.sns.AmazonSNSClient;

public class Test {
	
	public static void main(String[] args) {
		
		AmazonSnsDao dao = new AmazonSnsDao();
		List<com.amazonaws.services.sns.model.Subscription> snsList = dao.getSubscriptions();
		
		for(com.amazonaws.services.sns.model.Subscription snsSubscription : snsList) {
			System.out.println("");
			System.out.println(snsSubscription.getEndpoint());
			System.out.println(snsSubscription.getTopicArn());
			System.out.println(snsSubscription.getSubscriptionArn());
		};
		
		/*
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
	}
	//insert ARN# into db
	//switches value of current topic to false if true
	//if (person.getSubscriptions().get(topic.displayName())) {
	//person.getSubscriptions().put(topic.displayName(), false);
	//TODO call some sql update methods
	
		//if AWS nothing and DB 'pending' or 'ARN': remove from DB
		//if AWS has value
	
		//if DB 'pending' and AWS 'pending': do nothing
		//if DB 'pending' and AWS 'ARN': update DB
		//if DB 'ARN' and AWS 'ARN': do nothing
		
		/* checks status of all emails for the given topic
		ListSubscriptionsByTopicResult subResult = client.listSubscriptionsByTopic(Topic.COMPUTER_SUB_ARN);
		List<Subscription> list = subResult.getSubscriptions();
		
		for (Subscription pos : list) {
			System.out.println("email: " + pos.getEndpoint() + " , ARN value: " + pos.getSubscriptionArn());
		}
		*/
		
		/* test subscribe call to AWS SNS
		if (true) {
			
			SubscribeRequest request = new SubscribeRequest();
			request.setTopicArn(Topic.COMPUTER.awsSnsArn());
			request.setProtocol("email");
			request.setEndpoint("white_andrewj@yahoo.com");
						
			SubscribeResult result = client.subscribe(request);
			System.out.println(" result status code " + result.getSdkHttpMetadata().getHttpStatusCode()
					+ "\nHttp result headers: " + result.getSdkHttpMetadata().getHttpHeaders()
					+ "\nSubscription ARN: " + result.getSubscriptionArn()
					+ "\n" + result);
		}
	*/
	}
}	
	

 