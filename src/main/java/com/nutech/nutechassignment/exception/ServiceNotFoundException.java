package com.nutech.nutechassignment.exception;

public class ServiceNotFoundException extends RuntimeException{

    public ServiceNotFoundException() {
        super("Service not found");
    }
}
