package com.pelosi.notes.exception;

public class InvalidCredentialsException extends IllegalArgumentException{
    public InvalidCredentialsException(String s) {
        super(s);
    }
}
