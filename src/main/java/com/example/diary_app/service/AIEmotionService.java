package com.example.diary_app.service;

import com.example.diary_app.DTO.AIEmotionDto;
import com.example.diary_app.repository.AIEmotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AIEmotionService {

    private final AIEmotionRepository aiEmotionRepository;

    @Autowired
    public AIEmotionService(AIEmotionRepository aiEmotionRepository) {
        this.aiEmotionRepository = aiEmotionRepository;
    }

    public List<AIEmotionDto> findAllAIEmotionsWithEmotionCategory() {
        return aiEmotionRepository.findAll().stream().map(AIEmotion -> {
            AIEmotionDto dto = new AIEmotionDto();
            dto.setAIEmotionName(AIEmotion.getAi_emotion_name());

            byte[] emotionImageBytes = AIEmotion.getEmotionCategory().getEmotionImage();
            String imageBase64 = Base64.getEncoder().encodeToString(emotionImageBytes);
            dto.setEmotionImage(imageBase64);
            return dto;
        }).collect(Collectors.toList());
    }

    public void deleteAllAIEmotions() {
        aiEmotionRepository.deleteAll();
    }
}

