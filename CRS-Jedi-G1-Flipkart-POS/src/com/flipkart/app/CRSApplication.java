package com.flipkart.app;

import java.util.Scanner;

public class CRSApplication {

    public static void main(String[] args) {
        System.out.println("Welcome to the Course Registration System.\nPlease select the type of user: ");
        Scanner ob = new Scanner(System.in);
        int type = ob.nextInt();
        switch(type) {
            case 1: System.out.println("Welcome Admin!");
                    //display admin menu with appropriate options
                    break;

            case 2: System.out.println("Welcome Student1");
                    //display student relevant options
                    break;

            case 3: System.out.println("Welcome Professor");
                    //display professor relevant options
                    break;

            default: System.out.println("Invalid user choice. Program will terminate.");
                    //find a way to restart software after invalid input
        }
    }

}
