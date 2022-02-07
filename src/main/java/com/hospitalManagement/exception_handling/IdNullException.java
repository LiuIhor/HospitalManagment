package com.hospitalManagement.exception_handling;

public class IdNullException extends RuntimeException {

    public IdNullException(String message) {
        super(message);
    }

    public IdNullException() {
        super("The given id must not be null!");
    }
}