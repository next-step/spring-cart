CREATE TABLE PRODUCT (
                             id          INT         NOT NULL AUTO_INCREMENT,
                             name        VARCHAR(MAX)         NOT NULL,
                             price       INT         NOT NULL,
                             image       VARCHAR(300) NULL,
                             created_at  DATETIME    NOT NULL default current_timestamp,
                             PRIMARY KEY (id)
);