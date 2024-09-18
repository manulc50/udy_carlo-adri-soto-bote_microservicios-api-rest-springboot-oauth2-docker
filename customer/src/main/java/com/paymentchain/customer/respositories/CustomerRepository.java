package com.paymentchain.customer.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentchain.customer.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
