package com.example.diary_app.service;

import com.example.diary_app.DTO.DiaryDto;
import com.example.diary_app.entity.Diary;
import com.example.diary_app.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

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
}
