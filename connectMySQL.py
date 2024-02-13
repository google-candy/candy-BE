import pymysql

conn = pymysql.connect(host='localhost', user='root', password='0000')

# 커서 생성
cur = conn.cursor()


# 데이터 입력
cur.execute("sql 실행문")
