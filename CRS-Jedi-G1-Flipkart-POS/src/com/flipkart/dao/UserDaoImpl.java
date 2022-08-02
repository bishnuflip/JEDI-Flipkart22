package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDaoInterface{

    @Override
    public int login(String userId, String password) throws  RuntimeException{
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "Select password, type from user where userId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs);
            if(rs == null) {
                System.out.println("User does not exist");
            }
            String pwdHash = "";
            int type = -1;
            while(rs.next()) {
                pwdHash = rs.getString("password");
                type = rs.getInt("type");
            }
            if(!pwdHash.equals(password)) {
                stmt.close();
                util.closeConnection();
                return -1;
            }
            stmt.close();
            util.closeConnection();
            return type;
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
            sql = "SELECT " + column + " FROM INDEX";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            int index = rs.getInt(column);
            stmt.close();
            sql = "UPDATE INDEX SET " + column + " = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, index+1);
            stmt.executeQuery();
            stmt.close();
            util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
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
                return true;
            }
            stmt.close();
            util.closeConnection();
            return false;
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
                return true;
            }
            stmt.close();
            util.closeConnection();
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
