package com.paymentchain.customer.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.paymentchain.customer.domain.Transaction;

import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue // Por defecto, la estrategia a usar es "AUTO"
	private Long id;
	
	private String name;
	private String surname;
	private String phone;
	private String address;
	private String code;
	private String iban; // Este es el Iban de la cuenta del cliente. Con esta propiedad podemos saber las transacciones que ha hecho el cliente
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustomerProduct> products;
	
	// Para indicar que esta propiedad no se guarda en la tabla correspondiente de la base de datos
	@Transient
	private List<Transaction> transactions; // Información que se obtiene del microservicio "transaction"
	
}
