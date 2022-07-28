package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.Student;
import com.flipkart.bean.StudentCourseChoice;
import com.flipkart.database.dbConst;

public class StudentImpl implements StudentInterface {

	@Override
	public void addStudentdata(Student student) {
		//dbConst.students.add(student);
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Student> viewStudentData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student viewStudentDetails(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentCourseChoice selectCourses(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayCourseCatalog() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayGradeCard(int studentId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean studentAlreadyRegistered(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makePaymentSuccessful(int studentId, String referenceNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPaymentStatus(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isStudentRegistered(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSemesterRegistrationDone(int studentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGradeCardActivated(int studentId) {
		// TODO Auto-generated method stub
		return false;
	}

}
