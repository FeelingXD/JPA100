package com.example.sample1.notice.exception;

public class NoticeNotFoundException extends RuntimeException {
    public NoticeNotFoundException (String msg){
        super(msg);
    }
}
