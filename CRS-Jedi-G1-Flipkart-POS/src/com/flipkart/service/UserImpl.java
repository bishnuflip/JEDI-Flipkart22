package com.flipkart.service;

import java.util.Date;

import com.flipkart.app.CRSApplication;
import com.flipkart.bean.Notification;
import com.flipkart.bean.Student;

public class UserImpl implements UserInterface {

	@Override
	public void addUserdata(Student student) {
		UserDaoInterface userDaoOperation = new UserDaoOperation();
		if(student.getPasswordHash().length()<4)
			System.out.println("Password is very weak");
		if(userDaoOperation.addUsertData(student))
		{
			Notification notification = new Notification();
			//notification.setUserId(CRSApplication.getUserId());
			notification.setMessage("Welcome to our CRS Application portal!!!");
			Date date = new Date();
			notification.setDateTime(date);
			notification.setUserType(2);
			AdminDaoInterface adminDao = new AdminDaoOperation();
			adminDao.generateNotification(notification);
		}
	}

}
