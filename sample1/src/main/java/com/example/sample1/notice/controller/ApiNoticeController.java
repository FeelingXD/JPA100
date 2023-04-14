package com.example.sample1.notice.controller;

import com.example.sample1.notice.entity.Notice;
import com.example.sample1.notice.model.NoticeInput;
import com.example.sample1.notice.model.NoticeModel;
import com.example.sample1.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ApiNoticeController {

    private final NoticeRepository noticeRepository;
   /* @GetMapping("/api/notice")
    public String noticeString() {
        return "공지사항입니다.";
    }*/

    /*public NoticeModel notice(){

        LocalDateTime regDate= LocalDateTime.of(2021,2,8,0,0);

        NoticeModel notice = new NoticeModel();
        notice.setId(1);
        notice.setTitle("공지사항입니다.");
        notice.setContents("공지사항 내용입니다.");
        notice.setRegDate(regDate);
        return notice;
    }*/
    @GetMapping("/api/notice")
    public List<NoticeModel> notice(){
        List<NoticeModel> noticeList= new ArrayList<>();

        noticeList.add(NoticeModel.builder()
                        .id(1)
                        .title("공지사항입니다.")
                        .contents("공지사항내용입니다.")
                        .regDate(LocalDateTime.of(2021,1,30,0,0))
                .build());

        noticeList.add(NoticeModel.builder()
                .id(2)
                .title("두번쨰 공지사항입니다.")
                .contents("두번쨰 공지사항내용입니다.")
                .regDate(LocalDateTime.of(2021,1,31,0,0))
                .build());

        return  noticeList;

    }
    /*
    @GetMapping("/api/notice3")
    public List<NoticeModel> notice3(){
        List<NoticeModel> noticeList = new ArrayList<>();
        return noticeList;
    }*/
    @PostMapping("/api/notice")
    public NoticeModel addNotice(@RequestParam String title ,@RequestParam String contents){

        NoticeModel notice = NoticeModel.builder()
                .title(title)
                .contents(contents)
                .id(1)
                .regDate(LocalDateTime.now())
                .build();

        return notice;

    }

    @PostMapping("/api/notice3")
    public NoticeModel addNotice(@RequestBody NoticeModel noticeModel){

        noticeModel.setId(3);
        noticeModel.setRegDate(LocalDateTime.now());

        return noticeModel;
    }



    @PostMapping("/api/notice4")
    public Notice addNotice(@RequestBody NoticeInput noticeInput) {

        Notice notice = Notice.builder()
                .title(noticeInput.getTitle())
                .contents(noticeInput.getContents())
                .regDate(LocalDateTime.now())
                .build();

        return noticeRepository.save(notice);
    }
}
