package com.capgemini.service;

import java.util.List;
import com.capgemini.entity.StudentEntity;
import com.capgemini.exception.CourseIdNotFoundException;
import com.capgemini.exception.DuplicateRecordException;
import com.capgemini.exception.RecordNotFoundException;
import com.capgemini.exception.StudentNotFoundException;

public interface StudentService {

	StudentEntity getStudentById(int studentId) throws StudentNotFoundException;

	List<StudentEntity> getStudents() throws RecordNotFoundException;

	StudentEntity updateStudent(int studentId, StudentEntity entity) throws StudentNotFoundException;

	String deleteStudent(int studentId) throws RecordNotFoundException;	
	
	StudentEntity addStudentwithC(StudentEntity entity, int courseId)throws CourseIdNotFoundException, DuplicateRecordException;
	
	List<StudentEntity> findStudentByFirstName(String firstName)throws StudentNotFoundException;
	
	List<StudentEntity> findStudentByFirstNameAndLastName(String firstName,String lastName)throws StudentNotFoundException;

	boolean getStudentExistById(int studentId);
	
	

}
