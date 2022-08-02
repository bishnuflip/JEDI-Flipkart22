/**
 * 
 */
package com.flipkart.exceptions;

/**
 * Exception to check if GradeCard not published 
 * @author jain.harshil
 *
 */
public class GradeCardNotPublishedException extends Exception{
	public GradeCardNotPublishedException()
	{
		System.out.println("GradeCard is not published yet");
	}
}