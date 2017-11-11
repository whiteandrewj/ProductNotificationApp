package com.aca.classproject.model;

public enum Topic {

	COMPUTER("Computer", "arn:aws:sns:us-east-1:294989386768:Computer"),
	CONSOLE("Console", "arn:aws:sns:us-east-1:294989386768:Console"),
	HEATER("Heater", "arn:aws:sns:us-east-1:294989386768:Heater"),
	LAWNMOWER("Lawnmower", "arn:aws:sns:us-east-1:294989386768:Lawnmower"),
	TOOL("Tool", "arn:aws:sns:us-east-1:294989386768:Tool"),
	TELEVISION("Television", "arn:aws:sns:us-east-1:294989386768:Television");
	
	
	private final String displayName;
	private final String awsSnsArn;
	
	Topic(String displayName, String awsSnsArn) {
		this.displayName = displayName;
		this.awsSnsArn = awsSnsArn;
	}
	
	public String displayName() {
		return displayName;
	}
	
	public String awsSnsArn() {
		return awsSnsArn;
	}
	
	//public static final String COMPUTER_SUB_ARN = "arn:aws:sns:us-east-1:294989386768:NewComputers";
	//add other topic ARNs here
}
