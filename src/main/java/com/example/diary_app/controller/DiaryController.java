package com.example.diary_app.controller;

import com.example.diary_app.entity.Diary;
import com.example.diary_app.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

package com.example.candysihyeon.controller;


import com.example.candysihyeon.service.FirebaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiaryContoller {
    private final FirebaseService firebaseService;


    // 생성자 만들기
    public DiaryContoller(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @PostMapping("/diary")
    public ResponseEntity<String> answerQuestion(@RequestBody String answer){

        // 플러터에게서 받은 답변 파베에 저장하기
        firebaseService.saveAnswer(answer);

        // 파베에 저장된 질문 플러터로 전달
        String question = firebaseService.getNextQuestion();

    @GetMapping("/search")
    public List<DiaryDto> getDiariesByDate(
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return diaryService.findDiariesByDate(date);
        return ResponseEntity.ok(question);
    }

//    for(int i=0; i<5; i++){
//        Stirng answer = receive
//    }
}
