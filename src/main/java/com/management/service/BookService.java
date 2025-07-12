package com.management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.model.BookCourse;
import com.management.model.Courses;
import com.management.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;

	
	public List<BookCourse> AllBookedList()
	{
		return repository.findAll();
	}
	
	public BookCourse SaveCourses(BookCourse book_course)
	{
		return repository.save(book_course);
	}
	
	public BookCourse GetByIDBookedCourse(Long id)
	{
		return repository.findById(id).orElseThrow();
	}
	
	
	public BookCourse RepostByStudentId(Integer id)
	{
		return repository.getByStudentID(id);
	}
	
	
	public void DeleteByIdBookedCourse(Long id)
	{
		repository.deleteById(id);
	}

	public BookCourse AvoidDuplicates(Integer studid,Long courseId)
	{
		return repository.findByStudidAndCourseId(studid, courseId);
	}
	
}
