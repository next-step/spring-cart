# jwp-cart

# 1단계 - 상품 관리 기능

### 미션1

* [미션 링크](https://github.com/next-step/spring-cart/pull/41)

### 미션 소개

이 미션은 Spring Web MVC를 이용하여 쇼핑몰의 상품 관리 기능을 구현하는 미션입니다.  
상품 관리 페이지를 Thymeleaf를 이용하여 확인할 수 있도록 코드를 제공합니다.  
이를 위해 상품 관리 CRUD API를 구현하고 사용자를 위한 상품 목록 페이지와 어드민을 위한 상품 관리 페이지를 연동해야 합니다.

### 요구사항

* 상품 목록 페이지 연동
    - [x] `index.html` 파일을 이용하여 상품 목록이 노출되는 페이지를 완성
    - [x] `/` url로 접근할 경우 상품 목록 페이지를 조회할 수 있도록 구현
    - [x] `/` url로 접근할 경우 `index.html` 페이지 반환
* 상품
    - [x] 상품 정보를 수정하는 기능

    * 상품이 가지고 있는 정보
        - [x] 상품 ID
        - [x] 상품 이름
        - [x] 상품 이미지
        - [x] 상품 가격
* 돈 도메인
    - [x] 0이상의 값을 가진다.
* 상품 관리 CRUD API 기능 추가
    - [x] 상품 생성 API
    - [x] 상품 목록 조회 API
    - [x] 상품 수정 API
    - [x] 상품 삭제 API
* 관리자 도구 페이지 연동
    - [x] `admin.html` 파일과 상품 관리 CRUD API를 이용하여 상품 관리 페이지를 완성
    - [x] `admin.js` 파일의 기능을 완성한다.
    - [x] `/admin` url로 접근할 경우 관리자 도구 페이지를 조회할 수 있어야 한다.
