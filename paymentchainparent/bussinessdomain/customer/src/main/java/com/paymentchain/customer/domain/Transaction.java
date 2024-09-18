package com.paymentchain.customer.domain;


import java.util.Date;

import lombok.Data;


@Data
public class Transaction {
	private Long id;
	private String reference;
	private String ibanAccount;
	private Date date;
	private Double amount;
	private Double fee;
	private String description;
	private Status status;
	private Channel channel;

}
