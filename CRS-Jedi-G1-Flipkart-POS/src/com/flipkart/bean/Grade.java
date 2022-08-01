/**
 * 
 */
package com.flipkart.bean;

/**
 * @author jain.harshil
 *
 */
public class Grade {

	private String courseId;
	private String courseName;
	private float grade;

	/**
	 * @return the course
	 */
	public String getCourseId() {
		return courseId;
	}

	/**
	 * @param cId the course to set
	 */
	public void setCourseId(String cId) {
		this.courseId = cId;
	}

	/**
	 * @return the name of the course
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName the name of the course
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the grade
	 */
	public float getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(float grade) {
		this.grade = grade;
	}

}
