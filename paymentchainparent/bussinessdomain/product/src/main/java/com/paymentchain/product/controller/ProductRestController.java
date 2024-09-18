package com.paymentchain.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.paymentchain.product.entity.Product;
import com.paymentchain.product.service.ProductService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductRestController {
	
	private final ProductService productService;
	//private final String role;
	
	/*@GetMapping("hello")
	public String sayHello() {
		return "Hello! your role is: " + role;
	}*/
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		List<Product> list = productService.getAll();
		if(list == null || list.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public Product getById(@PathVariable Long id) {
		return productService.getById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Product create(@RequestBody Product product) {
		return productService.create(product);
	}
	
	@PutMapping("/{id}")
	public Product update(@RequestBody Product product, @PathVariable Long id) {
		return productService.update(id, product);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") Long idProduct) {
		productService.deleteById(idProduct);
	}
}
