package com.example.diary_app.controller;

import com.example.diary_app.DTO.AIEmotionDto;
import com.example.diary_app.DTO.EmotionSelectionRequest;
import com.example.diary_app.service.AIEmotionService;
import com.example.diary_app.service.DiaryService;
import com.example.diary_app.service.UserEmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/emotions")
public class AIEmotionController {

    private final AIEmotionService aiEmotionService;
    private final DiaryService diaryService;
    private final UserEmotionService userEmotionService;

    @Autowired
    public AIEmotionController(AIEmotionService aiEmotionService, DiaryService diaryService, UserEmotionService userEmotionService) {
        this.aiEmotionService = aiEmotionService;
        this.diaryService = diaryService;
        this.userEmotionService = userEmotionService;
    }

    @GetMapping("/ai-emotions")
    public List<AIEmotionDto> getAllAiEmotionsWithEmotionCategory() {
        return aiEmotionService.findAllAIEmotionsWithEmotionCategory();
    }

    @PostMapping("/select")
    public ResponseEntity<String> selectEmotion(@RequestBody EmotionSelectionRequest request) {
        // 사용자 감정을 user_emotion에 저장
        userEmotionService.saveUserEmotion(request.getEmotionName(), request.getEmotionCategoryId());
        // 동일한 감정 정보를 해당 날짜의 일기에 업데이트
        diaryService.updateDiaryWithEmotionByDate(request.getDiaryDate(), request.getEmotionName(), request.getEmotionCategoryId());
        // ai_emotion 테이블의 데이터 삭제
        aiEmotionService.deleteAllAIEmotions();

        return ResponseEntity.ok("Emotion selected, diary updated, AI emotions cleared");
    }
}

