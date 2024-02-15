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
    private Long emotion_category_id;

    @Column(name = "emotion_category_name", nullable = false, length = 255)
    private String emotion_category_name;

    @Column(name = "emotion_image", nullable = false, length = 255)
    private String emotion_image;

    @Column(name = "count", nullable = true)
    private Long count;
}
