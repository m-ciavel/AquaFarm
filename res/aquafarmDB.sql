CREATE DATABASE  IF NOT EXISTS `aquafarm` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `aquafarm`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: aquafarm
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `fishtbl`
--

DROP TABLE IF EXISTS `fishtbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fishtbl` (
  `fish_ID` int NOT NULL,
  `fish_type` varchar(255) NOT NULL,
  `fish_Price` int DEFAULT NULL,
  PRIMARY KEY (`fish_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fishtbl`
--

LOCK TABLES `fishtbl` WRITE;
/*!40000 ALTER TABLE `fishtbl` DISABLE KEYS */;
INSERT INTO `fishtbl` VALUES (0,'AtlanticBass',10),(1,'BlueGill',20),(2,'Clownfish',30),(3,'GoldenTench',40),(4,'Guppy',50),(5,'HIghFinBandedShark',60);
/*!40000 ALTER TABLE `fishtbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userbtntbl`
--

DROP TABLE IF EXISTS `userbtntbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userbtntbl` (
  `btnID` int NOT NULL,
  `user_Name` varchar(255) NOT NULL,
  `btnFish1` tinyint(1) NOT NULL,
  `btnFish2` tinyint(1) NOT NULL,
  `btnFish3` tinyint(1) NOT NULL,
  `btnFish4` tinyint(1) NOT NULL,
  `btnFish5` tinyint(1) NOT NULL,
  `btnFish6` tinyint(1) NOT NULL,
  PRIMARY KEY (`btnID`),
  KEY `fk_userTable_userBtntbl` (`user_Name`),
  CONSTRAINT `fk_userTable_userBtntbl` FOREIGN KEY (`user_Name`) REFERENCES `usertable` (`user_Name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userbtntbl`
--

LOCK TABLES `userbtntbl` WRITE;
/*!40000 ALTER TABLE `userbtntbl` DISABLE KEYS */;
INSERT INTO `userbtntbl` VALUES (0,'user0',1,1,0,0,0,0),(1,'userdel',1,1,0,0,0,0);
/*!40000 ALTER TABLE `userbtntbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userfishinvtbl`
--

DROP TABLE IF EXISTS `userfishinvtbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userfishinvtbl` (
  `userFishID` int NOT NULL,
  `user_Name` varchar(255) NOT NULL,
  `fish_ID` int NOT NULL,
  `added_date` date DEFAULT NULL,
  PRIMARY KEY (`userFishID`),
  KEY `fk_fishTbl_fish_ID` (`fish_ID`),
  KEY `fk_userTable_user_Name` (`user_Name`),
  CONSTRAINT `fk_fishTbl_fish_ID` FOREIGN KEY (`fish_ID`) REFERENCES `fishtbl` (`fish_ID`) ON UPDATE CASCADE,
  CONSTRAINT `fk_userTable_user_Name` FOREIGN KEY (`user_Name`) REFERENCES `usertable` (`user_Name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userfishinvtbl`
--

LOCK TABLES `userfishinvtbl` WRITE;
/*!40000 ALTER TABLE `userfishinvtbl` DISABLE KEYS */;
INSERT INTO `userfishinvtbl` VALUES (0,'user0',4,'2023-11-27'),(1,'user0',2,'2023-11-27'),(2,'user0',2,'2023-11-27'),(40,'user0',0,'2023-12-01'),(93,'user0',0,'2023-12-01'),(94,'userdel',0,'2023-12-01'),(95,'userdel',0,'2023-12-01'),(96,'userdel',0,'2023-12-01'),(97,'userdel',0,'2023-12-01'),(98,'userdel',0,'2023-12-01'),(99,'userdel',0,'2023-12-01'),(100,'userdel',0,'2023-12-01'),(101,'userdel',0,'2023-12-01'),(102,'userdel',0,'2023-12-01'),(103,'userdel',0,'2023-12-01'),(104,'userdel',0,'2023-12-01'),(105,'userdel',0,'2023-12-01'),(106,'userdel',0,'2023-12-01'),(107,'userdel',0,'2023-12-01'),(108,'userdel',0,'2023-12-01'),(109,'userdel',1,'2023-12-01'),(110,'userdel',1,'2023-12-01'),(111,'userdel',1,'2023-12-01'),(112,'userdel',1,'2023-12-01'),(113,'userdel',1,'2023-12-01');
/*!40000 ALTER TABLE `userfishinvtbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userfishstatustbl`
--

DROP TABLE IF EXISTS `userfishstatustbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userfishstatustbl` (
  `userFishID` int NOT NULL,
  `fish_name` varchar(255) NOT NULL,
  `fish_gender` varchar(255) DEFAULT NULL,
  `fish_size` int NOT NULL,
  PRIMARY KEY (`fish_name`),
  KEY `fk_userFishInvTbl_userFishID` (`userFishID`),
  CONSTRAINT `fk_userFishInvTbl_userFishID` FOREIGN KEY (`userFishID`) REFERENCES `userfishinvtbl` (`userFishID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userfishstatustbl`
--

LOCK TABLES `userfishstatustbl` WRITE;
/*!40000 ALTER TABLE `userfishstatustbl` DISABLE KEYS */;
INSERT INTO `userfishstatustbl` VALUES (100,'AtlanticBass100','male',0),(101,'AtlanticBass101','male',0),(102,'AtlanticBass102','female',0),(103,'AtlanticBass103','female',0),(104,'AtlanticBass104','male',0),(105,'AtlanticBass105','they',0),(106,'AtlanticBass106','female',0),(107,'AtlanticBass107','they',0),(108,'AtlanticBass108','they',0),(40,'AtlanticBass40','female',0),(93,'AtlanticBass93','female',0),(94,'AtlanticBass94','female',0),(95,'AtlanticBass95','male',0),(96,'AtlanticBass96','male',0),(97,'AtlanticBass97','male',0),(98,'AtlanticBass98','male',0),(99,'AtlanticBass99','they',0),(109,'BlueGill109','female',0),(110,'BlueGill110','female',0),(111,'BlueGill111','male',0),(112,'BlueGill112','male',0),(113,'BlueGill113','male',0),(1,'Clown Leclerc','male',2),(2,'Guppy Russel','they',1),(0,'Pierre Guppy','they',1);
/*!40000 ALTER TABLE `userfishstatustbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinvtbl`
--

DROP TABLE IF EXISTS `userinvtbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userinvtbl` (
  `itemID` int NOT NULL,
  `user_Name` varchar(255) NOT NULL,
  `money` int NOT NULL,
  `fishnum` int DEFAULT NULL,
  PRIMARY KEY (`itemID`),
  KEY `fk_userTable_userInvTbl` (`user_Name`),
  CONSTRAINT `fk_userTable_userInvTbl` FOREIGN KEY (`user_Name`) REFERENCES `usertable` (`user_Name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinvtbl`
--

LOCK TABLES `userinvtbl` WRITE;
/*!40000 ALTER TABLE `userinvtbl` DISABLE KEYS */;
INSERT INTO `userinvtbl` VALUES (0,'user0',57,5),(1,'userdel',9770,20);
/*!40000 ALTER TABLE `userinvtbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `ID` int NOT NULL,
  `user_ID` int NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UQ_users_user_ID` (`user_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (0,1555464681,1),(1,1419358566,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertable`
--

DROP TABLE IF EXISTS `usertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usertable` (
  `user_ID` int NOT NULL,
  `user_Name` varchar(255) NOT NULL,
  `age` tinyint NOT NULL,
  `pass_salt` varchar(255) NOT NULL,
  `pass_hash` varchar(255) NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `logged_in` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`user_Name`),
  KEY `fk_users_user_ID` (`user_ID`),
  CONSTRAINT `fk_users_user_ID` FOREIGN KEY (`user_ID`) REFERENCES `users` (`user_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertable`
--

LOCK TABLES `usertable` WRITE;
/*!40000 ALTER TABLE `usertable` DISABLE KEYS */;
INSERT INTO `usertable` VALUES (1555464681,'user0',18,'d0a064b583aedb4fc113ee9599f8618a','bc4dbe76e720591863dd267c3a0883a4bb14dda989dabe797e14aa90efa20bc393f5dc9ce4bcf893bc7cb6309b01745a0181d69bd28dd4a4d20d2de997d2e3b7','2023-11-26',NULL,0),(1419358566,'userdel',81,'3a7e141ab9ecae436c9de8c6eea58a6f','496e9ae56ead4e9ce339413f260ca14e8032eab6be994068ed209d45c163dd5afc210f6a42bef005bb2250b1be5ad9170176fbca2b77bdd6d8f3102d4b8bc236','2023-12-01',NULL,0);
/*!40000 ALTER TABLE `usertable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-01 23:50:59
