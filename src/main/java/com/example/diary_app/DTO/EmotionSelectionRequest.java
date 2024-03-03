package com.example.diary_app.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class EmotionSelectionRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date diaryDate; // 일기 날짜 추가
    private String emotionName;
    private Long emotionCategoryId;

    // Getters and Setters
}
