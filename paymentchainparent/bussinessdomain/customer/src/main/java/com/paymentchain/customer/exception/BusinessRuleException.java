package com.paymentchain.customer.exception;

import org.springframework.http.HttpStatus;

public class BusinessRuleException extends RuntimeException {
	
	private static final long serialVersionUID = 2845429461643084826L;
	
	private Long id;
	private String code;
	private HttpStatus httpStatus;
	
	public BusinessRuleException(Long id, String code, String message, HttpStatus httpStatus) {
		super(message);
		this.id = id;
		this.code = code;
		this.httpStatus = httpStatus;
	}

	public BusinessRuleException(String code, String message, HttpStatus httpStatus) {
		super(message);
		this.code = code;
		this.httpStatus = httpStatus;
	}
	
	public BusinessRuleException(String message, Throwable cause) {
		super(message, cause);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
