/**
 * 
 */
package com.flipkart.app;

import java.util.ArrayList;
import java.util.Scanner;

import com.flipkart.Exceptions.CourseNotFoundException;
import com.flipkart.Exceptions.ProfessorNotFoundException;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.service.AdminImpl;
import com.flipkart.service.StudentImpl;
import com.flipkart.service.StudentInterface;

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
		System.out.println("\t9 : View Student List\n\t10: View Professor Details\n\t11: View All Courses\n\t12: Student Course Allocation\n\t13: Logout");
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
					scanner.next();

					System.out.print("Enter Course Fee: ");
					newCourse.setCourseFee(scanner.nextFloat());

					System.out.print("Enter department: ");
					newCourse.setDept(scanner.next());

					System.out.print("Enter semester: ");
					newCourse.setSemester(scanner.nextInt());
					scanner.next();

					admin.addCourse(newCourse);
					break;

				}
				case 2: {
					System.out.print("Enter CourseId: ");
					try {
						admin.removeCourse(scanner.next());
					} catch (CourseNotFoundException e) {
						throw new RuntimeException(e);
					}
					break;
				}
				case 3: {
					System.out.println("Enter the Course ID:");
					String courseID = scanner.next();
					System.out.println("Enter the field whose value is to be updated: ");
					String field = scanner.next();
					System.out.println("Enter the new value: ");
					String value = scanner.next();
					admin.updateCourse(courseID, field, value);
					break;
				}
				case 4: {
					System.out.print("Enter the student ID: ");
					String studentID = scanner.next();
					admin.activateGradeCard(studentID);
					System.out.println("Grade Card Activated");
					break;
				}
				case 5: {
					Professor professor = new Professor();
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
					try {
						admin.removeProfessor(professorId);
					} catch (ProfessorNotFoundException e) {
						throw new RuntimeException(e);
					}
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
					System.out.println("The list of pending students is as follows: ");
					StudentInterface students = new StudentImpl();
					students.pendingList();
					System.out.print("Enter StudentId of student to be registered (enter 'b' to go back): ");
					String studentId = scanner.next();
					if(studentId.equals("b")) {
						break;
					}
					admin.approveStudentRegistration(studentId);
					break;
				}
				case 9: {
					ArrayList<Student> students = admin.viewAllStudents();
					for(Student stud: students) {
						System.out.println(stud.getStudentId() + "\t"+ stud.getName() + "\t" + stud.getSemester() + "\t" + stud.getGender() + "\t" + stud.getRegStatus() + "\t" + stud.getPayStatus()+"\t"+stud.getGradeCardStatus());
					}
					break;
				}
				case 10: {
					ArrayList<Professor> professors = admin.viewAllProfessors();
					professors.stream().map(s -> s.getProfessorId()+"\t"+s.getName()+"\t"+s.getDept()+"\t"+s.getEmail()).forEach(System.out::println);
					break;
				}
				case 11: {
					ArrayList<Course> courses = admin.viewAllCourses();
					courses.stream().map(s -> s.getName()+"\t"+s.getCourseId()+"\t"+s.getDept()+"\t"+s.getCourseFee()+"\t"+s.getSemester()+"\t"+s.getProfessorId()+"\t"+s.getStrength()).forEach(System.out::println);
					System.out.println("View All Courses");
					break;
				}
				case 12: {
					System.out.println("1. View pending student list");
					System.out.println("2. View chosen courses, payment status, and allocate courses of student");
					System.out.print("Enter your choice: ");
					int ch = scanner.nextInt();
					switch(ch) {
						case 1: admin.viewPendingStudents();
								break;
						case 2: System.out.print("Enter the student ID: ");
								String studentID = scanner.next();
								ArrayList<Course> courseList = admin.viewStudentCourseChoice(studentID);
								for(Course crs: courseList) {
									System.out.println(crs.getCourseId() + "\t" + crs.getName() + "\t" + crs.getStrength());
								}
								if(admin.getPayStatus(studentID) == 1) {
									System.out.println("Fees paid. Courses will be allocated.");
									admin.allocateStudentCourse(studentID);
								}
								else {
									System.out.println("Fees not paid. Courses not allocated.");
								}
								break;
					}
					break;
				}

			}
		}
	}
}

