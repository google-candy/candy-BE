package com.example.diary_app.repository;

import com.example.diary_app.entity.EmotionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionCategoryRepository extends JpaRepository<EmotionCategory, Long> {
}
