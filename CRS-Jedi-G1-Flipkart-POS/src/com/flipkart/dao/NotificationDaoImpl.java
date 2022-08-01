package com.flipkart.dao;

import com.flipkart.bean.Notification;
import com.flipkart.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;

public class NotificationDaoImpl implements NotificationInterface{
    @Override
    public void writeNotification(Notification notif) {
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO NOTIFICATION ('userId', 'title', 'message', 'date', 'status', 'type') VALUES (?, ?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, notif.getUserId());
            stmt.setString(2, notif.getTitle());
            stmt.setString(3, notif.getMessage());
            stmt.setDate(4, (Date) notif.getDate());
            stmt.setInt(5, notif.getStatus());
            stmt.setInt(6, notif.getType());
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
    public Notification viewNotification(String userID, int notifID) {
        Notification notif = new Notification();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM NOTIFICATION WHERE userId = ? and notifId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userID);
            stmt.setInt(2, notifID);
            ResultSet rs = stmt.executeQuery();
            if(rs == null) {
                return null;
            }
            notif.setNotifId(notifID);
            notif.setUserId(userID);
            notif.setDate(rs.getDate("date"));
            notif.setMessage(rs.getString("message"));
            notif.setTitle(rs.getString("title"));
            notif.setType(rs.getInt("type"));
            notif.setStatus(0);
            stmt.close();
            sql = "UPDATE NOTIFICATION SET VALUE status = 0 WHERE userId = ? and notifId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userID);
            stmt.setInt(2, notifID);
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
        return notif;
    }

    @Override
    public ArrayList<Notification> viewNotificationList(String userID) {
        ArrayList<Notification> notifList = new ArrayList<Notification>();
        DBUtils util = new DBUtils();
        Connection conn = util.connect();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM NOTIFICATION WHERE userId = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            if(rs == null) {
                return null;
            }
            while(rs.next()) {
                Notification notif = new Notification();
                notif.setNotifId(rs.getInt("notifId"));
                notif.setUserId(userID);
                notif.setDate(rs.getDate("date"));
                notif.setMessage(rs.getString("message"));
                notif.setTitle(rs.getString("title"));
                notif.setType(rs.getInt("type"));
                notif.setStatus(rs.getInt("status"));
                notifList.add(notif);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            util.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
