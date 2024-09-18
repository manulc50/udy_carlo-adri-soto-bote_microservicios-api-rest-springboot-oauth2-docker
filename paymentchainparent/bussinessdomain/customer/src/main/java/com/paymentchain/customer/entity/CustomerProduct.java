package com.paymentchain.customer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "customerProducts")
public class CustomerProduct {
	
	@Id
	@GeneratedValue // Por defecto, la estrategia a usar es "AUTO"
	private Long id;
	
	private Long productId;
	
	@Transient // Para indicar que esta propiedad no se guarda en la tabla correspondiente de la base de datos
	private String productName; // Información que se obtiene del microservicio "product"
	
	@JsonIgnore // It is necesary for avoid infinite recursion
	@ManyToOne
	@JoinColumn(name = "fk_customer") // En este caso, el nombre de esta columna por defecto es "customerId"
	private Customer customer;

}
