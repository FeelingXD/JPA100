package com.example.sample1.notice.model;

import lombok.Data;

import java.util.List;

@Data
public class NoticeDeleteInput {
    private List<Long> idList;
}
