package com.capgemini.service;

import java.util.List;
import java.util.function.BooleanSupplier;

import com.capgemini.entity.SubjectEntity;
import com.capgemini.exception.CourseIdNotFoundException;
import com.capgemini.exception.DuplicateRecordException;
import com.capgemini.exception.FacultyIdNotFoundException;
import com.capgemini.exception.RecordNotFoundException;
import com.capgemini.exception.SubjectNotFoundException;

public interface SubjectService {
	
	SubjectEntity addSubject(SubjectEntity entity) throws DuplicateRecordException;
	
	SubjectEntity getSubjectById(int subjectId) throws SubjectNotFoundException;

	SubjectEntity updateSubjectById(int subjectId, SubjectEntity fe) throws SubjectNotFoundException;

	List<SubjectEntity> getAllSubjects() throws RecordNotFoundException;

	String deleteSubById(int subjectId) throws RecordNotFoundException;

	List<SubjectEntity> findSubjectByName(String subjectName) throws RecordNotFoundException;
	
	SubjectEntity addSubjectwithFC(SubjectEntity entity, int facultyId, int courseId)throws FacultyIdNotFoundException,
	                                                       CourseIdNotFoundException, DuplicateRecordException;
	
	List<SubjectEntity> findSubjectBySemester(String subjectSemester) throws RecordNotFoundException;

	boolean getSubjectExistById(int subjectId);
	
	
	
	

}