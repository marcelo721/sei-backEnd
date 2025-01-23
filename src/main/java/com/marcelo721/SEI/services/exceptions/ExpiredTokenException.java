package com.marcelo721.SEI.services.exceptions;

public class ExpiredTokenException extends RuntimeException{

    public ExpiredTokenException(String message){
        super(message);
    }
}
