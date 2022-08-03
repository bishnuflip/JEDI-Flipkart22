package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.utils.DBUtils;
import javafx.util.Pair;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class ProfDaoImpl implements ProfDaoInterface {
    @Override
    public ArrayList<Course> viewAvailableCourses() {
        ArrayList<Course> courseList = new ArrayList<Course>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM COURSE WHERE profId is NULL";
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
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

            stmt.close();
            util.closeConnection();
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
        String sql = "SELECT studentId, name, email, semester FROM (SELECT * FROM USER, ALLOTEDCOURSE WHERE userId = studentId AND courseId = ?) AS STUDLIST";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Student stud = new Student();
                stud.setStudentId(rs.getString("studentId"));
                stud.setName(rs.getString("name"));
                stud.setEmail(rs.getString("email"));
                stud.setSemester(rs.getInt("semester"));
                studList.add(stud);
            }
            stmt.close();
            util.closeConnection();
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
        String sql = "INSERT INTO PROFCHOICE (profId, courseId) VALUES (?, ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, profID);
            stmt.setString(2, courseID);
            stmt.executeUpdate();
            sql = "UPDATE COURSE SET profId = ? WHERE courseId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, profID);
            stmt.setString(2, courseID);
            stmt.executeUpdate();
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
        String sql = "UPDATE ALLOTEDCOURSE SET grade = ? WHERE studentId = ? AND courseId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setFloat(1, grade);
            stmt.setString(2, studID);
            stmt.setString(3, courseID);
            stmt.executeUpdate();
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
        String sql = "SELECT grade FROM ALLOTEDCOURSE WHERE studentId = ? AND courseId = ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            stmt.setString(2, courseID);
            ResultSet rs = stmt.executeQuery();
            if(rs == null) {
                return -101;
            }
            float res = 0;
            while(rs.next()) {
                res = rs.getFloat("grade");
            }
            stmt.close();
            util.closeConnection();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Course> viewChosenCourses(String profID) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM COURSE WHERE courseId IN (SELECT courseId FROM PROFCHOICE WHERE profId = ?) AS CHOICES";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, profID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Course crs = new Course();
                crs.setCourseId(rs.getString("courseId"));
                crs.setName(rs.getString("name"));
                crs.setDept(rs.getString("dept"));
                crs.setProfessorId(rs.getString("profId"));
                crs.setSemester(rs.getInt("semester"));
                crs.setCourseFee(rs.getFloat("fees"));
                crs.setStrength(rs.getInt("strength"));
                courseList.add(crs);
            }
            stmt.close();
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

    @Override
    public int deleteCourseChoice(String profID, String courseID) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM PROFCHOICE WHERE profId = ? AND courseId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, profID);
            stmt.setString(2, courseID);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    @Override
    public ArrayList<Course> viewAllottedCourses(String profID) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM COURSE WHERE profId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, profID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Course crs = new Course();
                crs.setCourseId(rs.getString("courseId"));
                crs.setName(rs.getString("name"));
                crs.setDept(rs.getString("dept"));
                crs.setProfessorId(rs.getString("profId"));
                crs.setSemester(rs.getInt("semester"));
                crs.setCourseFee(rs.getFloat("fees"));
                crs.setStrength(rs.getInt("strength"));
                courseList.add(crs);
            }
            stmt.close();
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
