package com.flipkart.dao;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public interface CourseDaoInterface {

    /**
     * method to check the details of the course
     * @param courseID
     * @return
     */
    public Course viewCourseDetails(String courseID);

    /**
     * method to check the courses that a student is registered for
     * @param studID
     * @return
     */
    public ArrayList<Course> getStudentRegisteredCourses(String studID);

}
