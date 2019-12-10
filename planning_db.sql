-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: planning_db
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `cours`
--

DROP TABLE IF EXISTS `cours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cours` (
  `matiere` varchar(40) NOT NULL,
  `salle` varchar(40) NOT NULL,
  `numero_professeur` smallint(5) unsigned NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  `date` date NOT NULL,
  `debut` time NOT NULL,
  `fin` time DEFAULT NULL,
  `formation` varchar(40) NOT NULL,
  PRIMARY KEY (`numero_professeur`,`date`,`debut`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cours`
--

LOCK TABLES `cours` WRITE;
/*!40000 ALTER TABLE `cours` DISABLE KEYS */;
/*!40000 ALTER TABLE `cours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departement`
--

DROP TABLE IF EXISTS `departement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departement` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_responsable` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departement`
--

LOCK TABLES `departement` WRITE;
/*!40000 ALTER TABLE `departement` DISABLE KEYS */;
INSERT INTO `departement` VALUES (1,'Sciences et Techniques',NULL);
/*!40000 ALTER TABLE `departement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enseignant`
--

DROP TABLE IF EXISTS `enseignant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enseignant` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `date_naissance` date NOT NULL,
  `mdp` bigint(20) DEFAULT NULL,
  `login` varchar(40) DEFAULT NULL,
  `id_departement` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enseignant`
--

LOCK TABLES `enseignant` WRITE;
/*!40000 ALTER TABLE `enseignant` DISABLE KEYS */;
INSERT INTO `enseignant` VALUES (4,'Jack','Jean','2019-11-14',-1499813715,'JJack788',1),(18,'miss','mister','2013-02-15',2134782028,'mmiss-28',1),(20,'peter','parker','2016-11-19',-166493559,'ppeter482',1),(21,'Adrienbg','Guard','2019-01-15',1563063939,'GAdrienbg288',1),(26,'zaada','adazdyttt','2019-11-09',1686191058,'azaada518',1),(32,'ezrt','azeaze','2022-11-06',-1056429475,'aezrt772',1),(33,'eeeeerico','eeeea','2019-11-10',-651991297,'eeeee164',1),(34,'eeee','eeeea','2019-11-29',1299163719,'eeeee133',1),(35,'eeeeerico','eeeea','2019-11-10',-651991297,'eeeeeerico603',1);
/*!40000 ALTER TABLE `enseignant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `etudiant` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date_naissance` date DEFAULT NULL,
  `ufr` varchar(40) DEFAULT NULL,
  `id_formation` int(10) unsigned DEFAULT NULL,
  `groupe` tinyint(3) unsigned DEFAULT NULL,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `mdp` bigint(20) DEFAULT NULL,
  `login` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_etudiant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etudiant`
--

LOCK TABLES `etudiant` WRITE;
/*!40000 ALTER TABLE `etudiant` DISABLE KEYS */;
INSERT INTO `etudiant` VALUES (6,'2019-11-14',NULL,1,NULL,'Jack','Paul',1682802253,'PJack875'),(7,'2019-11-23',NULL,1,NULL,'Perecic','Georgesano',-1906414871,'GPerecic-778'),(12,'2019-11-15',NULL,1,NULL,'az','aze',-1873814567,'aaz286'),(13,'2019-12-20',NULL,1,NULL,'bourboul','chomeur',-524537801,'cbourboul223'),(15,'2019-11-15',NULL,1,NULL,'chameau','poisson',-1785752953,'pchameau460'),(17,'2013-02-15',NULL,1,NULL,'miss','mister',2134782028,'mmiss-28'),(18,'2019-01-15',NULL,1,NULL,'Adrien','Guard',-1952878488,'GAdrien288'),(19,'2019-11-22',NULL,1,NULL,'100','99',721137975,'9100-726'),(20,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg',-598542565,'yninbin773'),(21,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg',-598542565,'yninbin742'),(22,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg',-598542565,'yninbin711'),(23,'2019-11-29',NULL,1,NULL,'ninbin','edrfgty',226724619,'yninbin680'),(24,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg',-598542565,'yninbin649'),(25,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg',-598542565,'yninbin618'),(26,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg',-598542565,'yninbin587'),(27,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg',-598542565,'yninbin556');
/*!40000 ALTER TABLE `etudiant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formation`
--

DROP TABLE IF EXISTS `formation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `intitule` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_departement` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formation`
--

LOCK TABLES `formation` WRITE;
/*!40000 ALTER TABLE `formation` DISABLE KEYS */;
INSERT INTO `formation` VALUES (1,'M1 Informatique',NULL);
/*!40000 ALTER TABLE `formation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materiel`
--

DROP TABLE IF EXISTS `materiel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materiel` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_salle` int(10) unsigned DEFAULT NULL,
  `type` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_salle` (`id_salle`),
  CONSTRAINT `fk_id_salle` FOREIGN KEY (`id_salle`) REFERENCES `salle` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiel`
--

LOCK TABLES `materiel` WRITE;
/*!40000 ALTER TABLE `materiel` DISABLE KEYS */;
INSERT INTO `materiel` VALUES (1,1,'ORDINATEUR'),(2,1,'VIDEO_PROJECTEUR'),(3,2,'IMPRIMANTE'),(4,2,'VIDEO_PROJECTEUR'),(5,3,'TABLEAU_TACTIL'),(18,9,'ORDINATEUR'),(29,11,'VIDEO_PROJECTEUR'),(30,12,'ORDINATEUR'),(31,13,'ORDINATEUR'),(32,13,'TABLEAU_TACTIL');
/*!40000 ALTER TABLE `materiel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matiere`
--

DROP TABLE IF EXISTS `matiere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matiere` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matiere`
--

LOCK TABLES `matiere` WRITE;
/*!40000 ALTER TABLE `matiere` DISABLE KEYS */;
INSERT INTO `matiere` VALUES (1,'Physique'),(2,'Maths'),(3,'Informatique'),(4,'Anglais');
/*!40000 ALTER TABLE `matiere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matiere_enseignant`
--

DROP TABLE IF EXISTS `matiere_enseignant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matiere_enseignant` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_matiere` int(10) unsigned DEFAULT NULL,
  `id_enseignant` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_matiere` (`id_matiere`),
  KEY `fk_id_enseignant` (`id_enseignant`),
  CONSTRAINT `fk_id_enseignant` FOREIGN KEY (`id_enseignant`) REFERENCES `enseignant` (`id`),
  CONSTRAINT `fk_id_matiere` FOREIGN KEY (`id_matiere`) REFERENCES `matiere` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matiere_enseignant`
--

LOCK TABLES `matiere_enseignant` WRITE;
/*!40000 ALTER TABLE `matiere_enseignant` DISABLE KEYS */;
INSERT INTO `matiere_enseignant` VALUES (4,1,32),(5,2,34),(6,4,4);
/*!40000 ALTER TABLE `matiere_enseignant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personne`
--

DROP TABLE IF EXISTS `personne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personne` (
  `id` int(10) unsigned NOT NULL,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personne`
--

LOCK TABLES `personne` WRITE;
/*!40000 ALTER TABLE `personne` DISABLE KEYS */;
INSERT INTO `personne` VALUES (1926046598,'Jacquie','Pierre');
/*!40000 ALTER TABLE `personne` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_enseignant` int(10) unsigned DEFAULT NULL,
  `id_salle` int(10) unsigned DEFAULT NULL,
  `date_reservation` date NOT NULL,
  `etat_reservation` varchar(10) DEFAULT 'en cours',
  PRIMARY KEY (`id`),
  KEY `fk_enseignant` (`id_enseignant`),
  KEY `fk_salle` (`id_salle`),
  CONSTRAINT `fk_enseignant` FOREIGN KEY (`id_enseignant`) REFERENCES `enseignant` (`id`),
  CONSTRAINT `fk_salle` FOREIGN KEY (`id_salle`) REFERENCES `salle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,4,13,'2019-12-10','en cours'),(2,4,12,'2019-12-14','en cours'),(3,4,12,'2019-12-10','en cours'),(4,4,13,'2019-12-14','en cours');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsable`
--

DROP TABLE IF EXISTS `responsable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `responsable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `login` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsable`
--

LOCK TABLES `responsable` WRITE;
/*!40000 ALTER TABLE `responsable` DISABLE KEYS */;
INSERT INTO `responsable` VALUES (1,'Anouar','sayadi','0000-00-00','azerty','asayadi246'),(2,'Anouar','sayadi','1995-07-30','azerty','asayadi246');
/*!40000 ALTER TABLE `responsable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salle`
--

DROP TABLE IF EXISTS `salle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salle` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `capacite` tinyint(3) unsigned DEFAULT NULL,
  `nom` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salle`
--

LOCK TABLES `salle` WRITE;
/*!40000 ALTER TABLE `salle` DISABLE KEYS */;
INSERT INTO `salle` VALUES (1,10,'lebellegou'),(2,42,'salle a bourboul'),(3,1,'bac a asable'),(8,2,'pekinParisNewyork'),(9,66,'salle vide'),(11,99,'masalleouPas'),(12,111,'Q522'),(13,22,'U458');
/*!40000 ALTER TABLE `salle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seance`
--

DROP TABLE IF EXISTS `seance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seance` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `debut_seance` time DEFAULT NULL,
  `fin_seance` time DEFAULT NULL,
  `id_matiere` int(11) DEFAULT NULL,
  `id_enseignant` int(10) unsigned DEFAULT NULL,
  `id_Salle` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seance`
--

LOCK TABLES `seance` WRITE;
/*!40000 ALTER TABLE `seance` DISABLE KEYS */;
/*!40000 ALTER TABLE `seance` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-10 17:15:17
