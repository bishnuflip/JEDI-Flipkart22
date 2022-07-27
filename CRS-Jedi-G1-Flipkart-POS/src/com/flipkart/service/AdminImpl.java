package com.flipkart.service;


import java.util.HashMap;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;


public class AdminImpl implements AdminInterface {
	HashMap<Integer,Professor> professors = new HashMap<Integer,Professor>();
	HashMap<Integer,Student> students = new HashMap<Integer,Student>();
	@Override
	public void activateGradeCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProfessor(Professor professor) {
		int id = UtilityService.getId();
		professor.setProfessorId(id);
		AuthorizationService.addUser(id, professor);
		professors.put(id, professor);
		// TODO Auto-generated method stub

	}

	@Override
	public void removeProfessor(int professorId) {
		AuthorizationService.removeUser(professorId);
		professors.remove(professorId);
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProfessor(Professor professor) {
		professors.put(professor.getProfessorId(), professor);
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
		for(int key:students.keySet()) {
			System.out.println(students.get(key).getName());
		}

	}

	@Override
	public void viewAllProfessors() {
		for(int key:professors.keySet()) {
			System.out.println(professors.get(key));
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void viewAllCourses() {
		// TODO Auto-generated method stub

	}

	@Override
	public void allocatePendingCourses() {
		// TODO Auto-generated method stub

	}

}
