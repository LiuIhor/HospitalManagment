package com.hospitalManagement.exception_handling;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}