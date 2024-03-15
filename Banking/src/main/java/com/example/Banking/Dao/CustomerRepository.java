package com.example.Banking.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Banking.Model.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	Customer findByAcctID(int acctID);

}
