package com.flipkart.bean;

import java.util.Date;

public class Notification {

	private String userId;
	private int notifId;
	private String title;
	private String message;
	private Date date;
	private int status;  //indicates read/unread status
	private int type;   //indicates payment or general or issue

	/**
	 * returns user id to whom the notification belongs
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * sets user id to whom the notification belongs
	 * @param uId
	 */
	public void setUserId(String uId) {
		this.userId = uId;
	}

	/**
	 * returns the notification id
	 * @return
	 */
	public int getNotifId() {
		return notifId;
	}

	/**
	 * sets the notification id
	 * @param notifId
	 */
	public void setNotifId(int notifId) {
		this.notifId = notifId;
	}

	/**
	 * sets the title of notification
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * returns title of notification
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * returns message content of notification
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * sets the notification content
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * returns the notification date
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * sets the notification date
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * returns read status of notification
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * sets the read status of notification
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * returns type of notification
	 * @return
	 */
	public int getType() {
		return type;
	}

	/**
	 * sets the type of notification
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}
}
