insert into product(id, name, price, image, created_at) values (1L, '치킨', 10000, '/images/chicken.jpeg', PARSEDATETIME('2023-01-01 12:00:00', 'yyyy-MM-dd hh:mm:ss'));
insert into product(id, name, price, image, created_at) values (2L, '샐러드', 2000, '/images/salad.jpeg', PARSEDATETIME('2023-01-01 12:00:00', 'yyyy-MM-dd hh:mm:ss'));
insert into product(id, name, price, image, created_at) values (3L, '피자', 20000, '/images/pizza.jpeg', PARSEDATETIME('2023-01-01 12:00:00', 'yyyy-MM-dd hh:mm:ss'));

insert into member(id, email, password) values (1L, 'a@a.com', 'password1');
insert into member(id, email, password) values (2L, 'b@b.com', 'password2');


insert into cart(id, member_id, product_id, count) values  (1L, 1L, 1L, 1);
insert into cart(id, member_id, product_id, count) values  (2L, 1L, 2L, 1);
