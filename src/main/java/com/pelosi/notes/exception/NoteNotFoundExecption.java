package com.pelosi.notes.exception;

public class NoteNotFoundExecption extends IllegalArgumentException{
    public NoteNotFoundExecption(String s) {
        super(s);
    }
}
