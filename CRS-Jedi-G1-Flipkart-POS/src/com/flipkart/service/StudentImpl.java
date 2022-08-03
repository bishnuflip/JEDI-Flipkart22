package com.flipkart.service;

import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

import com.flipkart.Exceptions.GradeCardNotPublishedException;
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
		System.out.println("Course Id\t\tCourse Name\t\t\tProfessor Id\t\tCourse Fee");
		 for(Course c : clist)
		 {
			 System.out.println(c.getCourseId()+"\t\t\t"+c.getName()+"\t\t\t\t"+c.getProfessorId()+"\t\t\t"+c.getCourseFee());
		 }

	}



	@Override
	public void displayGradeCard(String studentId) throws GradeCardNotPublishedException {
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
			throw new GradeCardNotPublishedException();
		}

	}

	@Override
	public Boolean studentAlreadyRegistered(String studentId) {
		return studentDaoImplementation.studentAlreadyRegistered(studentId);
	}

	@Override
	public void makePaymentSuccessful(String studentId) {
		UserDaoInterface user = new UserDaoImpl();
		if(!user.checkIDAvailable(studentId)) {
			System.out.println("Invalid Student ID. Payment failed");
		}
		if(paymentDaoImpl.getPaymentStatus(studentId)) {
			System.out.println("Fees already paid");
			return;
		}
		paymentDaoImpl.makePayment(studentId);
		System.out.println("Payment Successful.");
		Notification notif = new Notification();
		notif.setNotifId(Integer.parseInt(UtilityService.getId(4)));
		notif.setType(1);
		notif.setUserId(studentId);
		//java.util.Date d1 = new java.util.Date();
		//Date d = new java.sql.Date(d1.getTime());
		//notif.setDate(d);
		notif.setTitle("Payment Successful");
		notif.setMessage("The payment for the student is successful");
		notificationDaoImplementation.writeNotification(notif);
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

	public  void viewNotificationList(String userId) {
		ArrayList<Notification> notifications =  notificationDaoImplementation.viewNotificationList(userId);
		System.out.println("Title\t\tNotificationID\tRead Status");
		for(Notification n: notifications) {
			System.out.println(n.getTitle() + "\t" + n.getNotifId() + "\t" + n.getStatus());
		}
	}

	public void viewNotification(String userID, int notifID) {
		Notification notif = notificationDaoImplementation.viewNotification(userID, notifID);
		System.out.println(notif.toString());
	}

	public void registerNewStudent()
	{
		Student student = new Student();

		student.setStudentId(UtilityService.getId(2));
		System.out.println("The id generated is: "+student.getStudentId());

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Name: ");
		student.setName(sc.next());

		System.out.println("Enter Semester: ");
		student.setSemester(sc.nextInt());

		System.out.println("\nEnter Phone: ");
		student.setContactNo(sc.next());

		System.out.println("\nEnter Email: ");
		student.setEmail(sc.next());

		System.out.println("\nEnter Gender: ");
		student.setGender(sc.next());

		System.out.println("\nEnter Password: ");
		student.setPasswordHash(sc.next());

		studentDaoImplementation.addStudentData(student);
	}

	@Override
	public void pendingList() {
		StudentDaoInterafce students = new StudentDaoImpl();
		ArrayList<Student> studList = students.getPendingRegList();
		for(Student stud: studList) {
			System.out.println(stud.getStudentId() + "\t" + stud.getPayStatus());
		}
	}

	@Override
	public ArrayList<Course> viewStudentCourseChoice(String studentID) {
		StudentDaoInterafce student = new StudentDaoImpl();
		ArrayList<Course> studChoice = student.viewSelectedCourses(studentID);
		return studChoice;
	}

}
