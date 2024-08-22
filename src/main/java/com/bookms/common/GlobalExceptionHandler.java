package com.bookms.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle CustomException
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(DataNotFoundException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

//    // Handle all other exceptions
//    @ExceptionHandler(UpdateFailedException.class)
//    public ResponseEntity<ErrorResponse> handleUpdateFailedException(Exception ex, HttpServletRequest request) {
//    	ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage());
//        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
