/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

/**
 * @author jain.harshil
 *
 */
public interface AdminInterface {
	public void addCourse(Course course);
	public void removeCourse(String courseId);
	public void updateCourse(String courseID, String field, String value);
	public void activateGradeCard(String studentID);
	public String addProfessor(Professor professor);
	public void removeProfessor(String professorId) ;
	public void updateProfessor(String profID, String field, String value);
	public void approveStudentRegistration(String studentId);
	public ArrayList<Student> viewAllStudents();
	public ArrayList<Professor> viewAllProfessors();
	public ArrayList<Course> viewAllCourses();
	public ArrayList<Course> viewStudentCourseChoice(String studentID);
	public void allocateStudentCourse(String studentID);
	public void viewPendingStudents();

	public int getPayStatus(String studentID);
	

}
