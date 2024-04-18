package com.hhplus.ecommerce.exception;

public class InputValidationException extends IllegalArgumentException{
    public InputValidationException(String message) {
        super(message);
    }
}
