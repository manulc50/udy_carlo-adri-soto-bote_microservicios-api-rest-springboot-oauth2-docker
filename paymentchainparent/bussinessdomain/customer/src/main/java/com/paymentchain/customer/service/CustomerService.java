package com.paymentchain.customer.service;

import java.net.URI;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.paymentchain.customer.domain.Transaction;
import com.paymentchain.customer.entity.Customer;
import com.paymentchain.customer.entity.CustomerProduct;
import com.paymentchain.customer.exception.BusinessRuleException;
import com.paymentchain.customer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CustomerService {
	//private static final String PRODUCT_SERVICE_HOST = "http://businessdomain-product";
	private static final String PRODUCT_SERVICE_HOST = "http://localhost:8083";
	private static final String TRANSACTION_SERVICE_HOST = "http://businessdomain-transaction";
	
	private final CustomerRepository customerRepository;
	private final WebClient webClient;
	
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}
	
	public Optional<Customer> getById(Long id) {
		return customerRepository.findById(id);
	}
	
	public Optional<Customer> getFull(String code) {
		Optional<Customer> oCustomer = customerRepository.findByCode(code);
		oCustomer.ifPresent(customer -> {
			customer.getProducts().forEach(p -> p.setProductName(getProductName(p.getProductId())));
			//customer.setTransactions(getTransactions(customer.getIban()));
		});
		
		return oCustomer;
	}
	
	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public void delete(Customer customer) {
		customerRepository.delete(customer);
	}
	
	public Customer addProduct(Customer customer, Long idProduct) {
		getProductName(idProduct); // Validación de la existencias de los productos. Si la comunicación con el microservicio "product" devuelve el código 404, significa que el producto solicitado no existe
		CustomerProduct cp = new CustomerProduct();
		cp.setProductId(idProduct);
		cp.setCustomer(customer);
		customer.getProducts().add(cp);
		return customerRepository.save(customer);
	}
	
	public Customer deleteProduct(Customer customer, Long idProduct) {
		customer.getProducts().removeIf(p -> p.getProductId() == idProduct);
		return customerRepository.save(customer);
	}
	
	private String getProductName(Long id) {		
		URI uri = UriComponentsBuilder.fromUriString(PRODUCT_SERVICE_HOST + "/products/{productId}").build(id);

		JsonNode jNode = webClient.method(HttpMethod.GET)
				.uri(uri)
				.retrieve()
				.bodyToMono(JsonNode.class)
				// Otra opción más directa es manejar las excepciones de tipo "WebClientResponseException" directamente en la clase manejadora de excepciones "ApiExceptionHandler"
				.onErrorMap(WebClientResponseException.class, this::handleException) // Versión simplificada de la expresión "ex -> handleException(ex)"
				.block();

		return jNode.get("name").asText();
	}
	
	private List<Transaction> getTransactions(String ibanAccount) {
		List<Transaction> listTransactions = webClient.get()
			  .uri(uriBuilder -> uriBuilder
					  .host(TRANSACTION_SERVICE_HOST)
					  .path("/transactions")
					  .queryParam("ibanAccount", ibanAccount)
					  .build())
			  .retrieve()
			  .bodyToFlux(Transaction.class)
			  .collectList()
			  .block();
		
		return listTransactions;
	}
	
	private Throwable handleException(Throwable ex) {
        if (!(ex instanceof WebClientResponseException)) 
            return ex;
        
        WebClientResponseException wcre = (WebClientResponseException)ex;
        switch (wcre.getStatusCode()) {
        	case NOT_FOUND:
        		return new BusinessRuleException("1025", "Error de validación: " + wcre.getMessage(), HttpStatus.PRECONDITION_FAILED);
        	case SERVICE_UNAVAILABLE :
        		return new UnknownHostException(wcre.getMessage());
        	default:
        		return ex;
        }
    }
	
}
