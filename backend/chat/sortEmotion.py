import openai
import config
#
openai.api_key = config.api_key
#
# def sort(emotions):
#
#     emotion_categry = ["anger", "contempt","disgust", "fear", "happiness", "neutral", "sadness", "surprise"]
#     emotions_str = emotions,
#
#     contents = [{"role": "system",
#                  "content": f"다음은 다양한 감정을 나타내는 단어들입니다: {emotions_str}. 각 단어를 로버트 플러치크의 감정의 수레바퀴에 따라 8가지 기본 감정 카테고리 중 하나로 분류해주세요. 답변 양식은 '기쁨' 과 같이 단어 하나로 정해져있습니다."},
#                 {"role": "user",
#                  "content": "당신은 주어진 텍스트를 기쁨(Joy), 신뢰(Trust), 두려움(Fear), 놀람(Surprise), 슬픔(Sadness), 혐오(Disgust), 분노(Anger), 기대(Anticipation) 라는 8개의 카테고리로 분류하는 기계입니다."}]
#
#     response = openai.chat.completions.create(
#         model = 'gpt-3.5-turbo',
#         messages = contents
#     )
#
#     emo_cat = response.choices[0].message.content
#     return emo_cat
#

def classify_emotions_with_openai(emotion_list):
    emotions_str = ", ".join(emotion_list)
    message = [{
        "role": "system",
        "content": f"당신이 네글자 이하의 한국어를 반환해야합니다. {emotion_list}를 로버트 플러치크의 감정의 수레바퀴에 따라 8가지 기본 감정 카테고리 중 하나로 분류한다면 나올 카테고리 단어를 반환해주세요."
                   "이 8가지 기본 감정 카테고리는 기쁨(Joy), 신뢰(Trust), 두려움(Fear), 놀람(Surprise), 슬픔(Sadness), 혐오(Disgust), 분노(Anger), 기대(Anticipation)입니다."
    }]


    response = openai.chat.completions.create(
        model="gpt-3.5-turbo",  # 사용 가능한 최신 엔진을 선택하세요.
        messages=message,
        max_tokens=8,
        temperature=0,
        n=1,
        stop=None
    )
    return response.choices[0].message.content

def match_category_id(emotion_list):
    message = [{
        "role": "system",
        "content": f"당신은 1~8 숫자 중 하나를 반환해야합니다. 당신은 8가지 감정 카테고리에서 {emotion_list}와 일치하는 값을 찾아야 합니다. 일치하는 감정 카테고리의 (인덱스 숫자+1) 값을 반환해주세요."
                   "이 8가지 기본 감정 카테고리는 기쁨(Joy), 신뢰(Trust), 두려움(Fear), 놀람(Surprise), 슬픔(Sadness), 혐오(Disgust), 분노(Anger), 기대(Anticipation)입니다."
    }]

    response = openai.chat.completions.create(
        model="gpt-3.5-turbo",  # 사용 가능한 최신 엔진을 선택하세요.
        messages=message,
        max_tokens = 1,
        temperature=0,
        n=1,
        stop=None
    )
    return int(response.choices[0].message.content)



# 로버트 플러치크의 감정의 수레바퀴 분류법에 따른 감정 카테고리
emotion_categories = {
    "Joy": [],
    "Trust": [],
    "Fear": [],
    "Surprise": [],
    "Sadness": [],
    "Disgust": [],
    "Anger": [],
    "Anticipation": []
}

