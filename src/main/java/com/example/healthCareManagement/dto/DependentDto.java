package com.example.healthCareManagement.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DependentDto {

	

	@JsonProperty("name")
	@NotNull
	@NotBlank
	public String name;
	

	@JsonProperty("birthDate")
	@NotNull
	@NotBlank
	public String birthDate;


	public String getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	
	
	
}
