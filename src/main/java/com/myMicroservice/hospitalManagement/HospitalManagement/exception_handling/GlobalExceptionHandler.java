package com.myMicroservice.hospitalManagement.HospitalManagement.exception_handling;

import org.springframework.http.HttpStatus;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchDataException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public IncorrectData handleException(
            NoSuchDataException exception) {
        IncorrectData data = new IncorrectData();
        System.out.println("I catch exception");
        data.setInformation(exception.getMessage());
        return data;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public IncorrectData handleException(IdNullException exception) {
        IncorrectData data = new IncorrectData();
        data.setInformation(exception.getMessage());
        return data;
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public IncorrectData handleException(
            java.lang.NumberFormatException exception) {
        IncorrectData data = new IncorrectData();
        data.setInformation("Check your request. Because I have exception with NUMBER FORMAT");
        return data;
    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public IncorrectData handleException(
//            Exception exception) {
//        IncorrectData data = new IncorrectData();
//        data.setInformation(exception.getMessage());
//
//        System.out.println("I catch exception NumberFormatException II");
//        return data;
//    }
}