package com.ecommerce.walmart.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorHandler {
    private String message;
    private Throwable description;
}
