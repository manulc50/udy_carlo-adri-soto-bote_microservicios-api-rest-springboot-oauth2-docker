package com.paymentchain.billing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.paymentchain.billing.dto.InvoiceRequest;
import com.paymentchain.billing.dto.InvoiceResponse;
import com.paymentchain.billing.entity.Invoice;
import com.paymentchain.billing.mapper.InvoiceRequestMapper;
import com.paymentchain.billing.mapper.InvoiceResponseMapper;
import com.paymentchain.billing.repository.InvoiceRepository;

@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private InvoiceRequestMapper iRequestMapper;
	
	@Autowired
	private InvoiceResponseMapper iResponseMapper;
	
	
	public List<InvoiceResponse> getAll() {
		return iResponseMapper.invoiceListToInvoiceResposeList(invoiceRepository.findAll());
	}
	
	public InvoiceResponse getById(Long id) {
		return iResponseMapper.invoiceToInvoiceResponse(invoiceRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice with id " + id + " not Found")));
	}
	
	public InvoiceResponse create(InvoiceRequest invoiceRequest) {
		Invoice invoice = iRequestMapper.InvoiceRequestToInvoice(invoiceRequest);
		return iResponseMapper.invoiceToInvoiceResponse(invoiceRepository.save(invoice));
	}

	public void deleteById(Long idInvoice) {
		Optional<Invoice> oInvoice = invoiceRepository.findById(idInvoice);
		if(oInvoice.isPresent())
			invoiceRepository.delete(oInvoice.get());
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice with id " + idInvoice + " not Found");
	}
	
}
