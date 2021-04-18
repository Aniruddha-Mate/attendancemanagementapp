package com.capgemini.service;

import java.util.List;
import com.capgemini.entity.AttendanceEntity;
import com.capgemini.exception.AttendanceIdNotFoundException;
import com.capgemini.exception.RecordNotFoundException;
import com.capgemini.exception.StudentNotFoundException;
import com.capgemini.exception.SubjectNotFoundException;

public interface AttendanceService {
	
	//For Getting the details of all the attendance present in the database
	List<AttendanceEntity> getAttendance() throws RecordNotFoundException;
		
	//For Updating the details of the Attendance By Attendance ID
	AttendanceEntity updateAttendanceById(int attendanceId,AttendanceEntity entity)throws AttendanceIdNotFoundException;
	
	//For deleting records of Attendance by Attendance ID
	String deleteById(int attendanceId) throws RecordNotFoundException;
	
	//For adding attendance using StudentID
	AttendanceEntity addAttendanceWithStudentIdAndSubjectId(AttendanceEntity entity, int studentId, int subjectId) 
			throws StudentNotFoundException, SubjectNotFoundException;
	
	//For Getting attendance according to the Semester
	List<AttendanceEntity> findAttendanceBySemester(String semester)throws RecordNotFoundException;

	//For Getting the List of Students who are present or absent
	List<AttendanceEntity> findAttendanceByStatus(String status) throws RecordNotFoundException;

	boolean getAttendanceExistById(int attendanceId);

	
}

