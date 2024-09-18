package com.paymentchain.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.paymentchain.product.entity.Product;
import com.paymentchain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
	
	// Nota: La excepción de tipo "ResponseStatusException" genera de forma automática una respuesta http
	
	private final ProductRepository productRepository;
    private final Validator validator;
	
	public List<Product> getAll() {
		return productRepository.findAll();
	}
	
	public Product getById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + id + " not found"));
	}
	
	public Product create(Product product) {
		Set<ConstraintViolation<Product>> violations = validator.validate(product);

        if (!violations.isEmpty()) {
            //StringBuilder sb = new StringBuilder();
        	List<String> errors = new ArrayList<String>();
            for (ConstraintViolation<Product> constraintViolation : violations) {
                //sb.append(constraintViolation.getMessage());
            	errors.add(constraintViolation.getMessage());
            }
            //throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
        }
			
		return productRepository.save(product);
	}
	
	public Product update(Long id, Product newProduct) {
		Product product = getById(id);
		BeanUtils.copyProperties(newProduct, product, "id");
		return product;
	}
	
	public void deleteById(Long id) {
		Product product = getById(id);
		productRepository.delete(product);
	}

}
