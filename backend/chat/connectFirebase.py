import firebase_admin
from firebase_admin import credentials, db, auth, firestore


cred = credentials.Certificate('C:\\Users\\study\\candy_chat_ver2\\env\\serviceAccountKey.json')
apps = firebase_admin.initialize_app(cred)

client = firestore.client()
ref = client.document('diaries/gptDB')



def store_seperate(question, answer):
    ref.update({
        'question' : question,
        'answer' : answer
    })


def store_both(chat):
    ref.update({
        'chat' : chat
    })

def store_summary(summary):
    ref.update({
        'summaray':summary
    })

def store_emotions(emotions):
    ref.update({
        'emoitons':emotions
    })

