/**
 * 
 */
package com.flipkart.bean;

/**
 * @author jain.harshil
 *
 */
public class Course {
	private String courseId;
	private String name = null;
	private String professorId;
	private float courseFee = -1;
	private int semester;
	private String dept;
	private int strength = 0;

	/**
	 * @return the courseId
	 */
	public String getCourseId() {
		return courseId;
	}
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the professorId
	 */
	public String getProfessorId() {
		return professorId;
	}
	/**
	 * @param professorId the professorId to set
	 */
	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}
	/**
	 * @return the courseFee
	 */
	public float getCourseFee() {
		return courseFee;
	}
	/**
	 * @param courseFee the courseFee to set
	 */
	public void setCourseFee(float courseFee) {
		this.courseFee = courseFee;
	}
	/**
	 * @return the catalogId
	 */
	/*public int getCatalogId() {
		return catalogId;
	}
	/**
	 * @param catalogId the catalogId to set

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}
	 */

	@Override
	public String toString() {
		return "Course Name: " + this.getName() + "\nCourse  id: " + this.getCourseId() + "\nCourse Fee: " + this.getCourseFee();
	}

	/**
	 * @return strength of the course
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * set strength of the course
	 * @param val
	 */
	public void setStrength(int val) {
		this.strength = val;
	}

	/**
	 * @return semester for which course is available
	 */
	public int getSemester() {
		return semester;
	}

	/**
	 * set semester for which course is available
	 * @param val
	 */
	public void setSemester(int val) {
		this.semester = val;
	}

	/**
	 * @return department that the course belongs to
	 */
	public String getDept() {
		return dept;
	}

	/**
	 * set department to which the course belongs
	 * @param str
	 */
	public void setDept(String str) {
		this.dept = str;
	}
}
