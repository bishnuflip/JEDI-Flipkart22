package com.flipkart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;
import com.flipkart.bean.StudentCourseChoice;
import com.flipkart.bean.StudentRegisteredCourses;
import com.flipkart.dao.StudentDaoImpl;
import com.flipkart.dao.StudentDaoInterafce;
import com.flipkart.database.dbConst;

public class StudentImpl implements StudentInterface {
	
	private static StudentImpl instance = null;
	private CatalogInterface courseImplementation = CatalogImpl.getInstance();
	private GradeCardDaoInterface gradeCardOperation = GradeCardDaoImpl.getInstance();
	private CourseDaoInterface courseDaoImplementation = CourseDaoImplementation.getInstance();
	private StudentDaoInterface studentDaoImplementation = StudentDaoImpl.getInstance();
	
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

		StudentDaoInterafce studentdaoimpl = new StudentDaoImpl();
		if(studentdaoimpl.addStudentData(student)) {
			System.out.println("student is added");
		}

	}

	@Override
	public ArrayList<Student> viewStudentData() {
		return studentDaoImplementation.viewAllStudents();
	}

	@Override
	public Student viewStudentDetails(int studentId) {
		return studentDaoImplementation.viewStudentDetails(studentId);
	}

	@Override
	public StudentCourseChoice selectCourses(int studentId) {
		
		ArrayList<Course> selectedCourses = new ArrayList<Course>();
		ArrayList<Course> courseCatalog = courseImplementation.getAllCourses();
		Map<Integer,Course> courseList = new HashMap<>();
		for(Course c: courseCatalog)
			courseList.put(c.getCourseId(), c);
		for(int i=0; i<6; )
		{
			//System.out.print("Enter course(courseId) choice-"+i+": ");
			//int courseId = sc.nextInt();
			
			int courseId = id.get(i);
			Course course = null;
			if(courseList.containsKey(courseId))
				course = courseList.get(courseId);
			if(course != null)
			{
				i++;
				selectedCourses.add(course);
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
//		Notification notification = new Notification();
//		notification.setUserId(CRSApplication.getUserId());
//		notification.setMessage("Your application form has been submitted for the further process.");
//		Date date = new Date();
//		notification.setDateTime(date);
//		AdminDaoInterface adminDao = new AdminDaoImpl();
//		adminDao.generateNotification(notification);
		return studentCourseChoice;
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayCourseCatalog() {
		StudentDaoInterface studentdao = new StudentDaoImpl();
		ArrayList<Course> clist = studentdao.viewAllCourses();
		System.out.println("Course Id\tCourse Name\tProfessor Id\tCourse Fee");
		 int count = 1;
		 for(Course c : clist)
		 {
			 System.out.println(c.getCourseId()+"\t\t"+c.getName()+"\t\t"+c.getProfessorId()+"\t\t"+c.getCourseFee());
			 count++;
		 }

	}

	@Override
	public void displayGradeCard(int studentId) {
StudentRegisteredCourses studentRegisteredCourses = courseDaoImplementation.getStudentRegisteredCourses(studentId);
		
		GradeCard gradeCard = gradeCardOperation.getGradeCard(studentId);
		float grade1 = gradeCardOperation.getGradeFromCourseId(studentId, studentRegisteredCourses.getCourseId1());
		float grade2 = gradeCardOperation.getGradeFromCourseId(studentId, studentRegisteredCourses.getCourseId2());
		float grade3 = gradeCardOperation.getGradeFromCourseId(studentId, studentRegisteredCourses.getCourseId3());
		float grade4 = gradeCardOperation.getGradeFromCourseId(studentId, studentRegisteredCourses.getCourseId4());
		
		if(gradeCard.isPublished())
		{
			System.out.println("\n===================GRADE CARD===================\n");
			System.out.println("  Student Id: "+ gradeCard.getStudentId());
			System.out.println("\n  Serial No\tCourse Name\tGrade");
			System.out.println("  1\t\t"+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId1()).getName() +"\t\t" + grade1);
			System.out.println("  2\t\t"+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId2()).getName() +"\t\t" + grade2);
			System.out.println("  3\t\t"+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId3()).getName() +"\t\t" + grade3);
			System.out.println("  4\t\t"+ courseDaoImplementation.getCourseFromCourseId(studentRegisteredCourses.getCourseId4()).getName() +"\t\t" + grade4);
			System.out.printf("\n  SGPA: %.2f", gradeCard.getSgpa());
			System.out.println("\n=================================================\n");
		}
		else {
			System.out.println("Grade card is not yet published");
		}

	}

	@Override
	public Boolean studentAlreadyRegistered(int studentId) {
		return studentDaoImplementation.studentAlreadyRegistered(studentId);
	}

	@Override
	public void makePaymentSuccessful(int studentId, String referenceNo) {
		studentDaoImplementation.makePaymentSuccessful(studentId,referenceNo);

	}

	@Override
	public String getPaymentStatus(int studentId) {
		String paymentStatus = studentDaoImplementation.getPaymentStatus(studentId);
		return paymentStatus;
	}

	@Override
	public Boolean isStudentRegistered(int studentId) {
		return studentDaoImplementation.isStudentRegistered(studentId);
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
