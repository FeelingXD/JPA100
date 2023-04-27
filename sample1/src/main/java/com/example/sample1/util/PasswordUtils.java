package com.example.sample1.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCrypt;

@UtilityClass
public class PasswordUtils {
    //패스워드 암호화 리턴 함수

    //입력한 패스워드를 해시된 패스워드랑 비교

    public static boolean equalPassword(String password, String encryptedPassword){
        return BCrypt.checkpw(password,encryptedPassword);
    }
}
