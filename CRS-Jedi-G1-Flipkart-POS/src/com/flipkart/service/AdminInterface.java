/**
 * 
 */
package com.flipkart.service;

import com.flipkart.bean.Professor;

/**
 * @author jain.harshil
 *
 */
public interface AdminInterface {
	public void activateGradeCard();
	public void addProfessor(Professor professor);
	public void removeProfessor(int professorId) ;
	public void updateProfessor(Professor professor);
	public Boolean approveStudentRegistration(int studentId);
	public void viewAllStudents();
	public void viewAllProfessors();
	public void viewAllCourses();
	public void allocatePendingCourses();
	

}
