package com.flipkart.bean;

import com.flipkart.database.dbConst;

import java.util.ArrayList;
import java.util.HashMap;

public class GradeCard {

    private String studentId;
	private String studName;
    private int semester;
    private float sgpa;
    private boolean isPublished;
	private ArrayList<Grade> courseGrades;

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
	 * @return the name of the student
	 */
	public String getStudName() {
		return studName;
	}

	/**
	 * @param studName the name of the student to be set
	 */
	public void setStudName(String studName) {
		this.studName = studName;
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
		sgpa = 0;
		for(Grade grade: courseGrades) {
			sgpa += grade.getGrade();
		}
		sgpa = sgpa / 40 * 10;
		return sgpa;
	}

	/**
	 * @param sgpa the sgpa to set
	 */
	public void setSgpa(float sgpa) {
		this.sgpa = sgpa;
	}

	/**
	 * @return the list of grades with course name and id
	 */
	public ArrayList<Grade> getCourseGrades() {
		return courseGrades;
	}

	/**
	 * @param courseGrades set the list of course and grades
	 */
	public void setCourseGrades(ArrayList<Grade> courseGrades) {
		this.courseGrades = courseGrades;
	}
}
