SET NAMES 'utf8';

DROP DATABASE IF EXISTS internetshop;

CREATE DATABASE IF NOT EXISTS internetshop
    CHARACTER SET utf8
    COLLATE utf8_general_ci;

USE internetshop;

CREATE TABLE IF NOT EXISTS users (
                                     user_id int(11) NOT NULL AUTO_INCREMENT,
                                     name varchar(30) DEFAULT NULL,
                                     surname varchar(30) DEFAULT NULL,
                                     login varchar(25) NOT NULL,
                                     password varchar(255) NOT NULL,
                                     email varchar(50) DEFAULT NULL,
                                     phone varchar(20) DEFAULT NULL,
                                     token varchar(255) DEFAULT NULL,
                                     PRIMARY KEY (user_id)
)
    ENGINE = INNODB,
    AUTO_INCREMENT = 52,
    AVG_ROW_LENGTH = 5461,
    CHARACTER SET utf8,
    COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS orders (
                                      order_id int(11) NOT NULL AUTO_INCREMENT,
                                      user_id int(11) NOT NULL,
                                      PRIMARY KEY (order_id, user_id)
)
    ENGINE = INNODB,
    AUTO_INCREMENT = 7,
    AVG_ROW_LENGTH = 2730,
    CHARACTER SET utf8,
    COLLATE utf8_general_ci;

ALTER TABLE orders
    ADD INDEX `users-orders_fk_idx` (user_id);

ALTER TABLE orders
    ADD CONSTRAINT `users-orders_fk` FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

CREATE TABLE IF NOT EXISTS buckets (
                                       bucket_id int(11) NOT NULL AUTO_INCREMENT,
                                       user_id int(11) NOT NULL,
                                       PRIMARY KEY (bucket_id, user_id)
)
    ENGINE = INNODB,
    AUTO_INCREMENT = 7,
    AVG_ROW_LENGTH = 4096,
    CHARACTER SET utf8,
    COLLATE utf8_general_ci;

ALTER TABLE buckets
    ADD INDEX users_fk_idx (user_id);

ALTER TABLE buckets
    ADD CONSTRAINT users_fk FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

CREATE TABLE IF NOT EXISTS roles (
                                     role_id int(11) NOT NULL AUTO_INCREMENT,
                                     name varchar(20) NOT NULL,
                                     PRIMARY KEY (role_id)
)
    ENGINE = INNODB,
    AUTO_INCREMENT = 4,
    AVG_ROW_LENGTH = 8192,
    CHARACTER SET utf8,
    COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS users_roles (
                                           user_id int(11) NOT NULL,
                                           role_id int(11) NOT NULL,
                                           PRIMARY KEY (user_id, role_id)
)
    ENGINE = INNODB,
    AVG_ROW_LENGTH = 3276,
    CHARACTER SET utf8,
    COLLATE utf8_general_ci;

ALTER TABLE users_roles
    ADD INDEX role_fk_idx (role_id);

ALTER TABLE users_roles
    ADD CONSTRAINT role_fk FOREIGN KEY (role_id)
        REFERENCES roles (role_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE users_roles
    ADD CONSTRAINT user_fk FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

CREATE TABLE IF NOT EXISTS items (
                                     item_id int(11) NOT NULL AUTO_INCREMENT,
                                     name varchar(255) DEFAULT NULL,
                                     price decimal(6, 2) DEFAULT NULL,
                                     PRIMARY KEY (item_id)
)
    ENGINE = INNODB,
    AUTO_INCREMENT = 9,
    AVG_ROW_LENGTH = 4096,
    CHARACTER SET utf8,
    COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS orders_items (
                                            order_id int(11) NOT NULL,
                                            item_id int(11) NOT NULL,
                                            PRIMARY KEY (order_id, item_id)
)
    ENGINE = INNODB,
    AVG_ROW_LENGTH = 4096,
    CHARACTER SET utf8,
    COLLATE utf8_general_ci;

ALTER TABLE orders_items
    ADD INDEX `orders-items_fk_idx` (item_id);

ALTER TABLE orders_items
    ADD CONSTRAINT `items-orders_fk` FOREIGN KEY (item_id)
        REFERENCES items (item_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE orders_items
    ADD CONSTRAINT `orders-items_fk` FOREIGN KEY (order_id)
        REFERENCES orders (order_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

CREATE TABLE IF NOT EXISTS buckets_items (
                                             bucket_id int(11) NOT NULL,
                                             item_id int(11) NOT NULL,
                                             PRIMARY KEY (bucket_id, item_id)
)
    ENGINE = INNODB,
    AVG_ROW_LENGTH = 5461,
    CHARACTER SET utf8,
    COLLATE utf8_general_ci;

ALTER TABLE buckets_items
    ADD INDEX items_fk_idx (item_id);

ALTER TABLE buckets_items
    ADD CONSTRAINT buckets_fk FOREIGN KEY (bucket_id)
        REFERENCES buckets (bucket_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE buckets_items
    ADD CONSTRAINT items_fk FOREIGN KEY (item_id)
        REFERENCES items (item_id) ON DELETE NO ACTION ON UPDATE NO ACTION;


BEGIN;
INSERT INTO users(user_id, name, surname, login, password, email, phone, token) VALUES
(1, 'Admin', 'ADMIN', 'admin', '1', 'admin@gmail.com', '0675478593', 'c8cce9e7-f365-49c9-b3bb-0eefcebf10eb'),
(2, 'User', 'USER', 'user', '1', 'user@mail.com', '0685470547', '8838c80b-edbc-48c0-b36d-5c853df69714'),
(3, 'Victor', 'Petroff', 'victor', '1', 'victor@gmail.com', '0985475501', '9e51f710-8771-4b28-9f95-7f55ade88489');
COMMIT;


BEGIN;
INSERT INTO roles(role_id, name) VALUES
(1, 'ADMIN'),
(2, 'USER'),
(3, 'MANAGER');
COMMIT;


BEGIN;
INSERT INTO orders(order_id, user_id) VALUES
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2);
COMMIT;


BEGIN;
INSERT INTO items(item_id, name, price) VALUES
(4, 'Huawei Nova', 650.00),
(5, 'Phone', 671.00),
(6, 'Phone-000', 574.00),
(7, 'Tablet4', 1247.00),
(8, 'Watches7', 240.00);
COMMIT;


BEGIN;
INSERT INTO buckets(bucket_id, user_id) VALUES
(1, 1),
(2, 2),
(3, 3);
COMMIT;


BEGIN;
INSERT INTO users_roles(user_id, role_id) VALUES
(1, 1),
(3, 1),
(2, 2),
(3, 2),
(3, 3);
COMMIT;


BEGIN;
INSERT INTO orders_items(order_id, item_id) VALUES
(1, 4),
(6, 4),
(6, 6),
(6, 8);
COMMIT;


BEGIN;
INSERT INTO buckets_items(bucket_id, item_id) VALUES
(1, 4),
(2, 4),
(2, 6),
(2, 8);
COMMIT;