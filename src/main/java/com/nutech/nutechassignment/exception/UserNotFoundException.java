package com.nutech.nutechassignment.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("User with that email not found, something went wrong");
    }
}
