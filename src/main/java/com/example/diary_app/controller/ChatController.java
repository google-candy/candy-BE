package com.example.candysihyeon.controller;


import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;


@Controller
@EnableWebSocketMessageBroker
public class ChatController{

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ChatController(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    // 이 경로를 통해 웹소켓으로 받는다.

    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public void processMessage(@Payload ChatMessage message) throws Exception{

        System.out.println("Received message: " + message.getContent());

        //firestore에 사용자 메세지 저장
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("diaries").document("gptDB");
        docRef.update("answer", FieldValue.arrayUnion(message.getContent()));

        //파이썬 ai 돌리기
        RestTemplate restTemplate = new RestTemplate();
        String AI_API_URL = "http://localhost:5000/api/chat/message";
        ChatMessage responseMessage = restTemplate.postForObject(AI_API_URL, message, ChatMessage.class);
        assert responseMessage != null;
        messagingTemplate.convertAndSend("/chat/answer", responseMessage);
    }

    public static class ChatMessage {
        private String content;

        public String getContent(){
            return content;
        }

        public void setContent(String content){
            this.content = content;
        }

    }
}

