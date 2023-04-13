package com.abc.bank.management.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.abc.bank.management.model.Customer;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;


public class CustomerRepositoryImpl implements CustomerRepository{

	String url;
	String password;
	String userName;
	
	public CustomerRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		url = "jdbc:mysql://database-1.cmusfo0yvf3m.eu-north-1.rds.amazonaws.com:3306/bankinfo";
		password = getDatabasePassword();
		userName = "admin";
		try {
	        Connection conn = DriverManager.getConnection(url, userName, password);
	        Statement stmt = conn.createStatement();
	        String sql = "INSERT INTO Customer (custName) VALUES ('" + customer.getCustName() + "')";
	        stmt.executeUpdate(sql);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return null;
	}
	
	public String getDatabasePassword() {
	    String secretName = "/secrets/bankinfo/db";
	    String region = "eu-north-1";
	    AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
	        .withRegion(region)
	        .build();
	    GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
	        .withSecretId(secretName);
	    GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);
	    
	    if (getSecretValueResult.getSecretString() != null) {
	    	System.out.println("Secret Key inside if ="+getSecretValueResult.getSecretString());
	        return getSecretValueResult.getSecretString();
	        
	    } else {
	    	System.out.println("Secret Key inside else ="+new String(getSecretValueResult.getSecretBinary().array()));
	        return new String(getSecretValueResult.getSecretBinary().array());
	    }
	   
	}

}
