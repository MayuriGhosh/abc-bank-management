package com.abc.bank.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.bank.management.dao.CustomerRepository;
import com.abc.bank.management.dao.CustomerRepositoryImpl;
import com.abc.bank.management.model.Customer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomerService {
	@Autowired
	private CustomerRepositoryImpl customerRepository;

	
	public Customer createCustomer(Customer customer) {
		return customerRepository.createCustomer(customer);
	}

//	public Customer getCustomerInfo(int acctID) {
//		return customerRepository.findById(acctID).orElse(null);
//	}
//
//	public void deleteCustomer(int acctID) {
//		customerRepository.deleteById(acctID);
//	}
	
	public Customer createCustomerNew(Customer customer) {
		
		return customer;
		
	}
	
	
}
