package com.example.sample1.notice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NoticeModel {
    private int id;
    private String title;
    private String contents;
    private LocalDateTime regDate;

}
