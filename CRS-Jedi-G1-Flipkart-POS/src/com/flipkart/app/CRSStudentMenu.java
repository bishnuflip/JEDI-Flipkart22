/**
 * 
 */
package com.flipkart.app;

import java.util.Scanner;

/**
 * @author jain.harshil
 *
 */
public class CRSStudentMenu {

	public void studentMenuMain() {
		// TODO Auto-generated method stub
		System.out.println("Choose an option:-");
		System.out.println("----------------------------------------------------------");
		System.out.println("\t1 : View Course List\n\t2 : Semester Registration\n\t3 : Payment\n\t4 : View Grade Card\n\t5 : View Profile\n\t6 : View Notifications\n\t7 : Logout");
		System.out.println("==========================================================");
		Scanner scanner = new Scanner(System.in);
		
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
				System.out.println("View Course List");
				break;
			}
			case 2:{
				System.out.println("Semester Registration");
				break;
			}
			case 3:{
				System.out.println("Payment");
				break;
			}
			case 4:{
				System.out.println("View Grade Card");
				break;
			}
			case 5:{
				System.out.println("View Profile");
				break;
			}
			case 6:{
				System.out.println("View Notifications");
				break;
			}
			}
		}
		scanner.close();
		
	}

}
