package com.management.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.management.model.BookCourse;
import com.management.model.Courses;
import com.management.model.User;
import com.management.service.BookService;
import com.management.service.CourseService;
import com.management.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {

	@Autowired
	private BookService bservice;
	
	@Autowired
	private UserService uservice;
	
	@Autowired
	private CourseService cservices;
	
	
	
	@GetMapping("/bookedcourses")
	public String ListOFAllBookedCourses(Model model)
	{
		model.addAttribute("booked", bservice.AllBookedList());
		
		return "AllBookedList";
	}
	
//	 private Long id;
//
//	    private String studentName;+
//	    private String email;+
//	    private String mobile;+
//
//	    private Long courseId;+
//	    private String courseName;+
//	    private String trainerName;+
//
//	    private String bookingDate;+
//	    private String status;
//	   // private Double fees;+
//
//	    private String startDate;+
//	    private String endDate;+
	@GetMapping("/bookcourse/{courseId}")
	public String AddBookCourse(HttpSession session,@PathVariable Long courseId,Model model )  //courseId
	{
		//model.addAttribute("book",uservice.FindById((int)session.getAttribute("uid")));
		model.addAttribute("books", cservices.GetByIDCourse(courseId));
		model.addAttribute("bookdate", LocalDate.now()+" ");
		model.addAttribute("status","Unpaid");
		return "AddBook";
	}
	
	@PostMapping("/savebook/{courseId}")
	public String  SaveBookCourse(Model model,HttpSession session,BookCourse bookcourse,@PathVariable Long courseId)
	{
		
		
		 BookCourse existing = bservice.AvoidDuplicates(
				 bookcourse.getStudId(),
				 bookcourse.getCourseId());
		 
		 if(existing != null)
		 {
			 System.out.println("This Student is Already Booked her Course");
		 }
		
		 try {
		
		User user=uservice.FindById((int)session.getAttribute("uid"));
		bookcourse.setStudentName(user.getFull_name());
		bookcourse.setEmail(user.getEmail());
		bookcourse.setMobile(user.getMobile());
		Courses course=cservices.GetByIDCourse(courseId);   //course id
		session.setAttribute("courseID", courseId);     //Create Session CourseId
		bookcourse.setCourseId(courseId);
		bookcourse.setCourseName(course.getCourseName());
		bookcourse.setTrainerName(course.getTrainerName());
		bookcourse.setStartDate(course.getStartDate());
		bookcourse.setEndDate(course.getEndDate());
		bookcourse.setBookingDate(LocalDate.now()+" ");
		bookcourse.setDuration(course.getDuration());
		bookcourse.setStudId((int)session.getAttribute("uid"));
		//bookcourse.setFees("Paid");
		System.out.println(session.getAttribute("courseID"));
		bservice.SaveCourses(bookcourse);
		//model.addAttribute("bookcourse", bookcourse);
		return "Payment";
		 }
		 catch(Exception e)
		 {
			 String dateStr = LocalDate.now().toString();
				model.addAttribute("course", cservices.ListByUpcomingCourses(dateStr));
				return "CoursesList"; 
		 }
		
	}
	
	@GetMapping("/reportByid")
	public String ReposrtById(Model model,HttpSession session)
	{
	//	System.out.println(courseId);
		try {
		model.addAttribute("books",  bservice.AvoidDuplicates((int)session.getAttribute("uid"), (Long)session.getAttribute("courseID")));
//		int a=1;
//		long b=3;
//		model.addAttribute("books", bservice.AvoidDuplicates(a,b));
		return "Report";
		}
		catch(Exception e)
		{
			return "Login";   //write logic here
		}
	}
	
	
	
	
	
}
