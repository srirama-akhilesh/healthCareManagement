package com.example.healthCareManagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthCareManagement.dto.EnrolleeDto;
import com.example.healthCareManagement.entity.Enrollee;
import com.example.healthCareManagement.exception.ResourceNotFoundException;
import com.example.healthCareManagement.service.EnrolleeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Enrollee details Management Controller")
@Validated
public class EnrolleeController {

	private static final Logger logger = LoggerFactory.getLogger(EnrolleeController.class);
	   
	
	@Autowired
	EnrolleeService enrolleeService;
	
	
	@GetMapping("/getEnrolles")
	@ApiOperation(value = "View a list of available Enrollees")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!") })
	public ResponseEntity<List<Enrollee>> getEnrolles() {
		
		logger.info("This will call GetEnrollee method");
		
		List<Enrollee> enroleeList =  enrolleeService.getEnrolles();
		return new ResponseEntity<>(enroleeList,HttpStatus.OK);
	}
	
	
	@PostMapping("/createEnrollee")
	@ApiOperation(value = "To create Enrollee record")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Success|OK"),
			@ApiResponse(code = 400, message = "Bad request"), 
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!")
			 })
	public ResponseEntity<Enrollee> createEnrollee(@Valid @RequestBody EnrolleeDto enrolleeDto) {
		 
		logger.info("Creating new Enrollee Details");
		
		Enrollee enrollee =  enrolleeService.createEnrollee(enrolleeDto);
		return new ResponseEntity<>(enrollee,HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/updateEnrolle/{id}")
	@ApiOperation(value = "This is to update Enrollee Record based on its Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Enrollee> updateEnrollee(@PathVariable(value="id") Long id,@RequestBody EnrolleeDto enrolleeDto) throws ResourceNotFoundException {
		
		logger.info("Updating existing Enrollee Details");
		
		Enrollee updatedEnrollee =  enrolleeService.updateEnrollee(enrolleeDto, id);
		return new ResponseEntity<>(updatedEnrollee,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteEnrollee/{id}")
	@ApiOperation(value = "Delete the Enrollee record")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public void deleteEnrollee(@PathVariable(value="id") Long id) {

		logger.info("Deleting existing Enrollee Details");
		
		 enrolleeService.deleteEnrollee(id);
	}
	
}
