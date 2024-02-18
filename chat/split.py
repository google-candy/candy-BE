
def seperate_conversation(text):
    # 대화를 줄바꿈으로 분리
    lines = text.split('\n')

    # USER와 리사의 대화를 저장할 배열 초기화
    user_responses = []
    lisa_responses = []

    # 각 줄을 순회하며 USER와 리사의 대화 분리
    for line in lines:
        # USER의 대화인 경우
        if line.startswith("USER:"):
            user_responses.append(line[5:].strip())  # USER: 다음의 텍스트를 추출하고 공백 제거 후 배열에 추가
        # 리사의 대화인 경우
        elif line.startswith("리사:"):
            # "리사:"가 두 번 나온 경우를 처리하기 위해 replace를 사용하여 첫 번째 "리사:"만 제거
            cleaned_line = line.replace("리사:", "", 1).strip()
            lisa_responses.append(cleaned_line)  # 공백 제거 후 배열에 추가

    # USER와 리사의 대화 배열 반환
    return user_responses, lisa_responses
