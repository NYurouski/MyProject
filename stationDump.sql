-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.21-log - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных station
CREATE DATABASE IF NOT EXISTS `station` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `station`;


-- Дамп структуры для таблица station.cars
CREATE TABLE IF NOT EXISTS `cars` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `make` varchar(50) NOT NULL DEFAULT '0',
  `model` varchar(50) NOT NULL DEFAULT '0',
  `year` varchar(50) NOT NULL DEFAULT '0',
  `vin` varchar(50) NOT NULL DEFAULT '0',
  `clientid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы station.cars: ~14 rows (приблизительно)
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` (`id`, `make`, `model`, `year`, `vin`, `clientid`) VALUES
	(1, 'Korea', 'Kia', '2013', '341213435', 3),
	(2, 'China', 'Geely', '2013', '34121343542', 3),
	(3, 'Germany', 'BMW', '2010', '5468123', 1),
	(4, 'Germany', 'Mersedes', '2009', '213123712321', 2),
	(6, 'Germany', 'BMW', '2011', '123213675471', 2),
	(7, 'China', 'Geely', '2015', '123213414', 2),
	(11, 'USA', 'GMC', '2014', '2132178414141', 5),
	(13, 'France', 'Citroen', '2011', '23635123123', 7),
	(15, 'USA', 'Ford', '2130', '3213214', 7),
	(16, 'USA', 'Ford', '2012', '3213213123', 8),
	(17, 'France', 'Peguet', '2013', '3214555', 11),
	(19, 'Germany', 'BMW', '1995', '63153413', 9),
	(21, 'USA', 'Ford', '2015', '23254245646', 12),
	(24, 'France', 'Reno', '2014', '5645435234', 13);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;


-- Дамп структуры для таблица station.clients
CREATE TABLE IF NOT EXISTS `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0',
  `surname` varchar(50) NOT NULL DEFAULT '0',
  `birth` varchar(50) NOT NULL DEFAULT '0',
  `address` varchar(50) NOT NULL DEFAULT '0',
  `mail` varchar(50) NOT NULL DEFAULT '0',
  `phone` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_surname` (`name`,`surname`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы station.clients: ~9 rows (приблизительно)
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` (`id`, `name`, `surname`, `birth`, `address`, `mail`, `phone`) VALUES
	(1, 'alex', 'pro', '30.08.1983', 'Minsk', 'alex@mail.by', '321321321'),
	(2, 'Jeff', 'Glass', '17.03.1993', 'cascsa', 'Jeff@mail.ru', '32132134'),
	(3, 'serg', 'Devant', '30.07.1993', 'Orsha', 'sadas@mail.ru', '43256252'),
	(5, 'Rain', 'Weske', '14.02.1985', 'Boston', 'rain@gmail.com', '896786243'),
	(6, 'Sharle', 'Linglet', '6.08.1987', 'Chicago', 'linglet@gmail.com', '8686432'),
	(7, 'Pavel', 'Freedom', '12.07.1993', 'Minsk', 'ololo@mail.ru', '677853'),
	(8, 'David', 'Luis', '12.05.1990', 'Minsk', 'lusi@mail.ru', '45632486'),
	(9, 'Pavel', 'Spichakov', '02.09.1992', 'Minsk', 'sadas@mail.ru', '9864329'),
	(10, 'Pavel', 'Spich', '12.09.1992', 'Minsk', 'sad@mail.ru', '97432'),
	(11, 'Pavel', 'Insane', '30.08.1992', 'Minsk', 'ins@gmail.com', '5435678'),
	(12, 'Nick', 'Mouse', '30.07.1993', 'Minsk', 'nuvytcr@mail.ru', '686746535'),
	(13, 'Sergey', 'Ivanov', '30.07.1993', 'Minsk', 'rfsf@mail.ru', '75675645643');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;


-- Дамп структуры для таблица station.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(50) NOT NULL DEFAULT '0',
  `amount` int(11) NOT NULL DEFAULT '0',
  `status` varchar(50) NOT NULL DEFAULT '0',
  `carid` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы station.orders: ~14 rows (приблизительно)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `date`, `amount`, `status`, `carid`) VALUES
	(3, '08.04.2013                  ', 10000, 'In Progress', '4'),
	(5, '30.10.2003', 6900, 'Completed', '4'),
	(8, '11.09.2015', 3200, 'In Progress', '4'),
	(9, '12.08.2010', 7999, 'Cancelled', '4'),
	(14, '29.09.2014  ', 2540, 'Completed', '6'),
	(15, '12.08.2010', 2300, 'Completed', '4'),
	(16, '11.05.2014', 7500, 'Completed', '4'),
	(22, '30.01.2014', 3213, 'Completed', '13'),
	(23, '12.04.2015', 2350, 'Cancelled', '15'),
	(24, '23.05.2014', 7500, 'In Progress', '16'),
	(25, '30.01.2015    ', 10000, 'Cancelled', '19'),
	(27, '13.06.2014  ', 0, 'In Progress', '19'),
	(28, '30.01.2015', 4320, 'In Progress', '6'),
	(29, '30.07.2015  ', 9500, 'Completed', '21');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
