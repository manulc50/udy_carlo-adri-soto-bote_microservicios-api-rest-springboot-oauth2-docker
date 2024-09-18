package com.paymentchain.transaction.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.paymentchain.transaction.entity.Transaction;
import com.paymentchain.transaction.repository.TransactionRepository;

public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	
	public List<Transaction> getAll() {
		return transactionRepository.findAll();
	}
	
	public Optional<Transaction> getById(Long id) {
		return transactionRepository.findById(id);
	}
	
	public List<Transaction> getByIban(String iban) {
		return transactionRepository.findByIbanAccount(iban);
	}
	
	public Optional<Transaction> getByReference(String reference) {
		return transactionRepository.findByReference(reference);
	}
	
	public Transaction create(Transaction transaction) {			
		return transactionRepository.save(transaction);
	}
	
	public void delete(Transaction transaction) {
		transactionRepository.delete(transaction);
	}

}
