/**
 * 
 */
package com.flipkart.app;

import com.flipkart.bean.Course;
import com.flipkart.service.StudentImpl;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author jain.harshil
 *
 */
public class CRSStudentMenu {

	public void studentMenuMain(String studentId) {
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
				CRSApplication.startApplication();
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
				//to be implemented, iterate through and display choices plus sum up course fees to display total amount payable
				studentImpl.makePaymentSuccessful(studentId);
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
		scanner.close();
		
	}

}
