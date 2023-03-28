package com.abc.bank.management.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.abc.bank.management.dao.CustomerRepository;
import com.abc.bank.management.model.Customer;
import com.abc.bank.management.model.Accounts;
import com.abc.bank.management.service.AccountService;
import com.abc.bank.management.service.CustomerService;


@ExtendWith(MockitoExtension.class)
public class BankAccountsControllerTest {
	@Mock	
	private CustomerService customerService;
	@Mock	
	private AccountService accountService;
		
		
	BankAccountsController bankAccountsController;
	
	@BeforeEach void setUp()
    {
        this.bankAccountsController
            = new BankAccountsController(this.accountService,this.customerService);
    }

	/**
	 * @param customer
	 * This method create new customer and bank account
	 * for the customer. Create Account happens after create customer
	 * @throws Exception 
	 */
	@Test
	public void testCreateCustomer() throws Exception {
		Customer customer1 = new Customer(12342, "Test rest", "Kolkata", "West Bengal", "India",97863421,
				"2weds");
		Accounts acct = new Accounts(customer1.getAcctID(), 0, "Active");
		when(customerService.createCustomer(any(Customer.class))).thenReturn(customer1);
		when(accountService.createAccount(any(Accounts.class))).thenReturn(acct);
		bankAccountsController.createCustomer(customer1);
		verify(customerService).createCustomer(customer1);
		verify(accountService).createAccount(acct);
		
	}


}
