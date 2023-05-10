CREATE TABLE PRODUCT
(
    id         INT            NOT NULL AUTO_INCREMENT,
    name       VARCHAR(50)    NOT NULL,
    image      VARCHAR(200)   NOT NULL,
    price      DECIMAL(10, 2) NOT NULL,
    created_at DATETIME       NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE MEMBER
(
    id         INT          NOT NULL AUTO_INCREMENT,
    email      VARCHAR(50)  NOT NULL,
    password   VARCHAR(100) NOT NULL,
    created_at DATETIME     NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE CART
(
    id         INT      NOT NULL AUTO_INCREMENT,
    member_id  INT      NOT NULL,
    product_id INT      NOT NULL,
    created_at DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

INSERT INTO product (name, image, price)
VALUES ('떡볶이', '/images/tteokbokki.jpg', 5000);

INSERT INTO member (email, password)
VALUES ('user', '1111');
