# 1단계 - 상품 관리 기능

## 미션소개

---

-   이 미션은 Spring Web MVC를 이용하여 쇼핑몰의 상품 관리 기능을 구현하는 미션입니다.
-   상품 관리 페이지를 Thymeleaf를 이용하여 확인할 수 있도록 코드를 제공합니다.
-   이를 위해 상품 관리 CRUD API를 구현하고 사용자를 위한 상품 목록 페이지와 어드민을 위한 상품 관리 페이지를 연동해야 합니다.

## 요구사항

---

-   [ ] 상품 목록 페이지 연동
-   [ ] 상품 관리 CRUD API 작성
-   [ ] 관리자 도구 페이지 연동

## 상품 목록 페이지 연동

> ! index.html 파일 내 TODO 주석을 참고하여 설계한 상품 정보에 맞게 코드를 변경하세요.

-   index.html 파일을 이용하여 상품 목록이 노출되는 페이지를 완성합니다.
-   `/` url로 접근할 경우 상품 목록 페이지를 조회할 수 있어야 합니다이 미션은 Spring Web MVC를 이용하여 쇼핑몰의 상품 관리 기능을 구현하는 미션입니다.

    -   ![img](https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/ae0f63d0601844cface0034869758e21)

-   index.html 파일을 이용하여 상품 목록이 노출되는 페이지를 완성합니다.
    -   `/` url로 접근할 경우 상품 목록 페이지를 조회할 수 있어야 합니다

```
## 상품 기본 정보
- 상품 ID
- 상품 이름
- 상품 이미지
- 상품 가격
```

-   `장바구니`와 `설정` 메뉴는 2단계를 위한 기능이니 무시합니다.

## 상품 관리 CRUD API 작성

-   상품 생성, 상품 목록 조회, 상품 수정, 상품 삭제 API를 작성합니다.
-   API 스펙은 정해진것이 없으므로 API 설계를 직접 진행 한 후 기능을 구현 합니다.

## 관리자 도구 페이지 연동

> ! admin.html, admin.js 파일 내 TODO 주석을 참고하여 코드를 변경해주세요.

-   admin.html 파일과 상품 관리 CRUD API를 이용하여 상품 관리 페이지를 완성합니다.
-   `/admin` url로 접근할 경우 관리자 도구 페이지를 조회할 수 있어야 합니다.
    -   ![img](https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/853b14129c9d48fbaac1bb08dadc2d5d)
-   상품 추가, 수정, 삭제 기능이 동작하게 만듭니다.
    -   ![img](https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/7425fbb7f1694893a2ae65462f06d4d0)
