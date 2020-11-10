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
import org.springframework.web.bind.annotation.RestController;

import com.example.healthCareManagement.dto.DependentDto;
import com.example.healthCareManagement.entity.Dependent;
import com.example.healthCareManagement.exception.ResourceNotFoundException;
import com.example.healthCareManagement.service.DependentService;
import com.mongoRest.controller.StoreDetailsController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Dependents Management Controller")
@Validated
public class DependentController {

	private static final Logger logger = LoggerFactory.getLogger(DependentController.class);
	
	@Autowired
	DependentService dependentService;
	
	
	@GetMapping("/getDependents")
	@ApiOperation(value = "To view list of all Dependents")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<List<Dependent>> getDependents() {

		logger.info("Getting existing Dependent Details as List");
		
		List<Dependent> dependentList =  dependentService.getDependent();
		return new ResponseEntity<>(dependentList,HttpStatus.OK);
	}
	
	@PostMapping("/{enrolleeId}/createDependent")
	@ApiOperation(value = "To create Dependent record")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Dependent>  createDependent(@PathVariable(value="enrolleeId") Long id, @Valid  @RequestBody DependentDto dependentDto){
		logger.info("Creating new Dependent record");
		
		Dependent dependent =  dependentService.createDependent(id, dependentDto);
		return new ResponseEntity<>(dependent,HttpStatus.CREATED);
	}

	@PutMapping("/updateDependent/{id}")
	@ApiOperation(value = "This is to update Dependent Record based on its Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Dependent> updateDependentbyId(@PathVariable(value="id")Long id, @RequestBody DependentDto dependentDto){
		
		logger.info("Updating existing Dependent Detail based on ID");
		Dependent dependent =   dependentService.updateDependentbyID(id, dependentDto);
		return new ResponseEntity<>(dependent,HttpStatus.OK);
	}
	
	@DeleteMapping("deleteDependent/{id}")
	@ApiOperation(value = "Delete the Dependent record")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public void deleteDependent(@PathVariable(value="id")Long id) {
		logger.info("Deleting existing Dependent Detail based on ID from Path variable ");
		dependentService.deleteDependent(id);
	}
}
