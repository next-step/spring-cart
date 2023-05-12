DROP TABLE IF EXISTS product;

CREATE TABLE product (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    image_url varchar(255),
    price int NOT NULL,
    PRIMARY KEY (id)
);
