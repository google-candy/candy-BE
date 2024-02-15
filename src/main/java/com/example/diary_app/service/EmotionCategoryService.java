package com.example.diary_app.service;

import com.example.diary_app.entity.EmotionCategory;
import com.example.diary_app.repository.EmotionCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmotionCategoryService {

    @Autowired
    private EmotionCategoryRepository emotionCategoryRepository;

    public List<EmotionCategory> getAllEmotionCategories() {
        return emotionCategoryRepository.findAll();
    }

    public void deleteEmotionCategoryById(Long id) {
        emotionCategoryRepository.deleteById(id);
    }
}

