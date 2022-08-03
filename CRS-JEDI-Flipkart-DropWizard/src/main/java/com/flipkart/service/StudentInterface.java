package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.Exceptions.GradeCardNotPublishedException;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.StudentCourseChoice;

public interface StudentInterface {

	
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
	 public Student viewStudentDetails(String studentId);
	 
	 /**
		 * Method for Selecting Courses
		 * @param StudentId
		 * @return StudentCourseChoice object
		 */
	 public StudentCourseChoice selectCourses(String studentId);
	 
	 /**
		 * Method to display course catalog
	*/
	 public void displayCourseCatalog();
	 
	 /**
		 * Method to display grade card
		 * @param StudentId
		 * @throws GradeCardNotPublishedException
	*/
	 public void displayGradeCard(String studentId) throws GradeCardNotPublishedException;
	 
	 /**
		 * Method for checking student registered or not
		 * @param StudentId
		 * @return boolean object
		 */
	 public Boolean studentAlreadyRegistered(String studentId);
	 
	 /**
		 * Method for making payment successful
		 * @param StudentId
		 * @param referenceNo
	*/
	 public void makePaymentSuccessful(String studentId);
	 
	 /**
		 * Method to get the payment status
		 * @param StudentId
		 * @param payment status 
	 */
	 public boolean getPaymentStatus(String studentId);
	 
	 /**
		 * Method for checking student registered or not
		 * @param StudentId
		 * @return boolean object
		 */
	 public Boolean isStudentRegistered(String studentId);

	public boolean isSemesterRegistrationDone(String studentId);

	public boolean isGradeCardActivated(String studentId);

	public  void viewNotificationList(String userId);

	public void viewNotification(String userId, int notifID);

	public void registerNewStudent();

	/**
	 * method to print list of students who are yet to be registered
	 */
	public void pendingList();

	/**
	 * method to get courses chosen by student
	 * @param studentId
	 */
	public ArrayList<Course> viewStudentCourseChoice(String studentId);

}
