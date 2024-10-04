LV.1

API 명세서 작성 O

![image](https://github.com/user-attachments/assets/91033441-935a-4ae0-857a-b154b4a5ffd7)

ERD생성 O

![image](https://github.com/user-attachments/assets/1d34cc67-becf-454f-9be5-1fd60280c73c)

SQL 작성하기 
schedule.sql 작성! 

Lv.2
일정생성
- 일정생성시 할일, 작성자명, 비밀번호, 작성/수정일 저장 O
- 작성/수정일 LOCAL DATE TIME을 활용하여 날짜와 시간을 모두 포함 O
- 최초 입력시, 수정일과 작성일 동일 O

전체 일정조회
- 수정일을 입력시 목록 조회가능 O
- 작성자명으로는 조회 불가능 X(구현x)
- 수정일 기준 내림차순 정렬 O

선택 일정 조회
-단건정보 조회 X
-id사용 조회 X

Lv.3
선택한 일정 수정
- 작성일은 변경할수 없으며 수정일은 수정완료시 수정한시점으로 변경 O
- 수정요청시 비밀번호 함께 전달 X(구현 x)

선택한 일정 삭제
- 서버에서 일정 수정을 요청시 비밀전호 함께 전달 X

요약!
HTML구현 X(버튼 비활성화 issue)
LV3까지 구현하려하였지만 부분적으로 구현을 못한 부분이 있음.
많이 부족한걸 깨달았고 앞으로 더 노력할 예정입니다.

