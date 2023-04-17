package com.example.sample1.notice.repository;

import com.example.sample1.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByIdIn(List<Long> idList);

    List<Notice> findByTitleAndContentsAndRegDateIsGreaterThanEqual(
            String title,
            String contents,
            LocalDateTime regDate);
}
