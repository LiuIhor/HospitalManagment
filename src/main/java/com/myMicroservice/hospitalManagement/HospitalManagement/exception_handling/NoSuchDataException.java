package com.myMicroservice.hospitalManagement.HospitalManagement.exception_handling;

public class NoSuchDataException extends RuntimeException {

    public NoSuchDataException(String message) {
        super(message);
    }
}