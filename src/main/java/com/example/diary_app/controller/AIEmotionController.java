package com.example.diary_app.controller;

import com.example.diary_app.DTO.AIEmotionDto;
import com.example.diary_app.service.AIEmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/ai-emotions")
public class AIEmotionController {

    private final AIEmotionService aiEmotionService;

    @Autowired
    public AIEmotionController(AIEmotionService aiEmotionService) {
        this.aiEmotionService = aiEmotionService;
    }

    @GetMapping
    public List<AIEmotionDto> getAllAiEmotionsWithEmotionCategory() {
        return aiEmotionService.findAllAIEmotionsWithEmotionCategory();
    }
}

