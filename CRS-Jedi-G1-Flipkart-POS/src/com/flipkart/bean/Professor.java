/**
 * 
 */
package com.flipkart.bean;

/**
 * @author jain.harshil
 *
 */
public class Professor extends User{
	private int professorId;

	/**
	 * @return the professorId
	 */
	public int getProfessorId() {
		return professorId;
	}

	/**
	 * @param professorId the professorId to set
	 */
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	
	public String toString() {
		return getName();
	}
	
}
