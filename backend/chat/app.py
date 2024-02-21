import openai
import split
import connectFirebase
import config
import sortEmotion
from flask import Flask, request, jsonify

app = Flask(__name__)

openai.api_key = config.api_key

def get_response(prompt):
    completions = openai.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role" : "system", "content" : "당신은 따뜻하고 상냥한 상담가 'CANDY'입니다. 상담자는 자신의 감정에 대해 이해하고 싶은 경계선 지능인으로, 상대를 어린아이 다루듯이 대해주세요. 답변에 이모티콘을 적절하게 이모티콘을 사용하는 것을 좋아합니다. "},
            {"role" : "user", "content" : "user의 응답에 대한 따뜻한 위로나 공감을 한 뒤에 오늘 일상에 대해 순차적으로 되돌아 볼 수 있는 질문을 해주세요. 당신은 사용자에게 이미 나온 질문을 반복할 수 없습니다."},
            {"role" : "assistant", "content" : prompt}
        ],
        max_tokens=220,
        temperature=0,
    )

    return completions.choices[0].message.content


# 파베에 채팅 히스토리 업뎃
# def update_history(update_text):


# 답변 생성하는 함수
# def get_response(history):



@app.route('/api/chat/message', methods=['POST'])
def process_message_ing():
    data = request.json
    user_input = data['content']

    # 이전 대화 히스토리 가져오기
    history = connectFirebase.get_history()

    # 사용자 입력을 히스토리에 추가
    history += f"USER: {user_input}\n"

    ai_response = get_response(history)
    print(f"ai_Response: {ai_response} \n")

    if not ai_response.startswith("CANDY: "):
        # "CANDY:"가 두 번 나온 경우를 처리하기 위해 replace를 사용하여 첫 번째 "CANDY:"만 제거
        ai_response = "CANDY: " + ai_response
        # ai_response = ai_response.replace("\u0043\u0041\u004e\u0044\u0059\u003a\u0020\u0020\u0043\u0041\u004e\u0044\u0059\u003a\u0020: ", "", 1).strip()
        # responses.append(cleaned_line)  # 공백 제거 후 배열에 추가

    history += f"{ai_response}\n"
    # history += f"{ai_response}\n"

    connectFirebase.update_history(history)

    return jsonify({"response": ai_response})




if __name__ == "__main__":
    app.run(debug=True, port=5000)