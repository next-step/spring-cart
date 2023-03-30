-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE PRODUCT
(
    ID    INT          NOT NULL AUTO_INCREMENT,
    NAME  VARCHAR(50)  NOT NULL,
    IMAGE VARCHAR(500) NULL,
    PRICE INT          NOT NULL,
    PRIMARY KEY (ID)
);

INSERT INTO PRODUCT (NAME, IMAGE, PRICE)
VALUES ('Cake and Coffee',
        'https://images.unsplash.com/photo-1677523875054-a615d799d9c8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80',
        10000);
INSERT INTO PRODUCT (NAME, IMAGE, PRICE)
VALUES ('Cop Salad',
        'https://images.unsplash.com/photo-1677414283794-677bdf6dc6c9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80',
        20000);
INSERT INTO PRODUCT (NAME, IMAGE, PRICE)
VALUES ('Bagle',
        'https://images.unsplash.com/photo-1675790959822-b164bea916f9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=866&q=80',
        30000);
INSERT INTO PRODUCT (NAME, IMAGE, PRICE)
VALUES ('Dount',
        'https://plus.unsplash.com/premium_photo-1676473229316-4cd9c91a5ef8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80',
        40000);
INSERT INTO PRODUCT (NAME, IMAGE, PRICE)
VALUES ('Ice Cream',
        'https://images.unsplash.com/photo-1676064414802-9de367aeb595?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80',
        50000);

CREATE TABLE MEMBER
(
    ID       INT          NOT NULL AUTO_INCREMENT,
    NAME     VARCHAR(50)  NOT NULL,
    EMAIL    VARCHAR(100) NOT NULL,
    PASSWORD VARCHAR(500) NOT NULL,
    PRIMARY KEY (ID)
);

INSERT INTO MEMBER (NAME, EMAIL, PASSWORD)
VALUES ('Tom', 'Tom.tam@kakaopaysec.com', 'tom123');
INSERT INTO MEMBER (NAME, EMAIL, PASSWORD)
VALUES ('Jerry', 'Jerry.cheese@kakaopaysec.com', 'jjpad123');
INSERT INTO MEMBER (NAME, EMAIL, PASSWORD)
VALUES ('Bob', 'Bob.baobob@kakaopaysec.com', 'bobobob999');

CREATE TABLE CART
(
    EMAIL      VARCHAR(100) NOT NULL,
    PASSWORD   VARCHAR(500) NOT NULL,
    PRODUCT_ID INT          NOT NULL
);
