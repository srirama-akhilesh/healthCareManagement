package com.example.healthCareManagement.service;

import java.net.URI;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.healthCareManagement.dao.DependentDao;
import com.example.healthCareManagement.dao.EnrolleeDao;
import com.example.healthCareManagement.dto.DependentDto;
import com.example.healthCareManagement.dto.EnrolleeDto;
import com.example.healthCareManagement.entity.Dependent;
import com.example.healthCareManagement.entity.Enrollee;
import com.example.healthCareManagement.exception.ResourceNotFoundException;


@Service
public class DependentService {
	

	private static final Logger logger = LoggerFactory.getLogger(DependentService.class);
	
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	DependentDao dependentDao;
	
	@Autowired
	EnrolleeDao enrolleeDao;
	

	public List<Dependent> getDependent() {

		List<Dependent> dList= dependentDao.findAll();
		
		return dList;
	}

	public Dependent createDependent(Long id,DependentDto dependentDto){
		
        
        Dependent dependent= prepareDependent(dependentDto);
        
        if(!enrolleeDao.existsById(id)) {
        	throw new ResourceNotFoundException("Enrollee with id " + id + " does not exist",HttpStatus.NOT_FOUND);
        }

        Enrollee enrollee = enrolleeDao.findById(id).get();
        
        
        dependent.setEnrollee(enrollee);
        return dependentDao.save(dependent);

	}

	public Dependent updateDependentbyID(Long id, DependentDto dependentDto){

		Optional<Dependent> dependent = dependentDao.findById(id);

		if (!dependent.isPresent()) {
			throw new ResourceNotFoundException("Enrollee with id " + id + " not found",HttpStatus.NOT_FOUND);
		}

		Dependent dependentUpdate = dependent.get();
		
		if(dependentDto.getName()!=null && !dependentDto.getName().isEmpty())
		dependentUpdate.setName(dependentDto.getName());
		
		if(dependentDto.getBirthDate()!=null && !dependentDto.getBirthDate().isEmpty())
			dependentUpdate.setDateOfBirth(parseDate(dependentDto.getBirthDate()));

		return dependentDao.save(dependentUpdate);

	}
	
	
	
	public void deleteDependent(Long id) {
		 dependentDao.deleteById(id);
		
	}
	
	
	private Dependent prepareDependent(DependentDto dependentDto) {
		Dependent dependent = new Dependent();
		dependent.setName(dependentDto.getName());
		dependent.setDateOfBirth(parseDate(dependentDto.getBirthDate()));
		return dependent;
	}
	
	private java.sql.Date parseDate(String date) {
	    try {
	        return new Date(DATE_FORMAT.parse(date).getTime());
	    } catch (Exception e) {
	        throw new IllegalArgumentException(e);
	    }
	}
}
