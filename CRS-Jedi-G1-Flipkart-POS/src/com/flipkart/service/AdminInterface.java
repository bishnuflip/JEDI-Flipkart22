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
	public void removeCourse(int courseId);
	public void updateCourse(Course course);
	public void activateGradeCard();
	public int addProfessor(Professor professor);
	public void removeProfessor(int professorId) ;
	public void updateProfessor(Professor professor);
	public Boolean approveStudentRegistration(int studentId);
	public ArrayList<Student> viewAllStudents();
	public ArrayList<Professor> viewAllProfessors();
	public ArrayList<Course> viewAllCourses();
	public void allocatePendingCourses();
	

}
