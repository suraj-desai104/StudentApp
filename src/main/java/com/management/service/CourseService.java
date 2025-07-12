package com.management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.model.Courses;
import com.management.repository.CourseRepository;

@Service
public class CourseService {

	
	@Autowired
	private CourseRepository repository;
	
	
	public List<Courses> AllCourses()
	{
		return repository.findAll();
	}
	
	
	public Courses AddCourse(Courses course)
	{
		return repository.save(course);
	}
	
	public Courses GetByIDCourse(Long courseId)
	{
		return repository.findById(courseId).orElseThrow();
	}
	
	
	//courses By perticular Trainer
	public List<Courses> PerticularTrainerCourses(Integer trainerId)
	{
		return repository.showbytrainersId(trainerId);
	}
	
	//for Course Name
	public List<Courses> GetByCourseName(String courseName)
	{
		return repository.CoursesByName(courseName);
	}
	
	public void DeleteById(Long courseId)
	{
		repository.deleteById(courseId);
	} 
	
	
	public List<Courses> showingBeforeStartDateCourses()
	{
		LocalDate date=LocalDate.now();
		String today=date.toString();
		return repository.showBeforetodaycourses(today);
		
	}
	
	
	public List<Courses> ListByUpcomingCourses(String Current_date)
	{
		return repository.findUpcomingCourses(Current_date);
	}
	
}
