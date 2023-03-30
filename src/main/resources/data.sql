-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE PRODUCT
(
    product_id        INT          NOT NULL AUTO_INCREMENT,
    product_name      VARCHAR(50)  NOT NULL,
    product_price     INT          NOT NULL,
    product_imagename VARCHAR(200) NOT NULL,
    created_at        DATETIME     NOT NULL default current_timestamp,
    PRIMARY KEY (product_id)
);
INSERT INTO PRODUCT (product_name, product_price, product_imagename)
VALUES ('치킨',
        50000,
        'https://images.unsplash.com/photo-1679926591722-79bff79c561a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80');