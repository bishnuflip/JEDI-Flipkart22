package com.flipkart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.flipkart.bean.*;
import com.flipkart.dao.*;
import javafx.util.Pair;

public class StudentImpl implements StudentInterface {
	
	private static StudentImpl instance = null;
	private CatalogDaoInterface catalogDaoImplementation = new CatalogDaoImpl();
	private GradeCardDaoInterface gradeCardDaoImplementation = new GradeCardDaoImpl();
	private CourseDaoInterface courseDaoImplementation = new CourseDaoImpl();
	private StudentDaoInterafce studentDaoImplementation = new StudentDaoImpl();

	private NotificationInterface notificationDaoImplementation = new NotificationDaoImpl();
	private PaymentDaoInterface paymentDaoImpl = new PaymentDaoImpl();

	public static StudentImpl getInstance(){
		if(instance==null){
			synchronized (StudentImpl.class){
				instance = new StudentImpl();
			}
		}
		return instance;
	}

	@Override
	public void addStudentdata(Student student) {

		studentDaoImplementation.addStudentData(student);
		}

	}

	@Override
	public ArrayList<Student> viewStudentData() {
		return studentDaoImplementation.viewAllStudents();
	}


	@Override
	public Student viewStudentDetails(String studentId) {

		return  studentDaoImplementation.viewStudentDetails(studentId);
	}

	@Override
	public StudentCourseChoice selectCourses(String studentId) {
		
		ArrayList<Pair<String, Integer>> selectedCourses = new ArrayList<Pair<String, Integer>>();
		ArrayList<Course> courseCatalog = catalogDaoImplementation.viewCatalog(2);
		Map<String,Course> courseList = new HashMap<>();
		for(Course c: courseCatalog)
			courseList.put(c.getCourseId(), c);
		for(int i=0; i<6; )
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter course(courseId) choice-"+i+": ");
			String courseId = sc.next();
			System.out.print("Enter the type of choice (1-Core 2-Elective) : ");
			Integer type = sc.nextInt();
			Course course = null;
			if(courseList.containsKey(courseId))
				course = courseList.get(courseId);
			if(course != null)
			{
				i++;
				selectedCourses.add(new Pair<>(courseId,type));
			}
			else
			{
				System.out.println("Course not found with id "+courseId);
			}
			
		}
		
		StudentCourseChoice studentCourseChoice = new StudentCourseChoice();
		studentCourseChoice.setStudentId(studentId);
		studentCourseChoice.setCourses(selectedCourses);
		studentDaoImplementation.storeStudentCourseChoice(studentCourseChoice);
		
		System.out.println("Registration form submitted!!");
		return studentCourseChoice;
	}

	@Override
	public void displayCourseCatalog() {
		ArrayList<Course> clist = catalogDaoImplementation.viewCatalog(2);
		System.out.println("Course Id\tCourse Name\tProfessor Id\tCourse Fee");
		 for(Course c : clist)
		 {
			 System.out.println(c.getCourseId()+"\t\t"+c.getName()+"\t\t"+c.getProfessorId()+"\t\t"+c.getCourseFee());
		 }

	}



	@Override
	public void displayGradeCard(String studentId) {
		 GradeCard gradeCard = gradeCardDaoImplementation.viewGradeCard(studentId,2);
		 ArrayList<Grade> grades = gradeCard.getCourseGrades();

		if(gradeCard.isPublished())
		{
			System.out.println("\n===================GRADE CARD===================\n");
			System.out.println("  Student Id: "+ gradeCard.getStudentId());
			System.out.println("\n  Serial No\tCourse Name\tGrade");
			int sno = 1;
			for(Grade g : grades) {
				System.out.printf("  %d\t\t%s\t\t%s\n",  sno,g.getCourseName() ,g.getGrade());
				sno++;
			}
			System.out.printf("\n  SGPA: %.2f", gradeCard.getSgpa());
			System.out.println("\n=================================================\n");
		}
		else {
			System.out.println("Grade card is not yet published");
		}

	}

	@Override
	public Boolean studentAlreadyRegistered(String studentId) {
		return studentDaoImplementation.studentAlreadyRegistered(studentId);
	}

	@Override
	public void makePaymentSuccessful(String studentId) {
		paymentDaoImpl.makePayment(studentId);

	}

	@Override
	public boolean getPaymentStatus(String studentId) {
		boolean paymentStatus = paymentDaoImpl.getPaymentStatus(studentId);
		return paymentStatus;
	}

	@Override
	public Boolean isStudentRegistered(String studentId) {
		if(studentDaoImplementation.checkRegStatus(studentId)==1)
			return true;
		return false;
	}

	@Override
	public boolean isSemesterRegistrationDone(String studentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGradeCardActivated(String studentId) {
		if(studentDaoImplementation.checkGradeCardStatus(studentId) == 1)
			return true;
		return false;
	}

	public  void viewNotifications(String userId) {
		ArrayList<Notification> notifications =  notificationDaoImplementation.viewNotificationList(userId);
		for(Notification n: notifications) {
			System.out.println(n.toString());
		}
	}


}
