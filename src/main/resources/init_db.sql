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
                                     salt blob NOT NULL,
                                     PRIMARY KEY (user_id)
)
    ENGINE = INNODB,
    AUTO_INCREMENT = 89,
    AVG_ROW_LENGTH = 4096,
    CHARACTER SET utf8,
    COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS orders (
                                      order_id int(11) NOT NULL AUTO_INCREMENT,
                                      user_id int(11) NOT NULL,
                                      PRIMARY KEY (order_id, user_id)
)
    ENGINE = INNODB,
    AUTO_INCREMENT = 18,
    AVG_ROW_LENGTH = 1489,
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
    AUTO_INCREMENT = 32,
    AVG_ROW_LENGTH = 4096,
    CHARACTER SET utf8,
    COLLATE utf8_general_ci;

ALTER TABLE buckets
    ADD INDEX users_fk_idx (user_id);

ALTER TABLE buckets
    ADD CONSTRAINT users_fk FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE IF NOT EXISTS roles (
                                     role_id int(11) NOT NULL AUTO_INCREMENT,
                                     name varchar(20) NOT NULL,
                                     PRIMARY KEY (role_id)
)
    ENGINE = INNODB,
    AUTO_INCREMENT = 5,
    AVG_ROW_LENGTH = 8192,
    CHARACTER SET utf8,
    COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS users_roles (
                                           user_id int(11) NOT NULL,
                                           role_id int(11) NOT NULL,
                                           PRIMARY KEY (user_id, role_id)
)
    ENGINE = INNODB,
    AVG_ROW_LENGTH = 2730,
    CHARACTER SET utf8,
    COLLATE utf8_general_ci;

ALTER TABLE users_roles
    ADD INDEX role_fk_idx (role_id);

ALTER TABLE users_roles
    ADD CONSTRAINT role_fk FOREIGN KEY (role_id)
        REFERENCES roles (role_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE users_roles
    ADD CONSTRAINT user_fk FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE IF NOT EXISTS items (
                                     item_id int(11) NOT NULL AUTO_INCREMENT,
                                     name varchar(255) DEFAULT NULL,
                                     price decimal(6, 2) DEFAULT NULL,
                                     PRIMARY KEY (item_id)
)
    ENGINE = INNODB,
    AUTO_INCREMENT = 18,
    AVG_ROW_LENGTH = 1820,
    CHARACTER SET utf8,
    COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS orders_items (
                                            order_id int(11) NOT NULL,
                                            item_id int(11) NOT NULL,
                                            PRIMARY KEY (order_id, item_id)
)
    ENGINE = INNODB,
    AVG_ROW_LENGTH = 862,
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
    AVG_ROW_LENGTH = 16384,
    CHARACTER SET utf8,
    COLLATE utf8_general_ci;

ALTER TABLE buckets_items
    ADD INDEX items_fk_idx (item_id);

ALTER TABLE buckets_items
    ADD CONSTRAINT buckets_fk FOREIGN KEY (bucket_id)
        REFERENCES buckets (bucket_id) ON DELETE NO ACTION;

ALTER TABLE buckets_items
    ADD CONSTRAINT items_fk FOREIGN KEY (item_id)
        REFERENCES items (item_id) ON DELETE NO ACTION;

CREATE USER IF NOT EXISTS 'adm'@'%' IDENTIFIED WITH mysql_native_password PASSWORD EXPIRE NEVER;



CREATE USER IF NOT EXISTS 'adm'@'192.168.1.34' IDENTIFIED WITH mysql_native_password PASSWORD EXPIRE DEFAULT;
GRANT Select, Insert, Update, Delete, Create, Drop, References, Index, Alter, Create Temporary Tables, Lock Tables, Create View, Show View, Create Routine, Alter Routine, Execute, Event, Trigger ON  internetshop.* TO 'adm'@'192.168.1.34';



CREATE USER IF NOT EXISTS 'adm'@'192.168.1.51' IDENTIFIED WITH mysql_native_password PASSWORD EXPIRE DEFAULT;
GRANT Select, Insert, Update, Delete, Create, Drop, References, Index, Alter, Create Temporary Tables, Lock Tables, Create View, Show View, Create Routine, Alter Routine, Execute, Event, Trigger ON  internetshop.* TO 'adm'@'192.168.1.51';



CREATE USER IF NOT EXISTS 'mysql.session'@'localhost' IDENTIFIED WITH mysql_native_password PASSWORD EXPIRE ACCOUNT LOCK;
GRANT Super ON *.* TO 'mysql.session'@'localhost';
GRANT Select ON  performance_schema.* TO 'mysql.session'@'localhost';
GRANT Select ON TABLE mysql.user TO 'mysql.session'@'localhost';



CREATE USER IF NOT EXISTS 'mysql.sys'@'localhost' IDENTIFIED WITH mysql_native_password PASSWORD EXPIRE ACCOUNT LOCK;
GRANT Trigger ON  sys.* TO 'mysql.sys'@'localhost';
GRANT Select ON TABLE sys.sys_config TO 'mysql.sys'@'localhost';



CREATE USER IF NOT EXISTS 'root'@'%' IDENTIFIED WITH mysql_native_password PASSWORD EXPIRE NEVER;
GRANT Usage ON *.* TO 'root'@'%'
    WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
GRANT Select, Insert, Update, Delete, Create, Drop, Grant Option, References, Index, Alter, Create Temporary Tables, Lock Tables, Create View, Show View, Create Routine, Alter Routine, Execute, Event, Trigger ON  internetshop.* TO 'root'@'%';



CREATE USER IF NOT EXISTS 'root'@'192.168.1.51' IDENTIFIED WITH mysql_native_password PASSWORD EXPIRE DEFAULT;
GRANT Usage ON *.* TO 'root'@'192.168.1.51'
    WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'192.168.1.51';
GRANT Select, Insert, Update, Delete, Create, Drop, Grant Option, References, Index, Alter, Create Temporary Tables, Lock Tables, Create View, Show View, Create Routine, Alter Routine, Execute, Event, Trigger ON  internetshop.* TO 'root'@'192.168.1.51';



CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED WITH mysql_native_password PASSWORD EXPIRE DEFAULT;
GRANT Usage ON *.* TO 'root'@'localhost'
    WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost';
GRANT PROXY ON ''@'' TO 'root'@'localhost' WITH GRANT OPTION;



CREATE USER IF NOT EXISTS 'task'@'%' IDENTIFIED WITH mysql_native_password PASSWORD EXPIRE DEFAULT;
GRANT Select, Insert, Update, Delete, Create, Drop, References, Index, Alter, Create Temporary Tables, Lock Tables, Create View, Show View, Create Routine, Alter Routine, Execute, Event, Trigger ON  task.* TO 'task'@'%';




BEGIN;
INSERT INTO users(user_id, name, surname, login, password, email, phone, token, salt) VALUES
(1, 'Admin', 'ADMIN', 'admin', '1bf004e9feb26ba4bb92418e1017746c3618959e797ae176bf8b620292861a5f44f27bb1dfc5fe28235c610da53b1b008f76524e2110ec61793cb85571bf6528', 'admin@gmail.com', '0677580666', 'd1e06ef6-6beb-4754-9ec6-9e8eb8890062', x'E1F9E9A9C90136D7DA12D64D7EA1EDC8'),
(2, 'User', 'USER', 'user', 'affb80cf4c8f6a0f60dd3c221af5098e6d823f8a4fc05018471088bcb489595b49d4a554afc96adfdfe2f3631c2bc2e9843bb755acab6d33553b3bb8bc3c70a1', 'user@gmail.com', '0684571241', '72a18643-3f1d-455d-9a96-575e4140ab07', x'AEE8B54E94368C9EAD06FE80AB753993'),
(3, 'Victor', 'Petroff', 'victor', 'bc69badfc7a87ee44acc6540ddfea28e10b9be74da290763bd52e20ab8f11260c51630e52a22b3c39ba70566d6b0928e0bb36b792e7c9a74b7b1a3c0be8f03d0', 'victor@mail.com', '0985234754', 'd2d4429a-2c78-4704-9ec4-ab3b260f99ab', x'986038C41750242235651EA942C7D2B4');
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
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 2),
(12, 2),
(13, 2),
(14, 2),
(15, 2),
(16, 2),
(17, 2);
COMMIT;


BEGIN;
INSERT INTO items(item_id, name, price) VALUES
(4, 'Huawei Nova', 650.00),
(5, 'Phone', 671.00),
(6, 'Phone-000', 574.00),
(7, 'Tablet4', 1247.00),
(8, 'Watches7', 240.00),
(9, 'Tommy Hilfiger', 657.00),
(10, 'Tablet Lenovo', 243.00),
(11, 'Google Pixel 3a', 846.00),
(12, 'Samsung Galaxy Note10', 864.00);
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
(7, 4),
(7, 5),
(6, 6),
(7, 6),
(15, 7),
(6, 8),
(7, 8),
(12, 8),
(13, 8),
(14, 8),
(16, 8),
(17, 8),
(10, 10),
(11, 10),
(9, 11),
(7, 12),
(8, 12);
COMMIT;


BEGIN;
INSERT INTO buckets_items(bucket_id, item_id) VALUES
(1, 4);
COMMIT;
