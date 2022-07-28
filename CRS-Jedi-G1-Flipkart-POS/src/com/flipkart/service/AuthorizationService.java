/**
 * 
 */
package com.flipkart.service;

import java.util.HashMap;

import com.flipkart.bean.User;

/**
 * @author jain.harshil
 *
 */
public class AuthorizationService {
	static HashMap<Integer, User> credentials = new HashMap<Integer, User>();
	
	public static void addUser(Integer userId, User user) {
		credentials.put(userId, user);
	}
	public int authorize(Integer userId, String password) {
		// TODO Auto-generated method stub
		boolean success = credentials.containsKey(userId) && credentials.get(userId).getPasswordHash() == password;
		if(!success) return -1;
		int role = credentials.get(userId).getRole();
		return role;
	}
	public static void removeUser(int professorId) {
		// TODO Auto-generated method stub
		if(credentials.containsKey(professorId)) credentials.remove(professorId);
		
	}
	 
	

}