package com.example.healthCareManagement.dao;

import org.springframework.stereotype.Repository;

import com.example.healthCareManagement.entity.Enrollee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EnrolleeDao extends JpaRepository<Enrollee, Long>{

	
}
