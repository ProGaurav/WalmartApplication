package com.ecommerce.walmart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorHandler> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setMessage(ex.getMessage());
        errorHandler.setDescription(ex.getCause());
        return new ResponseEntity(errorHandler, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorHandler> invalidUserExceptionHandler (InvalidUserException ex) {
        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setMessage(ex.getMessage());
        errorHandler.setDescription(ex.getCause());
        return new ResponseEntity<>(errorHandler, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorHandler> genericExceptionHandler (Exception ex) {
        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setMessage(ex.getMessage());
        errorHandler.setDescription(ex.getCause());
        return new ResponseEntity<>(errorHandler, HttpStatus.BAD_REQUEST);
    }
}
