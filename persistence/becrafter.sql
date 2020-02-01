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
  `id` int(11) NOT NULL DEFAULT 0,
  `name` varchar(50) NOT NULL,
  `type` enum('ALE','LAMBIC','LAGER') NOT NULL,
  `color` enum('LIGHT','AMBER','RUBY','DARK') NOT NULL,
  `filtering` enum('FILTERED','UNFILTERED') NOT NULL,
  `alcohol` float NOT NULL,
  `price` float NOT NULL,
  `description` text NOT NULL,
  `recipeId` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella becrafter.beers: ~3 rows (circa)
/*!40000 ALTER TABLE `beers` DISABLE KEYS */;
INSERT INTO `beers` (`id`, `name`, `type`, `color`, `filtering`, `alcohol`, `price`, `description`, `recipeId`) VALUES
	(1, 'The Wrecking', 'LAGER', 'LIGHT', 'FILTERED', 4.1, 6, 'The simplest light beer', 'R001'),
	(2, 'The Sober', 'ALE', 'DARK', 'FILTERED', 9.3, 9.5, 'An Ale beer brewed using warm fermentation method, resulting in a sweet, full-bodied and fruity taste.', 'R002'),
	(3, 'The Matryoshka', 'LAMBIC', 'AMBER', 'FILTERED', 5.9, 7.5, 'A marriage between beer and wine', 'R003');
/*!40000 ALTER TABLE `beers` ENABLE KEYS */;

-- Dump della struttura di tabella becrafter.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `date` varchar(50) NOT NULL,
  `price` float NOT NULL,
  `shippingCode` varchar(50) DEFAULT NULL,
  `shippingCompany` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella becrafter.orders: ~1 rows (circa)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `email`, `date`, `price`, `shippingCode`, `shippingCompany`) VALUES
	(1, 'test@provider.org', '2020/02/01', 92.25, '', '');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Dump della struttura di tabella becrafter.registered
CREATE TABLE IF NOT EXISTS `registered` (
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `firstname` varchar(50) NOT NULL DEFAULT '',
  `lastname` varchar(50) NOT NULL DEFAULT '',
  `address` varchar(50) NOT NULL DEFAULT '',
  `city` varchar(50) NOT NULL DEFAULT '',
  `country` varchar(50) NOT NULL DEFAULT '',
  `postalCode` varchar(50) NOT NULL DEFAULT '',
  `phone` varchar(50) NOT NULL DEFAULT '',
  `card` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella becrafter.registered: ~0 rows (circa)
/*!40000 ALTER TABLE `registered` DISABLE KEYS */;
INSERT INTO `registered` (`email`, `password`, `firstname`, `lastname`, `address`, `city`, `country`, `postalCode`, `phone`, `card`) VALUES
	('test@provider.org', 'TestPass1', 'Firstname', 'Lastname', 'Address, 00', 'City', 'Country', '00000', '+00 000 000 0000', '1234-1234-1234-1234');
/*!40000 ALTER TABLE `registered` ENABLE KEYS */;

-- Dump della struttura di tabella becrafter.storehouse_containers
CREATE TABLE IF NOT EXISTS `storehouse_containers` (
  `type` enum('BOTTLE','CAN','BARREL') NOT NULL,
  `volume` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`type`,`volume`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella becrafter.storehouse_containers: ~7 rows (circa)
/*!40000 ALTER TABLE `storehouse_containers` DISABLE KEYS */;
INSERT INTO `storehouse_containers` (`type`, `volume`, `quantity`) VALUES
	('BOTTLE', 33, 1000),
	('BOTTLE', 50, 1000),
	('BOTTLE', 75, 1000),
	('CAN', 33, 1000),
	('CAN', 50, 1000),
	('BARREL', 5000, 1000),
	('BARREL', 10000, 1000);
/*!40000 ALTER TABLE `storehouse_containers` ENABLE KEYS */;

-- Dump della struttura di tabella becrafter.storehouse_products
CREATE TABLE IF NOT EXISTS `storehouse_products` (
  `beerId` varchar(50) NOT NULL,
  `containerType` enum('BOTTLE','CAN','BARREL') NOT NULL,
  `containerVolume` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`beerId`,`containerType`,`containerVolume`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella becrafter.storehouse_products: ~21 rows (circa)
/*!40000 ALTER TABLE `storehouse_products` DISABLE KEYS */;
INSERT INTO `storehouse_products` (`beerId`, `containerType`, `containerVolume`, `quantity`) VALUES
	('B001', 'BOTTLE', 33, 250),
	('B001', 'BOTTLE', 50, 250),
	('B001', 'BOTTLE', 75, 250),
	('B001', 'CAN', 33, 250),
	('B001', 'CAN', 50, 250),
	('B001', 'BARREL', 5000, 250),
	('B001', 'BARREL', 10000, 250),
	('B002', 'BOTTLE', 33, 250),
	('B002', 'BOTTLE', 50, 250),
	('B002', 'BOTTLE', 75, 250),
	('B002', 'CAN', 33, 250),
	('B002', 'CAN', 50, 250),
	('B002', 'BARREL', 5000, 250),
	('B002', 'BARREL', 10000, 250),
	('B003', 'BOTTLE', 33, 250),
	('B003', 'BOTTLE', 50, 250),
	('B003', 'BOTTLE', 75, 250),
	('B003', 'CAN', 33, 250),
	('B003', 'CAN', 50, 250),
	('B003', 'BARREL', 5000, 250),
	('B003', 'BARREL', 10000, 250);
/*!40000 ALTER TABLE `storehouse_products` ENABLE KEYS */;

-- Dump della struttura di tabella becrafter.storehouse_rawmaterials
CREATE TABLE IF NOT EXISTS `storehouse_rawmaterials` (
  `type` enum('HOP','BARLEY','RICE','CORN','WHEAT','SUGAR','WATER') NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella becrafter.storehouse_rawmaterials: ~7 rows (circa)
/*!40000 ALTER TABLE `storehouse_rawmaterials` DISABLE KEYS */;
INSERT INTO `storehouse_rawmaterials` (`type`, `quantity`) VALUES
	('HOP', 1000000),
	('BARLEY', 1000000),
	('RICE', 1000000),
	('CORN', 1000000),
	('WHEAT', 1000000),
	('SUGAR', 1000000),
	('WATER', 1000000);
/*!40000 ALTER TABLE `storehouse_rawmaterials` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
