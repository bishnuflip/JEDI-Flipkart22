/**
 * 
 */
package com.flipkart.app;

import java.util.Scanner;

import com.flipkart.service.AdminImpl;

/**
 * @author jain.harshil
 *
 */
public class CRSAdminMenu {
	
	

	public void adminMenuMain() {
		// TODO Auto-generated method stub
		System.out.println("\033[0;1m" +"\n-----------------------!!Welcome Admin!!----------------------\n"+"\033[0m" );
		System.out.println("Choose an option:-");
		System.out.println("--------------------------------------------------------------");
		System.out.println("\t1 : Add Course\n\t2 : Delete Course\n\t3 : Update Course\n\t4 : Activate Grade Card");
		System.out.println("\t5 : Add Professor Details\n\t6 : Delete Professor Details\n\t7 : Update Professor Details\n\t8 : Approve Student Registration");
		System.out.println("\t9 : View Student Details\n\t10: View Professor Details\n\t11: View All Courses\n\t12: Student Course Allocation\n\t13: Logout");
		System.out.println("==============================================================");
		Scanner scanner;
		AdminImpl admin = new AdminImpl();
		while(true)
		{
			scanner = new Scanner(System.in);
			System.out.print("\nEnter Choice Number: ");
			int choice = scanner.nextInt();
			if(choice == 13)
			{
				CRSApplication.startApplication();
				break;
			}
			switch(choice) {
			case 1:{
				System.out.println("Add Course");
				break;
			}
			case 2:{
				System.out.println("Delete Course");
				break;
			}
			case 3:{
				System.out.println("Update Course");
				break;
			}
			case 4:{
				System.out.println("Activate Grade Card");
				break;
			}
			case 5:{
				System.out.println("Add Professor Details");
				break;
			}
			case 6:{
				System.out.println("Delete Professor Details");
				break;
			}
			case 7:{
				System.out.println("Update Professor Details");
				break;
				
			}
			case 8:{
				System.out.println("Approve Student Registration");
				break;
			}
			case 9:{
				System.out.println("View Student Details");
				break;
			}
			case 10:{
				System.out.println("View Professor Details");
				break;
			}
			case 11:{
				System.out.println("View All Courses");
				break;
			}
			case 12:{
				System.out.println("Student Course Allocation");
				break;
			}
			}
			
		}
		scanner.close();	
		
	}

}
