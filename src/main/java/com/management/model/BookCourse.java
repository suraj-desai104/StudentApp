package com.management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="book_seat")


public class BookCourse {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private int studid;
	    private String studentName;
	    private String email;
	    private String mobile;

	    private Long courseId;
	    private String courseName;
	    private String trainerName;
	    private String Duration;
	    private String bookingDate;
	    private String status;
	    private Double fees;

	    private String startDate;
	    private String endDate;
		public BookCourse() {
			super();
			// TODO Auto-generated constructor stub
		}
		public BookCourse(Long id,int studid,  String duration,String studentName, String email, String mobile, Long courseId, String courseName,
				String trainerName, String bookingDate, String status,Double fees, String startDate, String endDate) {
			super();
			this.id = id;
			this.studid=studid;
			this.studentName = studentName;
			this.email = email;
			this.mobile = mobile;
			Duration = duration;
			this.courseId = courseId;
			this.courseName = courseName;
			this.trainerName = trainerName;
			this.bookingDate = bookingDate;
			this.status = status;
			this.fees = fees;
			this.startDate = startDate;
			this.endDate = endDate;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		public int getStudId() {
			return studid;
		}
		public void setStudId(int studid) {
			this.studid = studid;
		}
		public String getDuration() {
			return Duration;
		}
		public void setDuration(String duration) {
			Duration = duration;
		}
		public String getStudentName() {
			return studentName;
		}
		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public Long getCourseId() {
			return courseId;
		}
		public void setCourseId(Long courseId) {
			this.courseId = courseId;
		}
		public String getCourseName() {
			return courseName;
		}
		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}
		public String getTrainerName() {
			return trainerName;
		}
		public void setTrainerName(String trainerName) {
			this.trainerName = trainerName;
		}
		public String getBookingDate() {
			return bookingDate;
		}
		public void setBookingDate(String bookingDate) {
			this.bookingDate = bookingDate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Double getFees() {
			return fees;
		}
		public void setFees(Double fees) {
			this.fees = fees;
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

	    
}



