package com.example.sample1.notice.entity;

import com.example.sample1.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn
    private User user;
    private String title;
    private String contents;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    private int hits;

    private int likes;


    private boolean deleted=false;

    private LocalDateTime deletedDate=null;
}
