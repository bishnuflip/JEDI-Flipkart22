/**
 * 
 */
package com.flipkart.Exceptions;

/**
 * @author jain.harshil
 *
 */
public class AdminNotFoundException extends Exception{
	
	public AdminNotFoundException() {
		
	}
	public AdminNotFoundException(int id) {
		super();
		System.out.println("Admin does not exists with id "+ id);
	}
	@Override
	public String getMessage() {
		
		return "Admin does not exists";
	}

}
