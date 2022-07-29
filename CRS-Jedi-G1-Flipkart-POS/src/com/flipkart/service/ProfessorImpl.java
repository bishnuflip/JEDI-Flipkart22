package com.flipkart.service;
import java.util.*;
import com.flipkart.bean.Course;
import com.flipkart.database.dbConst;
import com.flipkart.bean.Student;
import com.flipkart.bean.Grade;
public class ProfessorImpl implements ProfessorInterface {
	
	private static volatile ProfessorImpl instance = null;
    ProfessorDaoOperation professorDaoOperation = ProfessorDaoOperation.getInstance();
    
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
		ArrayList<Course> clist = professorDaoOperation.viewAvailableCourses();
		if(clist.size()==0) {
			System.out.println("No available courses at this point of time");
		}
		return clist;
	}

	@Override
	public List<Student> viewEnrolledStudents(int professorId) {
		
		Map<Integer,ArrayList<Student>> stulist = professorDaoOperation.viewEnrolledStudents(professorId);
		ArrayList<Student> student = new ArrayList<Student>();
		for(Map.Entry<Integer,ArrayList<Student>> entry: stulist.entrySet())
		{
				student = entry.getValue();
				break;
		}
		return student;
	}

	@Override
	public boolean selectCourse(int professorId, int courseId) {
		if(!professorDaoOperation.selectCourse(professorId,courseId)) {
			System.out.println("course not found with id "+ courseId);
		}
	}

	@Override
	public void assignGrade(int studentId, int courseId, int semesterNumber,int grade) {
		if(!professorDaoOperation.assignGrade(studentId,courseId,grade)) {
			System.out.println("course not found with id "+ courseId);
		}
	}

}
