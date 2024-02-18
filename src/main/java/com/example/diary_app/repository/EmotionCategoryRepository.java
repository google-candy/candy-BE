package com.example.diary_app.repository;

import com.example.diary_app.entity.EmotionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmotionCategoryRepository extends JpaRepository<EmotionCategory, Long> {
    Optional<EmotionCategory> findByEmotionCategoryName(String emotionCategoryName);
}

