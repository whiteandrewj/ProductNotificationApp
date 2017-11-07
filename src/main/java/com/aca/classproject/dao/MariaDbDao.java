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
import com.aca.classproject.model.Subscription;
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
		
		private final static String SQL_UPDATE_SUBSCRIPTION = " UPDATE subscription " 
															+ " SET ARN=? "
															+ " WHERE PersonID=? AND Topic=? ";
		
		private final static String SQL_DELETE_SUBSCRIPTION = " DELETE FROM subscription " 
															+ " WHERE PersonID=? AND Topic=? ";
												
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
		
		public List<Person> getPersons() {
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
				
		public List<Person> getSubscriptions(List<Person> list) {

			Connection conn = MariaDbUtilities.getConnection();
			
			ResultSet result = null;
			Statement statement = null;
			
			try {
				statement = conn.createStatement();
				result = statement.executeQuery(SQL_SELECT_SUBSCRIPTIONS);
					
				//loops through each person pulled from DB
				for (Person person : list) {
					
					//creates an instance of the subscription object
					//will instantiating a new instance of the List ensure all the person 'subscriptions' variables are pointing to separate objects in the heap??
					List<Subscription> subscriptions = new ArrayList<Subscription>();
					
					//loops through each Topic enum
					for (Topic topic : Topic.values()) {
						
						//create a new instance of Subscription and set initial values to current
						Subscription subscription = new Subscription();
						subscription.setTopic(topic);
						subscription.setIsSubscribed(false);
						subscription.setStatus(null);
						
						while (result.next()) {
				
							//checks if there is a match for both PersonID and the current topic in the resultSet
							if (person.getSqlPrimaryKey() == result.getInt("PersonID") && topic.displayName().equals(result.getString("Topic"))) {
								subscription.setIsSubscribed(true);
								subscription.setStatus(result.getString("ARN"));		
							}
						} 
						subscriptions.add(subscription);
						result.beforeFirst();						
					}
					//assigns completed hashmap to person object
					person.setSubscriptions(subscriptions);
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
		
		public void updateSubscription(String arn, int personPrimaryKey, Topic topic) {
			Connection conn = MariaDbUtilities.getConnection();
			PreparedStatement statement = null;
			int recordsUpdated = 0;
			
			try {
				statement = conn.prepareStatement(SQL_UPDATE_SUBSCRIPTION);
				
				statement.setString(1, arn);
				statement.setInt(2, personPrimaryKey);
				statement.setString(3, topic.displayName());
								
				recordsUpdated = statement.executeUpdate();
				System.out.println(recordsUpdated + " " + topic + " rows updated");
				
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
			
		}
		
		public void deleteSubscription(int personPrimaryKey, Topic topic) {
			Connection conn = MariaDbUtilities.getConnection();
			PreparedStatement statement = null;
			int recordsUpdated = 0;
			
			try {
				statement = conn.prepareStatement(SQL_DELETE_SUBSCRIPTION);
				
				statement.setInt(1, personPrimaryKey);
				statement.setString(2, topic.displayName());
								
				recordsUpdated = statement.executeUpdate();
				System.out.println(recordsUpdated + " " + topic + " rows updated");
				
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
			
		}
		
}
