package com.nutech.nutechassignment.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException() {
        super("User with that email already exists, try something else");
    }
}
