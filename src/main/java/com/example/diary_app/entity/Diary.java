package com.example.diary_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "diary")
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id", nullable = false)
    private Long diaryId;

    @Column(name = "diary_date")
    @Temporal(TemporalType.DATE)
    private Date diaryDate;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "emotion_name")
    private String emotionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emotion_category_id")
    private EmotionCategory emotionCategory;
}
