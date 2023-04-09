CREATE TABLE product_list
(
    id         INT          NOT NULL AUTO_INCREMENT,
    name       varchar(200) NOT NULL,
    image_url  varchar(200) NOT NULL,
    price      INT          NOT NULL,
    created_at DATETIME     NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE cart_list
(
    id         INT          NOT NULL AUTO_INCREMENT,
    email      varchar(200) NOT NULL,
    product_id INT          NOT NULL,
    created_at DATETIME     NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         INT          NOT NULL AUTO_INCREMENT,
    email      varchar(200) NOT NULL,
    password   varchar(200) NOT NULL,
    created_at DATETIME     NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

INSERT INTO users (email, password)
VALUES ('a@a.com', 'password1');
INSERT INTO users (email, password)
VALUES ('b@b.com', 'password2');

INSERT INTO product_list (name, image_url, price)
VALUES ('고양이', 'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FtEMUl%2FbtrDc6957nj%2FNwJoDw0EOapJNDSNRNZK8K%2Fimg.jpg', 9000);

INSERT INTO product_list (name, image_url, price)
VALUES ('치킨', 'https://pelicana.co.kr/resources/images/menu/original_menu01_200529.png', 10000);

INSERT INTO product_list (name, image_url, price)
VALUES ('샐러드', 'https://src.hidoc.co.kr/image/lib/2021/2/17/1613545788294_0.jpg', 5000);