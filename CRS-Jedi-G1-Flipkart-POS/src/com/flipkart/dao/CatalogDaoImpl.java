package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CatalogDaoImpl implements CatalogDaoInterface{
    @Override
    public ArrayList<Course> viewCatalog(int role) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = null;
        if(role == 1) {
            sql = "SELECT courseId, name, semester, fees, dept, profId, strength FROM COURSE";
        }
        else {
            sql = "SELECT courseId, name, semester, fees, dept, profId, strength FROM COURSE WHERE strength < 10";
        }
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            while(rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getString("courseId"));
                course.setSemester(rs.getInt("semester"));
                course.setName(rs.getString("name"));
                course.setCourseFee(rs.getFloat("fees"));
                course.setDept(rs.getString("dept"));
                course.setProfessorId(rs.getString("profId"));
                course.setStrength(rs.getInt("strength"));
                courseList.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseList;
    }
}
