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
    AUTO_INCREMENT = 62,
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
    AVG_ROW_LENGTH = 8192,
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
    AUTO_INCREMENT = 17,
    AVG_ROW_LENGTH = 5461,
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
(1, 'Admin', 'ADMIN', 'admin', '2206047770cd51a3fd79582a424901fae113783d706e013ec9276a1070f1a7556c39ab311b5fe058be559466bd0dc35bf4d490d2f99c28c083427f3e3fc55749', 'admin@gmail.com', '0675478593', 'c8cce9e7-f365-49c9-b3bb-0eefcebf10eb', x''),
(2, 'User', 'USER', 'user', '2206047770cd51a3fd79582a424901fae113783d706e013ec9276a1070f1a7556c39ab311b5fe058be559466bd0dc35bf4d490d2f99c28c083427f3e3fc55749', 'user@mail.com', '0685470547', '8838c80b-edbc-48c0-b36d-5c853df69714', x''),
(3, 'Victor', 'Petroff', 'victor', '2206047770cd51a3fd79582a424901fae113783d706e013ec9276a1070f1a7556c39ab311b5fe058be559466bd0dc35bf4d490d2f99c28c083427f3e3fc557491', 'victor@gmail.com', '0985475501', '9e51f710-8771-4b28-9f95-7f55ade88489', x'');
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
