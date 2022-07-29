package com.flipkart.service;
import java.util.*;
import com.flipkart.bean.Course;
import com.flipkart.database.dbConst;
import com.flipkart.bean.Student;
import com.flipkart.bean.Grade;
public class ProfessorImpl implements ProfessorInterface {

	@Override
	public List<Course> viewAllCourses() {
		List<Course> allCourses = new ArrayList<Course>(dbConst.courses.values());
		return allCourses;
	}

	@Override
	public List<Student> viewEnrolledStudents(int professorId) {
		HashSet<Integer> courses = new HashSet<>();
		final Integer profId = professorId;
		dbConst.courses.forEach((key,value)->{
			if(value.getProfessorId() == profId)
			{
				courses.add(key);
			}
		});
		List<Student>students = new ArrayList<>();
		dbConst.studentCourses.forEach((key,value)->{
			for(Integer course: value.getCourses())
			{
				if(courses.contains(course))
				{
					students.add(dbConst.students.get(key));
					break;
				}
			}
		});
		return students;
	}

	@Override
	public boolean selectCourse(int professorId, int courseId) {
		try {
			dbConst.courses.get(courseId).setProfessorId(professorId);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	@Override
	public void assignGrade(int studentId, int courseId, int semesterNumber,int grade) {
		// TODO Auto-generated method 
		Grade gradeObj = new Grade();
		gradeObj.setStudentId(studentId);
		gradeObj.setCourse(courseId);
		gradeObj.setGrade(grade);
		gradeObj.setSemester(semesterNumber);
		dbConst.grades.put(studentId+"$"+courseId,gradeObj);
	}

}
