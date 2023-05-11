CREATE TABLE PRODUCT
(
    id         INT            NOT NULL AUTO_INCREMENT,
    name       VARCHAR(100)   NOT NULL,
    image      VARCHAR(200)   NOT NULL,
    price      INT            NOT NULL,
    created_dt DATETIME       NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

INSERT INTO PRODUCT (name, image, price)
VALUES ( '히로', '/image/hero.JPG', '1000000' );

INSERT INTO PRODUCT (name, image, price)
VALUES ( '봉구', '/image/bonggu.JPG', '1100000' );