CREATE TABLE product_list
(
    product_id INT      NOT NULL AUTO_INCREMENT,
    product_name varchar(200)      NOT NULL,
    product_path varchar(200)      NOT NULL,
    product_price INT              NOT NULL,
    created_at DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (product_id)
);

