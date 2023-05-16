drop table if exists CART_ITEM;
drop table if exists PRODUCT;
drop table if exists MEMBER;

create table PRODUCT
(
    id         int auto_increment primary key,
    name       varchar(50) not null,
    price      int         not null,
    image_url  text        not null,
    created_at datetime    not null
);

insert into PRODUCT(name, price, image_url, created_at)
values ('사과', 1000,
        'https://i.namu.wiki/i/6dLtHMNcLvz8BWVrtEB9CNEPqjDEhLIbtEpnZdIcbxZ4TZPc-P-Yk69qoEWSIaCudos3zI0mT-jw89Lm881FHg.webp',
        now());

insert into PRODUCT(name, price, image_url, created_at)
values ('옥수수', 2000,
        'https://i.namu.wiki/i/6dLtHMNcLvz8BWVrtEB9CNEPqjDEhLIbtEpnZdIcbxZ4TZPc-P-Yk69qoEWSIaCudos3zI0mT-jw89Lm881FHg.webp',
        now());

insert into PRODUCT(name, price, image_url, created_at)
values ('바나나', 3000,
        'https://i.namu.wiki/i/6dLtHMNcLvz8BWVrtEB9CNEPqjDEhLIbtEpnZdIcbxZ4TZPc-P-Yk69qoEWSIaCudos3zI0mT-jw89Lm881FHg.webp',
        now());

create table MEMBER
(
    id         int auto_increment primary key,
    email      varchar(250) not null,
    password   varchar(500) not null,
    created_at datetime     not null
);

insert into MEMBER (email, password, created_at)
values ('a@a.com', 'password1', now());

insert into MEMBER (email, password, created_at)
values ('b@b.com', 'password2', now());

create table CART_ITEM
(
    id    int auto_increment primary key,
    member_id  int      not null,
    product_id int      not null,
    created_at datetime not null,
    FOREIGN KEY (member_id) REFERENCES MEMBER (id),
    FOREIGN KEY (product_id) REFERENCES PRODUCT (id)
)