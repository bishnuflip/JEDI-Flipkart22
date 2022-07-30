/**
 * 
 */
package com.flipkart.bean;

/**
 * @author jain.harshil
 *
 */
public class Student extends User{
	private String studentId;
	private int semester;

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
	 * @return semester of student
	 */
	public int getSemester() {
		return semester;
	}

	/**
	 * @param sem the semester of the student
	 */
	public void setSemester(int sem) {
		this.semester = sem;
	}

}
