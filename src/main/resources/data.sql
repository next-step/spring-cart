CREATE TABLE PRODUCT (
    id        INT         NOT NULL AUTO_INCREMENT,
    NAME      VARCHAR(50) NOT NULL,
    IMAGE     VARCHAR(200) NOT NULL,
    PRICE     INT NOT NULL,
    created_at   DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

INSERT INTO PRODUCT(ID, NAME, IMAGE, PRICE) VALUES (1, '단짠단짠 피자', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQS8d0Z2B1npEessiAoXj1HvBea-p8FptPwow&usqp=CAU', 25000);
INSERT INTO PRODUCT(ID, NAME, IMAGE, PRICE) VALUES (2, 'BBQ 3만원치킨', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlsg-j4to_QDL6R3jSkpELl_sgkN-0TykPlw&usqp=CAU', 30000);
INSERT INTO PRODUCT(ID, NAME, IMAGE, PRICE) VALUES (3, '1번가 떡볶이', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcc4HmNF6vetLFAWsBWluAHmCdeDs74ZmsFw&usqp=CAU', 12200);
INSERT INTO PRODUCT(ID, NAME, IMAGE, PRICE) VALUES (4, 'PC방 라면', 'https://health.chosun.com/site/data/img_dir/2020/09/07/2020090702900_0.jpg', 7700);
