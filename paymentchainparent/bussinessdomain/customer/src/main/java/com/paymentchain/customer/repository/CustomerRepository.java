package com.paymentchain.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentchain.customer.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long>{
	public Optional<Customer> findByCode(String code);
}
