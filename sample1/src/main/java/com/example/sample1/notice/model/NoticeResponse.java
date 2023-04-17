package com.example.sample1.notice.model;

import com.example.sample1.notice.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoticeResponse {


    private long id;


    private String title;
    private String contents;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    private int hits;

    private int likes;

    public static NoticeResponse of(Notice notice){
        return NoticeResponse.builder()
                .title(notice.getTitle())
                .contents(notice.getContents())
                .hits(notice.getHits())
                .likes(notice.getLikes())
                .build();
    }

}
