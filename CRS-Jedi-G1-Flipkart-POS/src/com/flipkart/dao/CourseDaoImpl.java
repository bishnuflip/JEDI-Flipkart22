package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDaoImpl implements CourseDaoInterface{
    @Override
    public Course viewCourseDetails(String courseID) {
        Course course = new Course();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM COURSE WHERE courseId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseID);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            if(rs == null) {
                return null;
            }
            course.setCourseId(rs.getString("courseId"));
            course.setName(rs.getString("name"));
            course.setDept(rs.getString("dept"));
            course.setProfessorId(rs.getString("profId"));
            course.setSemester(rs.getInt("semester"));
            course.setCourseFee(rs.getFloat("fees"));
            course.setStrength(rs.getInt("strength"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return course;
    }

    @Override
    public ArrayList<Course> getStudentRegisteredCourses(String studID) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT courseId, name, dept, profId FROM ALLOTEDCOURSE, COURSE WHERE ALLOTEDCOURSE.courseId = COURSE.courseId and studentId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            if(rs == null) {
                return null;
            }
            while(rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getString("courseId"));
                course.setName(rs.getString("name"));
                course.setDept(rs.getString("dept"));
                course.setProfessorId(rs.getString("profId"));
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
        return null;
    }
}
