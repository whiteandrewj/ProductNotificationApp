package com.aca.classproject.model;

public class Subscription {
	
	Topic topic;
	boolean isSubscribed;
	String status;
	
	public Topic getTopic() {
		return topic;
	}
	public boolean isSubscribed() {
		return isSubscribed;
	}
	public String getStatus() {
		return status;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public void setSubscribed(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
