CREATE DATABASE  IF NOT EXISTS `airlinemanagementsystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `airlinemanagementsystem`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: airlinemanagementsystem
-- ------------------------------------------------------
-- Server version	9.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `annulation`
--

DROP TABLE IF EXISTS `annulation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `annulation` (
  `pnr` varchar(20) DEFAULT NULL,
  `nom` varchar(20) DEFAULT NULL,
  `annulation_nb` varchar(20) DEFAULT NULL,
  `vcode` varchar(20) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `Identifiant` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `annulation`
--

LOCK TABLES `annulation` WRITE;
/*!40000 ALTER TABLE `annulation` DISABLE KEYS */;
INSERT INTO `annulation` VALUES ('PNR-285951','tamo','12676','1001','',NULL),('PNR-689726','tamo','TIC-764','','1856956494',''),('PNR-352687','','TIC-5373','','',''),('PNR-415549','','TIC-9037','','',''),('PNR-624812','tamo','TIC-46079','1004','1856956494','12 juin 2025'),('PNR-125307','tamo','TIC-5530','1004','1856956494','30 juin 2025'),('PNR-654208','tamo','TIC-49056','CM107','1856956494','17 oct. 2030'),('PNR-460419','LTKR','TIC-33553','YA-BRU-002','55555','11 juin 2025'),('PNR-441661','rerurur','TIC-21711','YA-BRU-002','ryruryf','Tunis'),('PNR-206391','rerurur','TIC-45385','MR-ROM-004','ryruryf','Rome'),('PNR-445202','MOMO','TIC-47712','YA-BRU-002','15461','21 juin 2025');
/*!40000 ALTER TABLE `annulation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('admin','admin');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passager`
--

DROP TABLE IF EXISTS `passager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passager` (
  `ID` varchar(20) DEFAULT NULL,
  `nom` varchar(20) DEFAULT NULL,
  `prenom` varchar(20) DEFAULT NULL,
  `genre` varchar(10) DEFAULT NULL,
  `nationalite` varchar(25) DEFAULT NULL,
  `adress` varchar(50) DEFAULT NULL,
  `ph_no` varchar(10) DEFAULT NULL,
  `passport_no` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passager`
--

LOCK TABLES `passager` WRITE;
/*!40000 ALTER TABLE `passager` DISABLE KEYS */;
INSERT INTO `passager` VALUES ('1856956494','tamo','pierre','HOMME','camerounaise','ekounou','697559595','28452625'),('ryruryf','rerurur','iruryrir','FEMME','fiufhfuf','fkfkfkf','ekekdkd','fkfifir'),('oijeow4','rjghrkqhri','jfnorquho','HOMME','kfnbhrouvb','eahvorqho3r','ofhbouqbh','onvoehvo'),('66946549','Matapo','brigite','FEMME','camerounaise','ekounou','698242810','455764946497'),('4519452659','penda','charles','HOMME','quebeqouiase','548464 rue des prés','54945','1845498449'),('56129549619','tameye','michelle','FEMME','camerounaise','Efoulan','690545658','562198415612918'),('855829','LEKEKE','KAREN','FEMME','CAMEROUNAISE','YAOUNDE','65523217','CE22'),('55555','LTKR','REGINA','FEMME','CAM','JGGGGF','633665559','LEZ663515'),('55552','TOTO','IBRQHIM','HOMME','CA?EROUNAIS','LOME 44','2258468','NV2659'),('8899','toto','junior','HOMME','camerounais','djdi','497','nskal489'),('15461','MOMO','Jean','HOMME','quebeqouis','189 rue des champs','695145252','5464649');
/*!40000 ALTER TABLE `passager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payement`
--

DROP TABLE IF EXISTS `payement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payement` (
  `pnr_no` varchar(10) DEFAULT NULL,
  `ph_no` varchar(15) DEFAULT NULL,
  `cheque_no` varchar(15) DEFAULT NULL,
  `card_no` varchar(20) DEFAULT NULL,
  `paid_amt` varchar(10) DEFAULT NULL,
  `pay_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payement`
--

LOCK TABLES `payement` WRITE;
/*!40000 ALTER TABLE `payement` DISABLE KEYS */;
/*!40000 ALTER TABLE `payement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personnel`
--

DROP TABLE IF EXISTS `personnel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personnel` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `info_specifique` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personnel`
--

LOCK TABLES `personnel` WRITE;
/*!40000 ALTER TABLE `personnel` DISABLE KEYS */;
INSERT INTO `personnel` VALUES (1,'kjhvfovu','kjvifjvpreiv','Naviguant','ntbwtlbbw'),(2,'yousouph',' adidja','Naviguant','maintenancier'),(3,'Akila ','charles','Administratif','bagagiste'),(4,'popyna','claire','Au Sol','agent de surete'),(5,'noupa','loic','Administratif','Ressource Humaine'),(6,'popou','jean','Administratif','RH');
/*!40000 ALTER TABLE `personnel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `PNR` varchar(20) DEFAULT NULL,
  `TICKET` varchar(20) DEFAULT NULL,
  `ID` varchar(20) DEFAULT NULL,
  `nom` varchar(20) DEFAULT NULL,
  `nationalite` varchar(30) DEFAULT NULL,
  `nomvol` varchar(20) DEFAULT NULL,
  `codevol` varchar(20) DEFAULT NULL,
  `depart` varchar(30) DEFAULT NULL,
  `arrivee` varchar(30) DEFAULT NULL,
  `ddate` varchar(15) DEFAULT NULL,
  `escale` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES ('PNR-698261','TIC-59682','oijeow4','rjghrkqhri','kfnbhrouvb','Bombardier CRJ900','MR-ROM-004','Maroua','Rome','6 juin 2025',NULL),('PNR-208062','TIC-44223','1856956494','tamo','camerounaise','Boeing 737 MAX','YA-BRU-002','Yaoundé','Bruxelles','12 juin 2025','Accra'),('PNR-40533','TIC-88985','56129549619','tameye','camerounaise','Boeing 737 MAX','YA-BRU-002','Yaoundé','Bruxelles','16 nov. 2025','Le Caire'),('PNR-836761','TIC-79451','55552','TOTO','CA?EROUNAIS','Boeing 737 MAX','YA-BRU-002','Yaoundé','Bruxelles','12 juin 2025','Tunis'),('PNR-575348','TIC-99061','56129549619','tameye','camerounaise','Embraer E190','GA-IST-003','Garoua','Istanbul','12 juin 2025','Tunis');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `section` (
  `vol_code` varchar(20) DEFAULT NULL,
  `capacite` varchar(10) DEFAULT NULL,
  `annulation_date` date DEFAULT NULL,
  `classe_code` varchar(5) DEFAULT NULL,
  `classe_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vol`
--

DROP TABLE IF EXISTS `vol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vol` (
  `id_vol` int NOT NULL AUTO_INCREMENT,
  `v_nom` varchar(100) DEFAULT NULL,
  `v_code` varchar(20) DEFAULT NULL,
  `depart` varchar(100) DEFAULT NULL,
  `arrivee` varchar(100) DEFAULT NULL,
  `escale` varchar(100) DEFAULT NULL,
  `compagnie` varchar(100) DEFAULT 'Air Cameroun',
  PRIMARY KEY (`id_vol`),
  UNIQUE KEY `v_code` (`v_code`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vol`
--

LOCK TABLES `vol` WRITE;
/*!40000 ALTER TABLE `vol` DISABLE KEYS */;
INSERT INTO `vol` VALUES (1,'Airbus A320','DC-PAR-001','Douala','Paris','Abidjan','Air Cameroun'),(2,'Boeing 737 MAX','YA-BRU-002','Yaoundé','Bruxelles','Lomé','Air Cameroun'),(3,'Embraer E190','GA-IST-003','Garoua','Istanbul','Le Caire','Air Cameroun'),(4,'Bombardier CRJ900','MR-ROM-004','Maroua','Rome','Tunis','Air Cameroun'),(5,'Airbus A330','BF-LIS-005','Bafoussam','Lisbonne','Madrid','Air Cameroun'),(6,'ATR 72','NG-CAS-006','Ngaoundéré','Casablanca','Accra','Air Cameroun'),(7,'Boeing 777-200','BE-DAK-007','Bertoua','Dakar','Bamako','Air Cameroun'),(8,'Embraer E145','EB-BAM-008','Ebolowa','Bamako','Niamey','Air Cameroun'),(9,'Boeing 787 Dreamliner','KR-LON-009','Kribi','Londres','Casablanca','Air Cameroun'),(10,'Airbus A350','LI-JOH-010','Limbé','Johannesburg','Lusaka','Air Cameroun');
/*!40000 ALTER TABLE `vol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-18 16:21:18
