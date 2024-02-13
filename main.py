import openai
import split
import connectFirebase
openai.api_key = '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'


conversation_history = ""

USERNAME = "USER"
AI_NAME = "CANDY"


# 대화내용 저장함수 제작
def cumulative_input(
        input_str : str,
        converation_history : str,
        USERNAME : str,
        AI_NAME : str,
):
    # 이전 대화 내용 업데이트
    converation_history += f"{USERNAME}: {input_str}\n"

    # gpt로 응답 생성
    message = get_response(converation_history)

    # 이전 대화 응답 내용 업데이트
    converation_history += f"{AI_NAME}: {message}\n"

    # 응답 출력하기
    print(f"{AI_NAME}: {message}\n")

    return converation_history


def get_response(prompt):
    completions = openai.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role" : "system", "content" : "당신은 따뜻하고 상냥한 상담가 'CANDY'입니다. 상담자는 자신의 감정에 대해 이해하고 싶은 경계선 지능인으로, 당신은 상대를 어린아이 다루듯이 대해주세요. 답변에 이모티콘을 적절하게 이모티콘을 사용하는 것을 좋아합니다. "},
            {"role" : "user", "content" : "이전 응답에 대한 따뜻한 위로나 반응한 뒤에 오늘 일상에 대해 순차적으로 되돌아 볼 수 있는 질문을 해주세요. 당신은 사용자에게 이미 나온 질문을 반복할 수 없습니다."},
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
            {"role" : "system", "content" : "당신 'CANDY' 입니다. 당신은 따뜻하고 상냥한 상담가로서 'USER'가 말한 내용에 대해서 요약과 반응을 해주는 사람입니다. 친근한 말투로 글을 작성합니다."},
            {"role" : "user", "content" : "'USER'의 마지막 답변에 대해 따뜻한 위로를 담아서 글을 두줄이나 세줄로 답을 해주세요. "},
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
        max_tokens=500,
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
            max_tokens= 150,
        )
        emotions = emotion.choices[0].message.content
        return emotions

    except Exception as e:
        print(f"\nAn error Occurred.")
        if hasattr(e, 'response'):
            print("Response status code: ", e.response.status_code)
        return "감정 분석 수행 불가능"


def main():
    global conversation_history
    user_input = input(f"{USERNAME}: ")
    for i in range(1):
        print("질문", i)
        chat = cumulative_input(user_input, conversation_history, USERNAME, AI_NAME )
        user_input = input(f"{USERNAME}: ")
        conversation_history = chat

    # 마지막 답변에 대한 반응
    las_message = last_response(conversation_history)
    conversation_history += f"{AI_NAME}: {las_message}\n"

    # 요약본 생성
    summary = summarize(conversation_history)
    print("[오늘 일기의 요약본]\n", summary)
    conversation_history += f"{AI_NAME}: {summary}\n"

    # 파이어베이스 저장
    user_context, ai_context = split.seperate_conversation(conversation_history)
    connectFirebase.store_seperate(ai_context,user_context)

    # 감정 텍스트 4개 추출
    emotions = sentiment_analysis(summary)


if __name__ == "__main__":
    main()