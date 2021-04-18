package com.capgemini.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entity.CourseEntity;
import com.capgemini.exception.CourseIdNotFoundException;
import com.capgemini.exception.RecordNotFoundException;
import com.capgemini.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseRepository courseRepository;
	
	List<CourseEntity> courseList;

	//For adding the courses one by one
	@Override
	public CourseEntity addCourse(CourseEntity entity) {
		CourseEntity ce = (CourseEntity) courseRepository.save(entity);		
		return ce;
	}
	
	//For Getting the details of all the courses present in the database
	@Override
	public List<CourseEntity> getCourse() throws RecordNotFoundException{
		courseList = new ArrayList<>();
		courseList = courseRepository.findAll();
		if(courseList.isEmpty()) {
		    throw new RecordNotFoundException("Record Not in the database");
		}
		return courseList;
	}
	
	//For Getting the details of the course by Course ID
	@Override
	public CourseEntity getCourseById(int courseId) throws CourseIdNotFoundException {
		Supplier<CourseIdNotFoundException> supplier = ()-> new CourseIdNotFoundException("Given Course Id Not In The Database");
		CourseEntity ce = courseRepository.findById(courseId).orElseThrow(supplier);
		return ce;
	}

	//For Updating the details of the courses By Course ID
	@Override
	public CourseEntity updateByCourseId(int courseId, CourseEntity entity) throws CourseIdNotFoundException {
		Supplier<CourseIdNotFoundException> supplier = ()-> new CourseIdNotFoundException("Given Course Id Not In The Database");
		CourseEntity ce = courseRepository.findById(courseId).orElseThrow(supplier);
		ce.setCourseName(entity.getCourseName());
		ce.setDescription(entity.getDescription());
//		ce.setSubList(entity.getSubList());
		
		
		courseRepository.save(ce);
		return ce;
	}
	
	//For deleting records of Course by Course ID	
	@Override
	public String deleteByCourseId(int courseId) throws RecordNotFoundException{
		Supplier<RecordNotFoundException> supplier = ()-> new RecordNotFoundException("Record Not In The database");
		courseRepository.findById(courseId).orElseThrow(supplier);
		courseRepository.deleteById(courseId);
	    return "Deleted By Course Id";
	}
	
	@Override
	public List<CourseEntity> findCourseByName(String courseName)throws RecordNotFoundException {
		List<CourseEntity> ce = courseRepository.findByCourseNameIgnoreCaseContains(courseName);
		if(ce.isEmpty())
			throw new RecordNotFoundException("Record Not In The Database");
		return ce;
	}

	@Override
	public boolean getCourseExistById(int courseId) {
		// TODO Auto-generated method stub
		return courseRepository.existsById(courseId);
	}



	
	




	
	
	
	

}
