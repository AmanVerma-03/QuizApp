package com.example.quizapp.repository;

import com.example.quizapp.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    UserSession findByUserId(Long userId);
}