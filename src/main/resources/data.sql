CREATE TABLE PRODUCTS (
    id          INT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR     NOT NULL,
    image       TEXT        NOT NULL,
    price       BIGINT      NOT NULL,
    PRIMARY KEY (id)
);

insert into PRODUCTS (NAME, IMAGE, PRICE)
values ('치킨','https://shopping-phinf.pstatic.net/main_2527139/25271396531.20220329183813.jpg?type=f640',20000),
('샐러드','https://www.subway.co.kr/upload/menu/%ED%96%84_20220413025435077.png',8000);

CREATE TABLE USERS (
    id          INT         NOT NULL AUTO_INCREMENT,
    email       VARCHAR     NOT NULL,
    password    VARCHAR     NOT NULL,
    PRIMARY KEY(id)
);

insert into users (email, password) values ('admin@gmail.com', 'aaaa12345');


CREATE TABLE CARTS (
    id          INT         NOT NULL AUTO_INCREMENT,
    user_id       INT     NOT NULL,
    product_id    INT     NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);