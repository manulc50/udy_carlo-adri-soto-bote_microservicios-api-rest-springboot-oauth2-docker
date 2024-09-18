package com.paymentchain.transaction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentchain.transaction.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	Optional<Transaction> findByReference(String reference);
	List<Transaction> findByIbanAccount(String ibanAccount);

}
