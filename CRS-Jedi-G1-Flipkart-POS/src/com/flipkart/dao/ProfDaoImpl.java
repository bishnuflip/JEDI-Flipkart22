package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.utils.DBUtils;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;

public class ProfDaoImpl implements ProfDaoInterface {
    @Override
    public ArrayList<Course> viewAvailableCourses() {
        ArrayList<Course> courseList = new ArrayList<Course>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM COURSE WHERE profId = NULL";
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            util.closeConnection();
            while(rs.next()) {
                Course crs = new Course();
                crs.setCourseId(rs.getString("courseId"));
                crs.setName(rs.getString("name"));
                crs.setDept(rs.getString("dept"));
                crs.setSemester(rs.getInt("semester"));
                crs.setCourseFee(rs.getFloat("fees"));
                crs.setProfessorId(null);
                crs.setStrength(rs.getInt("strength"));
                courseList.add(crs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseList;
    }

    @Override
    public ArrayList<Student> viewEnrolledStudents(String courseID) {
        ArrayList<Student> studList = new ArrayList<Student>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT studentId, name, email, semester FROM (SELECT * FROM USER, ALLOTEDCOURSE WHERE userId = studentId and courseId = ?)";
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            util.closeConnection();
            while(rs.next()) {
                Student stud = new Student();
                stud.setStudentId(rs.getString("studentId"));
                stud.setName(rs.getString("name"));
                stud.setEmail(rs.getString("email"));
                stud.setSemester(rs.getInt("semester"));
                studList.add(stud);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studList;
    }

    @Override
    public void selectCourse(String profID, String courseID) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO PROFCHOICE ('profId', 'courseId') VALUES (?, ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, profID);
            stmt.setString(2, courseID);
            stmt.executeQuery();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void assignGrade(String studID, String courseID, float grade) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "UPDATE ALLOTEDCOURSE SET grade = ? WHERE studentId = ? and courseId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setFloat(1, grade);
            stmt.setString(2, studID);
            stmt.setString(3, courseID);
            stmt.executeQuery();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public float viewGrade(String studID, String courseID) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT grade FROM ALLOTEDCOURSE WHERE studentId = ? and courseId = ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            stmt.setString(2, courseID);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            util.closeConnection();
            if(rs == null) {
                return -101;
            }
            return rs.getFloat("grade");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
