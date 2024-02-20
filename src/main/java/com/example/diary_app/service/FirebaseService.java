// 파이어베이스에 데이터 입력하고 꺼내오는 기능 구현한 파일임

package com.example.candysihyeon.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
//import

@Service
public class FirebaseService {

    @Autowired
    private final FirebaseApp firebaseApp;

    public FirebaseService(FirebaseApp firebaseApp) throws IOException {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setDatabaseUrl("https://diaryapp-5a047.firebaseio.com")
                .build();

        this.firebaseApp = FirebaseApp.initializeApp(options);

    }


    public void saveAnswer(String answer){
        // 파베에 답변 저장함
    }

    public String getNextQuestion(){
        // 파베에서 다음 질문 꺼내오기
        return "";
    }


}
