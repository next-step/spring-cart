DROP TABLE IF EXISTS PRODUCT_ITEMS;
CREATE TABLE PRODUCT_ITEMS (
                            id                INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            product_name      VARCHAR(200) NOT NULL,
                            image_url         VARCHAR(200) NOT NULL,
                            price             INT          NOT NULL,
                            work_detail_tmst  DATETIME    NOT NULL default current_timestamp
);

