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
-- Table structure for table `medico`
--

DROP TABLE IF EXISTS `medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medico` (
  `crm` int(11) NOT NULL,
  `ativo` bit(1) NOT NULL,
  `categoria` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`crm`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medico`
--

LOCK TABLES `medico` WRITE;
/*!40000 ALTER TABLE `medico` DISABLE KEYS */;
INSERT INTO `medico` VALUES (1298,'','socio','Carlos'),(2298,'','socio','Igor'),(2309,'','socio','Beth'),(2340,'\0','socio','Lyvia'),(2399,'','socio','Marcelo'),(3020,'','contratado','Laisa'),(3211,'','socio','Plinio'),(3290,'','contratado','Erlandson'),(3311,'','socio','Lyvia'),(3345,'\0','contratado','Paulo'),(3388,'','contratado','Thayanne'),(3411,'\0','contratado','Rafael'),(3456,'\0','contratado','liciane'),(3498,'','socio','Caio'),(3567,'\0','contratado','João'),(3644,'','socio','Gilmar'),(4355,'\0','contratado','JOAO'),(4378,'','contratado','Lici'),(4432,'','socio','Cyro'),(4435,'\0','contratado','Ana Célia'),(4567,'\0','contratado','Gustavo'),(4590,'\0','contratado','Francisco'),(4673,'\0','contratado','JAILSON'),(5466,'','socio','Jorge'),(6744,'\0','contratado','Giovani'),(6756,'','contratado','Lailson'),(6789,'','contratado','Karla'),(7872,'','socio','Cicero'),(8513,'\0','contratado','Alexandre'),(8567,'','contratado','Cristiano'),(8768,'','contratado','Tiago'),(8832,'','socio','J Vitor'),(8923,'\0','contratado','Liciane'),(10345,'','contratado','Felipe');
/*!40000 ALTER TABLE `medico` ENABLE KEYS */;
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
