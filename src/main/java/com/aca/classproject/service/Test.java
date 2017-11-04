package com.aca.classproject.service;

import java.util.List;
import java.util.Map;

import com.aca.classproject.dao.AwsCreds;
import com.aca.classproject.dao.MariaDbDao;
import com.aca.classproject.model.Notification;
import com.aca.classproject.model.Person;
import com.aca.classproject.model.Topic;
import com.aca.classproject.dao.AmazonSnsDao;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.ConfirmSubscriptionRequest;
import com.amazonaws.services.sns.model.ConfirmSubscriptionResult;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesResult;
import com.amazonaws.services.sns.model.GetTopicAttributesResult;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.amazonaws.services.sns.model.Subscription;

public class Test {
	
	public static void main(String[] args) {
		
		
		MariaDbDao db = new MariaDbDao();
		List<Person> personList = db.getPersonsWithSubscriptions();
		/*
		for (Person person : personList) {
			System.out.println(person.getSqlPrimaryKey());
			System.out.println(person.getFirstName());
			System.out.println(person.getLastName());
			System.out.println(person.getEmailAddress());
			System.out.println(person.getSubscriptions());
		}
		*/
		
		
		AmazonSnsDao dao = new AmazonSnsDao();
		List<Subscription> snsList = dao.getSubscriptions();
		for(Subscription subscription : snsList) {
			System.out.println(subscription.getEndpoint());
			System.out.println(subscription.getSubscriptionArn());
			System.out.println(subscription.getTopicArn());	
			
		};
		
		//e.g. white_andrewj@yahoo.com
		for (Person person : personList) {
			
			//e.g. "COMPUTER"
			for (Topic topic : Topic.values()) {
				
				//e.g. white_andrewj@yahoo.com
				for (Subscription snsSubscription : snsList) {
					
					//checks if current person email matches current subscription email && checks if current subscription ARN matches current topic ARN
					if (person.getEmailAddress().equals(snsSubscription.getEndpoint()) && snsSubscription.getTopicArn().equals(topic.awsSnsArn())) {
						//insert ARN# into db
					} else {
						
						//switches value of current topic to false if true
						if (person.getSubscriptions().get(topic.displayName())) {
							person.getSubscriptions().put(topic.displayName(), false);
							//TODO call some sql update methods
					}
					
				}
			}			
		}
		
		
		}
		
		/*Subscription sub = new Subscription();
		sub.setIsComputerSub(true);
		sub.setEmailAddress("white_andrewj@yahoo.com");
				
		
		SubscribeRequest request = new SubscribeRequest();
		if(sub.getIsComputerSub()) {
			
		}
		request.setTopicArn(Topic.COMPUTER.awsSnsArn());
		
		request.setProtocol("email");
		request.setEndpoint(sub.getEmailAddress());
		
		/* test looping through enum to determine notification topic
		Notification note = new Notification();
		note.setTopic("Lawnmower");
		
		AmazonSNSClient client = new AmazonSNSClient(AwsCreds.getAwsCreds());
		
		for(Topic t : Topic.values()) {
			if(t.displayName().equals(note.getTopic())) {
				System.out.println(t.displayName());
			}
			
		}
		*/
		
		//if DB 'pending' or 'ARN' and sub nothing, remove from DB
		//if DB 'pending' and sub 'pending': do nothing
		//if DB 'pending' and sub 'ARN': update DB
		//if DB 'ARN' and sub 'ARN': do nothing
		
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
 