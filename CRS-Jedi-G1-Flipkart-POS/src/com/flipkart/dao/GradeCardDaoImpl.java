package com.flipkart.dao;

import com.flipkart.bean.Grade;
import com.flipkart.bean.GradeCard;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GradeCardDaoImpl implements GradeCardDaoInterface{
    @Override
    public GradeCard viewGradeCard(String studID, int role) {
        GradeCard gcard = new GradeCard();
        ArrayList<Grade> grades = new ArrayList<Grade>();
        //check role of user, if admin display directly else check activation status then display if active
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        if(role == 2) {
            StudentDaoImpl stud = new StudentDaoImpl();
            if(stud.checkGradeCardStatus(studID) == 0) {
                gcard.setPublished(false);
                return gcard;
            }
        }
        String sql = "SELECT courseId, name, grade, semester FROM ALLOTEDCOURSE, COURSE WHERE COURSE.courseId = ALLOTEDCOURSE.courseId and studentID = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            if(rs == null) {
                return null;
            }
            gcard.setStudentId(studID);
            gcard.setSemester(rs.getInt("semester"));
            while(rs.next()) {
                Grade g = new Grade();
                g.setCourseId(rs.getString("courseId"));
                g.setCourseName(rs.getString("name"));
                g.setGrade(rs.getFloat("grade"));
                grades.add(g);
            }
            gcard.setCourseGrades(grades);
            util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int activateGradeCard(String studID) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT courseId FROM ALLOTEDCOURSE WHERE studentID = ? and grade = NULL";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            if(rs != null) {
                return 0;
            }
            else {
                sql = "UPDATE STUDENT SET gradeCardStatus = 1 WHERE studentId = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, studID);
                stmt.executeQuery();
                stmt.close();
                util.closeConnection();
                return 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
