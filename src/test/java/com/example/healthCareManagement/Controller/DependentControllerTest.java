package com.example.healthCareManagement.Controller;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.healthCareManagement.controller.DependentController;
import com.example.healthCareManagement.dto.DependentDto;
import com.example.healthCareManagement.entity.Dependent;
import com.example.healthCareManagement.entity.Enrollee;
import com.example.healthCareManagement.service.DependentService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages="com.example.healthCareManagement.*")
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
public class DependentControllerTest {
	
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	@InjectMocks
	private DependentController dependentController;
	
	@Mock
	private DependentService dependentService;
	
	
	@Test
	public void testGetDependents() {
		
		Enrollee enrollee= new Enrollee();
		
		Dependent dep= new Dependent();
		dep.setName("Ramu");
		dep.setDateOfBirth(parseDate("2020-06-17"));
		dep.setEnrollee(enrollee);
		
		Dependent dep2= new Dependent();
		dep2.setName("Ramu");
		dep2.setDateOfBirth(parseDate("2020-06-17"));
		dep2.setEnrollee(enrollee);
		
		Dependent dep3= new Dependent();
		dep3.setName("Ramu");
		dep3.setDateOfBirth(parseDate("2020-06-17"));
		dep3.setEnrollee(enrollee);
		
		List<Dependent> depList= new ArrayList<>();
		
		Mockito.when(dependentService.getDependent()).thenReturn(depList);
		ResponseEntity<List<Dependent>> resultantList= dependentController.getDependents();
		assertEquals(HttpStatus.OK, resultantList.getStatusCode());
		
	}
	
	
	@Test
	public void testCreateDependent() {
		

		Enrollee enrollee = new Enrollee();
		enrollee.setEnrolleeid(1L);
		
		Dependent dep= new Dependent();
		dep.setName("Ramu");
		dep.setDateOfBirth(parseDate("2020-06-17"));
		

		DependentDto dep3= new DependentDto();
		dep3.setName("Ramu");
		dep3.setBirthDate("2020-06-17");
		
		Mockito.when(dependentService.createDependent(Mockito.any(), Mockito.any())).thenReturn(dep);
		ResponseEntity<Dependent> dependent= dependentController.createDependent(enrollee.getEnrolleeid(), dep3);
		assertEquals(HttpStatus.CREATED, dependent.getStatusCode());
		
	}
	
	@Test
	public void testUpdateDependent() {
		
		Dependent dep= new Dependent();
		dep.setName("Ramu");
		dep.setDateOfBirth(parseDate("2020-06-17"));
		

		DependentDto dep3= new DependentDto();
		dep3.setName("Ramu");
		dep3.setBirthDate("2020-06-17");
		
		Mockito.when(dependentService.updateDependentbyID(Mockito.any(), Mockito.any())).thenReturn(dep);
		ResponseEntity<Dependent> updateDependent= dependentController.updateDependentbyId(2L, dep3);
		assertEquals(HttpStatus.OK, updateDependent.getStatusCode());
	
	}
	
	@Test
	public void testDeleteDependent() {
		
		Mockito.doNothing().when(dependentService).deleteDependent(Mockito.any());
		dependentController.deleteDependent(1L);
	}
	
	
	private java.sql.Date parseDate(String date) {
	    try {
	        return new Date(DATE_FORMAT.parse(date).getTime());
	    } catch (Exception e) {
	        throw new IllegalArgumentException(e);
	    }
	}

}
