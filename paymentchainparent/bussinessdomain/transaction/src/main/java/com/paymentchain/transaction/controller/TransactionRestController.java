package com.paymentchain.transaction.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.transaction.entity.Transaction;
import com.paymentchain.transaction.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionRestController {
	
	private final TransactionService transactionService;
	private final String role;
	
	@GetMapping("hello")
	public String sayHello() {
		return "Hello! your role is: " + role;
	}
	
	@GetMapping
	public ResponseEntity<List<Transaction>> getAll() {
		List<Transaction> list = transactionService.getAll();
		if(list == null || list.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{idTransaction}")
	public ResponseEntity<Transaction> getById(@PathVariable(name = "idTransaction") Long id) {
		Optional<Transaction> oTransaction = transactionService.getById(id);
		if(oTransaction.isPresent())
			return ResponseEntity.ok(oTransaction.get());
		else
			return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/transactions")
	public ResponseEntity<List<Transaction>> getByIban(@RequestParam(value = "ibanAccount") String iban) {
		List<Transaction> list =  transactionService.getByIban(iban);
		if(list == null || list.isEmpty())
			return ResponseEntity.noContent().build();
		return new ResponseEntity<List<Transaction>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/reference/{reference}")
	public ResponseEntity<Transaction> getByReference(@PathVariable String reference) {
		return transactionService.getByReference(reference).map(t -> ResponseEntity.ok(t))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Transaction create(@RequestBody Transaction transaction) {
		return transactionService.create(transaction);
	}
	
	@PutMapping("/{id}")
	public Transaction update(@RequestBody Transaction transaction, @PathVariable Long id) {
		return null;
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{idTransaction}")
	public ResponseEntity<?> delete(@PathVariable(name = "idTransaction") Long id) {
		return transactionService.getById(id).map(t -> {
			transactionService.delete(t);
			return ResponseEntity.noContent().build();
		})
		.orElse(ResponseEntity.notFound().build());
	}
	
}
