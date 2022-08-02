/**
 * 
 */
package com.flipkart.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.service.AdminImpl;

/**
 * @author jain.harshil
 *
 */
public class CRSAdminMenu {


	public void adminMenuMain() {
		// TODO Auto-generated method stub
		System.out.println("\033[0;1m" + "\n-----------------------!!Welcome Admin!!----------------------\n" + "\033[0m");
		System.out.println("Choose an option:-");
		System.out.println("--------------------------------------------------------------");
		System.out.println("\t1 : Add Course\n\t2 : Delete Course\n\t3 : Update Course\n\t4 : Activate Grade Card");
		System.out.println("\t5 : Add Professor Details\n\t6 : Delete Professor Details\n\t7 : Update Professor Details\n\t8 : Approve Student Registration");
		System.out.println("\t9 : View Student Details\n\t10: View Professor Details\n\t11: View All Courses\n\t12: Student Course Allocation\n\t13: Logout");
		System.out.println("==============================================================");
		Scanner scanner;
		AdminImpl admin = new AdminImpl();
		while (true) {
			scanner = new Scanner(System.in);
			System.out.print("\nEnter Choice Number: ");
			int choice = scanner.nextInt();
			if (choice == 13) {
				break;
			}
			switch (choice) {
				case 1: {
					Course newCourse = new Course();
					System.out.print("Enter CourseId: ");
					newCourse.setCourseId(scanner.next());

					System.out.print("Enter course name: ");
					newCourse.setName(scanner.next());

					System.out.print("Enter Course Fee: ");
					newCourse.setCourseFee(scanner.nextInt());


					admin.addCourse(newCourse);
					break;

				}
				case 2: {
					System.out.print("Enter CourseId: ");
					admin.removeCourse(scanner.next());
					break;
				}
				case 3: {
					System.out.println("Enter the Course ID:");
					String courseID = scanner.next();
					System.out.println("Enter the field whose value is to be updated: ");
					String field = scanner.next();
					System.out.println("Enter the new value: ");
					String value = scanner.next();
					admin.updateProfessor(courseID, field, value);
					break;
				}
				case 4: {
					admin.activateGradeCard();
					System.out.println("Activate Grade Card");
					break;
				}
				case 5: {
					Professor professor = new Professor();
//				System.out.println("Enter username: ");
//				String username = scanner.next();
//				professor.setUsername(username);
					System.out.println("Enter password: ");
					String passwordHash = scanner.next();
					professor.setPasswordHash(passwordHash);
					System.out.print("Enter name: ");
					String name = scanner.next();
					professor.setName(name);

					System.out.print("Enter gender('MALE'/'FEMALE'/'OTHERS'): ");
					String gender = scanner.next();    //Input MALE / FEMALE / OTHER all in capitals
					professor.setGender(gender);
					System.out.print("Enter contact number: ");
					String contactNo = scanner.next();
					professor.setContactNo(contactNo);
					admin.addProfessor(professor);
					System.out.println("Added Professor");
					break;
				}
				case 6: {
					System.out.print("Enter ProfessorId: ");
					String professorId = scanner.next();
					admin.removeProfessor(professorId);
					break;
				}
				case 7: {
					System.out.println("Enter the Professor ID:");
					String profID = scanner.next();
					System.out.println("Enter the field whose value is to be updated: ");
					String field = scanner.next();
					System.out.println("Enter the new value: ");
					String value = scanner.next();
					admin.updateProfessor(profID, field, value);
					break;

				}
				case 8: {
					System.out.print("Enter StudentId: ");
					String studentId = scanner.next();
					admin.approveStudentRegistration(studentId);
					break;
				}
				case 9: {
					ArrayList<Student> students = admin.viewAllStudents();
					students.stream().map(s -> s.getName()).forEach(System.out::println);
					break;
				}
				case 10: {
					ArrayList<Professor> professors = admin.viewAllProfessors();
					professors.stream().map(s -> s.getName()).forEach(System.out::println);
					break;
				}
				case 11: {
					ArrayList<Course> courses = admin.viewAllCourses();
					courses.stream().map(s -> s.getName()).forEach(System.out::println);
					System.out.println("View All Courses");
					break;
				}
				case 12: {
					System.out.println("Student Course Allocation");
					break;
				}

			}
			scanner.close();

		}
	}
}

