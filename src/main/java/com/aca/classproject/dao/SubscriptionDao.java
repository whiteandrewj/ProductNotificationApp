package com.aca.classproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.aca.classproject.model.Subscription;

public class SubscriptionDao {
	
		private final static String SQL_INSERT_PERSON = " INSERT INTO person "
			+ " (FirstName, LastName, EmailAddress) "
			+ " VALUES (?, ?, ? ); ";
		
		private final static String SQL_INSERT_SUBSCRIPTION = " INSERT INTO subscription "
			+ " (PersonID, IsComputerSub, IsConsoleSub, IsHeaterSub, IsLawnSub, IsToolSub, IsTelevisionSub) "
			+ " VALUES (?, ?, ?, ?, ?, ?, ? ); ";
		
		
		public String insertSubscription(Subscription subscription) {
						
			int personRecordsInserted;
			int subscriptionRecordsInserted;
			String message = null;
			PreparedStatement insertPersonStatement = null;
			PreparedStatement insertSubscriptionStatement = null;
			
			Connection conn = MariaDbUtil.getConnection();
		
			try {
			
			insertPersonStatement = conn.prepareStatement(SQL_INSERT_PERSON);
			insertPersonStatement.setString(1, subscription.getFirstName());
			insertPersonStatement.setString(2, subscription.getLastName());
			insertPersonStatement.setString(3, subscription.getEmailAddress());
			personRecordsInserted = insertPersonStatement.executeUpdate();
			//System.out.println("Person records: " + personRecordsInserted);
			
			
			insertSubscriptionStatement = conn.prepareStatement(SQL_INSERT_SUBSCRIPTION);
			insertSubscriptionStatement.setInt(1, MariaDbUtil.getRecordKey(conn));
			insertSubscriptionStatement.setBoolean(2, subscription.getIsComputerSub());
			insertSubscriptionStatement.setBoolean(3, subscription.getIsConsoleSub());
			insertSubscriptionStatement.setBoolean(4, subscription.getIsHeaterSub());
			insertSubscriptionStatement.setBoolean(5, subscription.getIsLawnSub());
			insertSubscriptionStatement.setBoolean(6, subscription.getIsTelevisionSub());
			insertSubscriptionStatement.setBoolean(7, subscription.getIsToolSub());
			subscriptionRecordsInserted = insertSubscriptionStatement.executeUpdate();
			//System.out.println("Subscription records: " + subscriptionRecordsInserted);
			
			message = "Person record:" + personRecordsInserted + ", Subscription record: " + subscriptionRecordsInserted;
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					insertPersonStatement.close();
					insertSubscriptionStatement.close();
					conn.close();
				} catch (SQLException e){
					e.printStackTrace();
				}
			}
			return message;
		}
		
		
}
