package com.flipkart.service;


import java.util.HashMap;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.database.dbConst;


public class AdminImpl implements AdminInterface {

	@Override
	public void activateGradeCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addProfessor(Professor professor) {
		int id = UtilityService.getId();
		professor.setProfessorId(id);
		AuthorizationService.addUser(id, professor);
		dbConst.professors.put(id, professor);
		// TODO Auto-generated method stub

	}

	@Override
	public void removeProfessor(int professorId) {
		AuthorizationService.removeUser(professorId);
		dbConst.professors.remove(professorId);
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProfessor(Professor professor) {
		dbConst.professors.put(professor.getProfessorId(), professor);
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean approveStudentRegistration(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void viewAllStudents() {
		// TODO Auto-generated method stub
		for(int key:dbConst.students.keySet()) {
			System.out.println(dbConst.students.get(key).getName());
		}

	}

	@Override
	public void viewAllProfessors() {
		for(int key:dbConst.professors.keySet()) {
			System.out.println(dbConst.professors.get(key));
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void viewAllCourses() {
		for(int key:dbConst.courses.keySet()) {
			System.out.println(dbConst.courses.get(key).getName());
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void allocatePendingCourses() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCourse(Course course) {
		dbConst.courses.put(course.getCourseId(),course);
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void removeCourse(int courseId) {
		dbConst.courses.remove(courseId);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCourse(Course course) {
		dbConst.courses.replace(course.getCourseId(), course);
		// TODO Auto-generated method stub
		
	}

}
