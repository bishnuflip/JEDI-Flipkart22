package com.flipkart.bean;

import com.flipkart.database.dbConst;

import java.util.HashMap;

public class GradeCard {

    int studentId;
    int semester;
    float sgpa;
    HashMap<Integer, Course> courseList = dbConst.courses;



}
