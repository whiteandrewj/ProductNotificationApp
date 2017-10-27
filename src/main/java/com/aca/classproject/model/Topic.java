package com.aca.classproject.model;

public enum Topic {

	COMPUTER("Computer", "arn:aws:sns:us-east-1:294989386768:NewComputers"),
	GAME_CONSOLE(null, null),
	HEATER(null, null),
	LAWN_CARE(null, null),
	TOOL(null, null),
	TELEVISION(null, null);
	
	
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
