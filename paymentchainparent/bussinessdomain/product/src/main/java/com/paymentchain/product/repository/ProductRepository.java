package com.paymentchain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentchain.product.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

}
