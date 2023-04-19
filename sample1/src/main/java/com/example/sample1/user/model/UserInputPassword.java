package com.example.sample1.user.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInputPassword {
    @NotBlank(message = "현재 비밀번호는 필수 항목입니다.")
    private String password;
    @Size(min = 4,max = 20,message = "비밀번호는 4-20 사이로 입력해주세요")
    @NotBlank(message = "신규 비밀번호는 필수 항목입니다.")
    private String newPassword;
}
