package com.aca.classproject.model;

import java.util.Map;

public class Person {
	
	private int sqlPrimaryKey;
	private String firstName;
	private String lastName;
	private String emailAddress;
	
	private Map<String, Boolean> subscriptions;
	
	/*
	private boolean isComputerSub;
	private boolean isConsoleSub;
	private boolean isHeaterSub;
	private boolean isLawnSub;
	private boolean isToolSub;
	private boolean isTelevisionSub;
	*/
	
	
	public int getSqlPrimaryKey() {
		return sqlPrimaryKey;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public Map<String, Boolean> getSubscriptions() {
		return subscriptions;
	}
	//not as readable; having the subscription object within person object gives impression that I want to be able to create a collection of subscriptions. 
	//It would be more clear to push all values from client in a single object, then map values to hashmap on server side
	/*
	public boolean getIsComputerSub() {
		return isComputerSub;
	}
	public boolean getIsConsoleSub() {
		return isConsoleSub;
	}
	public boolean getIsHeaterSub() {
		return isHeaterSub;
	}
	public boolean getIsLawnSub() {
		return isLawnSub;
	}
	public boolean getIsToolSub() {
		return isToolSub;
	}
	public boolean getIsTelevisionSub() {
		return isTelevisionSub;
	}
	*/
	
	
	public void setSqlPrimaryKey(int sqlPrimaryKey) {
		this.sqlPrimaryKey = sqlPrimaryKey;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public void setSubscriptions(Map<String, Boolean> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	/*
	public void setIsComputerSub(boolean isComputerSub) {
		this.isComputerSub = isComputerSub;
	}
	public void setIsConsoleSub(boolean isConsoleSub) {
		this.isConsoleSub = isConsoleSub;
	}
	public void setIsHeaterSub(boolean isHeaterSub) {
		this.isHeaterSub = isHeaterSub;
	}
	public void setIsLawnSub(boolean isLawnSub) {
		this.isLawnSub = isLawnSub;
	}
	public void setIsToolSub(boolean isToolSub) {
		this.isToolSub = isToolSub;
	}
	public void setIsTelevisionSub(boolean isTelevisionSub) {
		this.isTelevisionSub = isTelevisionSub;
	}
	*/
}
