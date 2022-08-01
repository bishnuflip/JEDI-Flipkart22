package com.flipkart.app;

import java.util.HashMap;
import java.util.Scanner;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.service.AuthorizationService;

public class CRSApplication {
	static HashMap<Integer, Professor> professors = new HashMap<Integer, Professor>();
	static HashMap<Integer, Student> students = new HashMap<Integer, Student>();
	
    public static void main(String[] args) {
    	startApplication();
    }

	public static void startApplication() {

		System.out.println("\033[0;1m" +"\n----------------!!Welcome to CRS Application!!----------------\n"+"\033[0m");
		System.out.println("Choose the following operation:-");
		System.out.println("--------------------------------------------------------------");
		System.out.println("\t1 : Login User\n\t2 : Self Register Student\n\t3 : Forgot/Change Password\n\t4 : Quit");
		System.out.println("==============================================================");
		Scanner scanner = new Scanner(System.in);
		AuthorizationService auth = new AuthorizationService();
		while(true)
		{
			System.out.print("Enter Choice Number: ");
			int choice = scanner.nextInt();
			if(choice==4)
				return;
			switch(choice)
			{
				case 1: {
							System.out.println("Enter your Login details");
					  		System.out.print("Enter UserId: ");
					  		int userId = scanner.nextInt();
					  		System.out.print("Enter Password: ");
					  		String password = scanner.next();
					  		int role = auth.authorize(userId, password);
 					  		if(role == -1) {
					  			System.out.println("Invalid user id or password. Please try again");
					  			break;
					  		}
					        switch(role) {
					            case 1: System.out.println("Welcome Admin!");
					                    //display admin menu with appropriate options
					            		CRSAdminMenu adminMenu = new CRSAdminMenu();
					            		adminMenu.adminMenuMain();
					                    break;
					
					            case 2: System.out.println("Welcome Student");
					                    //display student relevant options
					            		CRSStudentMenu studentMenu = new CRSStudentMenu();
					            		studentMenu.studentMenuMain();
					                    break;
					
					            case 3: System.out.println("Welcome Professor");
					                    //display professor relevant options
					            		CRSProfessorMenu professorMenu = new CRSProfessorMenu();
					            		professorMenu.professorMenuMain();
					                    break;
					
					            default: System.out.println("Invalid user choice. Program will terminate.");
					                    //find a way to restart software after invalid input
					        }
				}
				case 2: {
					//Register Student Portal
					System.out.println("Self Registration");
					break;
				}
				case 3: {
					System.out.println("Change Password");
					break;
				}

				case 4: System.exit(0);
			}
		}
		
	}
}
