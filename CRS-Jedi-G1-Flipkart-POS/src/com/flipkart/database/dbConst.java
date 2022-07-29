package com.flipkart.database;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.Grade;
import com.flipkart.bean.StudentCourseChoice;
import java.util.HashMap;

public class dbConst {
    public static final HashMap<Integer, Professor> professors = new HashMap<Integer,Professor>();
    public static final HashMap<Integer, Student> students = new HashMap<Integer,Student>();
    public static final HashMap<Integer, Course> courses = new HashMap<Integer,Course>();
    public static final HashMap<String,Grade> grades = new HashMap<String,Grade>();
    public static final HashMap<Integer,StudentCourseChoice> studentCourses = new HashMap<Integer,StudentCourseChoice>();
}
