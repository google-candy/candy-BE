package com.example.diary_app.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "emotion_category")
public class EmotionCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 값 자동증가
    @Column(name = "emotion_category_id", nullable = false)
    private Long emotionCategoryId;

    @Column(name = "emotion_category_name", nullable = true, length = 255)
    private String emotionCategoryName;

    @Lob
    @Column(name = "emotion_image", nullable = true)
    private byte[] emotionImage;

    @Column(name = "count", nullable = true)
    private Long count;
}
