package com.aca.classproject.service;

import java.util.List;
import java.util.Map;

import com.aca.classproject.dao.AwsCreds;
import com.aca.classproject.model.Topic;
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
		
		AmazonSNSClient client = new AmazonSNSClient(AwsCreds.getAwsCreds());
		
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
	}	
		
}
 