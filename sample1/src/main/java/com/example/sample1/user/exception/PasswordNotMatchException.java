package com.example.sample1.user.exception;

public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException(String s) {
        super(s);
    }
}
