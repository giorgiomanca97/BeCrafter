-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versione server:              10.4.11-MariaDB - mariadb.org binary distribution
-- S.O. server:                  Win64
-- HeidiSQL Versione:            10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dump della struttura del database becrafter
CREATE DATABASE IF NOT EXISTS `becrafter` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `becrafter`;

-- Dump della struttura di tabella becrafter.beers
CREATE TABLE IF NOT EXISTS `beers` (
  `id` varchar(50) NOT NULL,
  `name` text DEFAULT NULL,
  `type` enum('ALE','LAMBIC','LAGER') DEFAULT NULL,
  `color` enum('LIGHT','AMBER','RUBY','DARK') DEFAULT NULL,
  `filtered` binary(50) DEFAULT NULL,
  `alcohol` float DEFAULT NULL,
  `price` float DEFAULT NULL,
  `description` text DEFAULT NULL,
  `recipeId` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella becrafter.beers: ~0 rows (circa)
/*!40000 ALTER TABLE `beers` DISABLE KEYS */;
/*!40000 ALTER TABLE `beers` ENABLE KEYS */;

-- Dump della struttura di tabella becrafter.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` text DEFAULT NULL,
  `registeredMail` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella becrafter.orders: ~0 rows (circa)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Dump della struttura di tabella becrafter.registered
CREATE TABLE IF NOT EXISTS `registered` (
  `email` text DEFAULT NULL,
  `password` text DEFAULT NULL,
  `firstname` text DEFAULT NULL,
  `lastname` text DEFAULT NULL,
  `address` text DEFAULT NULL,
  `city` text DEFAULT NULL,
  `country` text DEFAULT NULL,
  `postalCode` int(11) DEFAULT NULL,
  `phone` text DEFAULT NULL,
  `card` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella becrafter.registered: ~0 rows (circa)
/*!40000 ALTER TABLE `registered` DISABLE KEYS */;
/*!40000 ALTER TABLE `registered` ENABLE KEYS */;

-- Dump della struttura di tabella becrafter.storehouse_containers
CREATE TABLE IF NOT EXISTS `storehouse_containers` (
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella becrafter.storehouse_containers: ~0 rows (circa)
/*!40000 ALTER TABLE `storehouse_containers` DISABLE KEYS */;
/*!40000 ALTER TABLE `storehouse_containers` ENABLE KEYS */;

-- Dump della struttura di tabella becrafter.storehouse_products
CREATE TABLE IF NOT EXISTS `storehouse_products` (
  `idBeer` int(11) NOT NULL,
  `containerType` int(11) NOT NULL,
  `containerVolume` int(11) NOT NULL,
  PRIMARY KEY (`idBeer`,`containerType`,`containerVolume`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella becrafter.storehouse_products: ~5 rows (circa)
/*!40000 ALTER TABLE `storehouse_products` DISABLE KEYS */;
INSERT INTO `storehouse_products` (`idBeer`, `containerType`, `containerVolume`) VALUES
	(1, 6, 33),
	(2, 6, 75),
	(3, 8, 50),
	(4, 8, 50),
	(666, 12, 50);
/*!40000 ALTER TABLE `storehouse_products` ENABLE KEYS */;

-- Dump della struttura di tabella becrafter.storehouse_rawmaterials
CREATE TABLE IF NOT EXISTS `storehouse_rawmaterials` (
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella becrafter.storehouse_rawmaterials: ~0 rows (circa)
/*!40000 ALTER TABLE `storehouse_rawmaterials` DISABLE KEYS */;
/*!40000 ALTER TABLE `storehouse_rawmaterials` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
