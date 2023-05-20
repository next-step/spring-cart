-- reset tables
DELETE FROM cart;
DELETE FROM product;
DELETE FROM users;

-- user insert
INSERT INTO users(email, password) VALUES('a@a.com', 'passwordA');
INSERT INTO users(email, password) VALUES('b@b.com', 'passwordB');

-- product insert
INSERT INTO product(name, image_url, price) VALUES('상품A', 'image.com/imageA', 10000);
INSERT INTO product(name, image_url, price) VALUES('상품B', 'image.com/imageB', 20000);
INSERT INTO product(name, image_url, price) VALUES('상품C', 'image.com/imageC', 30000);
