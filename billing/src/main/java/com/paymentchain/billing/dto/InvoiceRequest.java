package com.paymentchain.billing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(description = "This model represent a Invoice data that user should send on a request on post method" )
public class InvoiceRequest {
	
	@ApiModelProperty(name = "customer", required = true, example = "1", value = "Unique Id of customer taht represent the owner of invoice")
	private Long customer;
	
	@ApiModelProperty(name = "number", required = true, example = "2548975", value = "Bussines number that identified a invoice", allowEmptyValue = false)
	private String number;
	
	@ApiModelProperty(name = "detail", required = false, example = "Professional services")
	private String detail;
	
	@ApiModelProperty(name = "amount", required = true, example = "3659.23")
	private Double amount;

}
