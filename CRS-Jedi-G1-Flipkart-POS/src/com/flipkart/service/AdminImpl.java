package com.flipkart.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Challan;
import com.flipkart.bean.Course;
import com.flipkart.bean.PaymentReference;
import com.flipkart.bean.Professor;
import com.flipkart.bean.SemesterRegistration;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDaoImpl;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.database.dbConst;


public class AdminImpl implements AdminInterface {

	@Override
	public void activateGradeCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public String addProfessor(Professor professor) {
		//ProfessorImplementation.addProfessor data(professor);
		String profId = UtilityService.getId(3);
		professor.setProfessorId(profId);
		AdminDaoInterface admin = new AdminDaoImpl();
		try {
			admin.addProfessor(professor);
			System.out.println("Professor is successfully created");
			return profId;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void removeProfessor(String professorId) {
		AdminDaoInterface admin = new AdminDaoImpl();
		try {
			admin.deleteProfessor(professorId);
			System.out.println("Professor is succesfully removed");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateProfessor(String profID, String field, String value) {
		AdminDaoInterface admindao = new AdminDaoImpl();
		try {
			admindao.modifyProfessor(profID, field, value);
			System.out.println("Professor is updated");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	@Override
	public void approveStudentRegistration(String studentId) {
		AdminDaoInterface admindaoimpl = new AdminDaoImpl();
		admindaoimpl.approveStudent(studentId);
	}

	@Override
	public ArrayList<Student> viewAllStudents() {
		StudentInterface studentImplementation = StudentImpl.getInstance();
		ArrayList<Student> studentData = studentImplementation.viewStudentData();
		return studentData;

	}

	@Override
	public ArrayList<Professor> viewAllProfessors() {
		AdminDaoInterface admindaoimpl = new AdminDaoImpl();
		ArrayList<Professor> profList = admindaoimpl.getAllProfessorDetails();
		return profList;

	}

	@Override
	public ArrayList<Course> viewAllCourses() {
		AdminDaoInterface admindaoimpl = new AdminDaoImpl();
		 ArrayList<Course> clist = admindaoimpl.viewAllCourses();
		 return clist;

	}

	@Override
	public void allocatePendingCourses() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCourse(Course course) {
		AdminDaoInterface admin = new AdminDaoImpl();
		try {
			if(admin.addCourse(course)) {
				System.out.println("Course is successfully added");  
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public void removeCourse(String courseId) {
		AdminDaoInterface admin = new AdminDaoImpl();
		try {
			admin.deleteCourse(courseId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateCourse(Course course) {
		AdminDaoInterface admin = new AdminDaoImpl();
		try {
			if(admin.updateCourse(course)){
				System.out.println("Course is succesfully removed");
			}
			else {
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Challan generateChallan(SemesterRegistration semesterRegistration) {
		int fee = semesterRegistration.getTotalFee();
		int studentId = semesterRegistration.getStudentId();
		PaymentReference paymentReference = getPaymentReference(fee, studentId);
		int paymentReferenceNumber = paymentReference.getReferenceNo();
		Challan challan = new Challan();
		challan.setChallanNo((paymentReferenceNumber*265)%10000);
		challan.setPaymentReference(paymentReference);
		paymentsDaoImplementation.storeChallan(challan);
		return challan;
	}
	
	private PaymentReference getPaymentReference(int fee, int studentId) {
		PaymentReference paymentReference = new PaymentReference();
		StudentInterface studentImplementation = StudentImpl.getInstance();
		paymentReference.setPaymentStatus(1);
		paymentReference.setAmount(fee);
		paymentReference.setPayeeName(studentImplementation.viewStudentDetails(studentId).getName());
		int paymentReferenceNumber = paymentsDaoImplementation.storePaymentReference(paymentReference);
		paymentReference.setReferenceNo(paymentReferenceNumber);
		return paymentReference;
	}


}
