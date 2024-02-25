//package src.main.java.com.example.diary_app.config;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import jakarta.annotation.PostConstruct;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//
//@Configuration
//public class FirebaseConfig {
//
//    @PostConstruct
//    public void init(){
//        try{
//            FileInputStream serviceAccount =
//                    new FileInputStream("src/main/resources/diaryapp-5a047-firebase-adminsdk-bnow2-cca8c813be.json");
//
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .build();
//
//            FirebaseApp.initializeApp(options);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//
//        }
//    }
//}
