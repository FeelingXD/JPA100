package com.example.sample1.user.controller;

import com.example.sample1.notice.entity.Notice;
import com.example.sample1.notice.model.NoticeResponse;
import com.example.sample1.notice.model.ResponseError;
import com.example.sample1.notice.repository.NoticeRepository;
import com.example.sample1.user.entity.User;
import com.example.sample1.user.exception.PasswordNotMatchException;
import com.example.sample1.user.model.UserInput;
import com.example.sample1.user.model.UserInputPassword;
import com.example.sample1.user.model.UserResponse;
import com.example.sample1.user.model.UserUpdate;
import com.example.sample1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ApiUserController {

    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;

    @PostMapping("/api/user31")
    public ResponseEntity<?> addUser31(@RequestBody @Validated UserInput userInput, Errors errors){

        List<ResponseError> list =new ArrayList<>();
        if(errors.hasErrors()){
            errors.getAllErrors().forEach(e->{
                list.add(ResponseError.of((FieldError)e));
            });
            return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/user32")
    public ResponseEntity<?> addUser32(@RequestBody @Validated UserInput userInput, Errors errors){

        List<ResponseError> list =new ArrayList<>();
        if(errors.hasErrors()){
            errors.getAllErrors().forEach(e->{
                list.add(ResponseError.of((FieldError)e));
            });
            return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .email(userInput.getEmail())
                .userName(userInput.getUserName())
                .password(userInput.getPassword())
                .phone(userInput.getPhone())
                .regDate(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/user33/{id}")
    public ResponseEntity<?> updateUser33(@PathVariable Long id,
                             @RequestBody UserUpdate userUpdate,
                             Errors errors){

        List<ResponseError> list= new ArrayList<>();
        if(errors.hasErrors()){
            errors.getAllErrors().forEach(e->list.add(ResponseError.of((FieldError) e)));

            return new ResponseEntity<>(list,HttpStatus.BAD_REQUEST);

        }

        User user= userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("사용자 정보가 없습니다."));

        user.setPhone(userUpdate.getPhone());
        user.setUpdateDate(LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/user34/{id}")
    public UserResponse getUser34(@PathVariable Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("정보없음"));

        return UserResponse.of(user);
    }

    @GetMapping("/api/user/{id}/notice35")
    public List<NoticeResponse> userNotice35(@PathVariable Long id){

        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("정보없음"));

        List<Notice> list = noticeRepository.findByUser(user);

        return list.stream().map(NoticeResponse::of).collect(Collectors.toList());
    }

    @PostMapping("/api/user36")
    public ResponseEntity<?> addUser36(@RequestBody @Validated UserInput userInput, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getAllErrors().stream().map(e->ResponseError.of((FieldError)e)).collect(Collectors.toList()));
        }

        if(userRepository.countByEmail(userInput.getEmail())>0)
            throw new RuntimeException("이메일이 존재합니다.");

        User user = User.builder()
                .email(userInput.getEmail())
                .userName(userInput.getUserName())
                .password(userInput.getPassword())
                .phone(userInput.getPhone())
                .regDate(LocalDateTime.now())
                .build();

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/api/user/{id}/password")
    public HttpEntity<?> updateUserPassword37(@PathVariable Long id , @RequestBody UserInputPassword userInputPassword , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getAllErrors().stream().map(e->ResponseError.of((FieldError)e)).collect(Collectors.toList()));
        }
        System.out.println(userInputPassword.toString());
        User user= userRepository.findByIdAndPassword(id,userInputPassword.getPassword())
                .orElseThrow(() -> new PasswordNotMatchException("비밀번호가 일치하지않습니다."));
        user.setPassword(userInputPassword.getNewPassword());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/user38")
    public ResponseEntity<?> addUser38(@RequestBody @Validated UserInput userInput,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getAllErrors().stream().map(e->ResponseError.of((FieldError)e)).collect(Collectors.toList()));
        }
        if(userRepository.countByEmail(userInput.getEmail())>0)
            throw new RuntimeException("이메일이 존재합니다.");

        BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
        String encryptPassword = bCryptPasswordEncoder.encode(userInput.getPassword());

        User user = User.builder()
                .email(userInput.getEmail())
                .userName(userInput.getUserName())
                .password(encryptPassword)
                .phone(userInput.getPhone())
                .regDate(LocalDateTime.now())
                .build();

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }




}
