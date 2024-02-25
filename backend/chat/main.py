import openai
import split
import connectFirebase, connectMySQL
import config
import sortEmotion
from flask import Flask, request, jsonify

app = Flask(__name__)


openai.api_key = config.api_key


global conversation_history



# def cumulative_input(
#         input_str : str,
#         converation_history : str,
#         USERNAME : str,
#         AI_NAME : str,
# ):
#     # 이전 대화 내용 업데이트
#     converation_history += f"{USERNAME}: {input_str}\n"
#     # print("\n")
#     # gpt로 응답 생성
#     message = get_response(converation_history)
#
#     # 이전 대화 응답 내용 업데이트
#     converation_history += f"{AI_NAME}: {message}\n"
#     # converation_history += f"{message}\n"
#
#
# # 응답 출력하기
# #     print(f"{AI_NAME}{AI_NAME}:\n")
#     print(f"{message}\n")
#     print("\n")
#     return converation_history

def join_input(input_message, output_response, conversation_history):
    conversation_history += f"USER: {input_message}"
    conversation_history += f"CANDY: {output_response}"
    return conversation_history


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


def last_response(prompt):
    completions = openai.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role" : "system", "content" : "당신 'CANDY' 입니다. 당신은 따뜻하고 상냥한 상담가로서. 친근한 말투로 글을 작성합니다."},
            {"role" : "user", "content" : "'USER'의 마지막 답변에 대해 따뜻한 위로를 담아서 글을 세문장으로 감상과 마지막 마무리 인사를 말씀해주세요. "},
            {"role" : "assistant", "content" : prompt}
        ],
        max_tokens=500,
        temperature=0,
    )
    return completions.choices[0].message.content


def summarize(prompt):
    completions = openai.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role" : "system", "content" : "당신은 USER의 일기를 작성해주는 사람입니다."},
            {"role" : "user", "content" : "주어진 대화 내용을 바탕으로 'USER' 시점의 1인칭에서 오늘 하루를 요약하는 일기를 작성해주세요. "},
            {"role" : "assistant", "content" : prompt}
        ],
        max_tokens=250,
        temperature=0,
    )
    return completions.choices[0].message.content


def sentiment_analysis(diary):
    message = [{
        "role": "system",
        "content": "당신은 감정 분석가이자 아이를 상대하는 친절하고 다정한 심리 상담가입니다."
    }, {
        "role": "user",
        "content": diary
    }]

    message.append({
        "role" : "system",
        "content" : "일기에서 작성자가 어떤 감정을 느끼고 있는지를 추측해서 emotion adjective 4개를 추출해주세요. 4개의 단어를 ','로 구분해주세요."
    })


    try:
        emotion = openai.chat.completions.create(
            model = "gpt-4",
            temperature = 0,
            messages = message,
            max_tokens= 50,
        )
        emotions = emotion.choices[0].message.content
        return emotions

    except Exception as e:
        print(f"\nAn error Occurred.")
        if hasattr(e, 'response'):
            print("Response status code: ", e.response.status_code)
        return "감정 분석 수행 불가능"




def main():
    ## global conversation_history
    # conversation_history = ""
    # user_input = input(f"{USERNAME}: ")
    # for i in range(3):
    #     print("질문", i)
    #     chat = cumulative_input(user_input, conversation_history, USERNAME, AI_NAME )
    #     user_input = input(f"{USERNAME}: ")
    #     conversation_history = chat
    #
    # conversation_history += f"{USERNAME}: {user_input}\n"


    #

    global conversation_history
    conversation_history = ""
    user_input = input(f"USER: ")
    conversation_history += user_input
    for i in range(3):
        print(i, " 번째 질문입니다.")
        response = get_response(conversation_history)
        chat = join_input(user_input,response, conversation_history)
        conversation_history = chat
        user_input = input(f"USER: ")
    conversation_history += f"USER: {user_input}\n"



    print("\n//////////////////")


# 마지막 답변에 대한 반응
    las_message = last_response(conversation_history)
    conversation_history += f"{las_message}\n"

    print("CONVERSATION\n",conversation_history)
    print("\n//////////////////")


    # 요약본 생성
    summary = summarize(conversation_history)
    print("[오늘 일기의 요약본]\n", summary)


    # # conversation_history += f"{AI_NAME}: {summary}\n"



    # 파이어베이스 저장
    user_context, ai_context = split.seperate_conversation(conversation_history)
    connectFirebase.store_seperate(ai_context, user_context)
    connectFirebase.store_summary(summary)


    # 감정 텍스트 4개 추출
    emotions = sentiment_analysis(summary)
    print("emotions: ", emotions)
    print("\n")

    emotion_list = emotions.split(', ')

    # 감정 텍스트 분류
    emotion_category = []
    for i in range(4):
        emotion_category.append(sortEmotion.classify_emotions_with_openai(emotion_list[i]))


    # count = 0
    # for i in emotion_category:e
    #     print(i)
    #     count+=1


    emotion_category_id=[]
    for i in emotion_category:
        emotion_category_id.append(sortEmotion.match_category_id(i))


    for i in range(4):
        connectMySQL.insert_data(emotion_list[i], emotion_category_id[i])




# if __name__ == "__main__":
#     main()

# global conversation_history
# conversation_history = ""
# user_input = input(f"USER: ")
# conversation_history += user_input
# for i in range(3):
#     print(i, " 번째 질문입니다.")
#     response = get_response(conversation_history)
#     chat = join_input(user_input,response, conversation_history)
#     conversation_history = chat
#     user_input = input(f"USER: ")
# conversation_history += f"USER: {user_input}\n"




# 클라이언트에게 메세지를 받고 처리한 뒤, json 형태로 답을 반환하는 api 엔드포인트('/chat-end') 생성함
# 이 경로로 post 요청이 들어오면, process_message() 함수가 실행된다.
@app.route('/chat-ing', methods=['POST'])
def process_message_ing():
    # request json을 사용해서 클라이언트로부터 받은 json 데이터를 파싱한다.
    # 아래는 json 데이터 중 'message' 키 값에 해당되는 값을 user_input 에 저장한다.
    data = request.json
    user_input = data['message']
    join_input(user_input)
    ai_response = get_response(conversation_history)

    # 어떤 로직을 거쳐 얻은 답변을 response 딕셔너리에 'reply' 키로 저장해서 jsonify(response)를 사용해
    # json 형태로 클라이언트에게 반환
    response = {"reply": f"{ai_response}"}
    join_input(user_input, ai_response, conversation_history)

    return jsonify(response)



# 마지막 답변 전달
@app.route('/chat-end', methods=['POST'])
def process_message_end():
    data = request.json
    user_input = data['message']
    ai_last_response = last_response(user_input)
    response = {"last_reply":f"{ai_last_response}"}
    return jsonify(response)



if __name__ == "__main__":
    app.run(debug=True, port=5000)



