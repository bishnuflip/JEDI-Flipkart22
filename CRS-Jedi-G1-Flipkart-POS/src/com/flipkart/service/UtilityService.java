/**
 * 
 */
package com.flipkart.service;

import com.flipkart.dao.UserDaoImpl;
import com.flipkart.dao.UserDaoInterface;

import java.util.Random;

/**
 * @author jain.harshil
 *
 */
public class UtilityService {
	static String autoIds = "";
	
	public static String getId(int role) {
		UserDaoInterface user = new UserDaoImpl();
		int num = user.getLastIndex(role);
		if(role == 1) {
			autoIds = "A_";
		}
		else if(role == 2) {
			autoIds = "S_";
		}
		else if(role == 3) {
			autoIds = "P_";
		}
		else {
			return Integer.toString(num);
		}
		autoIds = autoIds + Integer.toString(num);
		return autoIds;
	}

}
