package com.flipkart.service;

import java.util.List;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

public interface ProfessorInterface {
	
	/**
	 * Method to view all courses
	 */
	public List<Course> viewAllCourses();
	
	/**
	 * Method to view list of enrolled Students
	 */
	public List<Student> viewEnrolledStudents(int professorId);
	
	/**
	 * Method to get Courses by Professor Id
	 * @param professorId the id of professor
	 * @param courseId the id of course
	 * @return boolean data whether offered to the professor or not.
	 */
	public boolean selectCourse(int professorId, int courseId) ;
	
	/**
	 * Assign grades to student
	 * @param studentId the student id
	 * @param courseId the course id
     * @param grade the grade to be assigned
	 */
	public void assignGrade(int studentId, int courseId, int semesterNumber,int grade);
	
}
