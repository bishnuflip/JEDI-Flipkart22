/**
 * 
 */
package com.flipkart.Exceptions;

/**
 * @author sayan
 *
 */
public class PasswordIsWeakException extends Exception {
	/**
	 * @param password -> password
	 */
	public PasswordIsWeakException() {
		 System.out.println("Entered password has less than 4 characters");
	}

	
	
}
