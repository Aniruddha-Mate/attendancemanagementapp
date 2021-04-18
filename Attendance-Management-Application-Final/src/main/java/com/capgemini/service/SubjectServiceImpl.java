package com.capgemini.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.capgemini.entity.CourseEntity;
import com.capgemini.entity.FacultyEntity;
import com.capgemini.entity.StudentEntity;
import com.capgemini.entity.SubjectEntity;
import com.capgemini.exception.CourseIdNotFoundException;
import com.capgemini.exception.DuplicateRecordException;
import com.capgemini.exception.FacultyIdNotFoundException;
import com.capgemini.exception.RecordNotFoundException;
import com.capgemini.exception.SubjectNotFoundException;
import com.capgemini.repository.CourseRepository;
import com.capgemini.repository.FacultyRepository;
import com.capgemini.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService{
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	FacultyRepository facultyRepository;
	
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	CourseService courseService;
	
	
	@Override
	public SubjectEntity addSubject(SubjectEntity entity) throws DuplicateRecordException{
		List<SubjectEntity> sub=subjectRepository.findAll();//this.getAllSubjects();
		for(SubjectEntity subjectentity:sub)
		{
			if(subjectentity.getSubjectName().equalsIgnoreCase(entity.getSubjectName()))
			{
				throw new DuplicateRecordException("The name of Subject is already exist......Provide different SubjectName");
			}
		}
		SubjectEntity subjectentity = (SubjectEntity) subjectRepository.save(entity);		
		return subjectentity;
	}

	  @Override 
	  public String deleteSubById(int subjectId) 
	  {
		  subjectRepository.findById(subjectId).orElse(null); 
		  subjectRepository.deleteById(subjectId);
	  return "Deleted"; }
	 
	
	
	
	@Override
	public SubjectEntity getSubjectById(int subjectId) throws SubjectNotFoundException {
		Supplier<SubjectNotFoundException> supplier=()->new SubjectNotFoundException("Subject not found with this id");
      SubjectEntity subjectentity = subjectRepository.findById(subjectId).orElseThrow(supplier);
		return subjectentity;
	}
	@Override
	public List<SubjectEntity> getAllSubjects() throws RecordNotFoundException {
		List<SubjectEntity> subjectentity = new ArrayList<SubjectEntity>();
		subjectentity = subjectRepository.findAll();		
		if(subjectentity.isEmpty())
			throw new RecordNotFoundException("Record Not Found In The Database");
		return subjectentity;
	}

	@Override
	public SubjectEntity updateSubjectById(int subjectId, SubjectEntity fe) throws SubjectNotFoundException{
		Supplier<SubjectNotFoundException> supplier=()->new SubjectNotFoundException("Given Subject id is not present in database");
		SubjectEntity se = subjectRepository.findById(subjectId).orElseThrow(supplier);
		se.setSubjectName(fe.getSubjectName());
		se.setSubjectSemester(fe.getSubjectSemester());
		se.setDescription(fe.getDescription());
		subjectRepository.save(se);
		return se;
	}

	@Override
	public List<SubjectEntity> findSubjectByName(String subjectName) throws RecordNotFoundException{
		List<SubjectEntity> se = subjectRepository.findBysubjectNameIgnoreCaseContains(subjectName);
		if(se.isEmpty())
			throw new RecordNotFoundException("Record Not Found In The Database");
		return se;
	}

	@Override
	public SubjectEntity addSubjectwithFC(SubjectEntity entity, int facultyId, int courseId) throws FacultyIdNotFoundException, CourseIdNotFoundException, DuplicateRecordException {
		List<SubjectEntity> le = new ArrayList<>();
		List<SubjectEntity> le1 = subjectRepository.findAll();
		for(SubjectEntity subjectEntity:le1)
		{
			if(subjectEntity.getSubjectName().equalsIgnoreCase(entity.getSubjectName()))
			{
				throw new DuplicateRecordException("Subject Already Present! Please Provide different Subject Name");
			}
		}
		FacultyEntity faculty;
		CourseEntity course;
		Supplier<FacultyIdNotFoundException> supplier1 = ()-> new FacultyIdNotFoundException("Faculty with given Id is not availabe");
		faculty = facultyRepository.findById(facultyId).orElseThrow(supplier1);
		Supplier<CourseIdNotFoundException> supplier2 = ()-> new CourseIdNotFoundException("Course with given Id is not availabe");
		course = courseRepository.findById(courseId).orElseThrow(supplier2);
		entity.setCourse(course);
		entity.setFacultyentity(faculty);
		subjectRepository.save(entity);
		le.add(entity);
		faculty.setSubjectList(le);
		course.setSubjectList(le);
        return entity;
	}
	
	 @Override 
	  public List<SubjectEntity> findSubjectBySemester(String subjectSemester) throws RecordNotFoundException
	  { 
		 List<SubjectEntity> se = subjectRepository.findBysubjectSemester(subjectSemester);
		 if(se.isEmpty())
			 throw new RecordNotFoundException("Data Not Found In The Database");
		  return se;
	  
	  }

	@Override
	public boolean getSubjectExistById(int subjectId) {
		// TODO Auto-generated method stub
		return subjectRepository.existsById(subjectId);
	}

}
