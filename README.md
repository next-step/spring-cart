# jwp-cart

# 1단계 - 상품 관리 기능

## 미션 PR 링크

* https://github.com/next-step/spring-cart/pull/25

## 미션 소개

이 미션은 Spring Web MVC를 이용하여 쇼핑몰의 상품 관리 기능을 구현하는 미션입니다.  
상품 관리 페이지를 Thymeleaf를 이용하여 확인할 수 있도록 코드를 제공합니다.  
이를 위해 상품 관리 CRUD API를 구현하고 사용자를 위한 상품 목록 페이지와 어드민을 위한 상품 관리 페이지를 연동해야 합니다.

## 요구사항

* 상품 목록 페이지 연동
    - [x] `index.html` 파일을 이용하여 상품 목록이 노출되는 페이지를 완성
    - [x] `/` url로 접근할 경우 상품 목록 페이지를 조회할 수 있도록 구현
    - [x] `/` url로 접근할 경우 `index.html` 페이지 반환
* 상품
    - [x] 상품 정보를 수정하는 기능
    - [x] 값 validation 기능 (null 체크)

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

---

# 2단계 - 장바구니 기능

## 미션 PR 링크

*

## 미션 소개

이 미션은 장바구니 기능을 구현하는 미션입니다.  
이번 단계는 크게 인증과 장바구니 기능 구현으로 나눌 수 있습니다.

어떤 사용자의 장바구니에 상품을 추가하거나 제거할 것인지에 대한 정보는 Basic Auth를 이용하여 인증을 하도록 합니다.  
사용자 설정은 설정페이지에서 다룹니다.

장바구니 기능은 인증 기반으로 기능을 구현하고 장바구니에 상품 추가, 제거, 목록 조회가 가능해야 합니다.  
이 때 필요한 도메인 설계와 DB 테이블 설계 그리고 이에 맞는 패키지 설계에 유의해주세요.

## 요구사항

- 사용자 기능 구현
    - 사용자 정보
        - [x] email
        - [x] password
    - [x] 사용자 추가 API
- 사용자 설정 페이지 연동
    - [x] `settings.html` 파일 내 TODO 주석을 참고하여 설계한 사용자 정보에 맞게 코드를 변경
    - [x] `settings.js` 파일 내 TODO 주석을 참고하여 설계한 사용자 정보에 맞게 코드를 변경
    - [x] `settings.html` 파일을 이용해서 사용자를 선택하는 기능을 구현
    - [x] `/settings` url로 접근할 경우 모든 사용자의 정보를 확인하고 사용자를 선택할 수 있습니다.
    - [x] 사용자 설정 페이지에서 사용자를 선택하면, 이후 요청에 선택한 사용자의 인증 정보가 포함
- 장바구니 기능 구현
    - 사용자 인증
        - [x] 사용자 정보는 요청 Header의 Authorization 필드를 사용
        - [x] 인증 방식은 Basic 인증을 사용
    - [x] 장바구니에 상품 추가
    - [x] 장바구니에 담긴 상품 제거
    - [x] 장바구니 목록 조회
- 장바구니 페이지 연동
    - [x] `cart.html` 파일 내 TODO 주석을 참고하여 설계한 장바구니 정보에 맞게 코드를 변경
    - [x] `cart.js` 파일 내 TODO 주석을 참고하여 설계한 장바구니 정보에 맞게 코드를 변경
    - [x] 1단계에서 구현한 상품 목록 페이지(/)에서 담기 버튼을 통해 상품을 장바구니에 추가
