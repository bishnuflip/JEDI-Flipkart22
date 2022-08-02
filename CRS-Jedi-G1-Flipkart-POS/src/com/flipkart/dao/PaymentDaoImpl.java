package com.flipkart.dao;

import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDaoImpl implements PaymentDaoInterface{
    @Override
    public void makePayment(String studID) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "UPDATE STUDENT SET payStatus = 1 WHERE studentId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
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
    public boolean getPaymentStatus(String studID) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT payStatus FROM STUDENT WHERE studentId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studID);
            ResultSet rs = stmt.executeQuery();
            if(rs == null) {
                return false;
            }
            while(rs.next()) {
                if (rs.getInt("payStatus") == 1) {
                    return true;
                }
            }
            stmt.close();
            util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
