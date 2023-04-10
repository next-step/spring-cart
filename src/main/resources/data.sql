CREATE TABLE PRODUCT (
    id        INT         NOT NULL AUTO_INCREMENT,
    NAME      VARCHAR(50) NOT NULL,
    IMAGE     VARCHAR(200) NOT NULL,
    PRICE     INT NOT NULL,
    created_at   DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

INSERT INTO PRODUCT(ID, NAME, IMAGE, PRICE) VALUES (1, '상품1', 'http://test.test', 2000);
INSERT INTO PRODUCT(ID, NAME, IMAGE, PRICE) VALUES (2, '상품2', 'http://test.test', 4400);
INSERT INTO PRODUCT(ID, NAME, IMAGE, PRICE) VALUES (3, '상품3', 'http://test.test', 2200);
INSERT INTO PRODUCT(ID, NAME, IMAGE, PRICE) VALUES (4, '상품4', 'http://test.test', 7700);
INSERT INTO PRODUCT(ID, NAME, IMAGE, PRICE) VALUES (5, '상품5', 'http://test.test', 2500);
