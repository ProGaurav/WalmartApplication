package com.ecommerce.walmart.exceptions;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
