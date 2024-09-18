package com.paymentchain.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue // Por defecto, la estrategia a usar es AUTO
	private Long id;
	
	@NotBlank(message = "Name must not be black")
	private String name;
	
	@NotBlank(message = "Code must not be black")
	private String code;

}
