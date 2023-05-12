CREATE TABLE PRODUCTS (
     id          INT         NOT NULL AUTO_INCREMENT,
     name     VARCHAR(100) NOT NULL,
     price INT         NOT NULL,
     image_url TEXT        NOT NULL,
     created_at  DATETIME    NOT NULL default current_timestamp,
     updated_at  DATETIME    NOT NULL default current_timestamp,
     PRIMARY KEY (id)
);

insert into PRODUCTS(name, price, image_url)
values ('치킨', 20000, 'https://i.namu.wiki/i/YVm0x8WHfLBtSyejD01_GTV1ITfWOJ-XODZzVTQPr386JsiBaz6Ucl1tKKxZmHiYStf_sXZBmK7AEXkEA18Tsg.webp'),
('샐러드', 18000, 'https://i.namu.wiki/i/_cLeYQStb5Xf8h6vC4ZtRoGFvsF1-rb9wqUMJ9pS510fYeJCGOnh-CQ0A0n9YNHbZ5Roi4WX3NB5RTPdYmhJyg.webp'),
('피자', 30000, 'https://handmadepizza.co.kr/uploadFiles/TB_MENU_MAIN/20230403163710691_93d0900607594ee2bfdbd6da4330fbfb.png');

CREATE TABLE USERS (
     id          INT         NOT NULL AUTO_INCREMENT,
     email     VARCHAR(100) NOT NULL,
     password     VARCHAR(100) NOT NULL,
     created_at  DATETIME    NOT NULL default current_timestamp,
     updated_at  DATETIME    NOT NULL default current_timestamp,
     PRIMARY KEY (id)
);

insert into USERS(email, password)
values ('a@a.com', 'password1'),
       ('b@b.com', 'password2');
