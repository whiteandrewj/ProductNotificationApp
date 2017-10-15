package com.aca.classproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aca.classproject.model.Subscription;

public class SubscriptionDao {
	
		private final static String SQL_INSERT_PERSON = " INSERT INTO person "
			+ " (FirstName, LastName, EmailAddress) "
			+ " VALUES (?, ?, ? ); ";
		
		private final static String SQL_RETURN_KEY = " SELECT @@identity ";
		
		public String insertSubscription(Subscription subscription) {
		// public static void main(String[] args) {
			
			
			int recordsUpdated;
			String message = null;
			PreparedStatement insertPersonStatement = null;			
			
			Connection conn = MariaDbUtil.getConnection();
		
			try {
			
			insertPersonStatement = conn.prepareStatement(SQL_INSERT_PERSON);
			insertPersonStatement.setString(1, subscription.getFirstName());
			insertPersonStatement.setString(2, subscription.getLastName());
			insertPersonStatement.setString(3, subscription.getEmailAddress());
			
			recordsUpdated = insertPersonStatement.executeUpdate();
			System.out.println(recordsUpdated);
			
			message = "Record key inserted: " + getRecordKey(conn);
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					insertPersonStatement.close();
					conn.close();
				} catch (SQLException e){
					e.printStackTrace();
				}
			}
			return message;
		}
		
		private int getRecordKey(Connection conn) throws SQLException {
			
			int recordKey = 0;
			ResultSet result = null;
			Statement returnKeyStatement = null;
			
			returnKeyStatement = conn.createStatement();
			result = returnKeyStatement.executeQuery(SQL_RETURN_KEY);
			result.next();
			recordKey = result.getInt("@@identity");
			
			//validate correct key has been returned + print to console
			System.out.println("Record key inserted: " + recordKey);
			
			returnKeyStatement.close();
			result.close();
			
			return recordKey;
		}
}
