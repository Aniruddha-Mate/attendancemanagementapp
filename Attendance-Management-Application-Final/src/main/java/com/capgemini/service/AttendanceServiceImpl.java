package com.capgemini.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.entity.AttendanceEntity;
import com.capgemini.entity.StudentEntity;
import com.capgemini.entity.SubjectEntity;
import com.capgemini.exception.AttendanceIdNotFoundException;
import com.capgemini.exception.RecordNotFoundException;
import com.capgemini.exception.StudentNotFoundException;
import com.capgemini.exception.SubjectNotFoundException;
import com.capgemini.repository.AttendanceRepository;
import com.capgemini.repository.StudentRepository;
import com.capgemini.repository.SubjectRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService{
	
	List<AttendanceEntity> attendanceList;
	
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	SubjectRepository subjectRepository;

	//For Getting the details of all the attendance present in the database
	@Override
	public List<AttendanceEntity> getAttendance() throws RecordNotFoundException{
		attendanceList = new ArrayList<>();
		attendanceList = attendanceRepository.findAll();
		if(attendanceList.isEmpty())
			throw new RecordNotFoundException("No Data In The Database Available");
		return attendanceList;
	}
    
	//For Updating the details of the Attendance By Attendance ID
	@Override
	public AttendanceEntity updateAttendanceById(int attendanceId, AttendanceEntity entity) throws AttendanceIdNotFoundException{
		Supplier<AttendanceIdNotFoundException> supplier = ()-> new AttendanceIdNotFoundException("Given Id Is Not Present In The Database");
		AttendanceEntity attendanceEntity = attendanceRepository.findById(attendanceId).orElseThrow(supplier);
		attendanceEntity.setSemester(entity.getSemester());
		attendanceEntity.setStatus(entity.getStatus());
		//attendanceEntity.setStudentEntity(entity.getStudentEntity());
		attendanceEntity.setCurrentDate(entity.getCurrentDate());
		//attendanceEntity.setSubjectEntity(entity.getSubjectEntity());
	    attendanceRepository.save(attendanceEntity);
	    return attendanceEntity;
	}
	
	//For deleting records of Attendance by Attendance ID
	@Override
	public String deleteById(int attendanceId) throws RecordNotFoundException {
		Supplier<RecordNotFoundException> supplier = ()-> new RecordNotFoundException("Given Id Is Not Present In The Database");
		attendanceRepository.findById(attendanceId).orElseThrow(supplier);
		attendanceRepository.deleteById(attendanceId);
		return "DELETED BY ID";
	}

	@Override
    public AttendanceEntity addAttendanceWithStudentIdAndSubjectId(AttendanceEntity entity, int studentId, int subjectId)
    		throws StudentNotFoundException, SubjectNotFoundException{
		StudentEntity student;
		SubjectEntity subject;
		List<AttendanceEntity> entities = new ArrayList<>();
	
		Supplier<StudentNotFoundException> supplier1 = ()-> new StudentNotFoundException("Given Student Id Is Not Present Database");
		student = studentRepository.findById(studentId).orElseThrow(supplier1);
		entity.setStudentEntity(student);
		Supplier<SubjectNotFoundException> supplier2 = ()-> new SubjectNotFoundException("Given Subject Id Is Not Present Database");
		subject = subjectRepository.findById(subjectId).orElseThrow(supplier2);
		entity.setSubjectEntity(subject);
		attendanceRepository.save(entity);
	    entities.add(entity);
	    student.setAttendanceList(entities);
		return entity;
	}
	
	@Override
	public List<AttendanceEntity> findAttendanceByStatus(String status) throws RecordNotFoundException  {
		List<AttendanceEntity> entity = attendanceRepository.findByStatusIgnoreCase(status);
		if(entity.isEmpty())
			throw new RecordNotFoundException("No Such Record Found");
		return entity;
	}

	@Override
	public List<AttendanceEntity> findAttendanceBySemester(String semester) throws RecordNotFoundException {
		List<AttendanceEntity> entity = (List<AttendanceEntity>) attendanceRepository.findBySemester(semester);
		if(entity.isEmpty())
			throw new RecordNotFoundException("No Such Record Found");
		return entity;
	}

	@Override
	public boolean getAttendanceExistById(int attendanceId) {
		// TODO Auto-generated method stub
		return attendanceRepository.existsById(attendanceId);
	}
	
	
}
