package com.paymentchain.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.customer.entity.Customer;
import com.paymentchain.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customers/{idCustomer}/products/{idProduct}")
public class ProductRestController {
	
	private final CustomerService customerService;
	
	@PostMapping
	public ResponseEntity<Customer> addProduct(@PathVariable Long idCustomer, @PathVariable Long idProduct) {
		return customerService.getById(idCustomer)
			.map(c -> new ResponseEntity<Customer>(customerService.addProduct(c, idProduct), HttpStatus.CREATED))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping
	public ResponseEntity<Customer> deleteProduct(@PathVariable Long idCustomer, @PathVariable Long idProduct) {
		return customerService.getById(idCustomer)
			.map(c -> new ResponseEntity<Customer>(customerService.deleteProduct(c, idProduct), HttpStatus.OK))
			.orElse(ResponseEntity.notFound().build());
	}
}
