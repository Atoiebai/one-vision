package com.onevision.exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String s) {
        super(s);
    }
}
