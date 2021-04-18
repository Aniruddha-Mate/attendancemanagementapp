package com.capgemini.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entity.StudentEntity;
import com.capgemini.exception.CourseIdNotFoundException;
import com.capgemini.exception.DuplicateRecordException;
import com.capgemini.exception.RecordNotFoundException;
import com.capgemini.exception.StudentNotFoundException;
import com.capgemini.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService studService;
	
	@GetMapping("/get/{studentId}")
	public ResponseEntity<StudentEntity> getStudentById(@Valid @PathVariable int studentId) throws StudentNotFoundException
	{
		return new ResponseEntity<StudentEntity>(studService.getStudentById(studentId),HttpStatus.FOUND);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<StudentEntity>> getStudents() throws RecordNotFoundException
	{
		return new ResponseEntity<List<StudentEntity>>(studService.getStudents(),HttpStatus.OK);
	}
	
	@PutMapping("/update/{studentId}")
	public ResponseEntity<StudentEntity> updateStudent(@Valid @PathVariable int studentId, @Valid @RequestBody StudentEntity entity) 
			throws StudentNotFoundException
	{
		return new ResponseEntity<StudentEntity>(studService.updateStudent(studentId, entity),HttpStatus.ACCEPTED);
	}
	

	@DeleteMapping("/delete/{studentId}")
	public ResponseEntity<String> deleteStudent(@Valid @PathVariable int studentId) throws RecordNotFoundException
	{
		return new ResponseEntity<String>(studService.deleteStudent(studentId),HttpStatus.ACCEPTED);
	}

	@PostMapping("/addStudentwithCourse/{courseId}")
	public ResponseEntity<StudentEntity> addStudentwithC(@Valid @PathVariable int courseId,@Valid @RequestBody StudentEntity entity)
			throws CourseIdNotFoundException, DuplicateRecordException
	{
		return new ResponseEntity<StudentEntity>(studService.addStudentwithC(entity,courseId),HttpStatus.CREATED);
	}
	
	@GetMapping("/getStudentByFirstName/{firstName}")
	public ResponseEntity<List<StudentEntity>> getStudentByFirstName(@Valid @PathVariable String firstName) throws StudentNotFoundException
	{
		return new ResponseEntity<List<StudentEntity>>(studService.findStudentByFirstName(firstName),HttpStatus.FOUND);
	}
	
	@GetMapping("/getStudentByFirstNameAndLastName/{firstName}/{lastName}")
	public ResponseEntity<List<StudentEntity>> getStudentByFirstName(@Valid @PathVariable String firstName, @Valid @PathVariable String lastName) 
			throws StudentNotFoundException
	{
		return new ResponseEntity<List<StudentEntity>>(studService.findStudentByFirstNameAndLastName(firstName,lastName),HttpStatus.FOUND);
	}
	

}
