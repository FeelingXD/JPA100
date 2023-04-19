package com.example.sample1.user.repository;

import com.example.sample1.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    int countByEmail(String email);
    Optional<User> findByIdAndPassword(Long id,String password);
}
