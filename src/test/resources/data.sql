DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS product;

create table product
(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NAME  CHARACTER VARYING(50),
    IMAGE CHARACTER VARYING(300),
    PRICE DOUBLE PRECISION
);


create table member
(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    EMAIL  CHARACTER VARYING(50),
    PASSWORD CHARACTER VARYING(50)
);

insert into member (EMAIL, PASSWORD)
values ('a@a.com','password1');

insert into member (EMAIL, PASSWORD)
values ('b@b.com','password2');

create table cart
(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    PRODUCT_ID INT ,
    MEMBER_ID INT,
    COUNT INT
);

