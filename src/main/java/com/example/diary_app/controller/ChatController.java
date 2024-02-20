package src.main.java.com.example.diary_app.controller;


import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
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
    @SendTo("/chat/answer")
    public void processMessage(ChatMessage message) throws Exception{

        //firestore에 사용자 메세지 저장
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("diaries").document("gptDB");
        docRef.update("answer", FieldValue.arrayUnion(message.getMessage()));

        //파이썬 ai 돌리기
        RestTemplate restTemplate = new RestTemplate();
        String AI_API_URL = "http://localhost:5000/api/chat/message";
        ChatMessage responseMessage = restTemplate.postForObject(AI_API_URL, message, ChatMessage.class);
        assert responseMessage != null;
        messagingTemplate.convertAndSend("/chat/answer", responseMessage);


    }

    private static class ChatMessage {
        private String content;

        public String getMessage(){
            return content;
        }

        public void setMessage(String message){
            this.content = message;
        }

    }
}




//
//@RestController
//public class ChatController {
//
//    // chatrequest 객체로 사용자 input 값을 가져온다.
//    @PostMapping("/diary/chat")
//    public ResponseEntity<String> processMessage(@RequestBody ChatRequest chatRequest){
//
//        //user_input 값 get으로 받기
//        String userMessage = chatRequest.getMessage();
//
//        // 파베에 사용자 answer 데이터 저장하는 과정
//        Firestore db = FirestoreClient.getFirestore();
//
//        //diary 컬렉션 'gptDB' 문서 참조 가져오기
//        DocumentReference docRef = db.collection("diaries").document("gptDB");
//
//        //'answer' 배열 필드에 새로운 메세지 추가하기
////        ApiFuture<WriteResult> arrayUnionFuture = docRef.update("answer", FieldValue.arrayUnion(userMessage));
//        docRef.update("answer", FieldValue.arrayUnion(userMessage));
//
//
//        /////////////////////////
////        // 메세지 객체 생성
////        Map<String, Object> message = new HashMap<>();
////        message.put("answer", userMessage);
////        message.put("timestamp", System.currentTimeMillis());
//
//
//        //RestTemplate로 파이썬 서비스에 요청을 보냄. 인스턴스 생성
//        RestTemplate restTemplate = new RestTemplate();
//
//        //헤더 설정
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        //요청 본문 생성
//        HttpEntity<ChatRequest> request = new HttpEntity<>(chatRequest, headers);
//
//
//
//        //파이썬 flask 앱에 post 요청 보내기
//        String AI_API_URL = "http://localhost:5000/api/chat/message";
//        ResponseEntity<String> response = restTemplate.postForEntity(AI_API_URL, request, String.class);
//
//        //응답 본문에서 응답 메세지 추출 후 저장
//        String responseMessage = response.getBody();
//
//        // firestore에 데이터 저장
////        db.collection("diaries").document("chat").update(message);
//
//
//
//        // ai 로 부터 받은 응답 클라이언트에게 전달
//        return ResponseEntity.ok(responseMessage);
//    }
//
//    private static class ChatRequest {
//        private String message;
//
//        public String getMessage(){
//            return message;
//        }
//
//        public void setMessage(String message){
//            this.message = message;
//        }
//    }
//}


