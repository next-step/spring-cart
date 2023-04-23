DROP TABLE IF EXISTS PRODUCT_ITEMS;
CREATE TABLE PRODUCT_ITEMS
(
    id           INT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(200)      NOT NULL,
    image_url    varchar(200)      NOT NULL,
    price        INT               NOT NULL,
    created_at   DATETIME NOT NULL default current_timestamp
);
