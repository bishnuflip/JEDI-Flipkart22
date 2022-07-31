package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.ArrayList;

public interface ProfDaoInterface {

    /**
     * method to get list of available courses
     * @return
     */
    public ArrayList<Course> viewAvailableCourses();

    /**
     * method to get list of students enrolled in a course
     * @param courseID
     * @return
     */
    public ArrayList<Student> viewEnrolledStudents(String courseID);

    /**
     * method to select course for professor
     * @param profID
     * @param courseID
     */
    public void selectCourse(String profID, String courseID);

    /**
     * method to assign grade to student
     * @param studID
     * @param courseID
     * @param grade
     */
    public void assignGrade(String studID, String courseID, float grade);

    /**
     * method to view grade assigned to a student
     * @param studID
     * @param courseID
     * @return
     */
    public float viewGrade(String studID, String courseID);
}
