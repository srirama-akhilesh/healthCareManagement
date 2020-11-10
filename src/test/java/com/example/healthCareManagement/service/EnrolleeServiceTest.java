package com.example.healthCareManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.healthCareManagement.dao.EnrolleeDao;
import com.example.healthCareManagement.dto.EnrolleeDto;
import com.example.healthCareManagement.entity.Enrollee;
import com.example.healthCareManagement.exception.ResourceNotFoundException;

@RunWith(SpringRunner.class)
public class EnrolleeServiceTest {
	

	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	@InjectMocks
	private EnrolleeService enrolleeService;
	
	@Mock
	private EnrolleeDao enrolleeDao;
	
	
	@Test
	public void createEnrollee() {
		
		Enrollee enrl= new Enrollee();
		enrl.setName("Ramki");
		enrl.setDateOfBirth(parseDate("2020-11-12"));
		enrl.setActivationStatus(true);
		enrl.setPhonenumber("12345");

		EnrolleeDto enrlDto= new EnrolleeDto();
		enrlDto.setName("Ramki");
		enrlDto.setBirthDate("2020-11-12");
		enrlDto.setActivationStatus(true);
		enrlDto.setPhonenumber("12345");
		
		Mockito.when(enrolleeDao.save(Mockito.any())).thenReturn(enrl);
		Enrollee newEnrollee= enrolleeService.createEnrollee(enrlDto);
		assertEquals(enrl.getName(),newEnrollee.getName());

	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateEnrollee_ResourceNotFoundException() {
		
		EnrolleeDto enrlDto= new EnrolleeDto();
		enrlDto.setName("Ramki");
		enrlDto.setBirthDate("2020-11-12");
		enrlDto.setActivationStatus(true);
		enrlDto.setPhonenumber("12345");
		
		Mockito.when(enrolleeDao.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
		enrolleeService.updateEnrollee(enrlDto, 34L);
		
	}
	
	@Test
	public void testGetEnrollee() {
		

		Enrollee enrl= new Enrollee();
		enrl.setName("Ramki");
		enrl.setDateOfBirth(parseDate("2020-11-12"));
		enrl.setActivationStatus(true);
		enrl.setPhonenumber("12345");
		

		Enrollee enrl2= new Enrollee();
		enrl2.setName("Ramki");
		enrl2.setDateOfBirth(parseDate("2020-11-12"));
		enrl2.setActivationStatus(true);
		enrl2.setPhonenumber("12345");
		

		Enrollee enrl3= new Enrollee();
		enrl3.setName("Ramki");
		enrl3.setDateOfBirth(parseDate("2020-11-12"));
		enrl3.setActivationStatus(true);
		enrl3.setPhonenumber("12345");
		
		List<Enrollee> enrolleeList= new ArrayList<>();
		enrolleeList.add(enrl);
		enrolleeList.add(enrl2);
		enrolleeList.add(enrl3);
		
		Mockito.when(enrolleeDao.findAll()).thenReturn(enrolleeList);
		List<Enrollee> resultantList= enrolleeService.getEnrolles();
		assertEquals(enrolleeList.size(), resultantList.size());
	}
	
	
	@Test
	public void testUpdateEnrollee() {
		
		Enrollee enrl= new Enrollee();
		enrl.setName("Ramki");
		enrl.setDateOfBirth(parseDate("2020-11-12"));
		enrl.setActivationStatus(true);
		enrl.setPhonenumber("12345");
		enrl.setEnrolleeid(1L);
		
		EnrolleeDto enrlDto= new EnrolleeDto();
		enrlDto.setName("Ramki");
		enrlDto.setBirthDate("2020-11-12");
		enrlDto.setActivationStatus(true);
		enrlDto.setPhonenumber("12345");
		
		Mockito.when(enrolleeDao.findById(Mockito.any())).thenReturn(Optional.of(enrl));
		Mockito.when(enrolleeDao.save(Mockito.any())).thenReturn(enrl);
		Enrollee updatedEnrollee= enrolleeService.updateEnrollee(enrlDto, enrl.getEnrolleeid());
		assertEquals(enrl.getPhonenumber(), updatedEnrollee.getPhonenumber());
		
	}
	
	@Test
	public void testDeleteEnrollee() {

		
		Mockito.doNothing().when(enrolleeDao).delete(Mockito.any());
		enrolleeService.deleteEnrollee(1L);
	}

	
	private java.sql.Date parseDate(String date) {
	    try {
	        return new Date(DATE_FORMAT.parse(date).getTime());
	    } catch (Exception e) {
	        throw new IllegalArgumentException(e);
	    }
	}
}
