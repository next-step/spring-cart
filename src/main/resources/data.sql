CREATE TABLE product_list
(
    id INT      NOT NULL AUTO_INCREMENT,
    name varchar(200)      NOT NULL,
    image_url varchar(200)      NOT NULL,
    price INT              NOT NULL,
    created_at DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

