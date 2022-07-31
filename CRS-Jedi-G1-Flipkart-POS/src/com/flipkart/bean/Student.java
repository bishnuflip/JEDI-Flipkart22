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
	private int payStatus;
	private int regStatus;

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

	/**
	 * @return int indicating status of pay
	 */
	public int getPayStatus() {
		return payStatus;
	}

	/**
	 * @param status the pay status of the student to be set
	 */
	public void setPayStatus(int status) {
		this.payStatus = status;
	}

	/**
	 * @return int indicating registration status
	 */
	public int getRegStatus() {
		return regStatus;
	}

	/**
	 * @param status the registration status to be set
	 */
	public void setRegStatus(int status) {
		this.regStatus = status;
	}

}
