package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Integer>{

	
	List<StudentEntity> findAllByFirstNameIgnoreCase(String firstName);
	
	List<StudentEntity> findAllByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName,String lastName);
}