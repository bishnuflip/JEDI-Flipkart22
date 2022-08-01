package com.flipkart.service;

import java.util.Date;

import com.flipkart.app.CRSApplication;
import com.flipkart.bean.Notification;
import com.flipkart.bean.Student;

public class UserImpl implements UserInterface {

	@Override
	public void addUserdata(Student student) {
		UserDaoInterface userDaoImpl = new UserDaoImpl();
		if(student.getPasswordHash().length()<4)
			System.out.println("Password is very weak");
		if(userDaoImpl.addUsertData(student))
		{
			Notification notification = new Notification();
			//notification.setUserId(CRSApplication.getUserId());
			notification.setMessage("Welcome to our CRS Application portal!!!");
			Date date = new Date();
			notification.setDateTime(date);
			notification.setUserType(2);
			AdminDaoInterface adminDao = new AdminDaoImpl();
			adminDao.generateNotification(notification);
		}
	}

}
