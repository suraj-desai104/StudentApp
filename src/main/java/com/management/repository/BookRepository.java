package com.management.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.management.model.BookCourse;
import com.management.model.Courses;

@Repository
public interface BookRepository extends JpaRepository<BookCourse, Long> {

	
	@Query("select c from BookCourse c where c.studid=?1")
	public BookCourse getByStudentID(Integer id);
	
	
	@Query("SELECT c from BookCourse c WHERE c.studid=?1 AND c.courseId=?2")
	public BookCourse findByStudidAndCourseId(Integer studid,Long courseId);
	
	
	

	

}
