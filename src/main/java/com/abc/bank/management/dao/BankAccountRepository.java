//package com.abc.bank.management.dao;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.abc.bank.management.model.Accounts;
//
//
//public interface BankAccountRepository extends CrudRepository<Accounts, Integer>, JpaRepository<Accounts, Integer> {
//	@Query("select balance from CustomerRepositoryImpl where acctID = ?1")
//	public int findBalanceByAcctID(int acctID);
//
//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("update CustomerRepositoryImpl set balance = balance+?2 where acctID=?1")
//	public void saveBalanceByAcctID(int acctID, int balance);
//
//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("update CustomerRepositoryImpl set balance = balance-?2 where acctID=?1")
//	public void withdrawAmountByAcctID(int acctID, int balance);
//
//}
