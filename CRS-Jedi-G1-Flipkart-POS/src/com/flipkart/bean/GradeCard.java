package com.flipkart.bean;

import com.flipkart.database.dbConst;

import java.util.HashMap;

public class GradeCard {

    private int studentId;
    private int semester;
    private float sgpa;
    private boolean isPublished;
	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * @return the isPublished
	 */
	public boolean isPublished() {
		return isPublished;
	}
	/**
	 * @param isPublished the isPublished to set
	 */
	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}
	/**
	 * @return the sgpa
	 */
	public float getSgpa() {
		return sgpa;
	}
	/**
	 * @param sgpa the sgpa to set
	 */
	public void setSgpa(float sgpa) {
		this.sgpa = sgpa;
	}
    
    



}
