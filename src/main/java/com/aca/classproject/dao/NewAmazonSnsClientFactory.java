package com.aca.classproject.dao;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

public class NewAmazonSnsClientFactory {
	
	private final AmazonSNSClientBuilder builder = AmazonSNSClientBuilder.standard()
			.withRegion(Regions.US_WEST_2)
			.withCredentials(null);
	
	public AmazonSNS createClient() {
		return builder.build();
	}
	
}

