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
import com.flipkart.database.dbConst;


public class AdminImpl implements AdminInterface {

	@Override
	public void activateGradeCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public int addProfessor(Professor professor) {
		//ProfessorImplementation.addProfessor data(professor);
				int profId = UtilityService.getId();
				professor.setProfessorId(profId);
				AdminDaoInterface admin = new AdminDaoOperation();
				try {
					if(admin.addProfessor(professor)) {
						System.out.println("Professor is successfully created"); 
						return profId; 
					}
					else {
						System.out.println("Professor is not added. Please enter valid professor id");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				return 0;

	}

	@Override
	public void removeProfessor(int professorId) {
		AdminDaoInterface admin = new AdminDaoOperation();
		try {
			if(admin.removeProfessor(professorId)){
				System.out.println("Professor is succesfully removed");
			}
			else{
				System.out.println("Professor is not removed. Please enter valid professor id");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateProfessor(Professor professor) {
		AdminDaoInterface admindao = new AdminDaoOperation();
		try {
			if(admindao.updateProfessor(professor,professor)){	//Ultimately we should update only the details that were newly changed
				System.out.println("Professor is updated");

			}
			else{
				System.out.println("Professor is not updated. Please enter valid professor id");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	@Override
	public Boolean approveStudentRegistration(int studentId) {
		AdminDaoInterface admindaooperation = new AdminDaoOperation();
		 return(admindaooperation.studentSelfRegistration(studentId));
	}

	@Override
	public ArrayList<Student> viewAllStudents() {
		StudentInterface studentImplementation = StudentImpl.getInstance();
		ArrayList<Student> studentData = studentImplementation.viewStudentData();
		return studentData;

	}

	@Override
	public ArrayList<Professor> viewAllProfessors() {
		AdminDaoInterface admindaooperation = new AdminDaoOperation();
		ArrayList<Professor> profList = admindaooperation.getAllProfessorDetails();
		return profList;

	}

	@Override
	public ArrayList<Course> viewAllCourses() {
		AdminDaoInterface admindaooperation = new AdminDaoOperation();
		 ArrayList<Course> clist = admindaooperation.viewAllCourses();
		 return clist;

	}

	@Override
	public void allocatePendingCourses() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCourse(Course course) {
		AdminDaoInterface admin = new AdminDaoOperation();
		try {
			if(admin.addProfessor(course)) {
				System.out.println("Course is successfully added");  
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public void removeCourse(int courseId) {
		AdminDaoInterface admin = new AdminDaoOperation();
		try {
			if(admin.removeCourse(courseId)){
				System.out.println("Course is succesfully removed");
			}
			else {
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateCourse(Course course) {
		AdminDaoInterface admin = new AdminDaoOperation();
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
