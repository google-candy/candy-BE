package com.example.diary_app.repository;

import com.example.diary_app.entity.UserEmotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEmotionRepository extends JpaRepository<UserEmotion, Long> {
}
