package com.paymentchain.customer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue // Por defecto, la estrategía a usar es AUTO
	private Long id;
	
	private String name;
	private String phone;
}
