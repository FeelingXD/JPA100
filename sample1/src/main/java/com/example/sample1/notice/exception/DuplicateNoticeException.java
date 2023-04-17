package com.example.sample1.notice.exception;

public class DuplicateNoticeException extends RuntimeException {
    public DuplicateNoticeException(String s) {
        super(s);
    }
}
