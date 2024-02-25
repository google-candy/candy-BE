package com.example.diary_app.DTO;

public class AIEmotionDto {
    private String ai_emotion_name;
    private String emotion_image; // byte[] 대신 String 타입으로 변경

    // 기본 생성자
    public AIEmotionDto() {
    }

    // 모든 필드를 포함하는 생성자
    public AIEmotionDto(String ai_emotion_name, String emotion_image) {
        this.ai_emotion_name = ai_emotion_name;
        this.emotion_image = emotion_image;
    }

    // aiEmotionName의 getter
    public String getAIEmotionName() {
        return ai_emotion_name;
    }

    // aiEmotionName의 setter
    public void setAIEmotionName(String ai_emotion_name) {
        this.ai_emotion_name = ai_emotion_name;
    }

    // emotionImage의 getter. byte[] 대신 String 반환
    public String getEmotionImage() {
        return emotion_image;
    }

    // emotionImage의 setter. byte[] 대신 String을 받음
    public void setEmotionImage(String emotion_image) {
        this.emotion_image = emotion_image;
    }

    @Override
    public String toString() {
        return "AIEmotionDto{" +
                "ai_emotion_name='" + ai_emotion_name + '\'' +
                ", emotion_image='" + emotion_image + '\'' + // Base64 인코딩된 데이터를 안전하게 로깅
                '}';
    }
}
