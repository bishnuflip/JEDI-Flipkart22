package com.flipkart.dao;

import com.flipkart.bean.Student;
import com.flipkart.bean.StudentCourseChoice;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDaoImpl implements StudentDaoInterafce{

    @Override
    public void addStudentData(Student student) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO STUDENT ('studentId', 'semester') VALUES (?, ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getStudentId());
            stmt.setInt(2, student.getSemester());
            stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "INSERT INTO USER ('userId', 'password', 'name', 'gender', 'phNo', 'email') VALUES (?, ?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getPasswordHash());
            stmt.setString(3, student.getName());
            stmt.setString(4, student.getGender());
            stmt.setString(5, student.getContactNo());
            stmt.setString(6, student.getEmail());
            stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<Student> viewAllStudents() {
        return null;
    }

    @Override
    public Student viewStudentDetails(String studID) {
        return null;
    }

    @Override
    public void storeStudentCourseChoice(StudentCourseChoice choice) {

    }

    @Override
    public boolean studentAlreadyRegistered(String email) {
        return false;
    }

    @Override
    public int getPayStatus(String studID) {
        return 0;
    }

    @Override
    public boolean checkRegStatus(String studID) {
        return false;
    }

    @Override
    public boolean checkGradeCardStatus(String studID) {
        return false;
    }
}
