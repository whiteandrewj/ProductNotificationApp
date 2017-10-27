package com.aca.classproject.model;

public class Notification {

	private String productName;
	private int price;
	private String description;
	private String topic;
	
	public String getProductName() {
		return productName;
	}
	public int getPrice() {
		return price;
	}
	public String getDescription() {
		return description;
	}
	public String getTopic() {
		return topic;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	
}
