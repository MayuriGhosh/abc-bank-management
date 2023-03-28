package com.abc.bank.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.bank.management.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
