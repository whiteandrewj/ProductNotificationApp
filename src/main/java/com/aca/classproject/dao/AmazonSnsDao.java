package com.aca.classproject.dao;

import java.util.List;

import com.aca.classproject.model.Notification;
import com.aca.classproject.model.Person;
import com.aca.classproject.model.Topic;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.ListSubscriptionsRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.amazonaws.services.sns.model.Subscription;

public class AmazonSnsDao { 

	public String newSubscription(Person person, Topic topic) {
		
		AmazonSNSClient client = new AmazonSNSClient(AwsCreds.getAwsCreds());	
			
		SubscribeRequest request = new SubscribeRequest();
		request.setTopicArn(topic.awsSnsArn());
		request.setProtocol("email");
		request.setEndpoint(person.getEmailAddress());
					
		SubscribeResult result = client.subscribe(request);
		
		System.out.println("AWS SNS response code: " + result.getSdkHttpMetadata().getHttpStatusCode()
				+ ", ARN: " + result.getSubscriptionArn());
	
		client.shutdown();
		
		return result.getSubscriptionArn().toString();
	}
	
	
	/*public static void main(String[] args) {
		Notification notification = new Notification();
		notification.setDescription("15\" screen, 200 GB, Intel 1.5 quad core processer");
		notification.setProductName("IBM ThinkPad Laptop");
		notification.setPrice(120);
		notification.setTopic("computer");
	*/	
	public void publishToTopic(Notification notification) {	
		AmazonSNSClient client = new AmazonSNSClient(AwsCreds.getAwsCreds());
		PublishRequest request = new PublishRequest();
		request.setMessage("Hello from US12 Resource.\nWe have a new " 
							+ notification.getTopic() + " in stock!\n" + notification.getProductName()
							+ "\nPrice: $" + notification.getPrice()
							+ "\n" + notification.getDescription());
		for(Topic topic : Topic.values()) {
			if(topic.displayName().equals(notification.getTopic())) {
				request.setTopicArn(topic.awsSnsArn());
			}
		}
		request.setSubject(notification.getTopic());
		
		System.out.println(request.getMessage());
		
		PublishResult result = client.publish(request);
		
		System.out.println("Aws publish response code: " + result.getSdkHttpMetadata().getHttpStatusCode());
	}
		
	public List<Subscription> getSubscriptions() {
		AmazonSNSClient client = new AmazonSNSClient(AwsCreds.getAwsCreds());
				
		ListSubscriptionsResult result = client.listSubscriptions();
		List<Subscription> list = result.getSubscriptions();
		
		return list;
	}
}
