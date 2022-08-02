

package com.flipkart.service;
import java.util.*;
import com.flipkart.bean.Course;
import com.flipkart.database.dbConst;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfDaoImpl;

public class ProfessorImpl implements ProfessorInterface {
	
	private static volatile ProfessorImpl instance = null;
    ProfDaoImpl profDaoImpl = new ProfDaoImpl();
    
    public static ProfessorImpl getInstance() {
        if (instance == null) {
            synchronized (ProfessorImpl.class) {
                instance = new ProfessorImpl();
            }
        }
        return instance;
    }


	@Override
	public List<Course> viewAllCourses() {
		ArrayList<Course> clist = profDaoImpl.viewAvailableCourses();
		return clist;
	}

	@Override
	public List<Student> viewEnrolledStudents(String professorId) {
		List<Course> courses = profDaoImpl.viewAllottedCourses(professorId);
		ArrayList<Student> student = new ArrayList<Student>();
		HashSet<Student> students_set = new HashSet<>();
		for(Course course: courses) {
		ArrayList<Student> stulist = profDaoImpl.viewEnrolledStudents(course.getCourseId());
		for(Student stu: stulist) {
		if(!(student.contains(stu))) {student.add(stu); students_set.add(stu);}}}
		return student;}
	

	@Override
	public boolean selectCourse(String professorId, String courseId) {
		try{ profDaoImpl.selectCourse(professorId,courseId);
		return true;}
		catch(Exception e) {
			return false;
		}
	}

	@Override
	public void assignGrade(String studentId, String courseId, float grade,String professorId) {
		try{
			List<Course>  courses = profDaoImpl.viewAllottedCourses(professorId);
			HashSet<String>courseids = new HashSet<>();
			for(Course course:courses) {
				courseids.add(course.getCourseId());
			}
			if(courseids.contains(courseId)) {
			profDaoImpl.assignGrade(studentId,courseId,grade);
			System.out.println("Grade Assigned Successfully");}
			else {System.out.println("You have selected wrong course Id");}}
		catch(Exception e){
			System.out.println("Some error occured while assigning grade");
		}
	}

}



