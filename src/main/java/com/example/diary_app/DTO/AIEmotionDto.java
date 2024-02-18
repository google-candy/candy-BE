package com.example.diary_app.DTO;

public class AIEmotionDto {
    private String ai_emotion_name;
    private byte[] emotion_image;

    // 기본 생성자
    public AIEmotionDto() {
    }

    // 모든 필드를 포함하는 생성자
    public AIEmotionDto(String ai_emotion_name, byte[] emotion_image) {
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

    // emotionImage의 getter
    public byte[] getEmotionImage() {
        return emotion_image;
    }

    // emotionImage의 setter
    public void setEmotionImage(byte[] emotion_image) {
        this.emotion_image = emotion_image;
    }

    @Override
    public String toString() {
        return "AIEmotionDto{" +
                "ai_emotion_name='" + ai_emotion_name + '\'' +
                ", emotion_image=[...]" + // 이미지 데이터는 로그에 직접 출력하는 것이 비효율적이므로 생략
                '}';
    }
}

