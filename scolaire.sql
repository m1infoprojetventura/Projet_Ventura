-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: localhost    Database: scolaire
-- ------------------------------------------------------
-- Server version	5.7.28-0ubuntu0.18.04.4

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
-- Table structure for table `Cours`
--

DROP TABLE IF EXISTS `Cours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cours` (
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
-- Dumping data for table `Cours`
--

LOCK TABLES `Cours` WRITE;
/*!40000 ALTER TABLE `Cours` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Departement`
--

DROP TABLE IF EXISTS `Departement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Departement` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_responsable` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Departement`
--

LOCK TABLES `Departement` WRITE;
/*!40000 ALTER TABLE `Departement` DISABLE KEYS */;
INSERT INTO `Departement` VALUES (1,'Sciences et Techniques',NULL);
/*!40000 ALTER TABLE `Departement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Enseignant`
--

DROP TABLE IF EXISTS `Enseignant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Enseignant` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `date_naissance` date NOT NULL,
  `mdp` bigint(20) DEFAULT NULL,
  `login` varchar(40) DEFAULT NULL,
  `id_departement` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Enseignant`
--

LOCK TABLES `Enseignant` WRITE;
/*!40000 ALTER TABLE `Enseignant` DISABLE KEYS */;
INSERT INTO `Enseignant` VALUES (4,'Jack','Jean','2019-11-14',-1499813715,'JJack788',1),(18,'miss','mister','2013-02-15',2134782028,'mmiss-28',1),(20,'peter','parker','2016-11-19',-166493559,'ppeter482',1),(21,'Adrienbg','Guard','2019-01-15',1563063939,'GAdrienbg288',1),(26,'zaad','adazdy','2019-11-09',-721703263,'azaad277',1),(32,'ezrt','azeaze','2022-11-06',-1056429475,'aezrt999',1);
/*!40000 ALTER TABLE `Enseignant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Etudiant`
--

DROP TABLE IF EXISTS `Etudiant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Etudiant` (
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Etudiant`
--

LOCK TABLES `Etudiant` WRITE;
/*!40000 ALTER TABLE `Etudiant` DISABLE KEYS */;
INSERT INTO `Etudiant` VALUES (6,'2019-11-14',NULL,1,NULL,'Jack','Paul',1682802253,'PJack875'),(7,'2019-11-23',NULL,1,NULL,'Perecic','Georgesano',-1906414871,'GPerecic-778'),(12,'2019-11-15',NULL,1,NULL,'az','aze',-1873814567,'aaz286'),(13,'2019-12-20',NULL,1,NULL,'bourboul','chomeur',-524537801,'cbourboul223'),(15,'2019-11-15',NULL,1,NULL,'chameau','poisson',-1785752953,'pchameau460'),(17,'2013-02-15',NULL,1,NULL,'miss','mister',2134782028,'mmiss-28'),(18,'2019-01-15',NULL,1,NULL,'Adrien','Guard',-1952878488,'GAdrien288'),(19,'2019-11-22',NULL,1,NULL,'100','99',721137975,'9100-726');
/*!40000 ALTER TABLE `Etudiant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Formation`
--

DROP TABLE IF EXISTS `Formation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Formation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `intitule` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_departement` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Formation`
--

LOCK TABLES `Formation` WRITE;
/*!40000 ALTER TABLE `Formation` DISABLE KEYS */;
INSERT INTO `Formation` VALUES (1,'M1 Informatique',NULL);
/*!40000 ALTER TABLE `Formation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Materiel`
--

DROP TABLE IF EXISTS `Materiel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Materiel` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_salle` int(10) unsigned DEFAULT NULL,
  `type` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_salle` (`id_salle`),
  CONSTRAINT `fk_id_salle` FOREIGN KEY (`id_salle`) REFERENCES `Salle` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Materiel`
--

LOCK TABLES `Materiel` WRITE;
/*!40000 ALTER TABLE `Materiel` DISABLE KEYS */;
INSERT INTO `Materiel` VALUES (1,1,'ORDINATEUR'),(2,1,'VIDEO_PROJECTEUR'),(3,2,'IMPRIMANTE'),(4,2,'VIDEO_PROJECTEUR'),(5,3,'TABLEAU_TACTIL'),(7,5,'ORDINATEUR'),(8,5,'IMPRIMANTE'),(9,5,'TABLEAU_TACTIL'),(10,5,'VIDEO_PROJECTEUR');
/*!40000 ALTER TABLE `Materiel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Personne`
--

DROP TABLE IF EXISTS `Personne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Personne` (
  `id` int(10) unsigned NOT NULL,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Personne`
--

LOCK TABLES `Personne` WRITE;
/*!40000 ALTER TABLE `Personne` DISABLE KEYS */;
INSERT INTO `Personne` VALUES (1926046598,'Jacquie','Pierre');
/*!40000 ALTER TABLE `Personne` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Salle`
--

DROP TABLE IF EXISTS `Salle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Salle` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `capacite` tinyint(3) unsigned DEFAULT NULL,
  `nom` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Salle`
--

LOCK TABLES `Salle` WRITE;
/*!40000 ALTER TABLE `Salle` DISABLE KEYS */;
INSERT INTO `Salle` VALUES (1,10,'lebellegou'),(2,42,'salle a bourboul'),(3,1,'bac a asable'),(5,6,'aazdze');
/*!40000 ALTER TABLE `Salle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-22 20:37:19
