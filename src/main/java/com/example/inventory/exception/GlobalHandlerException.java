package com.example.inventory.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound (ResourceNotFoundException ex, HttpServletRequest httpServletRequest){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ErrorResponse errorBody = new ErrorResponse(
            httpStatus.value(), httpStatus.getReasonPhrase(), 
            ex.getMessage(),httpServletRequest.getRequestURI(), LocalDateTime.now());
        return ResponseEntity.status(httpStatus).body(errorBody);
    }

}
