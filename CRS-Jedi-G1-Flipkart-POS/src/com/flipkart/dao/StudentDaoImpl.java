package com.flipkart.dao;

import com.flipkart.bean.Course;
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
        String sql = "INSERT INTO STUDENT (studentId, semester) VALUES (?, ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getStudentId());
            stmt.setInt(2, student.getSemester());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "INSERT INTO USER (userId, password, name, gender, phNo, email) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getPasswordHash());
            stmt.setString(3, student.getName());
            stmt.setString(4, student.getGender());
            stmt.setString(5, student.getContactNo());
            stmt.setString(6, student.getEmail());
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
    public ArrayList<Student> viewAllStudents() {
        ArrayList<Student> studList = new ArrayList<Student>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT studentId, name, gender, phNo, email, semester, payStatus, type FROM USER, STUDENT WHERE user.userId = student.studentId";
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Student stud = new Student();
                stud.setStudentId(rs.getString("studentId"));
                stud.setSemester(rs.getInt("semester"));
                stud.setName(rs.getString("name"));
                stud.setGender(rs.getString("gender"));
                stud.setContactNo(rs.getString("phNo"));
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
            stmt.close();
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
        String sql = "SELECT * FROM (SELECT studentId, name, gender, phNo, email, semester, payStatus, type FROM USER, STUDENT WHERE user.userId = student.studentId) AS STUDDETAILS WHERE studentId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            ResultSet rs = stmt.executeQuery();
            if(rs == null) {
                return null;
            }
            while(rs.next()) {
                stud.setStudentId(rs.getString("studentId"));
                stud.setSemester(rs.getInt("semester"));
                stud.setName(rs.getString("name"));
                stud.setGender(rs.getString("gender"));
                stud.setContactNo(rs.getString("phNo"));
                stud.setEmail(rs.getString("email"));
                stud.setRole(rs.getInt("type"));
                stud.setPayStatus(rs.getInt("payStatus"));
                if (stud.getRole() == -1) {
                    stud.setRegStatus(0);
                } else {
                    stud.setRegStatus(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt.close();
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
            String sql = "INSERT INTO STUDENTCHOICE (studentId, courseId, type) VALUES (?, ?, ?)";
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, studID);
                stmt.setString(2, ele.getKey());
                stmt.setInt(3, ele.getValue());
                stmt.executeUpdate();
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
            while(rs.next()) {
                if (rs != null && rs.getInt("type") == 2) {
                    stmt.close();
                    util.closeConnection();
                    return true;
                } else {
                    stmt.close();
                    util.closeConnection();
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
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
        String sql = "SELECT gradeCardStatus FROM STUDENT WHERE studId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            util.closeConnection();
            if(rs == null) {
                return -1;
            }
            if(rs.getInt("gradeCardStatus") == 0) {
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
    public ArrayList<Course> viewSelectedCourses(String studID) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM COURSE WHERE courseId IN (SELECT courseId FROM STUDENTCHOICE WHERE studentId = ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
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
    public ArrayList<Course> viewAllotedCourses(String studID) {
        ArrayList<Course> courseList = new ArrayList<Course>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM COURSE WHERE courseId IN (SELECT courseId FROM ALLOTEDCOURSE WHERE studentId = ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
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
    public ArrayList<Student> getPendingRegList() {
        ArrayList<Student> studList = new ArrayList<Student>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT userId, name, payStatus FROM USER, STUDENT WHERE userID = studentId AND type = -1";
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Student stud = new Student();
                stud.setStudentId(rs.getString("userId"));
                stud.setName(rs.getString("name"));
                stud.setPayStatus(rs.getInt("payStatus"));
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
    public ArrayList<Student> getPengingCourseAllotmentList() {
        ArrayList<Student> studList = new ArrayList<Student>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT userId, name FROM USER WHERE userId IN (SELECT DISTINCT studentId AS userId FROM STUDENTCHOICE) AS STUDCHOICE";
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Student stud = new Student();
                stud.setStudentId(rs.getString("userId"));
                stud.setName(rs.getString("name"));
                studList.add(stud);
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
        return studList;
    }
}
