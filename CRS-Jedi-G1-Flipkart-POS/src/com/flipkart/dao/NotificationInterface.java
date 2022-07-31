package com.flipkart.dao;

import com.flipkart.bean.Notification;

import java.util.ArrayList;

public interface NotificationInterface {

    /**
     * writes the notification into database
     * @param notif
     */
    public void writeNotification(Notification notif);

    /**
     * reads and returns the notification from database
     * @param notifID
     * @param userID
     * @return
     */
    public Notification viewNotification(String userID, int notifID);

    /**
     * lists all the notifications for a user
     * @param userID
     * @return
     */
    public ArrayList<Notification> viewNotificationList(String userID);
}
