package com.flipkart.exceptions;

public class CourseNotFoundException extends Exception{
	
	public CourseNotFoundException()
	{
		System.out.println("No courses found");
	}

}
