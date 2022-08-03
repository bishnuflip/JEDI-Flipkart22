package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDaoInterface{

    @Override
    public User login(String userId, String password) throws  RuntimeException{
        User usr = new User();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "Select name, password, type from user where userId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            //System.out.println(rs);
            if(rs == null) {
                System.out.println("User does not exist");
            }
            String pwdHash = "";
            while(rs.next()) {
                usr.setName(rs.getString("name"));
                pwdHash = rs.getString("password");
                usr.setRole(rs.getInt("type"));
            }
            if(!pwdHash.equals(password)) {
                stmt.close();
                util.closeConnection();
                usr.setRole(-1);
                return usr;
            }
            stmt.close();
            util.closeConnection();
            return usr;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getLastIndex(int role) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql;
        int index;
        String column = "";
        if(role == 1) {
            column = "admin";
        }
        else if(role == 2) {
            column = "student";
        }
        else if(role == 3) {
            column = "professor";
        }
        else {
            return -1;
        }
        try {
            sql = "SELECT " + column + " FROM INDEXTABLE";
            //System.out.println(sql);
            stmt = conn.prepareStatement(sql);
            //System.out.println("Statement prepared");
            ResultSet rs = stmt.executeQuery();
            //System.out.println("The result set is: "+rs);
            index = 0;
            while(rs.next()) {
                index = rs.getInt(column);
                System.out.println(index);
            }
            sql = "UPDATE INDEXTABLE SET " + column + " = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, index+1);
            stmt.executeUpdate();
            stmt.close();
            //util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return index;
    }

    @Override
    public boolean checkIDAvailable(String userID) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM USER WHERE userId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();
            if(rs == null) {
                return false;
            }
            stmt.close();
            util.closeConnection();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkEmailAvailable(String email) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM USER WHERE email = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs == null) {
                return false;
            }
            stmt.close();
            util.closeConnection();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean changePassword(String userID, String password) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql;
        if(!checkIDAvailable(userID)) {
            return false;
        }
        try {
            sql = "UPDATE USER SET password = ? WHERE userId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, password);
            stmt.setString(2, userID);
            stmt.executeUpdate();
            stmt.close();
            //util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
