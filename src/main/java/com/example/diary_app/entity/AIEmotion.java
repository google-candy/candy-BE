package com.example.diary_app.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "ai_emotion")
public class AIEmotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ai_emotion_id", nullable = false)
    private Long ai_emotion_id;

    @Column(name = "ai_emotion_name", length = 255)
    private String ai_emotion_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emotion_category_id")
    private EmotionCategory emotionCategory;
}
