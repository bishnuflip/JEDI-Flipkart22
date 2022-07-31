package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;

public interface AdminDaoInterface {
    public void addCourse(Course crs);

    public void deleteCourse(String courseID);

    public void modifyCourse(String courseID, String field, String value);

    public void addProfessor(Professor prof);

    public void deleteProfessor(String profID);

    public void modifyProfessor(String profID, String field, String value);

    public void addAdmin(Admin admin);

    public void deleteAdmin(String adminID);

    public void modifyAdmin(String adminID, String field, String value);

    public ArrayList<Student> viewStudents();

    public ArrayList<Professor> viewProfs();

    public void approveStudent(String studID);

    public void approveProfCourseChoice(String profID, String courseID);

    public int allotCourseStudent(String studID, String courseID);

}
