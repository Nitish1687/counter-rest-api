package com.nitish.counter.exception;

/**
 * Created by nsm1211 on 16-03-2016.
 */
public class InvalidFileReadException extends  RuntimeException{

    public InvalidFileReadException(String message, Exception e) {
        super(message, e);
    }

    public InvalidFileReadException(String message) {
        super(message);
    }
}
