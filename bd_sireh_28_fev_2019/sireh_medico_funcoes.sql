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
-- Table structure for table `medico_funcoes`
--

DROP TABLE IF EXISTS `medico_funcoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medico_funcoes` (
  `crm_medico` int(11) NOT NULL,
  `id_funcao` int(11) NOT NULL,
  KEY `FK9t39fh398txbxdfscxaf0tef8` (`id_funcao`),
  KEY `FKmxkc49ykcp0sgnsrxgjn4rmas` (`crm_medico`),
  CONSTRAINT `FK9t39fh398txbxdfscxaf0tef8` FOREIGN KEY (`id_funcao`) REFERENCES `funcao` (`id_funcao`),
  CONSTRAINT `FKmxkc49ykcp0sgnsrxgjn4rmas` FOREIGN KEY (`crm_medico`) REFERENCES `medico` (`crm`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medico_funcoes`
--

LOCK TABLES `medico_funcoes` WRITE;
/*!40000 ALTER TABLE `medico_funcoes` DISABLE KEYS */;
INSERT INTO `medico_funcoes` VALUES (2340,1),(2340,2),(5466,1),(5466,2),(2309,1),(2309,2),(4590,1),(4590,2),(8923,2),(3456,1),(3456,2),(4432,1),(4432,2),(6744,1),(6744,2),(2399,1),(2399,2),(3311,1),(3311,2),(3644,1),(3211,1),(3211,2),(7872,1),(7872,2),(3020,2),(1298,1),(1298,2),(2298,1),(2298,2),(8832,1),(8832,2),(4378,1),(4378,2),(3498,1),(3498,2),(8567,1),(8567,2);
/*!40000 ALTER TABLE `medico_funcoes` ENABLE KEYS */;
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
