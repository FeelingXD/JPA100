package com.example.sample1.notice.repository;

import com.example.sample1.notice.entity.NoticeLike;
import com.example.sample1.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeLikeRepository extends JpaRepository<NoticeLike,Long> {

    List<NoticeLike> findByUser(User user);
}
