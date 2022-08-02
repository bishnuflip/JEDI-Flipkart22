/**
 * 
 */
package com.flipkart.exceptions;

/**
 * Exception to check if the professor is not found or not
 * @author jain.harshil
 *
 */
public class ProfessorNotFoundException extends Exception{
	
	public ProfessorNotFoundException() {
		// TODO Auto-generated constructor stub
		System.out.println("Professor Not Found");
	}

}
