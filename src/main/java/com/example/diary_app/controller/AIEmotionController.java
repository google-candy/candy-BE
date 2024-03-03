package com.example.diary_app.controller;

import com.example.diary_app.DTO.AIEmotionDto;
import com.example.diary_app.DTO.EmotionSelectionRequest;
import com.example.diary_app.repository.AIEmotionRepository;
import com.example.diary_app.service.AIEmotionService;
import com.example.diary_app.service.DiaryService;
import com.example.diary_app.service.UserEmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/emotions")
public class AIEmotionController {

    private final AIEmotionService aiEmotionService;
    private final DiaryService diaryService;
    private final UserEmotionService userEmotionService;

    private AIEmotionRepository aiEmotionRepository;

    @Autowired
    public AIEmotionController(AIEmotionService aiEmotionService, DiaryService diaryService, UserEmotionService userEmotionService) {
        this.aiEmotionService = aiEmotionService;
        this.diaryService = diaryService;
        this.userEmotionService = userEmotionService;
    }

    @GetMapping("/ai-emotions")
    public List<AIEmotionDto> findAllAIEmotionsWithEmotionCategory() {
        return aiEmotionRepository.findAll().stream().map(aiEmotion -> {
            AIEmotionDto dto = new AIEmotionDto();
            dto.setAIEmotionName(aiEmotion.getAi_emotion_name());

            // BLOB 데이터를 Base64 문자열로 변환
            byte[] emotionImageBytes = aiEmotion.getEmotionCategory().getEmotionImage();
            String imageBase64 = Base64.getEncoder().encodeToString(emotionImageBytes);
            dto.setEmotionImage(imageBase64);

            return dto;
        }).collect(Collectors.toList());
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
