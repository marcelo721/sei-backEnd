package com.marcelo721.SEI.services.exceptions;

public class EmailUniqueViolationException extends RuntimeException{

    public EmailUniqueViolationException(String message) {
        super(message);
    }
}
