package com.abc.bank.management.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.abc.bank.management.dao.CustomerRepository;
import com.abc.bank.management.model.Customer;
 
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	@Mock
	private CustomerRepository customerRepository;
	
	private CustomerService customerService;
	
	@BeforeEach void setUp()
    {
        this.customerService
            = new CustomerService(this.customerRepository);
    }
 
	@Test
	public void testCreateCustomer() {
		Customer customer1 = new Customer(12342, "Test rest", "Kolkata", "West Bengal", "India",97863421,
				"2weds");
		
		customerService.createCustomer(customer1);
		verify(customerRepository).save(customer1);
	}

	
}