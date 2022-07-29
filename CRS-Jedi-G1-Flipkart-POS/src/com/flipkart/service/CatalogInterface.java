package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.Course;

public interface CatalogInterface {
	public void removeCourse(int courseId);
    public void addCourse(Course course);
    public void updateCourse(Course course);
    public ArrayList<Course> getAllCourses();
    public Course getCourseFromCatalog(int courseId);
}
