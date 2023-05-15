-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 30, 2022 at 05:37 PM
-- Server version: 5.5.31
-- PHP Version: 5.4.4-14+deb7u4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `BOULANGERIE`
--

-- --------------------------------------------------------

--
-- Table structure for table `CLIENTE`
--

CREATE TABLE IF NOT EXISTS `CLIENTE` (
  `IDCli` int(6) NOT NULL AUTO_INCREMENT,
  `NomCli` char(50) NOT NULL,
  `AdrCli` char(50) NOT NULL,
  `VilleCli` char(50) NOT NULL,
  PRIMARY KEY (`IDCli`)
);

--
-- Dumping data for table `CLIENTE`
--

INSERT INTO `CLIENTE` (`IDCli`, `NomCli`, `AdrCli`, `VilleCli`) VALUES
(1, 'Kalemba', '8b rue denis papin', 'Besançon'),
(2, 'Ercolani', '2 rue des courtils', 'Besançon');

-- --------------------------------------------------------

--
-- Table structure for table `MELANGE`
--

CREATE TABLE IF NOT EXISTS `MELANGE` (
  `IDMelange` int(6) NOT NULL AUTO_INCREMENT,
  `DescMelange` char(50) NOT NULL,
  PRIMARY KEY (`IDMelange`)
);

--
-- Dumping data for table `MELANGE`
--

INSERT INTO `MELANGE` (`IDMelange`, `DescMelange`) VALUES
(1, 'Farine de blé'),
(2, 'Farine de sarasin'),
(3, 'Farine de seigle');

-- --------------------------------------------------------

--
-- Table structure for table `PAIN`
--

CREATE TABLE IF NOT EXISTS `PAIN` (
  `IDPain` int(6) NOT NULL AUTO_INCREMENT,
  `DescPain` char(50) NOT NULL,
  `PrixPainHT` float(6) NOT NULL,
  `IDMelange` int(6) NOT NULL,
  PRIMARY KEY (`IDPain`),
  FOREIGN KEY (`IDMelange`) REFERENCES MELANGE(`IDMelange`)
);

--
-- Dumping data for table `PAIN`
--

INSERT INTO `PAIN` (`IDPain`, `DescPain`, `PrixPainHT`, `IDMelange`) VALUES
(1, 'Pain de seigle', 5, 3),
(2, 'Baguette', 1, 1),
(3, 'Pain de campagne', 4.5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `APPROVISIONNER`
--

CREATE TABLE IF NOT EXISTS `APPROVISIONNER` (
  `IDMelange` int(6) NOT NULL,
  `SemaineAppro` int(2) NOT NULL,
  `QuantiteMelange` int(6) NOT NULL,
  PRIMARY KEY (`IDMelange`,`SemaineAppro`),
  FOREIGN KEY (`IDMelange`) REFERENCES MELANGE(`IDMelange`)
);

--
-- Dumping data for table `APPROVISIONNER`
--
-- semaine doit étre valide
INSERT INTO `APPROVISIONNER` (`IDMelange`, `SemaineAppro`, `QuantiteMelange`) VALUES
(2, 50, 300);

-- --------------------------------------------------------

--
-- Table structure for table `LIVRER`
--

CREATE TABLE IF NOT EXISTS `LIVRER` (
  `IDCli` int(6) NOT NULL,
  `IDPain` int(6) NOT NULL,
  `DateLivraison` date NOT NULL,
  `NombreDePains` int(6) NOT NULL,
  PRIMARY KEY (`IDCli`,`IDPain`,`DateLivraison`),
  FOREIGN KEY (`IDCli`) REFERENCES CLIENTE(`IDCli`),
  FOREIGN KEY (`IDPain`) REFERENCES PAIN(`IDPain`)
);

--
-- Dumping data for table `LIVRER`
--

INSERT INTO `LIVRER` (`IDCli`, `IDPain`, `DateLivraison`, `NombreDePains`) VALUES
(1, 1, '2023-05-05', 5),
(2, 3, '2023-05-15', 3);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
