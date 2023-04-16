package com.example.sample1.notice.repository;

import com.example.sample1.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {

    List<Notice> findByIdIn(List<Long> idList);
}
