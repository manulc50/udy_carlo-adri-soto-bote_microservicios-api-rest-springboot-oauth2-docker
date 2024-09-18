package com.paymentchain.billing.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.paymentchain.billing.dto.InvoiceRequest;
import com.paymentchain.billing.entity.Invoice;

@Mapper(componentModel = "spring")
public interface InvoiceRequestMapper {
	
	@Mappings({
        @Mapping(source = "customer", target = "customerId") // La propiedad "customer" de la clase "InvoiceRequest" se mapea con la propiedad "customerId" de la clase "Invoice"
    })
	Invoice InvoiceRequestToInvoice(InvoiceRequest source);
	
	List<Invoice> invoiceRequestListToInvoiceList(List<InvoiceRequest> source);

}
