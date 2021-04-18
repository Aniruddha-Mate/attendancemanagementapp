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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.entity.AttendanceEntity;
import com.capgemini.exception.AttendanceIdNotFoundException;
import com.capgemini.exception.RecordNotFoundException;
import com.capgemini.exception.StudentNotFoundException;
import com.capgemini.exception.SubjectNotFoundException;
import com.capgemini.service.AttendanceService;

@RestController
@RequestMapping("/Attendance")
public class AttendanceController {

	@Autowired
	AttendanceService attendanceService;
	
 
	//Update Attendance By ID
	@PutMapping(path="/updateAttendance/{attendanceId}") 
	public ResponseEntity<AttendanceEntity> updateAttendById(@Valid @PathVariable int attendanceId,@Valid @RequestBody AttendanceEntity entity)throws AttendanceIdNotFoundException
	{
		return new ResponseEntity<AttendanceEntity>(attendanceService.updateAttendanceById(attendanceId,entity),HttpStatus.ACCEPTED);
	}
	
	//Get All Attendance
	@GetMapping(path="/getAllAttendance")
	public ResponseEntity<List<AttendanceEntity>> getAttendance() throws RecordNotFoundException{
	   return new ResponseEntity<List<AttendanceEntity>>(attendanceService.getAttendance(),HttpStatus.FOUND);
	}
	
	//Delete Attendance By Id
	@DeleteMapping(path="/deleteAttendanceById/{attendanceId}") 
	public ResponseEntity<String> deleteAttendanceById(@Valid @PathVariable int attendanceId) throws RecordNotFoundException
	{
		return new ResponseEntity<String>(attendanceService.deleteById(attendanceId),HttpStatus.ACCEPTED);
	}
	
	@PostMapping(path="/addAttendanceWithStudentIdAndSubjectId/{studentId}/{subjectId}")
	public ResponseEntity<AttendanceEntity> addAttendWithStudentIdAndSubjectId
	(@Valid @PathVariable int studentId, @Valid @PathVariable int subjectId, @Valid @RequestBody AttendanceEntity entity)
			throws StudentNotFoundException, SubjectNotFoundException
	{
		return new ResponseEntity<AttendanceEntity>(attendanceService.addAttendanceWithStudentIdAndSubjectId(entity,studentId,subjectId),HttpStatus.CREATED);
	}
	
	@GetMapping(path="/getAttendanceBySemester/{semester}") 
	public ResponseEntity<List<AttendanceEntity>> findAttendanceBySemester(@Valid @PathVariable String semester) 
			throws RecordNotFoundException
	{
		  List<AttendanceEntity> ae=attendanceService.findAttendanceBySemester(semester);
		  ResponseEntity<List<AttendanceEntity>> re=new ResponseEntity<List<AttendanceEntity>>(ae,HttpStatus.FOUND);
		  return re;
	}
	  
	
	@GetMapping(path="/getAttendanceByStatus/{status}")
	public ResponseEntity<List<AttendanceEntity>> findAttendanceByStatus(@Valid @PathVariable String status) 
			throws RecordNotFoundException
	{
		return new ResponseEntity<List<AttendanceEntity>>(attendanceService.findAttendanceByStatus(status),HttpStatus.FOUND);
	}
}
