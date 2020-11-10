package com.example.healthCareManagement.exception;

import java.util.Date;
import java.util.stream.StreamSupport;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class ExceptionProcessor {

	
	@ExceptionHandler(value = {ResourceNotFoundException.class})
	public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex){
		ErrorMessage errorMessage= new ErrorMessage();
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setStatus(ex.getStatusCode().name());
	return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<ErrorMessage> handleException(MethodArgumentNotValidException e, WebRequest request){

		ErrorMessage errorMessage= new ErrorMessage();

		errorMessage.setMessage(e.getBindingResult().getFieldErrors().stream().map( err -> err.getField()+": "+ err.getDefaultMessage())
		.collect(java.util.stream.Collectors.joining(",")));

		errorMessage.setStatus("Invalid request parameter");

		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(value = {ConstraintViolationException.class})
	public ResponseEntity<ErrorMessage> handleException(ConstraintViolationException e , WebRequest request){

		ErrorMessage errorMessage= new ErrorMessage();

		errorMessage.setMessage(e.getConstraintViolations().parallelStream().map(violation -> String.format("%s value '%s' %s", StreamSupport.stream(violation.getPropertyPath().spliterator(),false)
				.reduce((first,second) -> second).orElse(null),
				violation.getInvalidValue(), violation.getMessage()))
				.collect(java.util.stream.Collectors.joining(",")));

		errorMessage.setStatus("Invalid request parameter");


		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
}
