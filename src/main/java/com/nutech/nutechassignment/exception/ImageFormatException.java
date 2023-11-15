package com.nutech.nutechassignment.exception;

public class ImageFormatException extends RuntimeException{

    public ImageFormatException() {
        super("Format file or image is not valid");
    }
}
