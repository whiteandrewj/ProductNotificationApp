package com.aca.classproject.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.DeserializationFeature;


public class FacebookDao {
	
	
	
public static String executePost() {
	
	String targetURL = Credentials.URL;
	
	
	  HttpsURLConnection connection = null;

	  try {
	    //Create connection
	    URL url = new URL(targetURL);
	    connection = (HttpsURLConnection) url.openConnection();
	    connection.setRequestMethod("GET");
	  //  connection.setRequestProperty("Content-Type", 
	  //      "application/x-www-form-urlencoded");

	 //   connection.setRequestProperty("Content-Length", 
	 //       Integer.toString(urlParameters.getBytes().length));
	//    connection.setRequestProperty("Content-Language", "en-US");
	    connection.setRequestProperty("Authorization", Credentials.USER_ACCESS_TOKEN);

	    connection.setUseCaches(false);
	    connection.setDoOutput(true);

	    //Send request
	    DataOutputStream wr = new DataOutputStream (
	        connection.getOutputStream());
	//    wr.writeBytes(urlParameters);
	    wr.close();

	    //Get Response  
	    InputStream is = connection.getInputStream();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
	    String line;
	    while ((line = rd.readLine()) != null) {
	      response.append(line);
	      response.append('\r');
	      System.out.println(response.toString());
	    }
	    rd.close();
	    return response.toString();
	  } catch (Exception e) {
	    e.printStackTrace();
	    
	    return null;
	  } finally {
	    if (connection != null) {
	      connection.disconnect();
	    }
	  }
	}
}
