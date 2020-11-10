package com.example.healthCareManagement.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException{

	private final HttpStatus statusCode;

	public HttpStatus getStatusCode() {
		return statusCode;
	}



	public ResourceNotFoundException(String message, HttpStatus statusCode) {

		super(message);
		this.statusCode = statusCode;
	}

	public ResourceNotFoundException(String message, HttpStatus statusCode, Throwable ex) {

		super(message,ex);
		this.statusCode = statusCode;
	}

}