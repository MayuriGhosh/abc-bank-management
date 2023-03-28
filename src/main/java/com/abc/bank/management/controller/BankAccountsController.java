package com.abc.bank.management.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.bank.management.model.Customer;
import com.abc.bank.management.model.Accounts;
import com.abc.bank.management.service.AccountService;
import com.abc.bank.management.service.CustomerService;

import lombok.AllArgsConstructor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
@AllArgsConstructor
@Api(tags = { "Customer and Bank Accounts REST endpoints" })
public class BankAccountsController {
	@Autowired
	private AccountService accountService;

	@Autowired
	private CustomerService customerService;

	private static final Logger logger = LogManager.getLogger(BankAccountsController.class);

	/**
	 * @param customer
	 * This method create new customer and bank account
	 * for the customer. Create Account happens after create customer
	 */
	@PostMapping("/customer")
	@ApiOperation(value = "Add a new customer and bank account", notes = "Create an new account for new customer.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success",response = Customer.class, responseContainer = "Object"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Customer createCustomer(@RequestBody Customer customer) {
		Customer customerCreated = customerService.createCustomer(customer);
		Accounts acct = new Accounts(customerCreated.getAcctID(), 0, "Active");
		acct =  accountService.createAccount(acct);
		return customerCreated;
	}

	@GetMapping("/customer/{acctID}")
	@ApiOperation(value = "Get customer details", notes = "Get Customer details by customer number.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Customer.class, responseContainer = "Object"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Customer getCustomerInfo(@PathVariable int acctID) {
		return customerService.getCustomerInfo(acctID);
	}

	// getAccountInfo
	@GetMapping("/account/{acctID}")
	@ApiOperation(value = "Get aaccount information", notes = "Get aaccount information")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Accounts getAccountInfo(@PathVariable int acctID) {
		return accountService.getAccountInfo(acctID);
	}	

	// checkBalance
	@GetMapping("/account/{acctID}/balance")
	public int getBalance(@PathVariable int acctID) {
		return accountService.getBalance(acctID);
	}

	@DeleteMapping("/customer/{acctID}")
	public void deleteCustomer(@PathVariable int acctID) {
		customerService.deleteCustomer(acctID);
	}

	// deleteAccount
	@DeleteMapping("/account/{acctID}")
	public void deleteAccount(@PathVariable int acctID) {
		accountService.deleteAccount(acctID);
	}

	// depositAmount
	@PutMapping("/account/{acctID}/deposit/{amount}")
	public void depositAmount(@PathVariable int acctID, @PathVariable int amount) {
		int initBal = getBalance(acctID);
		accountService.depositAmount(acctID, amount);

	}

	// withdrawAmount
	@PutMapping("/account/{acctID}/withdraw/{amount}")
	public void withdrawAmount(@PathVariable int acctID, @PathVariable int amount) {
		int initBal = getBalance(acctID);
		accountService.withdrawAmount(acctID, amount);
		logger.info("Withdraw Amount"+initBal);

	}

	// transferAmount
	@PutMapping("/account/{acctID}/transfer/{destAcctID}/{amount}")
	@ApiOperation(value = "Transfer funds between accounts", notes = "Transfer funds between accounts.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void transferAmount(@PathVariable int acctID, @PathVariable int destAcctID, @PathVariable int amount) {
		int initBalSender = getBalance(acctID);
		int initBalReceiver = getBalance(destAcctID);
		accountService.transferAmount(acctID, destAcctID, amount);
		logger.info("Transferred amount"+initBalSender+".The account balance now is" +initBalReceiver); 
	}

}
