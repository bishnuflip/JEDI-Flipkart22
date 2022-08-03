/**
 * 
 */
package com.flipkart.Exceptions;

/**
 * Exception to check if the professor is not found or not
 * @author JEDI-03
 *
 */
public class ProfessorNotFoundException extends Exception{
	
	public ProfessorNotFoundException() {
		System.out.println("Professor Not Found");
	}

}
