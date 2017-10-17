package com.aca.classproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Utility methods for working with the local Heidi database
 * @author Andrew White/Daniel Lazenby
 *
 */
public class MariaDbUtil {
	
	private static String connectionUrl = "jdbc:mariadb://localhost:3306/northwind?user=root&password=kenYa2500";
	private final static String SQL_RETURN_KEY = " SELECT @@identity ";
	
	/**
	 * 
	 * @return an 'active' connection to the northwind Heidi database
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(connectionUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * 
	 * @param conn a live connection to a database
	 * @return the primary key of the last updated record (as an int)
	 */
	 public static int getRecordKey(Connection conn) {
		
		int recordKey = 0;
		ResultSet result = null;
		Statement returnKeyStatement = null;
		
		try {
			returnKeyStatement = conn.createStatement();
			result = returnKeyStatement.executeQuery(SQL_RETURN_KEY);
			result.next();
			recordKey = result.getInt("@@identity");
			
			//validate correct key has been returned + print to console
			//System.out.println("Record key inserted: " + recordKey);
			
	 	} catch (SQLException e) {
			e.printStackTrace();
	 	} finally {
	 		try {
				returnKeyStatement.close();
				result.close();
	 		} catch (SQLException e){
				e.printStackTrace();
			}	
		}
		return recordKey;	
	}
}


