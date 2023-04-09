DROP TABLE IF EXISTS PRODUCT;
CREATE TABLE PRODUCT
(
    id INT      NOT NULL AUTO_INCREMENT,
    name varchar(100)      NOT NULL,
    image_url varchar(200)      NOT NULL,
    price INT              NOT NULL,
    created_at DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

INSERT INTO PRODUCT (name, price, image_url)
VALUES ('치킨',
        10000,
        'https://images.unsplash.com/photo-1626082896492-766af4eb6501?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880&q=80');
INSERT INTO PRODUCT (name, price, image_url)
VALUES ('샐러드',
        20000,
        'https://images.unsplash.com/photo-1512621776951-a57141f2eefd?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80');
INSERT INTO PRODUCT (name, price, image_url)
VALUES ('피자',
        13000,
        'https://images.unsplash.com/photo-1593560708920-61dd98c46a4e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80');

DROP TABLE IF EXISTS MEMBER;
CREATE TABLE MEMBER
(
    email    VARCHAR(50) NOT NULL,
    password VARCHAR(20) NOT NULL,
    created_at      DATETIME     NOT NULL default current_timestamp,
    PRIMARY KEY (email)
);
INSERT INTO MEMBER (email, password)
VALUES ('cyan@springcart.com', 'password1');
INSERT INTO MEMBER (email, password)
VALUES ('jade@springcart.com', 'password2');
