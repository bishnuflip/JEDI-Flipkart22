package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.dao.AdminDaoImpl;
import com.flipkart.dao.AdminDaoInterface;

public class CatalogImpl implements CatalogInterface {
	
	private static CatalogImpl instance = null;
	private CourseDaoInterface courseDaoImplementation = CourseDaoImplementation.getInstance();
	
	public static CatalogImpl getInstance(){
		if(instance==null){
			synchronized (CatalogImpl.class){
				instance = new CatalogImpl();
			}
		}
		return instance;
	}

	@Override
	public void removeCourse(int courseId) {
		AdminDaoInterface admin = new AdminDaoImpl();
		try {
			if(admin.deleteCourse(courseId))
			{
				return;
			}
			else
			{
				System.out.println("Course is not removed. Please enter correct course id");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
	}

	@Override
	public void addCourse(Course course) {
		AdminDaoInterface admin = new AdminDaoImpl();
		Boolean ans = null;
		try {
			ans = admin.addCourse(course);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ans)
			return;
		else
			System.out.println("Course already exists");
		
	}

	@Override
	public void updateCourse(Course course) {
		AdminDaoInterface admin = new AdminDaoImpl();
		try {
			if(admin.modifyCourse(course))	//we need to change in the menu so that users can only add the details they can update
				return;
			else
			{
				System.out.println("Course not found");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<Course> getAllCourses() {
		StudentDaoInterface studao = new StudentDaoImpl();
		return studao.viewAllCourses();
	}

	@Override
	public Course getCourseFromCatalog(int courseId) {
		ArrayList<Course> courseList = getAllCourses();
		if(courseList.size()==0)
		{
			System.out.println("Course not found with id "+ courseId);
		}
		else
		{
			for(Course course:courseList)
			{
				if(course.getCourseId() == courseId)
				{
					return course;
				}
			}	
			return null;
			
		}
	}
}
