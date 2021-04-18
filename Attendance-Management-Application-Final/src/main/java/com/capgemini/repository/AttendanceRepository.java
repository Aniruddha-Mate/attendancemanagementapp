package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.entity.AttendanceEntity;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity,Integer>{

 
   // AttendanceEntity findByAttendanceId(int attendanceId);
    List<AttendanceEntity> findByStatusIgnoreCase(String status);
	
	List<AttendanceEntity> findBySemester(String semester);
}
