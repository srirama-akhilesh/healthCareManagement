package com.example.healthCareManagement.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Enrollee")
public class Enrollee {

	

	public Enrollee(Long enrolleeid, String name, boolean activationStatus, Date dateOfBirth, String phonenumber,
			Set<Dependent> dependent) {
		super();
		this.enrolleeid = enrolleeid;
		this.name = name;
		this.activationStatus = activationStatus;
		this.dateOfBirth = dateOfBirth;
		this.phonenumber = phonenumber;
		this.dependent = dependent;
	}

	@Column(name = "EnrolleID", nullable = false, length = 10)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long enrolleeid;
	
	
	@Column(name="Name")
	private String name;
	
	@Column(name= "ActivationStatus")
	private boolean activationStatus;
	
	@Column(name="DOB")
	private java.sql.Date dateOfBirth;
	

	public java.sql.Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(java.sql.Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name= "ContactNumber")
	private String phonenumber;
	
	@OneToMany(mappedBy= "enrollee", fetch= FetchType.LAZY, cascade = {CascadeType.ALL})
	private Set<Dependent> dependent= new HashSet<Dependent>();


	
	
	public Enrollee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getEnrolleeid() {
		return enrolleeid;
	}

	public void setEnrolleeid(Long enrolleeid) {
		this.enrolleeid = enrolleeid;
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

	

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Set<Dependent> getDependent() {
		return dependent;
	}

	public void setDependent(Set<Dependent> dependent) {
		this.dependent = dependent;
	}
	
	
}
