package com.example.diary_app.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DiaryDto {
    private Date diaryDate;
    private String content;
    private String emotionName;
    private byte[] emotionImage;
}

