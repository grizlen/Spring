DROP DATABASE `hiber_db`;
CREATE DATABASE  IF NOT EXISTS `hiber_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `hiber_db`;

DROP TABLE IF EXISTS `clients`;
CREATE TABLE `clients` (
  `client_id` int NOT NULL AUTO_INCREMENT,
  `client_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ;
INSERT INTO `hiber_db`.`clients`
(`client_name`)
VALUES
('First client'),
('Second client'),
('Third client');

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) DEFAULT NULL,
  `product_cost` float DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ;
INSERT INTO `hiber_db`.`products`
(`product_name`, `product_cost`)
VALUES
('product #1', 10),
('product #2', 15),
('product #3', 100);

CREATE TABLE `clients_products` (
  `client_id` int NOT NULL,
  `product_id` int NOT NULL,
  KEY `fk_client_id` (`client_id`),
  KEY `fk_product_id` (`product_id`),
  CONSTRAINT `fk_client_id` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `hiber_db`.`clients_products`
(`client_id`, `product_id`)
VALUES
(1, 1),
(1, 2),
(2, 3);
