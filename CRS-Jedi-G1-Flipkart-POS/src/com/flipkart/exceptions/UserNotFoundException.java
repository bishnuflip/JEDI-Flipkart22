/**
 * 
 */
package com.flipkart.exceptions;

/**
 * Exception to check if the user found or not
 * @author jain.harshil 
 *
 */
public class UserNotFoundException extends Exception{
	public UserNotFoundException()
	{
		System.out.println("User not found!!!");
	}
}
