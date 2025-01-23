package com.marcelo721.SEI.services.exceptions;

public class AccountNotEnabledException extends RuntimeException{
    public AccountNotEnabledException(String message){
        super(message);
    }
}
