DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS product;

CREATE TABLE product
(
    id        bigint      NOT NULL AUTO_INCREMENT,
    name      varchar(50) NOT NULL,
    image_url varchar(255),
    price     int         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       bigint       NOT NULL AUTO_INCREMENT,
    email    varchar(50)  NOT NULL,
    password varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE cart
(
    id         bigint NOT NULL AUTO_INCREMENT,
    user_id    bigint NOT NULL,
    product_id bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);
