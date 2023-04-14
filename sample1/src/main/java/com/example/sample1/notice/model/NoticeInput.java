package com.example.sample1.notice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoticeInput {
    private String title;
    private String contents;

}
