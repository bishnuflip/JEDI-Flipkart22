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

    /**
     * method to view the courses chosen by professor
     * @param profID
     * @return
     */
    public ArrayList<Course> viewChosenCourses(String profID);

    /**
     * method to delete a choice by professor
     * @param profID
     * @param courseID
     * @return status of deletion, 1 if successful, 0 if unsuccessful, -1 for error
     */
    public int deleteCourseChoice(String profID, String courseID);

    /**
     * method to view courses allotted to a professor
     * @param profID
     * @return
     */
    public ArrayList<Course> viewAllottedCourses(String profID);
}
