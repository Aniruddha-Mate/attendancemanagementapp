package com.capgemini.service;

import java.util.List;
import com.capgemini.entity.CourseEntity;
import com.capgemini.exception.CourseIdNotFoundException;
import com.capgemini.exception.RecordNotFoundException;

public interface CourseService {
	
	//For adding the courses one by one
    public CourseEntity addCourse(CourseEntity entity) ;
	
    //For Getting the details of all the courses present in the database
    public List<CourseEntity> getCourse() throws RecordNotFoundException;
    
    //For Getting the details of the course by Course ID
    public CourseEntity getCourseById(int courseId) throws CourseIdNotFoundException;
    
    //For Updating the details of the courses By Course ID
    public CourseEntity updateByCourseId(int courseid, CourseEntity entity) throws CourseIdNotFoundException;
     
    //For deleting records of Course by Course ID
    public String deleteByCourseId(int courseId) throws RecordNotFoundException;
    
    public List<CourseEntity> findCourseByName(String courseName) throws RecordNotFoundException;

	public boolean getCourseExistById(int courseId);	
    
}
