import pymysql

conn = pymysql.connect(host='104.198.176.69', user='root', password='candy', db='myDiary', charset='utf8')

# 커서 생성
cur = conn.cursor()


# 데이터 입력
def insert_data(emotion_category, emotion_category_id):
    # SQL 쿼리 수정: 안전한 파라미터 바인딩 방식 사용
    cur.execute("INSERT INTO ai_emotion (ai_emotion_name,emotion_category_id) VALUES (%s, %s)", (emotion_category,emotion_category_id))
    # 데이터베이스에 변경사항 적용
    conn.commit()