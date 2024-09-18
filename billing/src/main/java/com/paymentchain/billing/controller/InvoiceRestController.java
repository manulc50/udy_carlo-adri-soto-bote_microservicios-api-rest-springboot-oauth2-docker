package com.paymentchain.billing.controller;

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

import com.paymentchain.billing.dto.InvoiceRequest;
import com.paymentchain.billing.dto.InvoiceResponse;
import com.paymentchain.billing.entity.Invoice;
import com.paymentchain.billing.service.InvoiceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;


@Api(tags = "Billing API")
@RestController
@RequestMapping("/invoice")
public class InvoiceRestController {
	
	private final InvoiceService invoiceService;
	
	public InvoiceRestController(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}
	
	@ApiOperation(value = "Return all invoices bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "There are invoices"),
        @ApiResponse(code = 204, message = "There are not invoices"),
        @ApiResponse(code = 500, message = "Internal error")})
	@GetMapping
	public ResponseEntity<List<InvoiceResponse>> getAll() {
		List<InvoiceResponse> list = invoiceService.getAll();
		if(list == null || list.isEmpty())
			return ResponseEntity.noContent().build();
		return new ResponseEntity<List<InvoiceResponse>>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public InvoiceResponse getById(@PathVariable Long id) {
		return invoiceService.getById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public InvoiceResponse create(@RequestBody InvoiceRequest invoiceRequest) {
		return invoiceService.create(invoiceRequest);
	}
	
	@PutMapping("/{id}")
	public Invoice updateInvoice(@RequestBody Invoice invoice, @PathVariable Long id) {
		return null;
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") Long idInvoice) {
		invoiceService.deleteById(idInvoice);
	}

}
