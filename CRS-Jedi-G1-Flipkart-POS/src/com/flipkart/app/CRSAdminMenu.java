/**
 * 
 */
package com.flipkart.app;

import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
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
			Course newCourse = new Course();
			System.out.print("Enter CourseId: ");
			newCourse.setCourseId(scanner.nextInt());

			System.out.print("Enter course name: ");
			newCourse.setName(scanner.next());

			System.out.print("Enter Course Fee: ");
			newCourse.setCourseFee(scanner.nextInt());

			System.out.print("Enter CatalogId");
			newCourse.setCatalogId(scanner.nextInt());

			admin.addCourse(newCourse);
			break;
			
			}
			case 2:{
				System.out.print("Enter CourseId: ");
				admin.removeCourse(scanner.nextInt());
				break;
			}
			case 3:{
				Course newCourse1 = new Course();
				System.out.print("Enter Course Id: ");
				newCourse1.setCourseId(scanner.nextInt());
				System.out.println("Note: Enter NA if you want to keep the details same.");
				String s;
				System.out.print("Enter new course name: ");
				s = scanner.next();
				if(!s.equals("NA"))
					newCourse1.setName(s);
				
				System.out.print("Enter new Professor Id: ");
				s = scanner.next();
				if(!s.equals("NA"))
					newCourse1.setProfessorId(Integer.parseInt(s));
				
				System.out.print("Enter new Course Fee: ");
				s = scanner.next();
				if(!s.equals("NA"))
					newCourse1.setCourseFee(Integer.parseInt(s));
				admin.updateCourse(newCourse1);
				break;
			}
			case 4:{
				admin.activateGradeCard();
				System.out.println("Activate Grade Card");
				break;
			}
			case 5:{
				Professor professor = new Professor();
				System.out.print("Enter username: ");
				String username = scanner.next();
				professor.setUsername(username);
				System.out.print("Enter password: ");
				String passwordHash = scanner.next();
				professor.setPasswordHash(passwordHash);
				System.out.print("Enter name: ");
				String name = scanner.next();
				professor.setName(name);
				System.out.print("Enter address: ");
				String address = scanner.next();
				professor.setAddress(address);
				System.out.print("Enter gender('MALE'/'FEMALE'/'OTHERS'): ");
				String gender = scanner.next();	//Input MALE / FEMALE / OTHER all in capitals
				professor.setGender(gender);
				System.out.print("Enter contact number: ");
				String contactNo = scanner.next();
				professor.setContactNo(contactNo);
				admin.addProfessor(professor);
				System.out.println("Added Professor");
				break;
			}
			case 6:{
				System.out.print("Enter ProfessorId: ");
				int professorId = scanner.nextInt();
				admin.removeProfessor(professorId);
				break;
			}
			case 7:{
				Professor professor = new Professor();
				System.out.print("Enter username: ");
				String username = scanner.next();
				professor.setUsername(username);
				System.out.print("Enter password: ");
				String passwordHash = scanner.next();
				professor.setPasswordHash(passwordHash);
				System.out.print("Enter name: ");
				String name = scanner.next();
				professor.setName(name);
				System.out.print("Enter address: ");
				String address = scanner.next();
				professor.setAddress(address);
				System.out.print("Enter contact number: ");
				String contactNo = scanner.next();
				professor.setContactNo(contactNo);
				System.out.print("Enter role: ");
				int role = scanner.nextInt();
				professor.setRole(role);
				System.out.print("Enter ProfessorId: ");
				int ProfessorId = scanner.nextInt();
				professor.setProfessorId(ProfessorId);
				admin.updateProfessor(professor);
				break;
				
			}
			case 8:{
				System.out.println("Approve Student Registration");
				break;
			}
			case 9:{
				admin.viewAllStudents();
				break;
			}
			case 10:{
				admin.viewAllProfessors();
				break;
			}
			case 11:{
				admin.viewAllCourses();
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
