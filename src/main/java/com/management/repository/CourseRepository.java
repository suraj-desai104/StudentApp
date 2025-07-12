package com.management.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.management.model.Courses;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long>{
	
	
	@Query("select c from Courses c where c.courseName=?1")
	public List<Courses> CoursesByName(String courseName);
	
	@Query("select c from Courses c where c.startDate >= ?1")
	public List<Courses> findUpcomingCourses(String Current_date);
	
	
	@Query("select c from Courses c WHERE c.startDate <?1")
	public List<Courses> showBeforetodaycourses(String today);

	@Query("select c from Courses c where c.trainerId=?1")
	public List<Courses> showbytrainersId(Integer trainerId);
}
