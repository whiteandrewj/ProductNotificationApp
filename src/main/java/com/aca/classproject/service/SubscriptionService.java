package com.aca.classproject.service;

import com.aca.classproject.dao.SubscriptionDao;
import com.aca.classproject.model.Subscription;

public class SubscriptionService {
	
	public String insertSubscription(Subscription subscription) {
	
		SubscriptionDao subscriptionDao = new SubscriptionDao();
		 
		return subscriptionDao.insertSubscription(subscription);
	}

}
