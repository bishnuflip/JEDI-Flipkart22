package com.flipkart.service;

import com.flipkart.bean.Student;

public interface UserInterface {
	/**
	 * Method to add credentials of Users
	 * @param user contains the student details
	 */
	public void addUserdata(Student student);

	/**
	 * method to help user change password
	 * @param userID
	 * @param password
	 * @return
	 */
	public boolean changePassword(String userID, String password);
}
