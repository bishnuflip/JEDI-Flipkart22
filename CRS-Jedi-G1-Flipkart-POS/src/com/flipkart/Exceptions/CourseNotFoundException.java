package com.flipkart.Exceptions;

public class CourseNotFoundException extends Exception{
    public CourseNotFoundException()
    {
        super("Course Not found\n");
    }
}
