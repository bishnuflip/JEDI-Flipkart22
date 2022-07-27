package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.Student;
import com.flipkart.bean.StudentCourseChoice;

public interface StudentInterface {
	
public void addStudentdata(Student student);
	
	/**
	 * Method to view all student details
	 * @return list of student objects 
	 */
	 public ArrayList<Student> viewStudentData();
	 
	 /**
		 * Method to view  student details
		 * @param StudentId
		 * @return student object 
		 */
	 public Student viewStudentDetails(int studentId);
	 
	 /**
		 * Method for Selecting Courses
		 * @param StudentId
		 * @return StudentCourseChoice object
		 */
	 public StudentCourseChoice selectCourses(int studentId);
	 
	 /**
		 * Method to display course catalog
	*/
	 public void displayCourseCatalog();
	 
	 /**
		 * Method to display grade card
		 * @param StudentId
		 * @throws GradeCardNotPublishedException
	*/
	 public void displayGradeCard(int studentId);
	 
	 /**
		 * Method for checking student registered or not
		 * @param StudentId
		 * @return boolean object
		 */
	 public Boolean studentAlreadyRegistered(int studentId);
	 
	 /**
		 * Method for making payment successful
		 * @param StudentId
		 * @param referenceNo
	*/
	 public void makePaymentSuccessful(int studentId, String referenceNo);
	 
	 /**
		 * Method to get the payment status
		 * @param StudentId
		 * @param payment status 
	 */
	 public String getPaymentStatus(int studentId);
	 
	 /**
		 * Method for checking student registered or not
		 * @param StudentId
		 * @return boolean object
		 */
	 public Boolean isStudentRegistered(int studentId);

	public boolean isSemesterRegistrationDone(int studentId);

	public boolean isGradeCardActivated(int studentId);
}
