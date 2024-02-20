package com.example.diary_app.controller;

import com.example.diary_app.DTO.DiaryDto;
import com.example.diary_app.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/diaries")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @GetMapping("/search")
    public List<DiaryDto> getDiariesByDate(
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return diaryService.findDiariesByDate(date);
    }

}