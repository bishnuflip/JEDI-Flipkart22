/**
 * 
 */
package com.flipkart.app;

import com.flipkart.Exceptions.GradeCardNotPublishedException;
import com.flipkart.bean.Course;
import com.flipkart.service.StudentImpl;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author jain.harshil
 *
 */
public class CRSStudentMenu {

	public void studentMenuMain(String studentId) throws GradeCardNotPublishedException {
		// TODO Auto-generated method stub
		System.out.println("Choose an option:-");
		System.out.println("----------------------------------------------------------");
		System.out.println("\t1 : View Course List\n\t2 : Register for Courses\n\t3 : Payment\n\t4 : View Grade Card\n\t5 : View Profile\n\t6 : View Notifications\n\t7 : Logout");
		System.out.println("==========================================================");
		Scanner scanner = new Scanner(System.in);
		StudentImpl studentImpl = new StudentImpl();
		while(true) {
			System.out.print("Enter your Choice: ");
			int choice = scanner.nextInt();
			if(choice == 7)
			{
				break;
			}
			switch(choice) {
			case 1:{
				studentImpl.displayCourseCatalog();
				break;
			}
			case 2:{
				studentImpl.selectCourses(studentId);
				break;
			}
			case 3:{
				System.out.println("The courses selected are: ");
				ArrayList<Course> courseList = studentImpl.viewStudentCourseChoice(studentId);
				float amt = 0;
				for(Course crs: courseList) {
					System.out.println(crs.getCourseId() + "\t" + crs.getName() + "\t" + crs.getProfessorId() + "\t" + crs.getCourseFee());
					amt += crs.getCourseFee();
				}
				System.out.println("The total fees to be paid is: " + amt);
				System.out.print("Enter 1 to pay, 2 to abort: ");
				int ch = scanner.nextInt();
				switch(ch) {
					case 1: studentImpl.makePaymentSuccessful(studentId);
							break;
					case 2: System.out.println("Payment cancelled");
							break;
				}
				break;
			}
			case 4:{
				studentImpl.displayGradeCard(studentId);
				break;
			}
			case 5:{
				System.out.println(studentImpl.viewStudentDetails(studentId).toString());
				break;
			}
			case 6:{
				studentImpl.viewNotifications(studentId);
				break;
			}
			}
		}
	}

}
