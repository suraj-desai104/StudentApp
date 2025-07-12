package com.management.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.management.model.Courses;
import com.management.service.CourseService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CoursesController {
	
	@Autowired
	private CourseService service;

	
	@GetMapping("/AllCourse")
	public String AllCourses(Model model,HttpSession session)
	{
		int tidd=(int)session.getAttribute("tid");
			if(tidd==0)
			{
			model.addAttribute("course", service.AllCourses());
				return "AllCourses";
			}
			else {
				model.addAttribute("course", service.PerticularTrainerCourses((int)session.getAttribute("tid")));
				return "MyCourses";
			}
	}
	

//	@GetMapping("/trainercourse")
//	public String AllTrainersCourses(Model model,HttpSession session)
//	{
//			model.addAttribute("course", service.PerticularTrainerCourses((int)session.getAttribute("tid")));
//				return "AllCourses";
//	}
//	
//	@GetMapping("/AllCourseUpcoming")
//	public String AllCoursesUpcoming(Model model)
//	{
//		String dateStr = LocalDate.now().toString();
//			model.addAttribute("course", service.ListByUpcomingCourses(dateStr));
//				return "AllCourses";
//	}
	
	
	
//	@GetMapping("/CourseBy/{courseName}")
//	public String CourseByName(Model model,@PathVariable String courseName)
//	{
//		model.addAttribute("course", service.GetByCourseName(courseName));
//		return "CourseByName";
//	}
	
	@GetMapping("/Addcourse")
	public String AddCourses(Model model)
	{
		Courses course=new Courses();
		model.addAttribute("course", course);
		return "AddCourse";
	}
	
	@PostMapping("/saveCourse")
	public String SaveCourse(@ModelAttribute("course")Courses course,HttpSession session,@RequestParam("img")MultipartFile img)
	{
		
		course.setImage(img.getOriginalFilename());
		course.setTrainerId((int)session.getAttribute("tid"));
		course.setTrainerName((String)session.getAttribute("tname"));
		Courses uploadImg= service.AddCourse(course);
		if(uploadImg!=null) {
			 try {
				
				 File saveFile=new ClassPathResource("static/img").getFile();
				 Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+img.getOriginalFilename());
				 System.out.println(path);
				 Files.copy(img.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "redirect:/AllCourse";
		}
		return "redirect:/AllCourse";
	}
	
	
	@GetMapping("/logout")
	public String SessionInvalidated(HttpSession session)
	{
		session.invalidate();
		return "NavBar";
	}
	
	
	@GetMapping("/deletecourse/{courseId}")
	public String DeleteCourse(@PathVariable Long courseId)
	{
		service.DeleteById(courseId);
		return "redirect:/AllCourse";
	}
	
	
	@GetMapping("/deletepreviousdate")
	public String DeleteBeforeTodayCourse(Model model,HttpSession session)
	{
//		Integer userId = (Integer) session.getAttribute("userId");
//	    if (userId == null) {
//	        // Redirect to login page if user is not in session
//	        return "redirect:/login";
//	    }
		model.addAttribute("course",service.showingBeforeStartDateCourses());
		return "AllDeprecatedCourses";
	}
	
	@GetMapping("/CallByCourseNames")
	public String CourseByNames(@RequestParam("courseName") String courseName,Model model)
	{
		model.addAttribute("course", service.GetByCourseName(courseName));
		return "CoursesList";
	}
	
	//update Course
	
	@GetMapping("/getbyafterdate")
	public String CoursesListAfterToday(Model model)
	{
		String dateStr = LocalDate.now().toString();
		model.addAttribute("course", service.ListByUpcomingCourses(dateStr));
		return "CoursesList";
	}
	
	
	@GetMapping("/getbyafterdateforadmin")
	public String CoursesListAfterTodayForAdmin(Model model)
	{
		String dateStr = LocalDate.now().toString();
		model.addAttribute("course", service.ListByUpcomingCourses(dateStr));
		return "AllCourses";
	}
	
	
	
}
