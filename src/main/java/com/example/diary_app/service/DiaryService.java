package com.example.diary_app.service;

import com.example.diary_app.DTO.DiaryDto;
import com.example.diary_app.entity.Diary;
import com.example.diary_app.entity.EmotionCategory;
import com.example.diary_app.repository.DiaryRepository;
import com.example.diary_app.repository.EmotionCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private EmotionCategoryRepository emotionCategoryRepository;


    public List<DiaryDto> findDiariesByDate(Date date) {
        return diaryRepository.findByDiaryDate(date).stream().map(diary -> {
            DiaryDto dto = new DiaryDto();
            dto.setDiaryDate(diary.getDiaryDate());
            dto.setContent(diary.getContent());
            dto.setEmotionName(diary.getEmotionName());
            if (diary.getEmotionCategory() != null) {
                dto.setEmotionImage(diary.getEmotionCategory().getEmotionImage());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void updateDiaryWithEmotionByDate(Date diaryDate, String emotionName, Long emotionCategoryId) {
        List<Diary> diaries = diaryRepository.findByDiaryDate(diaryDate);

        if (!diaries.isEmpty()) {
            Diary diary = diaries.get(0); // 하루에 한 개의 일기만 있다고 가정
            diary.setEmotionName(emotionName);

            // EmotionCategory 엔티티를 조회하여 설정
            EmotionCategory emotionCategory = emotionCategoryRepository.findById(emotionCategoryId)
                    .orElseThrow(() -> new RuntimeException("EmotionCategory not found with id: " + emotionCategoryId));
            diary.setEmotionCategory(emotionCategory);

            diaryRepository.save(diary);
        } else {
            throw new RuntimeException("No diary found for the given date");
        }
    }

}
