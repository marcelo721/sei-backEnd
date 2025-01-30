package com.marcelo721.SEI.services.exceptions;

public class OtpExpiredException extends RuntimeException{

    public OtpExpiredException(String message){
        super(message);
    }
}
