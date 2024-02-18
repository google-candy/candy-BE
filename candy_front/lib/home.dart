import 'package:flutter/material.dart';
import 'colors.dart';
import 'chat.dart'; // 일기 작성(채팅) 페이지 파일
import 'calendar.dart'; // 캘린더 페이지 파일
import 'emotion.dart'; // 감정 모음집 페이지 파일

class HomePage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.backGroundColor,
      body: Column(
        children: <Widget>[
          SizedBox(height: 100), // AppBar를 아래로 내리기 위한 여백
          Material(
            color: AppColors.backGroundColor,
            //elevation: 4.0, // 그림자 효과
            child: Container(
              //height: 56, // 표준 AppBar 높이
              child: Center(
                child: Text(
                  'CANDY',
                  style: TextStyle(
                    fontSize: 30.0,
                    //fontWeight: FontWeight.bold,
                  ),
                ),
              ),
            ),
          ),

          SizedBox(height: 40,),

          Expanded(
            child: Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.start,
                children: <Widget>[
                  // 사용자 박스 부분
                  Container(
                    decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.circular(30),
                    ),
                    height: 150, width: 370,
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: <Widget>[
                        CircleAvatar(
                          radius: 55,
                          backgroundImage: AssetImage('assets/duck.png'),
                        ),

                        SizedBox(width: 25), // 오리 이미지와 텍스트 사이의 간격
                        Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: <Widget>[
                            SizedBox(height:35),
                            Text(
                                '어서와 희선아!',
                                style: TextStyle(
                                  fontSize: 30,
                                )
                            ), // 인사말 텍스트
                            Text(
                                '아직 오늘 일기를 쓰지 않았구나',
                                style: TextStyle(
                                  fontSize: 18,
                                )
                            ), // 일기 유무 텍스트
                            SizedBox(width: 10,),
                          ],
                        ),
                      ],
                    ),
                  ),
                  SizedBox(height: 30), // 여백 추가
                  // '오늘 일기 쓰러 가기' 버튼
                  ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      backgroundColor: AppColors.pointColor1, // 버튼 색상
                      foregroundColor: Colors.black, // 버튼 텍스트 색상
                      minimumSize: Size(370, 290), // 버튼 크기
                      shape: RoundedRectangleBorder( // 모서리를 각지게 설정
                        borderRadius: BorderRadius.circular(30), // 0은 완전 각진 모서리
                      ),
                    ),
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => ChatPage()),
                      );
                    },
                    child: Column(
                      mainAxisSize: MainAxisSize.min, // Column의 크기를 자식들의 크기에 맞춤
                      children: <Widget>[
                        SizedBox(height: 20,),
                        Text(
                          '오늘 일기 쓰러 가기',
                          style: TextStyle(
                            fontSize: 30, // 텍스트 크기
                            color: Colors.black, // 텍스트 색상
                          ),
                        ),
                        Container(
                          width: 220, // 이미지 너비 조절
                          height: 220, // 이미지 높이 조절
                          child: Image.asset('assets/notepad-117597_1280 2.png', // 이미지 파일명을 실제 파일명으로 변경
                            fit: BoxFit.contain, // 이미지가 Container 안에 맞게 조절
                          ),
                        ),
                      ],
                    ),
                  ),

                  SizedBox(height: 10),// 여백 추가

                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [

                      // 일기장 버튼
                      ElevatedButton(
                        style: ElevatedButton.styleFrom(
                          backgroundColor: AppColors.pointColor2, // 버튼 색상
                          foregroundColor: Colors.black, // 버튼 텍스트 색상
                          minimumSize: Size(180,145),
                          shape: RoundedRectangleBorder( // 모서리를 각지게 설정
                            borderRadius: BorderRadius.circular(30), // 0은 완전 각진 모서리
                          ),
                        ),

                        child: Column(
                          mainAxisSize: MainAxisSize.min, // Column의 크기를 자식들의 크기에 맞춤
                          children: <Widget>[
                            SizedBox(height: 10,),
                            Text(
                              '일기장',
                              style: TextStyle(
                                fontSize: 20, // 텍스트 크기
                                color: Colors.black, // 텍스트 색상
                              ),
                            ),
                            Container(
                              width: 90, // 이미지 너비 조절
                              height: 90, // 이미지 높이 조절
                              child: Image.asset('assets/pngwing.png', // 이미지 파일명을 실제 파일명으로 변경
                                fit: BoxFit.contain, // 이미지가 Container 안에 맞게 조절
                              ),
                            ),
                            SizedBox(height:10),
                          ],
                        ),

                        onPressed: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(builder: (context) => CalendarPage()),
                          );
                        },
                      ),

                      // '감정 모음집' 버튼
                      ElevatedButton(
                        style: ElevatedButton.styleFrom(
                          backgroundColor: AppColors.pointColor3, // 버튼 색상
                          foregroundColor: Colors.black, // 버튼 텍스트 색상
                          minimumSize: Size(180, 145), // 버튼 크기
                          shape: RoundedRectangleBorder( // 모서리를 각지게 설정
                            borderRadius: BorderRadius.circular(30), // 모서리 둥글게
                          ),
                        ),
                        child: Column(
                          mainAxisSize: MainAxisSize.min, // Column의 크기를 자식들의 크기에 맞춤
                          children: <Widget>[
                            SizedBox(height: 10,),
                            Text(
                              '감정 모음집',
                              style: TextStyle(
                                fontSize: 20, // 텍스트 크기
                                color: Colors.black, // 텍스트 색상
                              ),
                            ),
                            Container(
                              width: 90, // 이미지 너비 조절
                              height: 90, // 이미지 높이 조절
                              child: Image.asset(
                                'assets/pictures.png', // 이미지 파일명을 실제 파일명으로 변경
                                fit: BoxFit.fill, // 이미지가 컨테이너를 꽉 채우도록 조절하되 비율 유지
                              ),
                            ),
                          ],
                        ),
                        onPressed: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(builder: (context) => EmotionCollectionPage()),
                          );
                        },
                      ),

                    ],
                  )

                ],

              ),
            ),
          ),
        ],
      ),
    );

  }
}

