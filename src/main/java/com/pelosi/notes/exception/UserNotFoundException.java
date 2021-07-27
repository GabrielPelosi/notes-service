package com.pelosi.notes.exception;

public class UserNotFoundException extends IllegalArgumentException{

    public UserNotFoundException(String s) {
        super(s);
    }
}
