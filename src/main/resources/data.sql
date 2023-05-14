create table CART_PRODUCT
(
    product_id        int auto_increment primary key,
    product_name      varchar(50)  not null,
    product_price     int          not null,
    product_image_url varchar(500) not null,
    created_at        datetime     not null
);

insert into CART_PRODUCT(product_name, product_price, product_image_url, created_at)
values ('사과', 1000,
        'https://i.namu.wiki/i/6dLtHMNcLvz8BWVrtEB9CNEPqjDEhLIbtEpnZdIcbxZ4TZPc-P-Yk69qoEWSIaCudos3zI0mT-jw89Lm881FHg.webp',
        now());

insert into CART_PRODUCT(product_name, product_price, product_image_url, created_at)
values ('옥수수', 2000,
        'https://i.namu.wiki/i/6dLtHMNcLvz8BWVrtEB9CNEPqjDEhLIbtEpnZdIcbxZ4TZPc-P-Yk69qoEWSIaCudos3zI0mT-jw89Lm881FHg.webp',
        now());

insert into CART_PRODUCT(product_name, product_price, product_image_url, created_at)
values ('바나나', 3000,
        'https://i.namu.wiki/i/6dLtHMNcLvz8BWVrtEB9CNEPqjDEhLIbtEpnZdIcbxZ4TZPc-P-Yk69qoEWSIaCudos3zI0mT-jw89Lm881FHg.webp',
        now());

 create table CART_MEMBER
(
    member_id       int auto_increment primary key,
    member_email    varchar(250) not null,
    member_password varchar(500) not null,
    created_at      datetime     not null
);

insert into CART_MEMBER (member_email, member_password, created_at)
values ('a@a.com', 'password1', now());

insert into CART_MEMBER (member_email, member_password, created_at)
values ('b@b.com', 'password2', now());