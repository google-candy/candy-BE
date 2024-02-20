package com.example.diary_app.service;

import com.example.diary_app.entity.EmotionCategory;
import com.example.diary_app.entity.UserEmotion;
import com.example.diary_app.repository.EmotionCategoryRepository;
import com.example.diary_app.repository.UserEmotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserEmotionService {

    @Autowired
    private UserEmotionRepository userEmotionRepository;

    @Autowired
    private EmotionCategoryRepository emotionCategoryRepository;

    public void saveUserEmotion(String userEmotionName, Long emotionCategoryId) {
        UserEmotion userEmotion = new UserEmotion();
        userEmotion.setEmotionDate(new Date()); // 현재 날짜 설정
        userEmotion.setUserEmotionName(userEmotionName);

        EmotionCategory emotionCategory = emotionCategoryRepository.findById(emotionCategoryId)
                .orElseThrow(() -> new RuntimeException("EmotionCategory not found with id " + emotionCategoryId));
        userEmotion.setEmotionCategory(emotionCategory);

        userEmotionRepository.save(userEmotion);
    }
}
