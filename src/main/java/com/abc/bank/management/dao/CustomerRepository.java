package com.abc.bank.management.dao;

import org.springframework.stereotype.Repository;

import com.abc.bank.management.model.Customer;

@Repository
public interface CustomerRepository {

	
	public Customer createCustomer(Customer customer);
	
}
