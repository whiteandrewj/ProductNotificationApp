package com.aca.classproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aca.classproject.model.Person;
import com.aca.classproject.model.Topic;

public class MariaDbDao {
		
		private final static String SQL_INSERT_PERSON = " INSERT INTO person "
			+ " (FirstName, LastName, EmailAddress) "
			+ " VALUES (?, ?, ? ); ";
		
		private final static String SQL_INSERT_SUBSCRIPTION = " INSERT INTO subscription "
				+ " (PersonID, Topic, ARN) "
				+ " VALUES (?, ?, ? ); ";
		
		private final static String SQL_SELECT_PERSON = " SELECT PersonID, FirstName, LastName, EmailAddress " 
														+ " FROM person ";
		
		private final static String SQL_SELECT_PERSON_WITH_SUBS = " SELECT p.PersonID, p.FirstName, p.LastName, p.EmailAddress, s.Topic, s.ARN " 
																+ " FROM person p "
																+ " LEFT OUTER JOIN subscription s ON s.PersonID = p.PersonID ";
		
		private final static String SQL_SELECT_SUBSCRIPTIONS = " SELECT PersonID, Topic, ARN "
																+ " FROM subscription ";
																//+ "	WHERE PersonID = ? ";
												
		public int newPerson(Person person) {
			
			Connection conn = MariaDbUtilities.getConnection();
			
			int primaryKey = 0;
			int personRecordsInserted = 0;
			PreparedStatement insertPersonStatement = null;
			
			try {	
				insertPersonStatement = conn.prepareStatement(SQL_INSERT_PERSON);
				insertPersonStatement.setString(1, person.getFirstName());
				insertPersonStatement.setString(2, person.getLastName());
				insertPersonStatement.setString(3, person.getEmailAddress());
				personRecordsInserted = insertPersonStatement.executeUpdate();
			
				//gets primary key of person record inserted and assigns value to primaryKey
				primaryKey = MariaDbUtilities.getRecordKey(conn);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
					insertPersonStatement.close();
				} catch (SQLException e){
					e.printStackTrace();
				}
			}
			System.out.println("Person records inserted: " + personRecordsInserted);
			return primaryKey;
		}
		
		
		public void newSubscription(int personKey, Topic topic, String status) {
						
			Connection conn = MariaDbUtilities.getConnection();
			
			int subscriptionRecordsInserted = 0;
			PreparedStatement insertSubscriptionStatement = null;
			
			try {
				insertSubscriptionStatement = conn.prepareStatement(SQL_INSERT_SUBSCRIPTION);
				insertSubscriptionStatement.setInt(1, personKey);
				insertSubscriptionStatement.setString(2, topic.displayName());
				insertSubscriptionStatement.setString(3, status);
				subscriptionRecordsInserted = insertSubscriptionStatement.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
					insertSubscriptionStatement.close();
				} catch (SQLException e){
					e.printStackTrace();
				}
			}
			System.out.println(topic.displayName() + " subscription records inserted: " + subscriptionRecordsInserted);
		}
		
		public List<Person> getPersonsWithSubscriptions() {
			//objectives:
			//get person from database
			//put into collection of Person
			//get subscriptions for each person
			//put into subscription map inside person object
			Connection conn = MariaDbUtilities.getConnection();
			
			ResultSet result = null;
			Statement statement = null;
			List<Person> list = new ArrayList<Person>();
			
			try {
				statement = conn.createStatement();
				result = statement.executeQuery(SQL_SELECT_PERSON);
				
				while(result.next()) {
					Person person = new Person();
					person.setSqlPrimaryKey(result.getInt("PersonID"));
					person.setFirstName(result.getString("FirstName"));
					person.setLastName(result.getString("LastName"));
					person.setEmailAddress(result.getString("EmailAddress"));
					list.add(person);
				}
				
				result.close();
				result = statement.executeQuery(SQL_SELECT_SUBSCRIPTIONS);
				
				
				//loops through each person pulled from DB
				for (Person person : list) {
					
					//creates an instance of the subscription object
					//will instantiating a new instance of the HashMap ensure all the person 'subscriptions' variables are pointing to separate objects in the heap??
					Map<String, Boolean> subscription = new HashMap<String, Boolean>();
					
					//loops through each Topic enum
					for (Topic topic : Topic.values()) {
						
						//inserts a key/value pair into hashmap with an initial value of false
						subscription.put(topic.displayName(), false);
						
						while (result.next()) {
				
							//checks if there is a match for both PersonID and the current topic in the resultSet
							if (person.getSqlPrimaryKey() == result.getInt("PersonID") && topic.displayName().equals(result.getString("Topic"))) {
								
								//if a match is found replaces value with that of current topic key
								//is there a way to break out of the 'while' loop from this location?
								subscription.replace(topic.displayName(), true);
								
							} 
						} 	
						//resets result object cursor to before first row
						result.beforeFirst();						
					}
					//assigns completed hashmap to person object
	//				person.setSubscriptions(subscription);
				}			
			
			} catch (SQLException e) {
				e.printStackTrace();
		 	} finally {
		 		try {
					statement.close();
					result.close();
					conn.close();
		 		} catch (SQLException e){
					e.printStackTrace();
				}	
			}
			
			return list;
		}
		
}
