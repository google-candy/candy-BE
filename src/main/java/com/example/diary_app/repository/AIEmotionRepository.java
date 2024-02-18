package com.example.diary_app.repository;

import com.example.diary_app.entity.AIEmotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AIEmotionRepository extends JpaRepository<AIEmotion, Long> {
}
