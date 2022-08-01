/**
 * 
 */
package com.flipkart.app;

import com.flipkart.service.StudentImpl;

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
		System.out.println("\t1 : View Course List\n\t2 : Payment\n\t3 : View Grade Card\n\t4 : View Profile\n\t5 : View Notifications\n\t6 : Logout");
		System.out.println("==========================================================");
		Scanner scanner = new Scanner(System.in);
		StudentImpl studentImpl = new StudentImpl();
		while(true) {
			System.out.print("Enter your Choice: ");
			int choice = scanner.nextInt();
			if(choice == 6)
			{
				CRSApplication.startApplication();
				break;
			}
			switch(choice) {
			case 1:{
				System.out.println("View Course List");
				studentImpl.displayCourseCatalog();
				break;
			}
			case 2:{
				System.out.println("Payment");
				break;
			}
			case 3:{
				studentImpl.displayGradeCard(studentId);
				break;
			}
			case 4:{
				System.out.println(studentImpl.viewStudentDetails(studentId).toString());
				break;
			}
			case 5:{
				studentImpl.viewNotifications(studentId);
				break;
			}
			}
		}
		scanner.close();
		
	}

}
