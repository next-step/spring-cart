DROP TABLE IF EXISTS PRODUCT;
CREATE TABLE PRODUCT
(
    id    INT            NOT NULL AUTO_INCREMENT,
    name  VARCHAR(50)    NOT NULL,
    image VARCHAR(200)   NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);
INSERT INTO product(name, image, price)
VALUES ('떡볶이', 'http://localhost:8080/images/dduckbbockcki.png', 10000),
       ('김치찜', 'http://localhost:8080/images/kimchizzim.png', 20000),
       ('해물찜', 'http://localhost:8080/images/seafoodzzim.png', 30000);

