import cv2
import numpy as np
from firebase_admin import credentials, firestore, initialize_app

# Firebase 초기화
cred = credentials.Certificate('path/to/serviceKey.json')
default_app = initialize_app(cred)
db = firestore.client()

# 카메라 오픈
cap = cv2.VideoCapture(0)

while True:
    # 이미지 받아오기
    _, frame = cap.read()

    # 이미지를 HSV 색 공간으로 변환
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

    # HSV값으로 초록색이라고 인식되는 범위 설정
    lower_green = np.array([35, 100, 100])
    upper_green = np.array([85, 255, 255])

    # 이미지에서 초록색 부분 마스킹
    mask = cv2.inRange(hsv, lower_green, upper_green)

    # 초록색 부분 면적 계산(픽셀기준)
    green_area = np.sum(mask > 0)

    print(f'Green area: {green_area} pixels')

    # 전체 이미지 면적(픽셀기준)
    total_area = frame.shape[0] * frame.shape[1]

    # 초록색 부분의 면적을 퍼센트로 환산
    green_percent = green_area / total_area * 100

    print(f'Green area: {green_percent}% of total image area')

    # Firebase로 데이터 전송
    doc_ref = db.collection(u'green_area').document(u'area')
    doc_ref.set({
        u'percent': green_percent,
    })

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()
