package com.flipkart.dao;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public interface CatalogDaoInterface {

    /**
     * method to show catalog of courses based on user role
     * students see only courses that aren't full
     * admin can see all details of all courses
     * @param role
     * @return
     */
    public ArrayList<Course> viewCatalog(int role);

}
