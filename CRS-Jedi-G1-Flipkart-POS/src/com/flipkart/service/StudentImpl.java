package com.flipkart.service;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Student;
import com.flipkart.bean.StudentCourseChoice;
import com.flipkart.database.dbConst;

public class StudentImpl implements StudentInterface {

	@Override
	public void addStudentdata(Student student) {
  
		int id = UtilityService.getId();
		student.setStudentId(id);
		AuthorizationService.addUser(id, student);

		dbConst.students.put(id, student);

		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Student> viewStudentData() {
		List<Student> students = new ArrayList<Student>();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student viewStudentDetails(int studentId) {
		return dbConst.students.get(studentId);
	}

	@Override
	public StudentCourseChoice selectCourses(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayCourseCatalog() {
		for(int key: dbConst.courses.keySet()) {
			dbConst.courses.get(key).toString();
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void displayGradeCard(int studentId) {
		//Student student = dbConst.students.get(studentId);
		GradeCard gradeCard = new GradeCard();
		gradeCard.viewGradeCard(studentId);
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean studentAlreadyRegistered(int studentId) {
		if(dbConst.students.containsKey(studentId))
			return true;
		else
			return false;
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
