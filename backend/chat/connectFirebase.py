import firebase_admin
from firebase_admin import credentials, db, auth, firestore


cred = credentials.Certificate('C:\\Users\\study\\candy_chat_ver2\\env\\serviceAccountKey.json')
apps = firebase_admin.initialize_app(cred)

client = firestore.client()
ref = client.document('diaries/gptDB')


# 파이어베이스 채팅 히스토리를 불러오는 함수
# # 요약본 추출할 때 채팅 히스토리 반환 필요
def get_history():
    doc_ref = client.collection('diaries').document('gptDB')
    try:
        doc = doc_ref.get()
        if doc.exists:
            return doc.to_dict().get('history', '')
        else:
            return ''
    except Exception as e:
        print(f"An error occured: {e}")
        return ''


def update_history(update_text):
    doc_ref = client.collection('diaries').document('gptDB')
    doc_ref.update({
        'history' : update_text
    })

def split_name(line):
    responses = []

    if line.startswith("CANDY: CANDY: "):
        # "리사:"가 두 번 나온 경우를 처리하기 위해 replace를 사용하여 첫 번째 "리사:"만 제거
        cleaned_line = line.replace("CANDY: ", "", 1).strip()
        responses.append(cleaned_line)  # 공백 제거 후 배열에 추가

    return responses


# def store_seperate(question, answer):
#     ref.update({
#         'question' : question,
#         'answer' : answer
#     })
#
# def store_answer(answer):
#     ref.update({
#         'answer' : answer
# })
#
#
# def store_question(question):
#     ref.update({
#         'question' : question
#     })
#
# def store_summary(summary):
#     ref.update({
#         'summaray':summary
#     })
#
# def store_history(history):
#     ref.update({
#         "history":history
#     })
#
# def store_emotions(emotions):
#     ref.update({
#         'emoitons':emotions
#     })

