package com.example.healthCareManagement.dao;

import org.springframework.stereotype.Repository;

import com.example.healthCareManagement.entity.Dependent;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DependentDao extends JpaRepository<Dependent, Long>{

	
}
