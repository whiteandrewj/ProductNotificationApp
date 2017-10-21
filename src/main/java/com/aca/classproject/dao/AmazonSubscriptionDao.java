package com.aca.classproject.dao;

import com.aca.classproject.model.Subscription;
import com.aca.classproject.model.Topic;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

public class AmazonSubscriptionDao {

	public void createNewComputerSubscription(Subscription subscription) {
		
		AmazonSNSClient client = new AmazonSNSClient(AwsCreds.getAwsCreds());	
			
		SubscribeRequest request = new SubscribeRequest();
		request.setTopicArn(Topic.COMPUTER_SUB_ARN);
		request.setProtocol("email");
		request.setEndpoint(subscription.getEmailAddress());
					
		SubscribeResult result = client.subscribe(request);
		System.out.println("AWS.... result status code " + result.getSdkHttpMetadata().getHttpStatusCode()
				+ ", Subscription ARN: " + result.getSubscriptionArn());
	
		client.shutdown();
	}
	
	
	
}
