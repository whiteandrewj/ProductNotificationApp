package com.aca.classproject.dao;

import com.aca.classproject.model.Notification;
import com.aca.classproject.model.Subscription;
import com.aca.classproject.model.Topic;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

public class AmazonSnsDao { 

	public String createNewComputerSubscription(Subscription subscription) {
		
		AmazonSNSClient client = new AmazonSNSClient(AwsCreds.getAwsCreds());	
			
		SubscribeRequest request = new SubscribeRequest();
		request.setTopicArn(Topic.COMPUTER.awsSnsArn());
		request.setProtocol("email");
		request.setEndpoint(subscription.getEmailAddress());
					
		SubscribeResult result = client.subscribe(request);
		String amazonReturnMessage;
		amazonReturnMessage = "AWS.... Result status code: " + result.getSdkHttpMetadata().getHttpStatusCode()
				+ ", Subscription ARN: " + result.getSubscriptionArn() + "\n";
		System.out.println(amazonReturnMessage);
	
		client.shutdown();
		return amazonReturnMessage;
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
		request.setTopicArn(Topic.COMPUTER.awsSnsArn());
		request.setSubject(notification.getTopic());
		
		System.out.println(request.getMessage());
		
		PublishResult result = client.publish(request);
		
		System.out.println("Aws http response code: " + result.getSdkHttpMetadata().getHttpStatusCode());
	}
		
	
}
