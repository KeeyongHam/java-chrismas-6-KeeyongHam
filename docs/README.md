## 구현목록 정의

--------

### 입력

- 날짜
- 주문 메뉴 및 수량 입력

--------

### 도메인

- Date
    - 입력받은 날짜 검증 후 int 타입으로 반환
- Menu
    - 메뉴타입(dessert, main, ...), 메뉴이름, 가격을 열거형으로 정의
    - 입력받은 메뉴이름이 있는지 확인 후 반환
    - 파라미터로 Menu를 받으면 메뉴타입 혹은 메뉴 가격 반환
    - 주문한 메뉴에 디저트의 개수와 메인메뉴 개수 반환
- OrderMenu
    - 입력받은 주문 메뉴로 Menu를 만듬
    - 주문 포멧에 맞췄는지 확인
- Discount
    - 각종 할인을 열거형으로 정의
    - 날짜와 할인 전 가격, 디저트 및 메인 개수 확인 후 할인 내역 생성
- Amount
    - 할인 전 가격 계산
    - 할인 내역을 체크해 총 할인 내역 계산
    - 위 두개를 가지고 예상 결제금액 계산
- EventRank
    - 뱃지이름, 구매 가격을 열거형으로 정의

--------

## 출력

- 이벤트 주의사항 출력
- 혜택 미리보기 출력
- 주문 메뉴 출력
- 할인 전 금액 출력
- 증정 메뉴 출력
- 해택 내역 출력
- 총 할인 금액 출력
- 예상 결제 금액 출력
- 이벤트 배지 출력

--------

## 유의사항

- 입출력 클래스 별도록 구현
- 고객에게 안내할 이벤트 주의사항 출력
- 뱃지 등급은 총 결제 금액을 기준으로 함
- 메뉴판 양식에 맞추지 않는 여러 경우의 수 생각
- 중복 메뉴 시 예외
- 혜택, 증정 메뉴가 없을 시 "없음"으로 출력
- 입력값을 잘못입력시 다시 입력
