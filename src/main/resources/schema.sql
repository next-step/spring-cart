DROP TABLE PRODUCT IF EXISTS;

CREATE TABLE PRODUCT (
                         id       BIGINT       NOT NULL AUTO_INCREMENT,
                         name     VARCHAR(50)  NOT NULL,
                         image    VARCHAR(100) NOT NULL,
                         price    INT NULL,
                         PRIMARY KEY (id)
);

DROP TABLE MEMBER IF EXISTS;

CREATE TABLE MEMBER (
                        id          BIGINT           NOT NULL AUTO_INCREMENT,
                        email       VARCHAR(50)  NOT NULL,
                        password    VARCHAR(50)  NOT NULL,
                        PRIMARY KEY (id)
);

DROP TABLE CART IF EXISTS;

CREATE TABLE CART (
                        id          BIGINT        NOT NULL AUTO_INCREMENT,
                        member_id   BIGINT        NOT NULL,
                        product_id  BIGINT        NOT NULL,
                        PRIMARY KEY (id)
);
