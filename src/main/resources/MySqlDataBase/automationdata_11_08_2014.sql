CREATE DATABASE  IF NOT EXISTS `automationdata` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `automationdata`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 192.168.1.58    Database: automationdata
-- ------------------------------------------------------
-- Server version	5.6.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `executionconfig`
--

DROP TABLE IF EXISTS `executionconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `executionconfig` (
  `rowid` varchar(20) NOT NULL,
  `ModuleName` varchar(45) DEFAULT NULL,
  `TestCategory` varchar(45) DEFAULT NULL,
  `RiskLevel` varchar(45) DEFAULT NULL,
  `Ranking` varchar(45) DEFAULT NULL,
  `Dependencies` varchar(45) DEFAULT NULL,
  `SelectedToExecute` varchar(45) DEFAULT NULL,
  `Executed` varchar(45) DEFAULT NULL,
  `CreateAutomationPreData` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`rowid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `executionconfig`
--

LOCK TABLES `executionconfig` WRITE;
/*!40000 ALTER TABLE `executionconfig` DISABLE KEYS */;
INSERT INTO `executionconfig` VALUES ('01','Login','BVT','High','1','','No','No','No'),('02','LandingPage','BVT','High','2','','No','No','No'),('03','Administrator','BVT','High','3',NULL,'Yes','No','No');
/*!40000 ALTER TABLE `executionconfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productconfig`
--

DROP TABLE IF EXISTS `productconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productconfig` (
  `rowid` varchar(20) NOT NULL,
  `paramname` varchar(50) NOT NULL,
  `paramvalue` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`rowid`,`paramname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productconfig`
--

LOCK TABLES `productconfig` WRITE;
/*!40000 ALTER TABLE `productconfig` DISABLE KEYS */;
INSERT INTO `productconfig` VALUES ('01','username','Suhas'),('02','password','cac@ezdi2014'),('03','Environment Information***********',NULL),('04','URL','https://54.84.0.172:8443/ezCACWeb-1.0.0.212/#'),('05','QA Build','GA'),('06','Version','1.37'),('07','Browser','Chrome'),('08','Prepare Pre-Data Information******','FALSE'),('09','PreData Flag','FALSE'),('10','Run SQLs','FALSE'),('11','CreateCommonPreData','FALSE'),('12','Select Industry','FALSE'),('13','IndustryType',NULL),('14','Provide Product Specific Credentials*****',NULL),('15','User_ProductSpecific','FALSE'),('16','Password_ProductSpecific',NULL);
/*!40000 ALTER TABLE `productconfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tc_administrator`
--

DROP TABLE IF EXISTS `tc_administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tc_administrator` (
  `Rowid` varchar(20) NOT NULL,
  `TCID` varchar(45) DEFAULT NULL,
  `TestCategory` varchar(45) DEFAULT NULL,
  `Enabled` varchar(45) DEFAULT NULL,
  `action` varchar(60) DEFAULT NULL,
  `RiskLevel` varchar(45) DEFAULT NULL,
  `Type of Test
(Positive / Negative)` varchar(45) DEFAULT NULL,
  `TestExecution` varchar(45) DEFAULT NULL,
  `Doc ID` varchar(45) DEFAULT NULL,
  `Doc Trace` varchar(45) DEFAULT NULL,
  `Pre-Conditions` varchar(45) DEFAULT NULL,
  `Test Steps` varchar(45) DEFAULT NULL,
  `ActualResults` varchar(45) DEFAULT NULL,
  `Post-Conditions` varchar(45) DEFAULT NULL,
  `CoveredTCIDs` varchar(45) DEFAULT NULL,
  `Expected Result` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Rowid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tc_administrator`
--

LOCK TABLES `tc_administrator` WRITE;
/*!40000 ALTER TABLE `tc_administrator` DISABLE KEYS */;
INSERT INTO `tc_administrator` VALUES ('001','ezCAC_ MVP-51','BVT','1','addUserWithSingleRole','High','Positive','Automated',NULL,NULL,NULL,NULL,'',NULL,'ezCAC_ MVP-44',NULL);
/*!40000 ALTER TABLE `tc_administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `td_adduser`
--

DROP TABLE IF EXISTS `td_adduser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `td_adduser` (
  `RowId` int(11) NOT NULL,
  `FirstName` varchar(45) DEFAULT NULL,
  `MiddleInitial` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `EmailId` varchar(45) DEFAULT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `City` varchar(45) DEFAULT NULL,
  `State` varchar(45) DEFAULT NULL,
  `ZipCode` varchar(45) DEFAULT NULL,
  `Phone` varchar(45) DEFAULT NULL,
  `Fax` varchar(45) DEFAULT NULL,
  `Coder` varchar(45) DEFAULT NULL,
  `Reviewer` varchar(45) DEFAULT NULL,
  `CDI` varchar(45) DEFAULT NULL,
  `CoderGroup` varchar(45) DEFAULT NULL,
  `reviewerGroup` varchar(45) DEFAULT NULL,
  `cdiGroup` varchar(45) DEFAULT NULL,
  `Onsite` varchar(45) DEFAULT NULL,
  `Offsite` varchar(45) DEFAULT NULL,
  `Testing_Inpatient` varchar(45) DEFAULT NULL,
  `Testing_Outpatient` varchar(45) DEFAULT NULL,
  `Speciality` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`RowId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `td_adduser`
--

LOCK TABLES `td_adduser` WRITE;
/*!40000 ALTER TABLE `td_adduser` DISABLE KEYS */;
INSERT INTO `td_adduser` VALUES (2,'AutoTest','Ezdi','Peter','test@ezdi.com','A-101','Ahmedabad','Gujarat','3800052','1234322132','12192983','Yes',NULL,NULL,'Coder',NULL,NULL,'Yes','Yes','Yes','Yes','Autotesting');
/*!40000 ALTER TABLE `td_adduser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `Test1` int(11) NOT NULL,
  `Test2` varchar(54) DEFAULT NULL,
  PRIMARY KEY (`Test1`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (0,'Doc ID');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-11 20:00:21
