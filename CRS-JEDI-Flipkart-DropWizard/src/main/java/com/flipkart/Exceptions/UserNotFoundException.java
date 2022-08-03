package com.flipkart.Exceptions;

public class UserNotFoundException extends  Exception{
    public UserNotFoundException()
    {
        super("User not found\n");
    }
}
