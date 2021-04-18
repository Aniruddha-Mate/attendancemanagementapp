package com.capgemini.entity;



import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Faculties")
public class FacultyEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="faculties_generations")
	@SequenceGenerator(name="faculties_generations", sequenceName = "faculties_sequences", allocationSize=1)
	private int facultyId;
	
    @Column(name = "faculty_user_name") 
	@NotEmpty
	@Size(min=5, max = 30, message = "Not a valid name")
    private String facultyName;
    
    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, mappedBy="facultyentity")
  	private List<SubjectEntity> subjectList;


	public List<SubjectEntity> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<SubjectEntity> subjectList) {
		this.subjectList = subjectList;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public FacultyEntity(int facultyId,
			@NotEmpty @Size(min = 5, max = 30, message = "Not a valid name") String facultyName,
			List<SubjectEntity> subjectList, List<StudentEntity> studentList) {
		super();
		this.facultyId = facultyId;
		this.facultyName = facultyName;
		this.subjectList = subjectList;
	
	}
	
	

	public FacultyEntity() {
		super();
	}

	
	
	
	
}