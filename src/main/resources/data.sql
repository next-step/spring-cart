-- PRODUCT
CREATE TABLE PRODUCT
(
    id         INT          NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    price      INT          NOT NULL,
    image_url  VARCHAR(255),
    created_at DATETIME DEFAULT current_timestamp,

    PRIMARY KEY (id)
);