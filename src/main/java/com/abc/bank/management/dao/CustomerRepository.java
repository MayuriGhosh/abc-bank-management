package com.abc.bank.management.dao;

import org.springframework.data.repository.CrudRepository;
import com.abc.bank.management.model.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
