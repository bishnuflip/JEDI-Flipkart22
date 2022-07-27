/**
 * 
 */
package com.flipkart.service;

/**
 * @author jain.harshil
 *
 */
public class UtilityService {
	static int autoIds = 0;
	
	public static int getId() {
		autoIds++;
		return autoIds;
	}
	

}
