package com.example.healthCareManagement.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.example.healthCareManagement.controller.EnrolleeController;
import com.example.healthCareManagement.dto.EnrolleeDto;
import com.example.healthCareManagement.entity.Enrollee;
import com.example.healthCareManagement.service.EnrolleeService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages="com.example.healthCareManagement.*")
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
public class EnrolleControllerTest {

	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	@InjectMocks
	EnrolleeController enrolleeController;
	
	@Mock
	private EnrolleeService enrolleeService;
	
	@Test
	public void testCreateEnrollee() throws Exception{
		
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
		
		Mockito.when(enrolleeService.createEnrollee(Mockito.any())).thenReturn(enrl);
		ResponseEntity<Enrollee> enrollee = enrolleeController.createEnrollee(enrlDto);
		assertEquals(HttpStatus.CREATED, enrollee.getStatusCode());
		
		
	}
	
	@Test
	public void testGetEnrollee() throws Exception{
		
		Enrollee enrl= new Enrollee();
		enrl.setName("Ramki");
		enrl.setDateOfBirth(parseDate("2020-11-12"));
		enrl.setActivationStatus(true);
		enrl.setPhonenumber("12345");
		
		Enrollee enrl2= new Enrollee();
		enrl2.setName("Avinash");
		enrl2.setDateOfBirth(parseDate("2022-11-12"));
		enrl2.setActivationStatus(false);
		enrl2.setPhonenumber("45646");
		
		List<Enrollee> enrolleeList = new ArrayList<>();
		enrolleeList.add(enrl);
		enrolleeList.add(enrl2);
		
		
		Mockito.when(enrolleeService.getEnrolles()).thenReturn(enrolleeList);
		ResponseEntity<List<Enrollee>> returnedEnrolleeList = enrolleeController.getEnrolles();
		assertEquals(HttpStatus.OK, returnedEnrolleeList.getStatusCode());
		
	}
	
	
	@Test
	public void testPutEnrolleeRecord() throws Exception{
		

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
		
		Mockito.when(enrolleeService.updateEnrollee(Mockito.any(), Mockito.any())).thenReturn(enrl);
		ResponseEntity<Enrollee> enrollee = enrolleeController.updateEnrollee(1L,enrlDto);
		assertEquals(HttpStatus.OK, enrollee.getStatusCode());
		
	}
	
	
	
	@Test
	public void testdelteeEnrolle() {
		Mockito.doNothing().when(enrolleeService).deleteEnrollee(Mockito.any());
		enrolleeController.deleteEnrollee(1L);
	}
	
	private java.sql.Date parseDate(String date) {
	    try {
	        return new Date(DATE_FORMAT.parse(date).getTime());
	    } catch (Exception e) {
	        throw new IllegalArgumentException(e);
	    }
	}
	
}
