package com.management.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.management.model.Courses;
import com.management.model.User;
import com.management.service.CourseService;
import com.management.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private CourseService services;
	
	@GetMapping("/adduser")
	public String AddUser(Model model)
	{
		User user=new User();
		model.addAttribute("user", user);
		return "AddUser";
	}
	
	
	String us;
	String ps;
	@PostMapping("/saveuser")
	public String SaveUser(@ModelAttribute("user") User user,@RequestParam("full_name")String full_name,@RequestParam("password") String password)
	{
		try {
			
			List<User> u1=service.GetAll();
			
			for(User u2:u1)
			{
				if(full_name.equals(u2.getFull_name()) && password.equals(u2.getPassword()))
				{
					us=u2.getFull_name();
					ps=u2.getPassword();
				}
			}
			
			if(full_name.equals(us) && password.equals(ps))
			{
				return "AddUser";
			}
			else {
				service.Save(user);
				return "redirect:/login";
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return "";
		
	}
	
	
	
	@GetMapping("/login")
	public String Login()
	{
		return "Login";
	}
	
	@GetMapping("/listBystudent/{role}")
	public String ListByUsersOnly(@PathVariable String role,Model model)
	{ 
		String roles="STUDENT";
		if(roles.equals(role))
		{
		model.addAttribute("role", service.ByUsingRole(role));
		model.addAttribute("roles","List Of Student ");
		return "AllRoles";
		}
		else {
			model.addAttribute("role", service.ByUsingRole(role));
			model.addAttribute("roles","List Of Trainers");
			return "AllRoles";
		}
	}
	

	
	
	
	String name;
	String pass;
	int userid;
	String profession;
	@PostMapping("/valid")
	public String ValidUSer(Model model,@RequestParam("full_name") String full_name,@RequestParam("password") String password,HttpSession session)
	{
		
		
		List<User> user=service.GetAll();
		
		for(User u1:user)
		{
			if(full_name.equals(u1.getFull_name()) && password.equals(u1.getPassword()))
			{
				name=u1.getFull_name();
				pass=u1.getPassword();
				userid=u1.getId();
				profession=u1.getRole();
			}
		}
		
		if(full_name.equals(name) && password.equals(pass))
		{
			if(profession.equals("STUDENT"))
			{
				session.setAttribute("uid", userid);
				System.out.println("session is in UserDashBoard");
				System.out.println(session.getAttribute("uid"));
				String dateStr = LocalDate.now().toString();
				model.addAttribute("course", services.ListByUpcomingCourses(dateStr));
				return "CoursesList";
			}else {
				session.setAttribute("tid", userid);
				session.setAttribute("tname", name);
				System.out.println("session is in  Trainer DashBoard");
				System.out.println(session.getAttribute("tid"));
				//model.addAttribute("course", services.AllCourses());
				Courses course=new Courses();
				model.addAttribute("course", course);
				return "Addcourse";
			}
		}
		else if(full_name.equals("root") && password.equals("shriram")) 
		{
			System.out.println("session is in Admin DashBoard");
			session.setAttribute("aid", 0);
			System.out.println(session.getAttribute("aid"));
			return "AdminDashBoard";
		}
		else {
			return "Login";
		}
	}
	
	
	@GetMapping("/navbar")
	public String Navbarpage()
	{
		return "NavBar";
	}
	
	@GetMapping("/contact")
	public String ContactPage()
	{
		return "Contact";
	}
	
	@GetMapping("/aboutus")
	public String AboutPage()
	{
		return "AboutUs";
	}
	
	
	

}
