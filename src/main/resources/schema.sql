DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS users;

CREATE TABLE product (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    image_url varchar(255),
    price int NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users (
    id bigint NOT NULL AUTO_INCREMENT,
    email varchar(50) NOT NULL,
    password varchar(100) NOT NULL,
    PRIMARY KEY (id)
);
