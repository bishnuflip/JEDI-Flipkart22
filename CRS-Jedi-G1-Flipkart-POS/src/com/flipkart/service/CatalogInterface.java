package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.Course;

public interface CatalogInterface {
	public void removeCourse(String courseId);
    public void addCourse(Course course);
    public void updateCourse(String courseID, String field, String value);
    public ArrayList<Course> getAllCourses();
    public Course getCourseFromCatalog(String courseId);
}
