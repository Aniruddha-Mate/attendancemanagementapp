package com.capgemini;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.capgemini.entity.AttendanceEntity;
import com.capgemini.entity.CourseEntity;
import com.capgemini.entity.FacultyEntity;
import com.capgemini.entity.StudentEntity;
import com.capgemini.entity.SubjectEntity;
import com.capgemini.exception.AttendanceIdNotFoundException;
import com.capgemini.exception.CourseIdNotFoundException;
import com.capgemini.exception.DuplicateRecordException;
import com.capgemini.exception.FacultyIdNotFoundException;
import com.capgemini.exception.RecordNotFoundException;
import com.capgemini.exception.StudentNotFoundException;
import com.capgemini.exception.SubjectNotFoundException;
import com.capgemini.repository.CourseRepository;
import com.capgemini.repository.FacultyRepository;
import com.capgemini.repository.StudentRepository;
import com.capgemini.repository.SubjectRepository;
import com.capgemini.service.AttendanceService;
import com.capgemini.service.CourseService;
import com.capgemini.service.FacultyService;
import com.capgemini.service.StudentService;
import com.capgemini.service.SubjectService;
import com.capgemini.repository.AttendanceRepository;

@SpringBootTest
class AttendanceManagementApplicationTest  {

	//-------------------------------------------Attendance-------------------------------------
	 @Autowired
	 AttendanceService AttendanceService;
	 @MockBean
	 AttendanceRepository AttendanceRepository;
	 
	
	 @Test
	 public void testGetAttendance() throws RecordNotFoundException 
	 {
		 AttendanceEntity attendance = new AttendanceEntity();
		 attendance.setAttendanceId(1);
		 attendance.setSemester("1");
		//attendance.setCurrentDate(null);
		 attendance.setStatus("Present");
		 
		 AttendanceEntity attendance1 = new AttendanceEntity();
		 attendance1.setAttendanceId(2);
		 attendance1.setSemester("1");
		 //attendance1.setCurrentDate(null);
		 attendance1.setStatus("Absent");
		 
		 List<AttendanceEntity> attendanceList=new ArrayList<>();
		 attendanceList.add(attendance);
		 attendanceList.add(attendance1);
		 
		 Mockito.when(AttendanceRepository.findAll()).thenReturn(attendanceList);
		 assertThat(AttendanceService.getAttendance()).isEqualTo(attendanceList);
	 }
	 
	 
	@Test
	public void testUpdateAttendanceById() throws AttendanceIdNotFoundException 
	{
		AttendanceEntity attendance = new AttendanceEntity();
		 attendance.setAttendanceId(1);
		 attendance.setSemester("1");
		 //attendance.setCurrentDate(null);
		 attendance.setStatus("Present");
	 
		 Optional<AttendanceEntity> attendance1 =Optional.ofNullable(attendance);
		 Mockito.when(AttendanceRepository.findById(1)).thenReturn(attendance1);
		 
		 attendance.setStatus("Absent");
		 
		 Mockito.when(AttendanceRepository.save(attendance)).thenReturn(attendance);
		 assertThat(AttendanceService.updateAttendanceById(1, attendance)).isEqualTo(attendance);
		 
	}
	@Test
	public void testDeleteById()
	{
		AttendanceEntity attendance = new AttendanceEntity();
		 attendance.setAttendanceId(1);
		 attendance.setSemester("1");
		 //attendance.setCurrentDate(null);
		 attendance.setStatus("Present");
		 
		 Optional<AttendanceEntity> attendance1 =Optional.ofNullable(attendance);
		 Mockito.when(AttendanceRepository.findById(1)).thenReturn(attendance1);
		 
		 Mockito.when(AttendanceRepository.existsById(attendance.getAttendanceId())).thenReturn(false);	   
		    assertFalse(AttendanceService.getAttendanceExistById(attendance.getAttendanceId()));
	}
	
	 @Test
	 public void testFindAttendanceBySemester() throws RecordNotFoundException
	 {
		 AttendanceEntity attendance = new AttendanceEntity();
		 attendance.setAttendanceId(1);
		 attendance.setSemester("1");
		//attendance.setCurrentDate(null);
		 attendance.setStatus("Present");
		 
		 AttendanceEntity attendance1 = new AttendanceEntity();
		 attendance1.setAttendanceId(2);
		 attendance1.setSemester("1");
		 //attendance1.setCurrentDate(null);
		 attendance1.setStatus("Absent");
		 
		 List<AttendanceEntity> attendanceList=new ArrayList<>();
		 attendanceList.add(attendance);
		 attendanceList.add(attendance1);
		 
		 Mockito.when(AttendanceRepository.findBySemester("1")).thenReturn(attendanceList);
		 assertThat(AttendanceService.findAttendanceBySemester("1")).isEqualTo(attendanceList);
	 }
	 
	 @Test
	 public void testFindAttendanceByStatus() throws RecordNotFoundException
	 {
		 AttendanceEntity attendance = new AttendanceEntity();
		 attendance.setAttendanceId(1);
		 attendance.setSemester("1");
		//attendance.setCurrentDate(null);
		 attendance.setStatus("Present");
		 
		 AttendanceEntity attendance1 = new AttendanceEntity();
		 attendance1.setAttendanceId(2);
		 attendance1.setSemester("1");
		 //attendance1.setCurrentDate(null);
		 attendance1.setStatus("Absent");
		 
		 List<AttendanceEntity> attendanceList=new ArrayList<>();
		 attendanceList.add(attendance);
		 attendanceList.add(attendance1);
		 
		 Mockito.when(AttendanceRepository.findByStatusIgnoreCase("Present")).thenReturn(attendanceList);
		 assertThat(AttendanceService.findAttendanceByStatus("Present")).isEqualTo(attendanceList);
	 }
	 
	
//-----------------------------------------------------------------Courses-----------------------------------------
	 @Autowired
	 CourseService courseService;
	 @MockBean
	 CourseRepository courseRepository;
	 
	 @Test
	 public void testAddCourse()
	 {
		 CourseEntity course = new CourseEntity();
		 course.setCourseId(1);
		 course.setCourseName("BE");
		 course.setDescription("Bechlore Of Engineering");
		 
		 Mockito.when(courseRepository.save(course)).thenReturn(course);
		 assertThat(courseService.addCourse(course)).isEqualTo(course);
	 }
	 
	 @Test
	 public void testGetCourse() throws RecordNotFoundException
	 {
		 CourseEntity course = new CourseEntity();
		 course.setCourseId(1);
		 course.setCourseName("BE");
		 course.setDescription("Bechlore Of Engineering");
		 
		 CourseEntity course1 = new CourseEntity();
		 course1.setCourseId(2);
		 course1.setCourseName("M.Tech");
		 course1.setDescription("Master Of Technology");
		 
		 List<CourseEntity> courseList=new ArrayList<>();
		 courseList.add(course);
		 courseList.add(course1);
		 
		 Mockito.when(courseRepository.findAll()).thenReturn(courseList);
		 assertThat(courseService.getCourse()).isEqualTo(courseList);
	 }
	 
	 @Test
	 public void testGetCourseById() throws CourseIdNotFoundException
	 {
		 CourseEntity course = new CourseEntity();
		 course.setCourseId(1);
		 course.setCourseName("BE");
		 course.setDescription("Bechlore Of Engineering");
		 
		 Optional<CourseEntity> course1=Optional.ofNullable(course);
		 Mockito.when(courseRepository.findById(1)).thenReturn(course1);
		 assertThat(courseService.getCourseById(1)).isEqualTo(course);
	 }
	@Test
	public void testUpdateByCourseId() throws CourseIdNotFoundException
	{
		 CourseEntity course = new CourseEntity();
		 course.setCourseId(1);
		 course.setCourseName("BE");
		 course.setDescription("Bechlore Of Engineering");
		 
		 Optional<CourseEntity> course1 =Optional.ofNullable(course);
		 Mockito.when(courseRepository.findById(1)).thenReturn(course1);
		 
		 course.setCourseName("CS");
		 course.setDescription("Branch of Computer Science");
		 
		 Mockito.when(courseRepository.save(course)).thenReturn(course);
		 assertThat(courseService.updateByCourseId(1, course)).isEqualTo(course);
		 
	}
	@Test
	public void testDeleteByCourseId()
	{
		 CourseEntity course = new CourseEntity();
		 course.setCourseId(1);
		 course.setCourseName("BE");
		 course.setDescription("Bechlore Of Engineering");
		 
		 Optional<CourseEntity> course1 =Optional.ofNullable(course);
		 Mockito.when(courseRepository.findById(1)).thenReturn(course1);
		 
		 Mockito.when(courseRepository.existsById(course.getCourseId())).thenReturn(false);	   
		    assertFalse(courseService.getCourseExistById(course.getCourseId()));
	}
	
	 @Test
	 public void testFindCourseByName() throws CourseIdNotFoundException, RecordNotFoundException
	 {
		 CourseEntity course = new CourseEntity();
		 course.setCourseId(1);
		 course.setCourseName("BE");
		 course.setDescription("Bechlore Of Engineering");
		 
		 CourseEntity course1 = new CourseEntity();
		 course1.setCourseId(2);
		 course1.setCourseName("BE");
		 course1.setDescription("Bechlore Of Engineering");
		 
		 List<CourseEntity> courseList =new ArrayList<>();
		 courseList.add(course);
		 courseList.add(course1);
		 
		 Mockito.when(courseRepository.findByCourseNameIgnoreCaseContains("BE")).thenReturn(courseList);
		 assertThat(courseService.findCourseByName("BE")).isEqualTo(courseList);
	 }
	
//--------------------------------------------------Faculty---------------------------------------	
	@Autowired
	private FacultyService facultyService;
	
	@MockBean
	private FacultyRepository facultyRepository;
	
	@Test
	public void testGetAddFaculty()
	{
		FacultyEntity faculty = new FacultyEntity();
		faculty.setFacultyId(1);
		faculty.setFacultyName("Madhulika");
	    
	    Mockito.when(facultyRepository.save(faculty)).thenReturn(faculty);
	    assertThat(facultyService.addFaculty(faculty)).isEqualTo(faculty);
	}

	@Test
	public void testGetFacultyById() throws FacultyIdNotFoundException
	{
		FacultyEntity faculty = new FacultyEntity();
		faculty.setFacultyId(1);
		faculty.setFacultyName("Madhulika");
		Optional<FacultyEntity> faculty1=Optional.ofNullable(faculty);
		
		 Mockito.when(facultyRepository.findById(1)).thenReturn(faculty1);
		    assertThat(facultyService.getFacultyById(1)).isEqualTo(faculty);
	}
	
	@Test
	public void testGetAllFaculty() throws RecordNotFoundException
	{
		FacultyEntity faculty = new FacultyEntity();
		faculty.setFacultyId(1);
		faculty.setFacultyName("Madhulika");
		
		FacultyEntity faculty1 = new FacultyEntity();
		faculty1.setFacultyId(2);
		faculty1.setFacultyName("Sanjay");
		
		List<FacultyEntity> facultyList = new ArrayList<>();
		facultyList.add(faculty);
		facultyList.add(faculty1);
		
	    Mockito.when(facultyRepository.findAll()).thenReturn(facultyList);
	    
	    assertThat(facultyService.getAllFaculty()).isEqualTo(facultyList);
		
	}
	
	@Test
	public void testUpdateFacultyById() throws FacultyIdNotFoundException
	{
		FacultyEntity faculty = new FacultyEntity();
		faculty.setFacultyId(1);
		faculty.setFacultyName("Madhulika");
		
		Optional<FacultyEntity> faculty1=Optional.ofNullable(faculty);
		
		Mockito.when(facultyRepository.findById(1)).thenReturn(faculty1);
		
		faculty.setFacultyName("Sanjay");
		Mockito.when(facultyRepository.save(faculty)).thenReturn(faculty);
		
		assertThat(facultyService.updateFacultyById(1, faculty)).isEqualTo(faculty);
		
	}
	
	@Test
	public void testDeleteFacultyById()
	{
		FacultyEntity faculty = new FacultyEntity();
		faculty.setFacultyId(1);
		faculty.setFacultyName("Madhulika");
		
		Optional<FacultyEntity> faculty1=Optional.ofNullable(faculty);
	    Mockito.when(facultyRepository.findById(1)).thenReturn(faculty1);
	    Mockito.when(facultyRepository.existsById(faculty.getFacultyId())).thenReturn(false);	   
	    assertFalse(facultyService.getFacultyExistById(faculty.getFacultyId()));
	}
	
	
	
	@Test
	public void testFindByfacultyName() throws RecordNotFoundException
	{
		FacultyEntity faculty = new FacultyEntity();
		faculty.setFacultyId(1);
		faculty.setFacultyName("Madhulika");
	
		
		FacultyEntity faculty1 = new FacultyEntity();
		faculty1.setFacultyId(1);
		faculty1.setFacultyName("Sanjay");
		
		List<FacultyEntity> facultyList = new ArrayList<>();
		facultyList.add(faculty);
		facultyList.add(faculty1);
		
		Mockito.when(facultyRepository.findByfacultyNameIgnoreCaseContains("Madhulika")).thenReturn(facultyList);
		  assertThat(facultyService.findByfacultyName("Madhulika")).isEqualTo(facultyList);
	}
	//-----------------------------------------------------------------Students---------------------------
	@Autowired
	private StudentService studentService;
	
	@MockBean
	private StudentRepository studentRepository;

	
	@Test
	public void testAddStudent()
	{
		StudentEntity std = new StudentEntity();
		std.setStudentId(1);
		std.setFirstName("Kamal");
		std.setLastName("Sahu");
		//std.setDateOfBirth("1998-07-21");
	
		//	ticket.setBookingDate(new Date());
		std.setGender("Male");
		std.setMobileNumber("8225864337");
		std.setEmailId("kamal@gmail.com");
		std.setSemester("2");
		std.setFatherEmailId("father@gmail.com");
		std.setFatherMobileNumber("8455445788");
		Mockito.when(studentRepository.save(std)).thenReturn(std);
	    assertThat(studentService.addStudent(std)).isEqualTo(std);
	    
	    
	}
	
	@Test
	public void testGetStudents() throws RecordNotFoundException{

		StudentEntity std = new StudentEntity();
		std.setStudentId(1);
		std.setFirstName("Kamal");
		std.setLastName("Sahu");
		//std.setDateOfBirth("1998-07-21");
		
		//ticket.setBookingDate(new Date());
		std.setGender("Male");
		std.setMobileNumber("8225864337");
		std.setEmailId("kamal@gmail.com");
		std.setSemester("2");
		std.setFatherEmailId("father@gmail.com");
		std.setFatherMobileNumber("8455445788");
		
		
		StudentEntity std1 = new StudentEntity();
		std1.setStudentId(2);
		std1.setFirstName("Kunal");
		std1.setLastName("Verma");
		//std.setDateOfBirth("1998-07-21");
		
		//	ticket.setBookingDate(new Date());
		std1.setGender("Male");
		std1.setMobileNumber("8225864338");
		std1.setEmailId("kunal@gmail.com");
		std1.setSemester("2");
		std1.setFatherEmailId("father1@gmail.com");
		std.setFatherMobileNumber("8455445787");
		
		List<StudentEntity> stdList = new ArrayList<>();
		stdList.add(std);
		stdList.add(std1);
		
	    Mockito.when(studentRepository.findAll()).thenReturn(stdList);
	    
	    assertThat(studentService.getStudents()).isEqualTo(stdList);
	
	}
	@Test
	public void getStudentByIdTest() throws StudentNotFoundException{
		StudentEntity std = new StudentEntity();
		std.setStudentId(1);
		std.setFirstName("Kamal");
		std.setLastName("Sahu");
		//std.setDateOfBirth(LocalDate.of(29,07,1998));
		
		std.setGender("Male");
		std.setMobileNumber("8225864337");
		std.setEmailId("kamal@gmail.com");
		std.setSemester("2");
		std.setFatherEmailId("father@gmail.com");
		std.setFatherMobileNumber("8455445788");
		Optional<StudentEntity> std1=Optional.ofNullable(std);
		
	    Mockito.when(studentRepository.findById(1)).thenReturn(std1);
	    assertThat(studentService.getStudentById(1)).isEqualTo(std);
	}
	

	
	@Test
	public void testUpdateStudent() throws StudentNotFoundException{
		StudentEntity std = new StudentEntity();
		std.setStudentId(1);
		std.setFirstName("Kamal");
		std.setLastName("Sahu");
		//std.setDateOfBirth("1998-07-21");
		//	ticket.setBookingDate(new Date());
		std.setGender("Male");
		std.setMobileNumber("8225864337");
		std.setEmailId("kamal@gmail.com");
		std.setSemester("2");
		std.setFatherEmailId("father@gmail.com");
		std.setFatherMobileNumber("8455445788");
		
		Optional<StudentEntity> std1=Optional.ofNullable(std);
		
		Mockito.when(studentRepository.findById(1)).thenReturn(std1);
		
		std.setEmailId("Kamal.56@gmail.com");
		std.setMobileNumber("6264255252");
		Mockito.when(studentRepository.save(std)).thenReturn(std);
		
		assertThat(studentService.updateStudent(1, std)).isEqualTo(std);
	}
	
	@Test
	public void testDeleteStudent(){
	StudentEntity std = new StudentEntity();
		std.setStudentId(2);
		std.setFirstName("Kunal");
		std.setLastName("Verma");
		//std.setDateOfBirth("1998-07-21");
		
		//	ticket.setBookingDate(new Date());
		std.setGender("Male");
		std.setMobileNumber("8225864338");
		std.setEmailId("kunal@gmail.com");
		std.setSemester("2");
		std.setFatherEmailId("father1@gmail.com");
		std.setFatherMobileNumber("8455445787");
		
	Optional<StudentEntity> std1=Optional.ofNullable(std);
    Mockito.when(studentRepository.findById(1)).thenReturn(std1);
    Mockito.when(studentRepository.existsById(std.getStudentId())).thenReturn(false);	   
    assertFalse(studentService.getStudentExistById(std.getStudentId()));
}	

	@Test
	public void testAddStudentwithCourse() throws CourseIdNotFoundException, DuplicateRecordException
	{

		 CourseEntity course = new CourseEntity();
		 course.setCourseId(1);
		 course.setCourseName("BE");
		 course.setDescription("Bechlore Of Engineering");
		 
		StudentEntity std = new StudentEntity();
		std.setStudentId(1);
		std.setFirstName("Kamal");
		std.setLastName("Sahu");
		
		//std.setDateOfBirth("1998-07-21");
	
		//	ticket.setBookingDate(new Date());
		std.setGender("Male");
		std.setMobileNumber("8225864337");
		std.setEmailId("kamal@gmail.com");
		std.setSemester("2");
		std.setFatherEmailId("father@gmail.com");
		std.setFatherMobileNumber("8455445788");
		std.setCourse(course);
		
		StudentEntity std1 = new StudentEntity();
		std1.setStudentId(2);
		std1.setFirstName("Ayush");
		std1.setLastName("Verma");
		
		//std1.setDateOfBirth("1998-07-21");
	
		//	ticket.setBookingDate(new Date());
		std1.setGender("Male");
		std1.setMobileNumber("8225894337");
		std1.setEmailId("Ayush@gmail.com");
		std1.setSemester("2");
		std1.setFatherEmailId("father1@gmail.com");
		std1.setFatherMobileNumber("8455445798");
		std1.setCourse(course);
		
		List<StudentEntity> studentList = new ArrayList<>();
		studentList.add(std);
		studentList.add(std1);
		
	Optional<CourseEntity> course1=Optional.ofNullable(course);
    Mockito.when(courseRepository.findById(1)).thenReturn(course1);
		
		Mockito.when(studentRepository.saveAll(studentList)).thenReturn(studentList);
	    assertThat(studentService.addStudentwithC(std,1)).isEqualTo(std);
	    
	    
	}
	@Test
	public void testFindStudentByFirstName() throws StudentNotFoundException{
		 
		StudentEntity std = new StudentEntity();
		std.setStudentId(1);
		std.setFirstName("Kamal");
		std.setLastName("Sahu");
		
		//std.setDateOfBirth("1998-07-21");
	
		//	ticket.setBookingDate(new Date());
		std.setGender("Male");
		std.setMobileNumber("8225864337");
		std.setEmailId("kamal@gmail.com");
		std.setSemester("2");
		std.setFatherEmailId("father@gmail.com");
		std.setFatherMobileNumber("8455445788");
		
		StudentEntity std1 = new StudentEntity();
		std1.setStudentId(2);
		std1.setFirstName("Ayush");
		std1.setLastName("Verma");
		
		//std1.setDateOfBirth("1998-07-21");
	
		//	ticket.setBookingDate(new Date());
		std1.setGender("Male");
		std1.setMobileNumber("8225894337");
		std1.setEmailId("Ayush@gmail.com");
		std1.setSemester("2");
		std1.setFatherEmailId("father1@gmail.com");
		std1.setFatherMobileNumber("8455445798");
		
		List<StudentEntity> studentList = new ArrayList<>();
		studentList.add(std);
		studentList.add(std1);
		
		
	    Mockito.when(studentRepository.findAllByFirstNameIgnoreCase("Kamal")).thenReturn(studentList);
	    assertThat(studentService.findStudentByFirstName("Kamal")).isEqualTo(studentList);
	}
	@Test
	public void testFindStudentByFirstNameAndLastName() throws StudentNotFoundException
	{
		 
		StudentEntity std = new StudentEntity();
		std.setStudentId(1);
		std.setFirstName("Kamal");
		std.setLastName("Sahu");
		
		//std.setDateOfBirth("1998-07-21");
	
		//	ticket.setBookingDate(new Date());
		std.setGender("Male");
		std.setMobileNumber("8225864337");
		std.setEmailId("kamal@gmail.com");
		std.setSemester("2");
		std.setFatherEmailId("father@gmail.com");
		std.setFatherMobileNumber("8455445788");
		
		StudentEntity std1 = new StudentEntity();
		std1.setStudentId(2);
		std1.setFirstName("Ayush");
		std1.setLastName("Verma");
		
		//std1.setDateOfBirth("1998-07-21");
	
		//	ticket.setBookingDate(new Date());
		std1.setGender("Male");
		std1.setMobileNumber("8225894337");
		std1.setEmailId("Ayush@gmail.com");
		std1.setSemester("2");
		std1.setFatherEmailId("father1@gmail.com");
		std1.setFatherMobileNumber("8455445798");
		
		List<StudentEntity> studentList = new ArrayList<>();
		studentList.add(std);
		studentList.add(std1);
		
	    Mockito.when(studentRepository.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCase("Ayush","Verma")).thenReturn(studentList);
	    assertThat(studentService.findStudentByFirstNameAndLastName("Ayush","Verma")).isEqualTo(studentList);
	}
	
	//------------------------------------------------------------Subject----------------------------------
	
	@Autowired
	 SubjectService subjectService;
	 @MockBean
	 SubjectRepository subjectRepository;
	 
	 @Test
	 public void testAddSubject() throws DuplicateRecordException
	 {
		 SubjectEntity subject = new SubjectEntity();
		subject.setSubjectId(1);
		subject.setSubjectName("Java");
		subject.setSubjectSemester("2");
		subject.setDescription("Java is Programming Language");
		 
		 Mockito.when(subjectRepository.save(subject)).thenReturn(subject);
		 assertThat(subjectService.addSubject(subject)).isEqualTo(subject);
	 }
	 
	 @Test
	 public void testGetAllSubjects() throws RecordNotFoundException 
	 {
		 SubjectEntity subject = new SubjectEntity();
			subject.setSubjectId(1);
			subject.setSubjectName("Java");
			subject.setSubjectSemester("2");
			subject.setDescription("Java is Programming Language");
		 
			SubjectEntity subject1 = new SubjectEntity();
			subject1.setSubjectId(2);
			subject1.setSubjectName("Pyhton");
			subject1.setSubjectSemester("2");
			subject1.setDescription("Pyhton is Programming Language");
		 
		 List<SubjectEntity> subjectList=new ArrayList<>();
		 subjectList.add(subject);
		 subjectList.add(subject1);
		 
		 Mockito.when(subjectRepository.findAll()).thenReturn(subjectList);
		 assertThat(subjectService.getAllSubjects()).isEqualTo(subjectList);
	 }
	 
	 @Test
	 public void testGetSubjectById() throws SubjectNotFoundException
	 {
		 SubjectEntity subject = new SubjectEntity();
			subject.setSubjectId(1);
			subject.setSubjectName("Java");
			subject.setSubjectSemester("2");
			subject.setDescription("Java is Programming Language");
		 
		 Optional<SubjectEntity> subject1=Optional.ofNullable(subject);
		 Mockito.when(subjectRepository.findById(1)).thenReturn(subject1);
		 assertThat(subjectService.getSubjectById(1)).isEqualTo(subject);
	 }
	@Test
	public void testUpdateSubjectById() throws SubjectNotFoundException
	{
		SubjectEntity subject = new SubjectEntity();
		subject.setSubjectId(1);
		subject.setSubjectName("Java");
		subject.setSubjectSemester("2");
		subject.setDescription("Java is Programming Language");
	 
		 Optional<SubjectEntity> subject1 =Optional.ofNullable(subject);
		 Mockito.when(subjectRepository.findById(1)).thenReturn(subject1);
		 
		 subject.setSubjectName("Pyhton");
		 
		 Mockito.when(subjectRepository.save(subject)).thenReturn(subject);
		 assertThat(subjectService.updateSubjectById(1, subject)).isEqualTo(subject);
		 
	}
	@Test
	public void testDeleteSubById()
	{
		SubjectEntity subject = new SubjectEntity();
		subject.setSubjectId(1);
		subject.setSubjectName("Java");
		subject.setSubjectSemester("2");
		subject.setDescription("Java is Programming Language");
		 
		 Optional<SubjectEntity> subject1 =Optional.ofNullable(subject);
		 Mockito.when(subjectRepository.findById(1)).thenReturn(subject1);
		 
		 Mockito.when(subjectRepository.existsById(subject.getSubjectId())).thenReturn(false);	   
		    assertFalse(subjectService.getSubjectExistById(subject.getSubjectId()));
	}
	
	 @Test
	 public void testFindSubjectByName() throws SubjectNotFoundException, RecordNotFoundException
	 {
		 SubjectEntity subject = new SubjectEntity();
			subject.setSubjectId(1);
			subject.setSubjectName("Java");
			subject.setSubjectSemester("2");
			subject.setDescription("Programming Language");
			
		SubjectEntity subject1 = new SubjectEntity();
			subject1.setSubjectId(2);
			subject1.setSubjectName("Python");
			subject1.setSubjectSemester("2");
			subject1.setDescription("Programming Language");
			
		List<SubjectEntity> subjectList = new ArrayList<>();
		subjectList.add(subject);
		subjectList.add(subject1);
		
		 Mockito.when(subjectRepository.findBysubjectNameIgnoreCaseContains("Java")).thenReturn(subjectList);
		 assertThat(subjectService.findSubjectByName("Java")).isEqualTo(subjectList);
	 }
	 
	 
	 @Test
	 public void testAddSubjectwithFC() throws DuplicateRecordException, FacultyIdNotFoundException, CourseIdNotFoundException
	 {
		 CourseEntity course = new CourseEntity();
		 course.setCourseId(1);
		 course.setCourseName("BE");
		 course.setDescription("Bechlore Of Engineering");
		 
		 FacultyEntity faculty = new FacultyEntity();
			faculty.setFacultyId(1);
			faculty.setFacultyName("Madhulika");
			
		 SubjectEntity subject = new SubjectEntity();
		subject.setSubjectId(1);
		subject.setSubjectName("Java");
		subject.setSubjectSemester("2");
		subject.setDescription("Programming Language");
		subject.setCourse(course);
		subject.setFacultyentity(faculty);
		 
		SubjectEntity subject1 = new SubjectEntity();
			subject1.setSubjectId(2);
			subject1.setSubjectName("Pyhton");
			subject1.setSubjectSemester("2");
			subject1.setDescription("Programming Language");
			subject1.setCourse(course);
			subject1.setFacultyentity(faculty);
			
		List<SubjectEntity> subjectList = new ArrayList<>();
		subjectList.add(subject);
		subjectList.add(subject1);
		
		Optional<CourseEntity> course1 =Optional.ofNullable(course);
		 Mockito.when(courseRepository.findById(1)).thenReturn(course1);
		 
		 Optional<FacultyEntity> faculty1 =Optional.ofNullable(faculty);
		 Mockito.when(facultyRepository.findById(1)).thenReturn(faculty1);
		
		 Mockito.when(subjectRepository.saveAll(subjectList)).thenReturn(subjectList);
		 assertThat(subjectService.addSubjectwithFC(subject,1,1)).isEqualTo(subject);
	 }
	 
	 @Test
	 public void testFindSubjectBySemester() throws SubjectNotFoundException, RecordNotFoundException
	 {
		 SubjectEntity subject = new SubjectEntity();
			subject.setSubjectId(1);
			subject.setSubjectName("Java");
			subject.setSubjectSemester("2");
			subject.setDescription("Programming Language");
			
			SubjectEntity subject1 = new SubjectEntity();
				subject1.setSubjectId(2);
				subject1.setSubjectName("Pyhton");
				subject1.setSubjectSemester("2");
				subject1.setDescription("Programming Language");
				
				
			List<SubjectEntity> subjectList = new ArrayList<>();
			subjectList.add(subject);
			subjectList.add(subject1);
		 
		 Mockito.when(subjectRepository.findBysubjectSemester("2")).thenReturn(subjectList);
		 assertThat(subjectService.findSubjectBySemester("2")).isEqualTo(subjectList);
	 }
	
		
}
