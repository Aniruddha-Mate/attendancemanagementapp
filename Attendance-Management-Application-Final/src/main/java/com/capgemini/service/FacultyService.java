package com.capgemini.service;

import java.util.List;
import com.capgemini.entity.FacultyEntity;
import com.capgemini.exception.FacultyIdNotFoundException;
import com.capgemini.exception.RecordNotFoundException;

public interface FacultyService {
	
	public FacultyEntity addFaculty(FacultyEntity entity);

	public FacultyEntity getFacultyById(int facultyId) throws FacultyIdNotFoundException;

	public String deleteFacultyById(int facultyId) throws RecordNotFoundException;

	public List<FacultyEntity> getAllFaculty() throws RecordNotFoundException;

	FacultyEntity updateFacultyById(int facultyId, FacultyEntity se) throws FacultyIdNotFoundException;
	
	List<FacultyEntity> findByfacultyName(String facultyName) throws RecordNotFoundException;

	public boolean getFacultyExistById(int facultyId);
}
