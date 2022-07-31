package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDaoInterface{

    @Override
    public int login(String userId, String password) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "Select password, type from user where userId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if(rs == null) {
                System.out.println("User does not exist");
            }
            String pwdHash = rs.getString("password");
            int type = rs.getInt("type");
            if(!pwdHash.equals(password)) {
                stmt.close();
                util.closeConnection();
                return -1;
            }
            else {
                stmt.close();
                util.closeConnection();
                return rs.getInt("type");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
