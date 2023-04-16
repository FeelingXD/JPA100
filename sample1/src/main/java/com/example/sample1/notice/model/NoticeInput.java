package com.example.sample1.notice.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class NoticeInput {

//    @Size(min = 10,message = "제목은 10자 이상 100자 이하여야합니다.",max = 100)
    @NotBlank(message = "제목은 필수 항목입니다.")
    private String title;
//    @Size(min = 50,message = "제목은 50자 이상 1000자 이하여야합니다.",max = 1000)

    @NotBlank(message = "내용은 필수 항목입니다.")
    private String contents;

}
