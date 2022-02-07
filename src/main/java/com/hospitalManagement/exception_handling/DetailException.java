package com.hospitalManagement.exception_handling;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class DetailException {

    private final String massage;
    private final HttpStatus httpStatus;
    private final ZonedDateTime time;

    public DetailException(String massage, HttpStatus httpStatus, ZonedDateTime time) {
        this.massage = massage;
        this.httpStatus = httpStatus;
        this.time = time;
    }

    public String getMassage() {
        return massage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTime() {
        return time;
    }
}