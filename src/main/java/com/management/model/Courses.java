package com.management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="courses_info")
public class Courses {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long courseId;
	private String courseName;
	private String Description;
	private String Duration;
	private double fees;
	private String startDate;
	private String endDate;
	private String image;
	private int trainerId;
	private String TrainerName;
	public Courses() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Courses(long courseId, String courseName, String description, String duration,double fees, String startDate,
			String endDate,String image, int trainerId, String trainerName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		Description = description;
		Duration = duration;
		this.fees=fees;
		this.startDate = startDate;
		this.endDate = endDate;
		this.image=image;
		this.trainerId = trainerId;
		TrainerName = trainerName;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	public double getFees()
	{
		return fees;
	}
	
	public void setFees(double fees)
	{
		this.fees=fees;
	}
	public String getDuration() {
		return Duration;
	}
	public void setDuration(String duration) {
		Duration = duration;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getImage()
	{
		return image;
	}
	
	public void setImage(String image)
	{
		this.image=image;
	}
	public int getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}
	public String getTrainerName() {
		return TrainerName;
	}
	public void setTrainerName(String trainerName) {
		TrainerName = trainerName;
	}
	
	
	


}
