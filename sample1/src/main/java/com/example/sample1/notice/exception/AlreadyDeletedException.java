package com.example.sample1.notice.exception;

public class AlreadyDeletedException extends RuntimeException {
    public AlreadyDeletedException(String s) {
        super(s);
    }
}
