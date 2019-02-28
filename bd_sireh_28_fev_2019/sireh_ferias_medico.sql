-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: sireh
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `ferias_medico`
--

DROP TABLE IF EXISTS `ferias_medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ferias_medico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dt_fim` datetime NOT NULL,
  `dt_inicio` datetime NOT NULL,
  `medico_crm` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcf05x9hmu9smtecb76o9v7kdo` (`medico_crm`),
  CONSTRAINT `FKcf05x9hmu9smtecb76o9v7kdo` FOREIGN KEY (`medico_crm`) REFERENCES `medico` (`crm`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ferias_medico`
--

LOCK TABLES `ferias_medico` WRITE;
/*!40000 ALTER TABLE `ferias_medico` DISABLE KEYS */;
INSERT INTO `ferias_medico` VALUES (30,'2018-08-02 00:00:00','2018-07-19 00:00:00',2309),(32,'2018-09-07 00:00:00','2018-08-27 00:00:00',4378),(34,'2018-09-07 00:00:00','2018-09-01 00:00:00',4378),(41,'2018-11-14 00:00:00','2018-10-31 00:00:00',8832),(42,'2018-11-02 00:00:00','2018-10-19 00:00:00',3498),(44,'2018-10-19 00:00:00','2018-10-06 00:00:00',2399),(45,'2018-11-19 00:00:00','2018-11-03 00:00:00',8567),(46,'2018-11-14 00:00:00','2018-10-31 00:00:00',8832),(53,'2018-12-10 00:00:00','2018-11-26 00:00:00',5466),(54,'2018-11-16 00:00:00','2018-11-03 00:00:00',1298),(55,'2018-11-14 00:00:00','2018-11-09 00:00:00',2399),(56,'2018-12-17 00:00:00','2018-12-03 00:00:00',2309),(58,'2018-11-30 00:00:00','2018-11-19 00:00:00',3644),(60,'2019-01-27 00:00:00','2019-01-14 00:00:00',3311),(62,'2019-03-04 00:00:00','2019-02-19 00:00:00',4432),(65,'2019-04-03 00:00:00','2019-03-19 00:00:00',7872),(67,'2019-03-18 00:00:00','2019-03-05 00:00:00',2298);
/*!40000 ALTER TABLE `ferias_medico` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-27 18:42:38