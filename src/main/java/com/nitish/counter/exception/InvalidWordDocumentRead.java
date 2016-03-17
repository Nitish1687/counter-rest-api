package com.nitish.counter.exception;

/**
 * Created by nsm1211 on 16-03-2016.
 */
public class InvalidWordDocumentRead extends RuntimeException {

    public InvalidWordDocumentRead(String message, Throwable exception) {
        super(message, exception);
    }
}
