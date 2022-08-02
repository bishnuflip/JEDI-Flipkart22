package com.flipkart.service;


import java.util.*;

import com.flipkart.Exceptions.CourseNotFoundException;
import com.flipkart.Exceptions.ProfessorNotFoundException;
import com.flipkart.Exceptions.UserNotApprovedException;
import com.flipkart.bean.*;
import com.flipkart.dao.*;
import com.flipkart.database.dbConst;
import javafx.util.Pair;


public class AdminImpl implements AdminInterface {

	@Override
	public void activateGradeCard(String studentID) {
		GradeCardDaoInterface gradeCard = new GradeCardDaoImpl();
		gradeCard.activateGradeCard(studentID);
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
	public void removeProfessor(String professorId) throws ProfessorNotFoundException {
		AdminDaoInterface admin = new AdminDaoImpl();
		try {
			admin.deleteProfessor(professorId);
			System.out.println("Professor is succesfully removed");

		} 
		catch(ProfessorNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
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
		try {
			admindaoimpl.approveStudent(studentId);
		} catch (UserNotApprovedException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Student> viewAllStudents() {
		StudentInterface studentImplementation = new StudentImpl();
		ArrayList<Student> studentData = studentImplementation.viewStudentData();
		return studentData;

	}

	@Override
	public ArrayList<Professor> viewAllProfessors() {
		AdminDaoInterface admindaoimpl = new AdminDaoImpl();
		ArrayList<Professor> profList = admindaoimpl.viewProfs();
		return profList;

	}

	@Override
	public ArrayList<Course> viewAllCourses() {
		CatalogDaoInterface catalogdaoimpl = new CatalogDaoImpl();
		 ArrayList<Course> clist = catalogdaoimpl.viewCatalog(1);
		 return clist;

	}

	@Override
	public ArrayList<Course> viewStudentCourseChoice(String studentID) {
		StudentDaoInterafce student = new StudentDaoImpl();
		ArrayList<Course> studChoice = student.viewSelectedCourses(studentID);
		return studChoice;
	}


	@Override
	public void allocateStudentCourse(String studentID) {
		PaymentDaoInterface payment = new PaymentDaoImpl();
		if(payment.getPaymentStatus(studentID)) {
			System.out.println("Payment for courses pending. Allocation failed");
			return;
		}
		StudentDaoInterafce studentDao = new StudentDaoImpl();
		AdminDaoInterface adminDao = new AdminDaoImpl();
		ArrayList<Course> choiceList = studentDao.viewSelectedCourses(studentID);
		for(Course crs: choiceList) {
			adminDao.allotCourseStudent(studentID, crs.getCourseId());
		}
	}

	@Override
	public void viewPendingStudents() {
		StudentDaoInterafce students = new StudentDaoImpl();
		ArrayList<Student> studList = students.getPengingCourseAllotmentList();
		for(Student stud : studList) {
			System.out.println(stud.getStudentId() + "\t" + stud.getName());
		}
	}

	@Override
	public int getPayStatus(String studentID) {
		PaymentDaoInterface pay = new PaymentDaoImpl();
		if(pay.getPaymentStatus(studentID)) {
			return 1;
		}
		return 0;
	}

	@Override
	public void addCourse(Course course) {
		AdminDaoInterface admin = new AdminDaoImpl();
		admin.addCourse(course);
	}

	@Override
	public void removeCourse(String courseId) throws CourseNotFoundException {
		AdminDaoInterface admin = new AdminDaoImpl();
		try {
			admin.deleteCourse(courseId);
		}
		catch(CourseNotFoundException e) {
			System.out.println(e);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

//	@Override
//	public void updateCourse(Course course) {
////		AdminDaoInterface admin = new AdminDaoImpl();
////		if(admin.modifyCourse(course)){
////				System.out.println("Course is succesfully removed");
////			}
////			else {
////				
////			}
////		}
////		catch(Exception e) {
////			e.printStackTrace();
////		}
//		
//	}
	
/*	public Challan generateChallan(SemesterRegistration semesterRegistration) {
		int fee = semesterRegistration.getTotalFee();
		int studentId = semesterRegistration.getStudentId();
		PaymentReference paymentReference = getPaymentReference(fee, studentId);
		int paymentReferenceNumber = paymentReference.getReferenceNo();
		Challan challan = new Challan();
		challan.setChallanNo((paymentReferenceNumber*265)%10000);
		challan.setPaymentReference(paymentReference);
		PaymentDaoInterface paymentsDaoImplementation = new PaymentDaoImpl();
		paymentsDaoImplementation.storeChallan(challan);
		return challan;
	}
*/
	private PaymentReference getPaymentReference(float fee, String studentId) {
		PaymentReference paymentReference = new PaymentReference();
		StudentInterface studentImplementation = StudentImpl.getInstance();
		paymentReference.setPaymentStatus(1);
		paymentReference.setAmount(fee);
		paymentReference.setPayeeName(studentImplementation.viewStudentDetails(studentId).getName());
		PaymentDaoInterface paymentsDaoImplementation = new PaymentDaoImpl();
		//int paymentReferenceNumber = paymentsDaoImplementation.storePaymentReference(paymentReference);
		//paymentReference.setReferenceNo(paymentReferenceNumber);
		return paymentReference;
	}

	@Override
	public void updateCourse(String courseID, String field, String value) {
		// TODO Auto-generated method stub
		AdminDaoInterface admin = new AdminDaoImpl();
		admin.modifyCourse(courseID, field, value);
	}



}
