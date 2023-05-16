CREATE TABLE PRODUCTS (
     id         INT         NOT NULL AUTO_INCREMENT,
     name       VARCHAR     NOT NULL ,
     img        TEXT     NOT NULL ,
     price      BIGINT      NOT NULL ,
     created_at  DATETIME   NOT NULL default current_timestamp,
     PRIMARY KEY (id)
);

CREATE TABLE MEMBER (
    id         INT       NOT NULL AUTO_INCREMENT,
    email      VARCHAR2   NOT NULL,
    PASSWORD   VARCHAR2   NOT NULL,
    created_at DATETIME   NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

INSERT INTO PRODUCTS(name, img, price) VALUES ( '후라이드 치킨', 'https://pelicana.co.kr/resources/images/menu/original_menu01_200529.png', 25000 ),
                                              ('샐러드', 'https://m.subway.co.kr/upload/menu/%EC%89%AC%EB%A6%BC%ED%94%84_20220413025617416.png', 10000);

INSERT INTO MEMBER(email, PASSWORD) VALUES ( 'a@a.com', 'password1' ), ('b@b.com', 'password2');