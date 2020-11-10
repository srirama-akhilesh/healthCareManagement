package com.example.healthCareManagement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EnrolleeDto {

	
	@JsonProperty("name")
	@NotNull
	@NotBlank
	@Pattern(regexp="^[A-Za-z]+$", message=" must have characters only")
	public String name;
	
	@JsonProperty("activationStatus")
	@NotNull
	public boolean activationStatus;
	
	@JsonProperty("birthDate")
	@NotNull
	@NotBlank
	public String birthDate;
	
	@JsonProperty("phoneNumber")
	public String phonenumber;
	
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	

	
	
}
