package com.example.healthCareManagement.service;

import java.net.URI;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.healthCareManagement.dao.EnrolleeDao;
import com.example.healthCareManagement.dto.EnrolleeDto;
import com.example.healthCareManagement.entity.Enrollee;
import com.example.healthCareManagement.exception.*;

@Service
public class EnrolleeService {
	

	private static final Logger logger = LoggerFactory.getLogger(EnrolleeService.class);
	
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	EnrolleeDao enrolleeDao;

	public List<Enrollee> getEnrolles() {

		logger.info("We are getting the list of all available enrollee records");
		List<Enrollee> enrolleeList = enrolleeDao.findAll();

		return enrolleeList;
	}

	public Enrollee createEnrollee(EnrolleeDto enrolleeDto) {
		
		Enrollee enrollee = prepareEnrollee(enrolleeDto);
		
		logger.info("We are saving the Enrollee details");
		Enrollee savedEnrollee = enrolleeDao.save(enrollee);

		return savedEnrollee;
	}

	

	public Enrollee updateEnrollee(EnrolleeDto enrolleeDto, Long id) throws ResourceNotFoundException {

		Optional<Enrollee> enrollee = enrolleeDao.findById(id);

		if (!enrollee.isPresent()) {
			throw new ResourceNotFoundException("Enrollee with id " + id + " not found",HttpStatus.NOT_FOUND);
		}
		 logger.info("Recevied response from DB for enrolleeID: " + id + "and is not null");
		 
		Enrollee toUpdateEnrolee = enrollee.get();
		
		if(enrolleeDto.getName()!=null && !enrolleeDto.getName().isEmpty())
		toUpdateEnrolee.setName(enrolleeDto.getName());
		
		if(!enrolleeDto.isActivationStatus())
			toUpdateEnrolee.setActivationStatus(enrolleeDto.isActivationStatus());
		
		if(enrolleeDto.getBirthDate()!=null && !enrolleeDto.getBirthDate().isEmpty())
			toUpdateEnrolee.setDateOfBirth(parseDate(enrolleeDto.getBirthDate()));
		
		if(enrolleeDto.getPhonenumber()!=null && !enrolleeDto.getPhonenumber().isEmpty())
			toUpdateEnrolee.setPhonenumber(enrolleeDto.getPhonenumber());
		
		logger.info("We are updating the value from request body for Enrollee retrieved from DB");
		
		 return enrolleeDao.save(toUpdateEnrolee);
		

	}

	public void deleteEnrollee(Long id) {
		
		logger.info("Deleting the Enrollee record based on its ID:"+id);
		
		 enrolleeDao.deleteById(id);
		
	}
	
	private Enrollee prepareEnrollee(EnrolleeDto enrolleeDto) {
		Enrollee enrollee = new Enrollee();
		enrollee.setName(enrolleeDto.getName());
		enrollee.setActivationStatus(enrolleeDto.isActivationStatus());
		enrollee.setDateOfBirth(parseDate(enrolleeDto.getBirthDate()));
		enrollee.setPhonenumber(enrolleeDto.getPhonenumber());
		return enrollee;
	}
	
	 
	 
	private java.sql.Date parseDate(String date) {
	    try {
	    	
	    	logger.info("We are parsing the Date from String to DATE format");
	        return new Date(DATE_FORMAT.parse(date).getTime());
	    } catch (Exception e) {
	        throw new IllegalArgumentException(e);
	    }
	}
}
