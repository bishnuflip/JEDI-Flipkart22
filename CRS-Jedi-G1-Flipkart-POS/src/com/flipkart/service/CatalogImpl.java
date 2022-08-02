package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.dao.*;

public class CatalogImpl implements CatalogInterface {

	private CourseDaoInterface courseDaoImplementation = new CourseDaoImpl();

	@Override
	public void removeCourse(String courseId) {
		AdminDaoInterface admin = new AdminDaoImpl();
		try {
			admin.deleteCourse(courseId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
	}

	@Override
	public void addCourse(Course course) {
		AdminDaoInterface admin = new AdminDaoImpl();
		try {
			admin.addCourse(course);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateCourse(String courseID, String field, String value) {
		AdminDaoInterface admin = new AdminDaoImpl();
		try {
			admin.modifyCourse(courseID, field, value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<Course> getAllCourses() {
		CatalogDaoInterface catalog = new CatalogDaoImpl();
		return catalog.viewCatalog(2);
	}

	@Override
	public Course getCourseFromCatalog(String courseId) {
		ArrayList<Course> courseList = getAllCourses();
		if (courseList.size() == 0) {
			System.out.println("Course not found with id " + courseId);
		} else {
			for (Course course : courseList) {
				if (course.getCourseId() == courseId) {
					return course;
				}
			}
		}
		return null;
	}
}
