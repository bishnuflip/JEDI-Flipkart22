/**
 * 
 */
package com.flipkart.app;

import java.util.Scanner;

/**
 * @author jain.harshil
 *
 */
public class CRSProfessorMenu {

	public void professorMenuMain() {
		// TODO Auto-generated method stub]
		System.out.println("Choose an option:-");
		System.out.println("----------------------------------------------------------");
		System.out.println("\t1 : View Available Courses\n\t2 : View enrolled students\n\t3 : Select Course\n\t4 : Assign Grade\n\t5 : Logout");
		System.out.println("==========================================================");
		Scanner scanner = new Scanner(System.in);
		
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
				System.out.println("View Available Courses");
				break;
			}
			case 2:{
				System.out.println("View enrolled Students");
				break;
			}
			case 3:{
				System.out.println("Select Course");
				break;
			}
			case 4:{
				System.out.println("Assign Grade");
				break;
			}
			}
		}
		scanner.close();
	}
	

}
