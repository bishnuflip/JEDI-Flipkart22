package com.flipkart.dao;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public interface CourseDaoInterface {

    public ArrayList<Course> getStudentRegisteredCourses(String studID);

}
