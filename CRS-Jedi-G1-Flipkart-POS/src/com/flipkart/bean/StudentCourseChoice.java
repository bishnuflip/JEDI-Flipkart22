/**
 * 
 */
package com.flipkart.bean;

import java.util.ArrayList;

/**
 * @author jain.harshil
 *
 */
public class StudentCourseChoice {
	private int studentId;
	private ArrayList<Integer> courses;
	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the courses
	 */
	public ArrayList<Integer> getCourses() {
		return courses;
	}
	/**
	 * @param courses the courses to set
	 */
	public void setCourses(ArrayList<Integer> courses) {
		this.courses = courses;
	}
}
