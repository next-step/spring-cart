-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE PRODUCT
(
    product_id        INT          NOT NULL AUTO_INCREMENT,
    product_name      VARCHAR(50)  NOT NULL,
    product_price     INT          NOT NULL,
    product_imagename VARCHAR(100) NOT NULL,
    created_at        DATETIME     NOT NULL default current_timestamp,
    PRIMARY KEY (product_id)
);
