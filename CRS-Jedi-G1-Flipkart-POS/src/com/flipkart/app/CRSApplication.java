
package com.flipkart.app;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;

import com.flipkart.Exceptions.GradeCardNotPublishedException;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.service.*;

public class CRSApplication {

    public static void main(String[] args) {
		try {
			startApplication();
		} catch (GradeCardNotPublishedException e) {
			throw new RuntimeException(e);
		}
	}
    /**
     * Start of the application
     * @throws GradeCardNotPublishedException
     */

	public static void startApplication() throws GradeCardNotPublishedException {

		UserInterface user = new UserImpl();
		while(true)
		{
			System.out.println("\033[0;1m" +"\n----------------!!Welcome to CRS Application!!----------------\n"+"\033[0m");
			System.out.println("Choose one of the following operations:-");
			System.out.println("--------------------------------------------------------------");
			System.out.println("\t1 : Login User\n\t2 : Self Register Student\n\t3 : Forgot/Change Password\n\t4 : Quit");
			System.out.println("==============================================================");
			Scanner scanner = new Scanner(System.in);
			AuthorizationService auth = new AuthorizationService();
			System.out.print("Enter Choice Number: ");
			int choice = scanner.nextInt();
			if(choice==4)
				return;
			switch(choice)
			{
				case 1: {
							System.out.println("Enter your Login details");
					  		System.out.print("Enter UserId: ");
					  		String userId = scanner.next();
					  		System.out.print("Enter Password: ");
					  		String password = scanner.next();
					  		User loginUser = auth.authorize(userId, password);
							int role = loginUser.getRole();
 					  		if(role == -1) {
					  			System.out.println("Invalid user id or password. Please try again");
					  			break;
					  		}
 					  		//login successful
 					  		System.out.println("Welcome "+loginUser.getUsername()+ LocalDateTime.now());
					        switch(role) {
								case 1:
									System.out.println("Welcome Admin!");
									//display admin menu with appropriate options
									CRSAdminMenu adminMenu = new CRSAdminMenu();
									adminMenu.adminMenuMain();
									break;

								case 2:
									System.out.println("Welcome Student");
									//display student relevant options
									CRSStudentMenu studentMenu = new CRSStudentMenu();
									studentMenu.studentMenuMain(userId);
									break;

								case 3:
									System.out.println("Welcome Professor");
									//display professor relevant options
									CRSProfessorMenu professorMenu = new CRSProfessorMenu();
									professorMenu.professorMenuMain(userId);
									break;

								default:
									System.out.println("Invalid user choice. Please try again.");
							}
							   break;
				}
				case 2: {
					//Register Student Portal
					System.out.println("Self Registration");
					StudentInterface studentInterface = new StudentImpl();
					studentInterface.registerNewStudent();
					break;
				}
				case 3: {
					System.out.print("Enter userID: ");
					String uId = scanner.next();
					System.out.print("Enter new password: ");
					String str1 = scanner.next();
					System.out.print("Confirm new password: ");
					String str2 = scanner.next();
					if(str1.equals(str2)) {
						if(user.changePassword(uId, str1)) {
							System.out.println("Password changed successfully");
						}
						else {
							System.out.println("Invalid user ID. Please retry.");
						}
					}
					else {
						System.out.println("Passwords do not match. Please retry.");
					}
					break;
				}

				case 4: System.exit(0);
			}
		}
		
	}
}


