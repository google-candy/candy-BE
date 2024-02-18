package com.example.diary_app.service;

import com.example.diary_app.entity.EmotionCategory;
import com.example.diary_app.repository.EmotionCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public String updateEmotionCategoryImage(String categoryName, MultipartFile imageFile) {
        EmotionCategory emotionCategory = emotionCategoryRepository.findByEmotionCategoryName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found: " + categoryName));

        try {
            byte[] imageData = imageFile.getBytes();
            emotionCategory.setEmotionImage(imageData);
            emotionCategoryRepository.save(emotionCategory);
            return "Image updated successfully for the category: " + categoryName;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to update image for the category: " + categoryName;
        }
    }
}
