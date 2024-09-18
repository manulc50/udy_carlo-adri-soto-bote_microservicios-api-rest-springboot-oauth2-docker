package com.paymentchain.customer.controller;

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

import com.paymentchain.customer.entity.Customer;
import com.paymentchain.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;

/*import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;*/


/**
*
* Standard http communication have five levels of response codes
* 100-level (Informational) — Server acknowledges a request, it mean that request was received and understood, it is transient response , alert client for awaiting response
* 200-level (Success) — Server completed the request as expected
* 300-level (Redirection) — Client needs to perform further actions to complete the request
* 400-level (Client error) — Client sent an invalid request
* 500-level (Server error) — Server failed to fulfill a valid request due to an error with server
* 
* The goal of handler exception is provide to customer with appropriate code and 
* additional comprehensible information to help troubleshoot the problem. 
* The message portion of the body should be present as user interface, event if 
* customer send an Accept-Language header (en or french ie) we should translate the title part 
* to customer language if we support internationalization, detail is intended for developer
* of clients, so the translation is not necessary. If more than one error is need to report , we can 
* response a list of errors.
* 
*/

//@Api(tags = "Customer API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerRestController {
	
	private final CustomerService customerService;
	//private final String role;
	
	/*@ApiOperation(value = "Return all customers bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
    	@ApiResponse(code = 200, message = "There are transactions"),
        @ApiResponse(code = 204, message = "There are not transactions"),
        @ApiResponse(code = 500, message = "Internal error")})*/
	@GetMapping
	public ResponseEntity<List<Customer>> getAll() {
		List<Customer> list = customerService.getAll();
		if(list == null || list.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getById(@PathVariable Long id) {
		return customerService.getById(id)
				.map(ResponseEntity::ok) // Versión simplificada de la expresión "c -> ResponseEntity.ok(c)"
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/full")
	public ResponseEntity<Customer> getFull(@RequestParam String code) {
		Optional<Customer> oCustomer = customerService.getFull(code);
		return oCustomer
				.map(customer -> new ResponseEntity<Customer>(customer, HttpStatus.OK))
				.orElse(ResponseEntity.notFound().build());
	}
	
	/*@GetMapping("hello")
	public String sayHello() {
		return "Hello! your role is: " + role;
	}*/
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Customer create(@RequestBody Customer customer) {
		return customerService.create(customer);
	}
	
	@PutMapping("/{id}")
	public Customer update(@RequestBody Customer customer, @PathVariable Long id) {
		return null;
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long idCustomer) {
		return customerService.getById(idCustomer)
				.map(c -> {
					customerService.delete(c);
					return ResponseEntity.noContent().build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
