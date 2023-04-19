# 2단계 - 장바구니 기능

## 미션소개

---

- 이 미션은 장바구니 기능을 구현하는 미션입니다.
- 이번 단계는 크게 인증과 장바구니 기능 구현으로 나눌 수 있습니다.

- 어떤 사용자의 장바구니에 상품을 추가하거나 제거할 것인지에 대한 정보는 Basic Auth를 이용하여 인증을 하도록 합니다. 사용자 설정은 설정페이지에서 다룹니다.

- 장바구니 기능은 인증 기반으로 기능을 구현하고 장바구니에 상품 추가, 제거, 목록 조회가 가능해야 합니다. 이 때 필요한 도메인 설계와 DB 테이블 설계 그리고 이에 맞는 패키지 설계에 유의해주세요.

## 요구사항

---

- [x] 사용자 기능 구현
- [x] 사용자 설정 페이지 연동
- [x] 장바구니 기능 구현
- [x] 장바구니 페이지 연동


## 사용자 기능 구현

> ! index.html 파일 내 TODO 주석을 참고하여 설계한 상품 정보에 맞게 코드를 변경하세요.

- 사용자가 가지고 있는 정보는 다음과 같습니다.
    - 필요한 경우 사용자 정보의 종류를 추가할 수 있습니다. (ex. 이름, 전화번호)

```
## 사용자 기본 정보
- email
- password
```

## 사용자 설정 페이지 연동
> ! settings.html, settings.js 파일 내 TODO 주석을 참고하여 설계한 사용자 정보에 맞게 코드를 변경해주세요.
- settings.html 파일을 이용해서 사용자를 선택하는 기능을 구현합니다.
- /settings url로 접근할 경우 모든 사용자의 정보를 확인하고 사용자를 선택할 수 있습니다. 
- ![img](https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/d384b43201c54905b3c36e4557feea9c)
- 사용자 설정 페이지에서 사용자를 선택하면, 이후 요청에 선택한 사용자의 인증 정보가 포함됩니다.

## 장바구니 기능 구현
- 장바구니와 관련된 아래 기능을 구현합니다.
    - 장바구니에 상품 추가
    - 장바구니에 담긴 상품 제거
    - 장바구니 목록 조회
- 사용자 정보는 요청 Header의 Authorization 필드를 사용해 인증 처리를 하여 얻습니다.
    - 인증 방식은 Basic 인증을 사용합니다.
    - 예시)
    ```
     Authorization: Basic ZW1haWxAZW1haWwuY29tOnBhc3N3b3Jk
    ```
    - type: Basic
    - credentials : email:password를 base64로 인코딩한 문자열
    - ex) email@email.com:password -> ZW1haWxAZW1haWwuY29tOnBhc3N3b3Jk
- 그 외 API 스펙은 정해진것이 없으므로 API 설계를 직접 진행 한 후 기능을 구현 합니다.
- 기존에 구현되어있는 상품을 바탕으로 장바구니 기능을 위한 객체와 테이블 그리고 패키지 구조는 자유롭게 설계할 수 있습니다.

## 장바구니 페이지 연동
> ! cart.html, cart.js 파일 내 TODO 주석을 참고하여 설계한 장바구니 정보에 맞게 코드를 변경해주세요. 
### 장바구니 장품 추가
-   1단계에서 구현한 상품 목록 페이지(/)에서 담기 버튼을 통해 상품을 장바구니에 추가할 수 있습니다. 
- ![img](https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/1b671566891b4781ab3bb84e47365fee)
### 장바구니 목록 조회 및 제거
- cart.html 파일과 장바구니 관련 API를 이용하여 장바구니 페이지를 완성합니다.
- /cart url로 접근할 경우 장바구니 페이지를 조회할 수 있어야 합니다.
- 장바구니 목록을 확인하고 상품을 제거하는 기능을 동작하게 만듭니다.     
- ![img](https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/766094866e1d4a3c953ea23becf53388)
