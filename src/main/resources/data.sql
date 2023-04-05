CREATE TABLE PRODUCT (
    ID      INT NOT NULL AUTO_INCREMENT,
    NAME    VARCHAR(50) NOT NULL,
    IMAGE   VARCHAR(1000) NOT NULL,
    PRICE   INT NOT NULL,
    PRIMARY KEY (ID)
);
INSERT INTO PRODUCT VALUES (
                            1,
                            '치킨',
                            '../static/img/chicken.jpg',
                            12000
                           );