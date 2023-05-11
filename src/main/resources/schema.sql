CREATE TABLE PRODUCT (
                             id          INT         NOT NULL AUTO_INCREMENT,
                             name        VARCHAR(MAX)         NOT NULL,
                             price       INT         NOT NULL,
                             image       VARCHAR(300) NULL,
                             created_at  DATETIME    NOT NULL default current_timestamp,
                             PRIMARY KEY (id)
);

CREATE TABLE MEMBER (
                        id      INT             NOT NULL AUTO_INCREMENT,
                        email   VARCHAR(200)    NOT NULL,
                        password VARCHAR(200)   NOT NULL,
                        PRIMARY KEY (id)
);

CREATE TABLE CART (
                        id           INT            NOT NULL AUTO_INCREMENT,
                        member_id    INT            NOT NULL,
                        product_id   INT            NOT NULL,
                        count        INT            NOT NULL default 1,
                        PRIMARY KEY (id)
);
