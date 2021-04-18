package com.capgemini.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entity.FacultyEntity;
import com.capgemini.exception.FacultyIdNotFoundException;
import com.capgemini.exception.RecordNotFoundException;
import com.capgemini.repository.FacultyRepository;

@Service
public class FacultyServiceImpl implements FacultyService {
		
	@Autowired
	FacultyRepository facultyRepository;
	
	@Override
	public FacultyEntity addFaculty(FacultyEntity entity)
	{
		FacultyEntity fe = (FacultyEntity) facultyRepository.save(entity);
		return fe;
	}

	@Override
	public FacultyEntity getFacultyById(int facultyId) throws FacultyIdNotFoundException{
		
		Supplier<FacultyIdNotFoundException> supplier=()->new FacultyIdNotFoundException("This Faculty is not present");
		FacultyEntity fe = facultyRepository.findById(facultyId).orElseThrow(supplier);
		
		return fe;
	}

	@Override
	public String deleteFacultyById(int facultyId) throws RecordNotFoundException{
		Supplier<RecordNotFoundException> supplier=()->new RecordNotFoundException("No such Record Found");
		facultyRepository.findById(facultyId).orElseThrow(supplier);
		facultyRepository.deleteById(facultyId);
		return "Deleted by Id";	
	}

	@Override
	public List<FacultyEntity> getAllFaculty() throws RecordNotFoundException{
		List<FacultyEntity> fe = new ArrayList<FacultyEntity>();
		fe = facultyRepository.findAll();		
		if(fe.isEmpty())
			throw new RecordNotFoundException("Record Not In The Database");
		return fe;
	}

	@Override
	public FacultyEntity updateFacultyById(int facultyId, FacultyEntity se) throws FacultyIdNotFoundException{
		
		Supplier<FacultyIdNotFoundException> supplier=()->new FacultyIdNotFoundException("Given Id Is Not Present");
		FacultyEntity fe = facultyRepository.findById(facultyId).orElseThrow(supplier);
		fe.setFacultyName(se.getFacultyName());
//		fe.setStudentList(se.getStudentList());
//		fe.setSubjectList(se.getSubjectList());
		
		facultyRepository.save(fe);
		return fe;
	}
	
	  @Override 
	  public List<FacultyEntity> findByfacultyName(String facultyName) throws RecordNotFoundException{
		  List<FacultyEntity> fe = facultyRepository.findByfacultyNameIgnoreCaseContains(facultyName);
		  if(fe.isEmpty())
			  throw new RecordNotFoundException("Record Not In The Database");
		  return fe;
      }

	@Override
	public boolean getFacultyExistById(int facultyId) {
		// TODO Auto-generated method stub
		return facultyRepository.existsById(facultyId);
	}
	
	

    
}

