package com.paymentchain.billing.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.paymentchain.billing.dto.InvoiceResponse;
import com.paymentchain.billing.entity.Invoice;

@Mapper(componentModel = "spring")
public interface InvoiceResponseMapper {
	
	@Mappings({
        @Mapping(source = "customerId", target = "customer"), // La propiedad "customerId" de la clase "Invoice" se mapea con la propiedad "customer" de la clase "InvoiceReponse"
        @Mapping(source = "id", target = "invoiceId") // La propiedad "id" de la clase "Invoice" se mapea con la propiedad "invoiceId" de la clase "InvoiceReponse"
	})
	InvoiceResponse invoiceToInvoiceResponse(Invoice source);
	
	List<InvoiceResponse> invoiceListToInvoiceResposeList(List<Invoice> source);

}
