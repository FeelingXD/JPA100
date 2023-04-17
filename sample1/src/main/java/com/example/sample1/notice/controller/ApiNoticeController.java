package com.example.sample1.notice.controller;

import com.example.sample1.notice.entity.Notice;
import com.example.sample1.notice.exception.AlreadyDeletedException;
import com.example.sample1.notice.exception.DuplicateNoticeException;
import com.example.sample1.notice.exception.NoticeNotFoundException;
import com.example.sample1.notice.model.ResponseError;
import com.example.sample1.notice.model.NoticeDeleteInput;
import com.example.sample1.notice.model.NoticeInput;
import com.example.sample1.notice.model.NoticeModel;
import com.example.sample1.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ApiNoticeController {

    @ExceptionHandler(DuplicateNoticeException.class)
    public ResponseEntity<String> duplicateNotice(DuplicateNoticeException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
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
    public List<NoticeModel> notice() {
        List<NoticeModel> noticeList = new ArrayList<>();

        noticeList.add(NoticeModel.builder()
                .id(1)
                .title("공지사항입니다.")
                .contents("공지사항내용입니다.")
                .regDate(LocalDateTime.of(2021, 1, 30, 0, 0))
                .build());

        noticeList.add(NoticeModel.builder()
                .id(2)
                .title("두번쨰 공지사항입니다.")
                .contents("두번쨰 공지사항내용입니다.")
                .regDate(LocalDateTime.of(2021, 1, 31, 0, 0))
                .build());

        return noticeList;

    }

    /*
    @GetMapping("/api/notice3")
    public List<NoticeModel> notice3(){
        List<NoticeModel> noticeList = new ArrayList<>();
        return noticeList;
    }*/
    @PostMapping("/api/notice")
    public NoticeModel addNotice(@RequestParam String title, @RequestParam String contents) {

        NoticeModel notice = NoticeModel.builder()
                .title(title)
                .contents(contents)
                .id(1)
                .regDate(LocalDateTime.now())
                .build();

        return notice;

    }

    @PostMapping("/api/notice3")
    public NoticeModel addNotice(@RequestBody NoticeModel noticeModel) {

        noticeModel.setId(3);
        noticeModel.setRegDate(LocalDateTime.now());

        return noticeModel;
    }


    @PostMapping("/api/notice4")
    public Notice addNotice4(@RequestBody NoticeInput noticeInput) {

        Notice notice = Notice.builder()
                .title(noticeInput.getTitle())
                .contents(noticeInput.getContents())
                .regDate(LocalDateTime.now())
                .build();

        return noticeRepository.save(notice);
    }

    @PostMapping("/api/notice5")
    public Notice addNotice5(NoticeInput noticeInput) {

        Notice notice = Notice.builder()
                .title(noticeInput.getTitle())
                .contents(noticeInput.getContents())
                .regDate(LocalDateTime.now())
                .hits(0)
                .likes(0)
                .build();

        return noticeRepository.save(notice);
    }

    @GetMapping("/api/notice6/{id}")
    public Notice notice(@PathVariable Long id) {

        return noticeRepository.findById(id).orElseThrow(() -> new RuntimeException("miss id"));
    }

//    @PutMapping("/api/notice/{id}")
    public void updateNotice(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {

        Notice notice = noticeRepository.findById(id).orElseThrow(() -> {
            return null;
        });


        notice.setTitle(noticeInput.getTitle());
        notice.setContents(noticeInput.getContents());
        notice.setUpdateDate(LocalDateTime.now());

        noticeRepository.save(notice);


    }
    @ExceptionHandler(NoticeNotFoundException.class)
    public ResponseEntity<String> handlerNoticeNotFoundException(NoticeNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyDeletedException.class)
    public ResponseEntity<String> handlerAlreadyDeletedException(AlreadyDeletedException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }

//    @PutMapping("/api/notice/{id}")
    public void updateNotice2(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {

        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new NoticeNotFoundException("공지사항이 존재하지 않습니다."));


        notice.setTitle(noticeInput.getTitle());
        notice.setContents(noticeInput.getContents());
        notice.setUpdateDate(LocalDateTime.now());

        noticeRepository.save(notice);


    }

    @PatchMapping("/api/notice/hits/{id}")
    public void noticeHits(@PathVariable Long id){
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new NoticeNotFoundException("공지사항이 존재하지 않습니다."));

        notice.setHits(notice.getHits()+1);
        noticeRepository.save(notice);

    }

//    @DeleteMapping("/api/notice/{id}")
    public void deleteNotice(@PathVariable Long id){
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new NoticeNotFoundException("공지사항이 존재하지 않습니다."));
        noticeRepository.delete(notice);
    }


    @DeleteMapping("/api/notice/{id}")
    public void deleteNotice23(@PathVariable Long id){
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new NoticeNotFoundException("공지사항이 존재하지 않습니다."));

        if(notice.isDeleted()){
            throw new AlreadyDeletedException("이미 삭제된 글입니다.");
        }

        notice.setDeleted(true);
        notice.setDeletedDate(LocalDateTime.now());

        noticeRepository.save(notice);
    }

    @DeleteMapping("/api/notice")
    public void deleteNoticeList24(@RequestBody NoticeDeleteInput noticeDeleteInput){
        List<Notice> list = noticeRepository.findByIdIn(noticeDeleteInput.getIdList());
        if(list.size()==0){
            throw new NoticeNotFoundException("공지사항이 존재하지 않습니다.");
        }

        list.stream().forEach(notice -> {notice.setDeleted(true); notice.setDeletedDate(LocalDateTime.now());});

        noticeRepository.saveAll(list);
    }

    @DeleteMapping("/api/notice/all")
    public void deleteAll25(){
        noticeRepository.deleteAll();
    }

//    @PostMapping("/api/notice")
    public void addNotice26(@RequestBody NoticeInput noticeInput){
        Notice notice = Notice.builder().
                title(noticeInput.getTitle()).
                contents(noticeInput.getContents()).
                hits(0).
                likes(0).
                regDate(LocalDateTime.now())
                .build();

        noticeRepository.save(notice);

    }

    @PostMapping("/api/notice27")
    public ResponseEntity<Object> addNotice27(@RequestBody @Validated NoticeInput noticeInput
    , Errors errors){

        if(errors.hasErrors()){
            List<ResponseError> responseErrors = new ArrayList<>();

            errors.getAllErrors().stream().forEach(e->{

                responseErrors.add(ResponseError.of((FieldError) e));
            });
            return new ResponseEntity<>(responseErrors,HttpStatus.BAD_REQUEST);
        }

        noticeRepository.save(Notice.builder().
                title(noticeInput.getTitle()).
                contents(noticeInput.getContents()).
                hits(0).
                likes(0).
                regDate(LocalDateTime.now())
                .build());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/notice/latest/{size}")
    public Page<Notice> noticeLatest28(@PathVariable int size ){

        return noticeRepository.findAll(PageRequest.of(0,10, Sort.Direction.DESC,"regDate"));
    }
    
    @PostMapping("/api/notice30")
    public void addNotice30(@RequestBody NoticeInput noticeInput){
        
        //중복체크
        LocalDateTime checkDate=LocalDateTime.now().minusMinutes(1); //현재시간보다 1분전


        List<Notice> noticeList = noticeRepository.findByTitleAndContentsAndRegDateIsGreaterThanEqual(
                noticeInput.getTitle(),
                noticeInput.getContents(),
                checkDate);
        if(noticeList.size()>0){
            throw  new DuplicateNoticeException("동일한 내용의 공지사항이 존재합니다.");
        }

        noticeRepository.save(
                Notice.builder()
                        .title(noticeInput.getTitle())
                        .contents(noticeInput.getContents())
                        .regDate(LocalDateTime.now())
                        .likes(0)
                        .hits(0)
                        .build()
        );


    }





}
