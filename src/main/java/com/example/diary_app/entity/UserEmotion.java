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
@Table(name = "user_emotion")
public class UserEmotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_emotion_id", nullable = false)
    private Long userEmotionId;

    @Column(name = "emotion_date")
    @Temporal(TemporalType.DATE)
    private Date emotionDate;

    @Column(name = "user_emotion_name", length = 255)
    private String userEmotionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emotion_category_id")
    private EmotionCategory emotionCategory;
    // Getters and Setters
}

