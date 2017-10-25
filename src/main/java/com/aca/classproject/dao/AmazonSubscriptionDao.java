package com.aca.classproject.dao;

import com.aca.classproject.model.Subscript;
import com.aca.classproject.model.Topic;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

public class AmazonSubscriptionDao {
	
	private String amazonReturnMessage;

	public String createNewComputerSubscription(Subscript subscription) {
		
		AmazonSNSClient client = new AmazonSNSClient(AwsCreds.getAwsCreds());	
			
		SubscribeRequest request = new SubscribeRequest();
		request.setTopicArn(Topic.COMPUTER_SUB_ARN);
		request.setProtocol("email");
		request.setEndpoint(subscription.getEmailAddress());
					
		SubscribeResult result = client.subscribe(request);
		
		amazonReturnMessage = "AWS.... Result status code: " + result.getSdkHttpMetadata().getHttpStatusCode()
				+ ", Subscription ARN: " + result.getSubscriptionArn() + "\n";
		System.out.println(amazonReturnMessage);
	
		client.shutdown();
		return amazonReturnMessage;
	}
	
	
	
}
