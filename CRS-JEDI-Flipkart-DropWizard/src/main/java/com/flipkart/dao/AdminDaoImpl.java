package com.flipkart.dao;

import com.flipkart.Exceptions.AdminNotFoundException;
import com.flipkart.Exceptions.CourseNotFoundException;
import com.flipkart.Exceptions.ProfessorNotFoundException;
import com.flipkart.Exceptions.UserNotApprovedException;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;

public class AdminDaoImpl implements  AdminDaoInterface{
    @Override
    public void addCourse(Course crs) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO COURSE (courseId, name, semester, fees, dept) VALUES (?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, crs.getCourseId());
            stmt.setString(2, crs.getName());
            stmt.setInt(3, crs.getSemester());
            stmt.setFloat(4, crs.getCourseFee());
            stmt.setString(5, crs.getDept());
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
    public void deleteCourse(String courseID) throws CourseNotFoundException{
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM COURSE WHERE courseId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseID);
            int res = stmt.executeUpdate();
            if(res == 0) throw new CourseNotFoundException();
            sql = "DELETE FROM STUDENTCHOICE WHERE courseId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseID);
            stmt.executeUpdate();
            sql = "DELETE FROM PROFCHOICE WHERE courseId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseID);
            stmt.executeUpdate();
        } 
        catch(CourseNotFoundException ce) {
        	throw ce;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
        	try {
        		stmt.close();
                util.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
    }

    @Override
    public void modifyCourse(String courseID, String field, String value) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "UPDATE COURSE SET " + field + " = ? WHERE courseId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, value);
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
    public void addProfessor(Professor prof) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO USER ('userId', 'name', 'password', 'gender', 'phone', 'email', 'type') VALUES (?, ?, ?, ?, ?, ?, 3)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, prof.getUserId());
            stmt.setString(2, prof.getName());
            stmt.setString(3, prof.getPasswordHash());
            stmt.setString(4, prof.getGender());
            stmt.setString(5, prof.getContactNo());
            stmt.setString(6, prof.getEmail());
            stmt.executeQuery();
            stmt.close();
            sql = "INSERT INTO PROFESSOR ('profId', 'dept', 'position') VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, prof.getProfessorId());
            stmt.setString(2, prof.getDept());
            stmt.setString(3, prof.getPosition());
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
    public void deleteProfessor(String profID) throws ProfessorNotFoundException{
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM PROFESSOR WHERE profId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, profID);
            stmt.executeUpdate();
            sql = "DELETE FROM USER WHERE userId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, profID);
            stmt.executeUpdate();
            sql = "DELETE FROM PROFCHOICE WHERE profId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, profID);
            stmt.executeUpdate();
            sql = "UPDATE COURSE SET prodId = null WHERE profId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, profID);
            int res = stmt.executeUpdate();
            stmt.close();
            if(res == 0) throw new ProfessorNotFoundException();
        }
        catch(ProfessorNotFoundException e) {
        	throw e;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifyProfessor(String profID, String field, String value) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        if(field == "profId" || field == "dept" || field == "position") {
            String sql = "UPDATE PROFESSOR SET " + field + " = ? WHERE profId = ?";
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, value);
                stmt.setString(2, profID);
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
        else {
            String sql = "UPDATE USER SET " + field + " = ? WHERE profId = ?";
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, value);
                stmt.setString(2, profID);
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
    }

    @Override
    public void addAdmin(Admin admin) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO USER ('userId', 'name', 'password', 'gender', 'phone', 'email', 'type') VALUES (?, ?, ?, ?, ?, ?, 1)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, admin.getUserId());
            stmt.setString(2, admin.getName());
            stmt.setString(3, admin.getPasswordHash());
            stmt.setString(4, admin.getGender());
            stmt.setString(5, admin.getContactNo());
            stmt.setString(6, admin.getEmail());
            stmt.executeQuery();
            stmt.close();
            sql = "INSERT INTO ADMIN ('adminId', 'dateJoined') VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, admin.getAdminId());
            stmt.setDate(2, (Date) admin.getDateOfJoining());
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
    public void deleteAdmin(String adminID) throws AdminNotFoundException {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM ADMIN WHERE adminId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, adminID);
            stmt.executeQuery();
            stmt.close();
            sql = "DELETE FROM USER WHERE userId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, adminID);
            int res = stmt.executeUpdate();
            stmt.close();
            if(res == 0) throw new AdminNotFoundException();
        }
        catch(AdminNotFoundException e) {
        	throw e;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifyAdmin(String adminID, String field, String value) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        if(field == "adminId" || field == "dateJoined") {
            String sql = "UPDATE ADMIN SET ? = ? WHERE adminId = ?";
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, field);
                stmt.setString(2, value);
                stmt.setString(3, adminID);
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
        else {
            String sql = "UPDATE USER SET ? = ? WHERE userId = ?";
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, field);
                stmt.setString(2, value);
                stmt.setString(3, adminID);
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
    }

    @Override
    public ArrayList<Student> viewStudents() {
        ArrayList<Student> studList = new ArrayList<Student>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT studentId, name, gender, phone, email, semester, payStatus, type FROM (SELECT * FROM USER, STUDENT WHERE userId = studentId)";
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            util.closeConnection();
            while(rs.next()) {
                Student stud = new Student();
                stud.setStudentId(rs.getString("studentId"));
                stud.setUserId(rs.getString("studentId"));
                stud.setName(rs.getString("name"));
                stud.setGender(rs.getString("gender"));
                stud.setContactNo(rs.getString("phone"));
                stud.setEmail(rs.getString("email"));
                stud.setSemester(rs.getInt("semester"));
                stud.setPayStatus(rs.getInt("payStatus"));
                int reg = (rs.getInt("type") == -1)?0:1;
                stud.setRegStatus(reg);
                studList.add(stud);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studList;
    }

    @Override
    public ArrayList<Professor> viewProfs() {
        ArrayList<Professor> profList = new ArrayList<Professor>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT profId, name, gender, phNo, email, dept, position FROM (SELECT * FROM USER, PROFESSOR WHERE userId = profId) AS PROFS";
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Professor prof = new Professor();
                prof.setProfessorId(rs.getString("profId"));
                prof.setUserId(rs.getString("profId"));
                prof.setName(rs.getString("name"));
                prof.setGender(rs.getString("gender"));
                prof.setContactNo(rs.getString("phNo"));
                prof.setEmail(rs.getString("email"));
                prof.setDept(rs.getString("dept"));
                prof.setPosition(rs.getString("position"));
                profList.add(prof);
            }
            stmt.close();
            util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profList;
    }

    @Override
    public void approveStudent(String studID) throws UserNotApprovedException {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "UPDATE USER SET type = 2 WHERE userId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            int res  = stmt.executeUpdate();
            stmt.close();
            if(res == 0) throw new UserNotApprovedException(studID);
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
    public void approveProfCourseChoice(String profID, String courseID) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "UPDATE COURSE SET profId = ? WHERE courseId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, profID);
            stmt.setString(2, courseID);
            stmt.executeQuery();
            stmt.close();
            sql = "DELETE FROM PROFCHOICE WHERE courseId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseID);
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
    public int allotCourseStudent(String studID, String courseID) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;

        //get current strength of course and semester from database

        String sql = "SELECT strength, semester FROM COURSE WHERE courseId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseID);
            ResultSet rs = stmt.executeQuery();
            int strength = 0;
            while(rs.next()) {
                strength = rs.getInt("strength");
            }
            if(strength == 10) {

                //delete the course choice from student choice table
                sql = "DELETE FROM STUDENTCHOICE WHERE courseId = ? AND studentId = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, courseID);
                stmt.setString(2, studID);
                stmt.executeQuery();
                stmt.close();
                util.closeConnection();

                //return fail status to notify student
                return 0;
            }
            int semester = 0;
            while(rs.next()) {
                semester = rs.getInt("semester");
            }

            //update course strength
            sql = "UPDATE COURSE SET strength = ? WHERE courseId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, strength+1);
            stmt.setString(2, courseID);
            stmt.executeUpdate();

            sql = "INSERT INTO ALLOTEDCOURSE (studentId, courseId, semester) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            stmt.setString(2, courseID);
            stmt.setInt(3, semester);
            stmt.executeUpdate();

            sql = "DELETE FROM STUDENTCHOICE WHERE courseId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseID);
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
}
