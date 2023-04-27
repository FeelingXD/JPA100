package com.example.sample1.user.model;

import lombok.*;

@Builder
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginToken {
    private String token;
}
