package com.flipkart.dao;

import com.flipkart.bean.Student;
import com.flipkart.bean.StudentCourseChoice;
import com.flipkart.utils.DBUtils;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            stmt.close();
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
    public ArrayList<Student> viewAllStudents() {
        ArrayList<Student> studList = new ArrayList<Student>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT studentId, name, gender, phone, email, semester, payStatus, type FROM USER, STUDENT WHERE user.userId = student.studentId";
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            while(rs.next()) {
                Student stud = new Student();
                stud.setStudentId(rs.getString("studentId"));
                stud.setSemester(rs.getInt("semester"));
                stud.setName(rs.getString("name"));
                stud.setGender(rs.getString("gender"));
                stud.setContactNo(rs.getString("phone"));
                stud.setEmail(rs.getString("email"));
                stud.setRole(rs.getInt("type"));
                stud.setPayStatus(rs.getInt("payStatus"));
                if(stud.getRole() == -1) {
                    stud.setRegStatus(0);
                }
                else {
                    stud.setRegStatus(1);
                }
                studList.add(stud);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studList;
    }

    @Override
    public Student viewStudentDetails(String studID) {
        Student stud = new Student();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM (SELECT studentId, name, gender, phone, email, semester, payStatus, type FROM USER, STUDENT WHERE user.userId = student.studentId) WHERE studentId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            if(rs == null) {
                return null;
            }
            while(rs.next()) {
                stud.setStudentId(rs.getString("studentId"));
                stud.setSemester(rs.getInt("semester"));
                stud.setName(rs.getString("name"));
                stud.setGender(rs.getString("gender"));
                stud.setContactNo(rs.getString("phone"));
                stud.setEmail(rs.getString("email"));
                stud.setRole(rs.getInt("type"));
                stud.setPayStatus(rs.getInt("payStatus"));
                if(stud.getRole() == -1) {
                    stud.setRegStatus(0);
                }
                else {
                    stud.setRegStatus(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stud;
    }

    @Override
    public void storeStudentCourseChoice(StudentCourseChoice choice) {
        String studID = choice.getStudentId();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        ArrayList<Pair<String, Integer>> choiceList = choice.getCourses();
        for(Pair<String, Integer> ele: choiceList) {
            String sql = "INSERT INTO STUDENTCHOICE ('studentId', 'courseId', 'type') VALUES (?, ?, ?)";
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, studID);
                stmt.setString(2, ele.getKey());
                stmt.setInt(3, ele.getValue());
                stmt.executeQuery();
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean studentAlreadyRegistered(String email) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT type FROM USER WHERE email = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            util.closeConnection();
            if(rs != null && rs.getInt("type") == 2) {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getPayStatus(String studID) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT payStatus FROM STUDENT WHERE studentId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            util.closeConnection();
            if(rs == null) {
                return -1;
            }
            return rs.getInt("payStatus");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int checkRegStatus(String studID) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT type FROM USER WHERE userId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            util.closeConnection();
            if(rs == null) {
                return -1;
            }
            if(rs.getInt("type") == -1) {
                return 0;
            }
            else {
                return 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int checkGradeCardStatus(String studID) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT courseId FROM ALLOTEDCOURSE WHERE studentID = ? and grade = NULL";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            util.closeConnection();
            if(rs == null) {
                return 0;
            }
            else {
                return 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
