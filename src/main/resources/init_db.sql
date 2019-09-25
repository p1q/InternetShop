CREATE SCHEMA `internetshop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internetshop`.`items` (
                                        `item_id` INT NOT NULL AUTO_INCREMENT,
                                        `name` VARCHAR(255) NULL,
                                        `price` DECIMAL(6,2) NULL,
                                        PRIMARY KEY (`item_id`));

INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('Huawei Nova 4', '1000');

SELECT * FROM internetshop.items where item_id = 1;

INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('Samsung S5', '521');
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('Meizu x2', '465');
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('Xiaomi Redmi 7', '241');
