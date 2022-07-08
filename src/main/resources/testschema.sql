DROP TABLE IF EXISTS `customer`;	

CREATE TABLE `customer` (
	`id` BIGINT AUTO_INCREMENT,										
	`first_name` VARCHAR(255) NOT NULL,
	`last_name` VARCHAR(255) NOT NULL,
	`email` VARCHAR(255) UNIQUE NOT NULL,
	PRIMARY KEY(`id`)
);					