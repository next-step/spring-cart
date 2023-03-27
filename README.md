# jwp-cart

## Controller

- AdminController : 관리자 컨트롤러
- CartController : 장바구니 컨트롤러
- IndexController : 최초 인덱스 컨트롤러
- ProductController : 상품 컨트롤러
- SettingsController : 사용자 세팅 컨트롤러

## dao

- AuthDAO : 권한 체크용 dao
- CardDAO : 장바구니 dao
- MemberDAO : 사용자 dao
- ProductDAO : 상품 dao

## dto

- AuthInfo : 권한 체크 후, 저장 및 리턴용 객체

## Exception

- AuthorizationException : 권한 예외처리 클래스

## infrastructure

- AuthorizationExtractor
- BasicAuthorizationExtracotr
  - 인증 처리용 클래스

## model

- Cart : 장바구니
- Carts : 장바구니 일급 클래스
- Member : 사용자
- Members : 사용자 일급 클래스
- Product : 상품
- Products : 상품 일급 클래스

## Service

- AuthService : 권한 관련 서비스
- CartService : 장바구니 관련 서비스
- MemberService : 사용자 관련 서비스
- ProductService : 상품 관련 서비스

## Table

### Product

```sql
CREATE TABLE PRODUCT
(
    ID    INT          NOT NULL AUTO_INCREMENT,
    NAME  VARCHAR(50)  NOT NULL,
    IMAGE VARCHAR(500) NULL,
    PRICE INT          NOT NULL,
    PRIMARY KEY (ID)
);
```

### Member

```sql
CREATE TABLE MEMBER
(
    ID       INT          NOT NULL AUTO_INCREMENT,
    NAME     VARCHAR(50)  NOT NULL,
    EMAIL    VARCHAR(100) NOT NULL,
    PASSWORD VARCHAR(500) NOT NULL,
    PRIMARY KEY (ID)
);
```


### Cart

```sql

CREATE TABLE CART
(
    EMAIL      VARCHAR(100) NOT NULL,
    PASSWORD   VARCHAR(500) NOT NULL,
    PRODUCT_ID INT          NOT NULL
);
```
