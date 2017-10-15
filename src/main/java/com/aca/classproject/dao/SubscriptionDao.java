package com.aca.classproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aca.classproject.model.Subscription;

public class SubscriptionDao {
	
		private final static String SQL_INSERT_SUBSCRIPTION = " INSERT INTO person "
			+ " (FirstName, LastName, EmailAddress) "
			+ " VALUES (?, ?, ? ) ";
		
		public int insertSubscription(Subscription subscription) {
		// public static void main(String[] args) {
			
			int rowsInserted = 0;			
			PreparedStatement statement = null;
			
			Connection conn = MariaDbUtil.getConnection();
			
			try {
			
			statement = conn.prepareStatement(SQL_INSERT_SUBSCRIPTION);
			statement.setString(1, subscription.getFirstName());
			statement.setString(2, subscription.getLastName());
			statement.setString(3, subscription.getEmailAddress());
			
			rowsInserted = statement.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					statement.close();
					conn.close();
				} catch (SQLException e){
					e.printStackTrace();
				}
			}
			return rowsInserted;
		}

}
