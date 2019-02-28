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
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `tipo_pedido` bit(1) NOT NULL,
  `turno` varchar(255) NOT NULL,
  `medico_crm` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKki6l915gftpoutu89abfmc8ot` (`medico_crm`),
  CONSTRAINT `FKki6l915gftpoutu89abfmc8ot` FOREIGN KEY (`medico_crm`) REFERENCES `medico` (`crm`)
) ENGINE=InnoDB AUTO_INCREMENT=575 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (11,'2017-10-10 00:00:00','\0','manha',2340),(12,'2017-10-24 00:00:00','\0','manha',2340),(13,'2017-10-26 00:00:00','\0','manha',2340),(14,'2017-10-31 00:00:00','\0','manha',2340),(15,'2017-10-20 00:00:00','\0','todos',2340),(16,'2017-10-21 00:00:00','\0','todos',2340),(17,'2017-10-22 00:00:00','\0','todos',2340),(46,'2017-10-23 00:00:00','\0','todos',2340),(95,'2017-11-04 00:00:00','','todos',4590),(96,'2017-11-26 00:00:00','','manha',4590),(97,'2017-11-26 00:00:00','','tarde',4590),(139,'2018-01-16 00:00:00','\0','todos',8832),(224,'2018-03-19 00:00:00','\0','todos',6744),(225,'2018-03-20 00:00:00','\0','todos',6744),(283,'2018-05-03 00:00:00','','todos',3345),(284,'2018-05-17 00:00:00','','todos',3345),(285,'2018-05-12 00:00:00','','manha',3345),(286,'2018-05-12 00:00:00','','tarde',3345),(300,'2018-05-11 00:00:00','','manha',4435),(301,'2018-05-11 00:00:00','','tarde',4435),(302,'2018-05-15 00:00:00','','manha',4435),(303,'2018-05-15 00:00:00','','tarde',4435),(304,'2018-05-16 00:00:00','','manha',4435),(305,'2018-05-16 00:00:00','','tarde',4435),(306,'2018-05-18 00:00:00','','manha',4435),(307,'2018-05-18 00:00:00','','manha',4435),(308,'2018-05-18 00:00:00','','tarde',4435),(324,'2018-05-26 00:00:00','','manha',3345),(347,'2018-06-27 00:00:00','\0','tarde',2399),(414,'2018-09-06 00:00:00','\0','todos',5466),(415,'2018-09-07 00:00:00','\0','todos',5466),(416,'2018-09-08 00:00:00','\0','todos',5466),(417,'2018-09-09 00:00:00','\0','todos',5466),(480,'2018-11-01 00:00:00','\0','manha',2309),(481,'2018-11-20 00:00:00','\0','manha',2309),(482,'2018-11-29 00:00:00','\0','manha',2309),(483,'2018-11-25 00:00:00','\0','todos',2309),(484,'2018-11-06 00:00:00','\0','manha',3311),(485,'2018-11-13 00:00:00','\0','manha',3311),(486,'2018-11-22 00:00:00','\0','manha',3311),(487,'2018-11-12 00:00:00','\0','todos',3311),(488,'2018-11-26 00:00:00','\0','todos',4432),(489,'2018-11-27 00:00:00','\0','todos',4432),(490,'2018-11-10 00:00:00','\0','todos',4432),(494,'2018-11-14 00:00:00','','tarde',2399),(495,'2018-11-14 00:00:00','','tarde',2399),(496,'2018-11-30 00:00:00','','manha',4432),(497,'2018-11-30 00:00:00','','tarde',4432),(498,'2018-11-26 00:00:00','','manha',2309),(499,'2018-11-30 00:00:00','','manha',4432),(535,'2019-01-03 00:00:00','\0','todos',4378),(539,'2019-01-11 00:00:00','\0','tarde',3211),(540,'2019-01-12 00:00:00','\0','todos',3211),(541,'2019-01-13 00:00:00','\0','todos',3211),(542,'2019-02-12 00:00:00','\0','tarde',3020),(546,'2019-02-23 00:00:00','\0','todos',2298),(547,'2019-02-24 00:00:00','\0','todos',2298),(548,'2019-02-14 00:00:00','\0','manha',3498),(550,'2019-02-02 00:00:00','\0','todos',3644),(551,'2019-02-03 00:00:00','\0','todos',3644),(552,'2019-02-08 00:00:00','\0','todos',7872),(553,'2019-02-15 00:00:00','\0','manha',3498),(559,'2019-02-02 00:00:00','','noite',3290),(560,'2019-02-06 00:00:00','','noite',6789),(561,'2019-02-09 00:00:00','','tarde',6789),(562,'2019-02-09 00:00:00','','noite',6789),(564,'2019-02-11 00:00:00','','noite',6789),(565,'2019-02-13 00:00:00','','noite',6756),(566,'2019-02-26 00:00:00','','tarde',3020),(567,'2019-03-15 00:00:00','\0','manha',7872),(568,'2019-03-13 00:00:00','\0','todos',1298),(569,'2019-03-04 00:00:00','','manha',2309),(570,'2019-03-04 00:00:00','','tarde',3498),(571,'2019-03-28 00:00:00','\0','tarde',3388),(572,'2019-04-01 00:00:00','\0','tarde',3388),(573,'2019-03-09 00:00:00','','todos',8567),(574,'2019-03-13 00:00:00','\0','manha',8567);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
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
