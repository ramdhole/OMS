package com.cg.mobileStore.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.mobileStore.Entity.Customer;


public interface CustomerRepository  extends JpaRepository<Customer, Integer>{

	public Optional<Customer> findByEmailId(String emailId);
	public Optional<Customer> findByMobileNumber(String mobileNo);
	
}
