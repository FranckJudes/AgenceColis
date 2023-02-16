-- MariaDB dump 10.19  Distrib 10.6.11-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: AgenceColis
-- ------------------------------------------------------
-- Server version	10.6.11-MariaDB-1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  PRIMARY KEY (`id_admin`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'franck','1234');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `CNI_Client` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(250) NOT NULL,
  `prenom` varchar(250) NOT NULL,
  `profession` varchar(250) DEFAULT NULL,
  `ville` varchar(250) DEFAULT NULL,
  `age` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`CNI_Client`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'franck','judes','Douala','yaounde','18'),(2,'Jojo','Barbu','manager','Douala','60');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colis`
--

DROP TABLE IF EXISTS `colis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `colis` (
  `idColis` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(250) DEFAULT NULL,
  `type` varchar(250) NOT NULL,
  `prix` varchar(200) NOT NULL,
  PRIMARY KEY (`idColis`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colis`
--

LOCK TABLES `colis` WRITE;
/*!40000 ALTER TABLE `colis` DISABLE KEYS */;
INSERT INTO `colis` VALUES (1,'Sac a main','Documents','1200'),(2,'lettre a une femme','courrier','1000');
/*!40000 ALTER TABLE `colis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `envois`
--

DROP TABLE IF EXISTS `envois`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `envois` (
  `idEnvois` int(11) NOT NULL AUTO_INCREMENT,
  `CNI_Client` int(11) NOT NULL,
  `CNI_Personnel` int(11) NOT NULL,
  `idColis` int(11) NOT NULL,
  `dateExpedition` varchar(250) DEFAULT NULL,
  `nomDes` varchar(250) NOT NULL,
  `prenomDes` varchar(250) NOT NULL,
  `professionDes` varchar(250) NOT NULL,
  `ageDes` varchar(250) NOT NULL,
  `agenceDestination` varchar(250) NOT NULL,
  `codeReception` varchar(250) NOT NULL,
  `statut` varchar(250) NOT NULL,
  PRIMARY KEY (`idEnvois`),
  KEY `CNI_Client` (`CNI_Client`),
  KEY `idColis` (`idColis`),
  KEY `CNI_Personnel` (`CNI_Personnel`),
  CONSTRAINT `envois_ibfk_1` FOREIGN KEY (`CNI_Client`) REFERENCES `client` (`CNI_Client`),
  CONSTRAINT `envois_ibfk_2` FOREIGN KEY (`idColis`) REFERENCES `colis` (`idColis`),
  CONSTRAINT `envois_ibfk_3` FOREIGN KEY (`CNI_Personnel`) REFERENCES `personnel` (`CNI_Personnel`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `envois`
--

LOCK TABLES `envois` WRITE;
/*!40000 ALTER TABLE `envois` DISABLE KEYS */;
INSERT INTO `envois` VALUES (1,1,1,1,'16/02/2023 05:56:30','franck','dupont','Etudiant','19','Douala','8505','Recu'),(2,2,1,2,'16/02/2023 09:49:12','dimi','ruth','etudiant','15','douala-yaounde','1814','Recu'),(3,2,2,1,'16/02/2023 11:11:00','Gallagher','Gallagher','Marchand','45','yaounde-buea','3959','envoyer');
/*!40000 ALTER TABLE `envois` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personnel`
--

DROP TABLE IF EXISTS `personnel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personnel` (
  `CNI_Personnel` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(250) NOT NULL,
  `prenom` varchar(250) NOT NULL,
  `nomService` varchar(250) DEFAULT NULL,
  `typePermis` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`CNI_Personnel`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personnel`
--

LOCK TABLES `personnel` WRITE;
/*!40000 ALTER TABLE `personnel` DISABLE KEYS */;
INSERT INTO `personnel` VALUES (1,'Gaston','Gaston','Chaufeur',''),(2,'John','John','Chauffeur','2@TU'),(3,'Mother','Father','Chauffeur','12FRRR');
/*!40000 ALTER TABLE `personnel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-16 11:21:15
