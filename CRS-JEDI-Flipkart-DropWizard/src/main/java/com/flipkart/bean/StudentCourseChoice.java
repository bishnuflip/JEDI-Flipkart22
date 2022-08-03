/**
 * 
 */
package com.flipkart.bean;

import javafx.util.Pair;

import java.util.ArrayList;

/**
 * @author jain.harshil
 *
 */
public class StudentCourseChoice {
	private String studentId;

	//pair consists of course ID and type of choice
	private ArrayList<Pair<String, Integer>> courses;
	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the courses
	 */
	public ArrayList<Pair<String, Integer>> getCourses() {
		return courses;
	}
	/**
	 * @param courses the courses to set
	 */
	public void setCourses(ArrayList<Pair<String, Integer>> courses) {
		this.courses = courses;
	}
}
