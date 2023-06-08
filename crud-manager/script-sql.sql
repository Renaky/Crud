-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.24-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para crud_manager
CREATE DATABASE IF NOT EXISTS `crud_manager` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `crud_manager`;

-- Copiando estrutura para tabela crud_manager.companies
CREATE TABLE IF NOT EXISTS `companies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `role` varchar(128) NOT NULL,
  `start` date NOT NULL,
  `end` date DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `companies_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela crud_manager.companies: ~3 rows (aproximadamente)
INSERT INTO `companies` (`id`, `name`, `role`, `start`, `end`, `user_id`) VALUES
	(1, 'Epic Games Corporation', 'Filho do dono', '2023-01-04', '2023-06-04', 3),
	(2, 'Xumbrelium Detetização', 'Detetização', '2022-06-04', '2025-06-04', 4),
	(3, 'Sensorizadora remota', 'Internet das coisas', '2020-06-02', '2025-06-02', 1);

-- Copiando estrutura para tabela crud_manager.posts
CREATE TABLE IF NOT EXISTS `posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `post_date` date NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela crud_manager.posts: ~8 rows (aproximadamente)
INSERT INTO `posts` (`id`, `content`, `post_date`, `user_id`) VALUES
	(1, 'Olá do Emerson', '2023-06-04', 1),
	(2, 'Olá da Luiza', '2023-06-04', 2),
	(3, 'Olá da Denise', '2023-06-04', 3),
	(4, 'Olá do Noé', '2023-06-04', 4),
	(5, 'Olá da Rosânia', '2023-06-04', 5),
	(6, 'Olá da Rosânia 1', '2023-06-04', 5),
	(7, 'Olá da Rosânia 2', '2023-06-04', 5),
	(8, 'Olá da Rosânia 3', '2023-06-04', 5);

-- Copiando estrutura para tabela crud_manager.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `sexo` enum('M','F') DEFAULT NULL,
  `email` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela crud_manager.users: ~5 rows (aproximadamente)
INSERT INTO `users` (`id`, `nome`, `sexo`, `email`) VALUES
	(1, 'Emerson Carvalho', 'M', 'emerson@mail.com'),
	(2, 'Luiza Carvalho', 'F', 'lu@mail.com'),
	(3, 'Elenice Carvalho', 'F', 'le@mail.com'),
	(4, 'Noé Carvalho', 'M', 'noe@mail.com'),
	(5, 'Rosânia Carvalho', 'F', 'ro@mail.com');

-- Copiando estrutura para tabela crud_manager.vehicles
CREATE TABLE IF NOT EXISTS `vehicles` (
  `idvehicles` int(11) NOT NULL AUTO_INCREMENT,
  `color` varchar(45) DEFAULT NULL,
  `brand` varchar(45) DEFAULT NULL,
  `model` varchar(45) DEFAULT NULL,
  `type` enum('truck','car','van','bike') DEFAULT NULL,
  `number_plate` varchar(10) NOT NULL,
  `companies_id` int(11) NOT NULL,
  PRIMARY KEY (`idvehicles`),
  KEY `fk_vehicles_companies1_idx` (`companies_id`),
  CONSTRAINT `fk_vehicles_companies1` FOREIGN KEY (`companies_id`) REFERENCES `companies` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela crud_manager.vehicles: ~3 rows (aproximadamente)
INSERT INTO `vehicles` (`idvehicles`, `color`, `brand`, `model`, `type`, `number_plate`, `companies_id`) VALUES
	(2, 'Vermelho', 'Toyota', 'Camry', 'car', 'GXD-2058', 1),
	(3, 'Branco', 'Kia', 'Cadenza', 'car', 'PXG-2023', 3);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
