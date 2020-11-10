package com.example.healthCareManagement.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.healthCareManagement.dao.DependentDao;
import com.example.healthCareManagement.dao.EnrolleeDao;
import com.example.healthCareManagement.dto.DependentDto;
import com.example.healthCareManagement.entity.Dependent;
import com.example.healthCareManagement.entity.Enrollee;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DependentServiceTest {
	
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@InjectMocks
	private DependentService dependentService;
	
	
	@Mock
	private DependentDao dependentDao;
	
	@Mock
	private EnrolleeDao enrolleeDao;
	
	
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
		
		Mockito.when(dependentDao.findAll()).thenReturn(depList);
		List<Dependent> resultantList= dependentService.getDependent();
		assertEquals(depList.size(), resultantList.size());
	}
	
	
	@Test
	public void testcreateDependent() {
		

		Enrollee enrollee= new Enrollee();
		enrollee.setEnrolleeid(1L);
		enrollee.setName("Akhilesh");
		enrollee.setActivationStatus(true);
		enrollee.setDateOfBirth(parseDate("2020-02-22"));
		enrollee.setPhonenumber("123456");
		
		Dependent dep= new Dependent();
		dep.setName("Ramu");
		dep.setDateOfBirth(parseDate("2020-06-17"));
		dep.setEnrollee(enrollee);
		
		//dep.setEnrollee(new Enrollee(1L,null,true,parseDate("2020-06-17"),null,null));
		
		
		DependentDto depDto= new DependentDto();
		depDto.setName("Ramu");
		depDto.setBirthDate("2020-06-17");
		
		Mockito.when(enrolleeDao.existsById(Mockito.any())).thenReturn(true);
		Mockito.when(enrolleeDao.findById(Mockito.any())).thenReturn(Optional.of(enrollee));
		Mockito.when(enrolleeDao.save(Mockito.any())).thenReturn(dep);
		Dependent newDependent= dependentService.createDependent(enrollee.getEnrolleeid(), depDto);
		assertNull(newDependent);
		
	}
	
	
	@Test
	public void testUpdateDependent() {
		

		Enrollee enrollee= new Enrollee();
		enrollee.setEnrolleeid(1L);
		enrollee.setName("Akhilesh");
		enrollee.setActivationStatus(true);
		enrollee.setDateOfBirth(parseDate("2020-02-22"));
		enrollee.setPhonenumber("123456");
		
		Dependent dep= new Dependent();
		dep.setName("Ramu");
		dep.setDateOfBirth(parseDate("2020-06-17"));
		dep.setEnrollee(enrollee);
		
		
		DependentDto depDto= new DependentDto();
		depDto.setName("Ramu");
		depDto.setBirthDate("2020-06-17");
		
		Mockito.when(dependentDao.findById(Mockito.any())).thenReturn(Optional.of(dep));
		Mockito.when(dependentDao.save(Mockito.any())).thenReturn(dep);
		Dependent updatedDependent= dependentService.updateDependentbyID(enrollee.getEnrolleeid(), depDto);
		assertEquals(dep.getName(), updatedDependent.getName());
	}
	
	@Test
	public void testDeleteDependent() {
		
		Mockito.doNothing().when(dependentDao).delete(Mockito.any());
		dependentService.deleteDependent(1L);
	}
	private java.sql.Date parseDate(String date) {
	    try {
	        return new Date(DATE_FORMAT.parse(date).getTime());
	    } catch (Exception e) {
	        throw new IllegalArgumentException(e);
	    }
	}
}
