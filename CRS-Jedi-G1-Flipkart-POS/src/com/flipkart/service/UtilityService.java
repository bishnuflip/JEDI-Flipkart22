/**
 * 
 */
package com.flipkart.service;

import java.util.Random;

/**
 * @author jain.harshil
 *
 */
public class UtilityService {
	static String autoIds = "";
	
	public static String getId() {
		Random rand = new Random();
		int iNum = rand.nextInt(1000);
		autoIds = Integer.toString(iNum);
		return autoIds;
	}

}
