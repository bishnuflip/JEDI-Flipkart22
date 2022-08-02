package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.StudentCourseChoice;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface StudentDaoInterafce {

    public void  addStudentData(Student student);

    /**
     * method to get list of all students from database
     * @return lsit of students
     */
    public ArrayList<Student> viewAllStudents();

    /**
     * method to view student details
     * @param studID
     * @return student object with all details
     */
    public Student viewStudentDetails(String studID);

    /**
     * method to store student choices in database
     * @param choice
     */
    public void storeStudentCourseChoice(StudentCourseChoice choice);

    /**
     * method to check if student already exists
     * @param email
     * @return true if student is already registered, false otherwise
     */
    public boolean studentAlreadyRegistered(String email);

    /**
     * method to check status of fee payment
     * @param studID
     * @return status of payment
     */
    public int getPayStatus(String studID);

    /**
     * method to check status of registration of student
     * @param studID
     * @return true or false based on registration status
     */
    public int checkRegStatus(String studID);

    /**
     * method to check if grade card is available
     * @param studID
     * @return true if grade card is available, false otherwise
     */
    public int checkGradeCardStatus(String studID);

    /**
     * method to get the courses chosen by student
     * @param studID
     * @return
     */
    public ArrayList<Course> viewSelectedCourses(String studID);

    /**
     * method to see the courses a student is registered for
     * @param studID
     * @return
     */
    public ArrayList<Course> viewAllotedCourses(String studID);

    /**
     * method to get list of students whose registration is pending
     * @return
     */
    public ArrayList<Student> getPendingRegList();

    public ArrayList<Student> getPengingCourseAllotmentList();
}
