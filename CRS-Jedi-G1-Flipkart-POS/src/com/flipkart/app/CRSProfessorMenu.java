

/**
 * 
 */
package com.flipkart.app;

import java.util.*;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.Grade;
import com.flipkart.service.ProfessorImpl;

/**
 * @author jain.harshil
 *
 */
public class CRSProfessorMenu {

	public void professorMenuMain(String profId) {
		// TODO Auto-generated method stub]
		System.out.println("Choose an option:-");
		System.out.println("----------------------------------------------------------");
		System.out.println("\t1 : View Available Courses\n\t2 : View enrolled students\n\t3 : Select Course\n\t4 : Assign Grade\n\t5 : Logout");
		System.out.println("==========================================================");
		Scanner scanner = new Scanner(System.in);
		ProfessorImpl profImpl = new ProfessorImpl();
		while(true) {
			System.out.print("Enter your Choice: ");
			int choice = scanner.nextInt();
			if(choice == 5)
			{
				CRSApplication.startApplication();
				break;
			}
			switch(choice) {
			case 1:{
				List<Course> courses = profImpl.viewAllCourses();
				for(Course course: courses) {
				System.out.println(course.toString());}
				break;
			}
			case 2:{
				List<Student>students = profImpl.viewEnrolledStudents(profId);
				for(Student student:students)
				{
					System.out.print(student.toString());
				}
				break;
			}
			case 3:{
				System.out.println("Enter Course Id");
				String courseId = scanner.next();
				boolean done = profImpl.selectCourse(profId, courseId);
				if(done) {System.out.println("Course "+courseId+" successfully registered by "+profId);}
				else {System.out.println("some error occured");}
				break;
			}
			case 4:{
				System.out.println("Enter Student Id");
				String studentId = scanner.next();
				System.out.println("Enter Course Id");
				String courseId = scanner.next();
				System.out.println("Enter Grade");
				float grade = scanner.nextFloat();
				profImpl.assignGrade(studentId, courseId, grade, profId);
				
				break;
			}
			}
		}
		scanner.close();
	}
	

}

