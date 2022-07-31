/**
 * 
 */
package com.flipkart.bean;

/**
 * @author jain.harshil
 *
 */
public class Professor extends User{
	private String professorId;
	private String dept;
	private String position;

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
	 * @return the department of the professor
	 */
	public String getDept() {
		return dept;
	}

	/**
	 * @param d the department to set
	 */
	public void setDept(String d) {
		this.dept = d;
	}

	/**
	 * @return the position of the professor
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param pos the position of the professor to be set
	 */
	public void setPosition(String pos) {
		this.position = pos;
	}

	public String toString() {
		return getName();
	}
	
}
