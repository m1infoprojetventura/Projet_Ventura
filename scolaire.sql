-- MariaDB dump 10.17  Distrib 10.4.11-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: planning_db
-- ------------------------------------------------------
-- Server version	10.4.11-MariaDB

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
-- Table structure for table `Contrainte`
--

DROP TABLE IF EXISTS `Contrainte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Contrainte` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `debut_contrainte` time DEFAULT NULL,
  `fin_contrainte` time DEFAULT NULL,
  `id_enseignant` int(10) unsigned DEFAULT NULL,
  `motif` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_enseignant_contrainte` (`id_enseignant`),
  CONSTRAINT `fk_id_enseignant_contrainte` FOREIGN KEY (`id_enseignant`) REFERENCES `Enseignant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Contrainte`
--

LOCK TABLES `Contrainte` WRITE;
/*!40000 ALTER TABLE `Contrainte` DISABLE KEYS */;
INSERT INTO `Contrainte` VALUES (2,'2019-12-31','13:00:01','16:00:01',4,''),(6,'2020-01-22','17:00:33','20:00:33',4,''),(7,'2020-01-29','17:00:33','20:00:33',4,''),(8,'2020-02-05','17:00:33','20:00:33',4,''),(9,'2020-02-12','17:00:33','20:00:33',4,''),(10,'2020-02-19','17:00:34','20:00:34',4,''),(11,'2020-02-26','17:00:34','20:00:34',4,''),(12,'2020-03-04','17:00:34','20:00:34',4,''),(13,'2020-03-11','17:00:34','20:00:34',4,'');
/*!40000 ALTER TABLE `Contrainte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cours`
--

DROP TABLE IF EXISTS `Cours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cours` (
  `Matiere` varchar(40) NOT NULL,
  `Salle` varchar(40) NOT NULL,
  `numero_professeur` smallint(5) unsigned NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  `date` date NOT NULL,
  `debut` time NOT NULL,
  `fin` time DEFAULT NULL,
  `Formation` varchar(40) NOT NULL,
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
  `nom` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_responsable` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
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
  `mdp` varchar(64) DEFAULT NULL,
  `login` varchar(40) DEFAULT NULL,
  `id_departement` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Enseignant`
--

LOCK TABLES `Enseignant` WRITE;
/*!40000 ALTER TABLE `Enseignant` DISABLE KEYS */;
INSERT INTO `Enseignant` VALUES (4,'Jack','Jean','2019-11-14','f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9','JJack788',1),(18,'miss','mister','2013-02-15','2134782028','mmiss-28',1),(20,'peter','parker','2016-11-19','-166493559','ppeter482',1),(21,'Adrienbg','Guard','2019-01-15','1563063939','GAdrienbg288',1),(26,'zaada','adazdyttt','2019-11-09','1686191058','azaada518',1),(32,'ezrt','azeaze','2022-11-06','-1056429475','aezrt772',1),(33,'eeeeerico','eeeea','2019-11-10','-651991297','eeeee164',1),(34,'eeee','eeeea','2019-11-29','1299163719','eeeee133',1),(35,'eeeeerico','eeeea','2019-11-10','-651991297','eeeeeerico603',1);
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
  `mdp` varchar(64) DEFAULT NULL,
  `login` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_etudiant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Etudiant`
--

LOCK TABLES `Etudiant` WRITE;
/*!40000 ALTER TABLE `Etudiant` DISABLE KEYS */;
INSERT INTO `Etudiant` VALUES (6,'2019-11-14',NULL,1,NULL,'Jack','Paul','1682802253','PJack875'),(7,'2019-11-23',NULL,1,NULL,'Perecic','Georgesano','-1906414871','GPerecic-778'),(12,'2019-11-15',NULL,1,NULL,'az','aze','-1873814567','aaz286'),(13,'2019-12-20',NULL,1,NULL,'bourboul','chomeur','-524537801','cbourboul223'),(15,'2019-11-15',NULL,1,NULL,'chameau','poisson','-1785752953','pchameau460'),(17,'2013-02-15',NULL,1,NULL,'miss','mister','2134782028','mmiss-28'),(18,'2019-01-15',NULL,1,NULL,'Adrien','Guard','-1952878488','GAdrien288'),(19,'2019-11-22',NULL,1,NULL,'100','99','721137975','9100-726'),(20,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg','-598542565','yninbin773'),(21,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg','-598542565','yninbin742'),(22,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg','-598542565','yninbin711'),(23,'2019-11-29',NULL,1,NULL,'ninbin','edrfgty','226724619','yninbin680'),(24,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg','-598542565','yninbin649'),(25,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg','-598542565','yninbin618'),(26,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg','-598542565','yninbin587'),(27,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg','-598542565','yninbin556');
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
  `intitule` varchar(40) DEFAULT NULL,
  `id_departement` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Formation`
--

LOCK TABLES `Formation` WRITE;
/*!40000 ALTER TABLE `Formation` DISABLE KEYS */;
INSERT INTO `Formation` VALUES (1,'M1 Informatique',NULL),(2,'Sciences Humaines',NULL),(3,'M1 Maths',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Materiel`
--

LOCK TABLES `Materiel` WRITE;
/*!40000 ALTER TABLE `Materiel` DISABLE KEYS */;
INSERT INTO `Materiel` VALUES (1,1,'ORDINATEUR'),(2,1,'VIDEO_PROJECTEUR'),(3,2,'IMPRIMANTE'),(4,2,'VIDEO_PROJECTEUR'),(5,3,'TABLEAU_TACTIL'),(18,9,'ORDINATEUR'),(29,11,'VIDEO_PROJECTEUR'),(30,12,'ORDINATEUR'),(31,13,'ORDINATEUR'),(32,13,'TABLEAU_TACTIL');
/*!40000 ALTER TABLE `Materiel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Matiere`
--

DROP TABLE IF EXISTS `Matiere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Matiere` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Matiere`
--

LOCK TABLES `Matiere` WRITE;
/*!40000 ALTER TABLE `Matiere` DISABLE KEYS */;
INSERT INTO `Matiere` VALUES (1,'Physique'),(2,'Maths'),(3,'Informatique'),(4,'Anglais');
/*!40000 ALTER TABLE `Matiere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Matiere_Enseignant`
--

DROP TABLE IF EXISTS `Matiere_Enseignant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Matiere_Enseignant` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_matiere` int(10) unsigned DEFAULT NULL,
  `id_enseignant` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_matiere` (`id_matiere`),
  KEY `fk_id_enseignant` (`id_enseignant`),
  CONSTRAINT `fk_id_enseignant` FOREIGN KEY (`id_enseignant`) REFERENCES `Enseignant` (`id`),
  CONSTRAINT `fk_id_matiere` FOREIGN KEY (`id_matiere`) REFERENCES `Matiere` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Matiere_Enseignant`
--

LOCK TABLES `Matiere_Enseignant` WRITE;
/*!40000 ALTER TABLE `Matiere_Enseignant` DISABLE KEYS */;
INSERT INTO `Matiere_Enseignant` VALUES (4,1,32),(5,2,34),(6,4,4),(7,3,20);
/*!40000 ALTER TABLE `Matiere_Enseignant` ENABLE KEYS */;
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
-- Table structure for table `Reservation`
--

DROP TABLE IF EXISTS `Reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reservation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_enseignant` int(10) unsigned DEFAULT NULL,
  `id_salle` int(10) unsigned DEFAULT NULL,
  `date_reservation` date NOT NULL,
  `etat_reservation` varchar(10) DEFAULT 'en Cours',
  `id_seance` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_enseignant` (`id_enseignant`),
  KEY `fk_salle` (`id_salle`),
  CONSTRAINT `fk_salle` FOREIGN KEY (`id_salle`) REFERENCES `Salle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation`
--

LOCK TABLES `Reservation` WRITE;
/*!40000 ALTER TABLE `Reservation` DISABLE KEYS */;
INSERT INTO `Reservation` VALUES (1,4,13,'2019-12-10','Confirmé',NULL),(2,4,12,'2019-12-14','en Cours',NULL),(3,4,12,'2019-12-10','en Cours',NULL),(4,4,13,'2019-12-14','en Cours',NULL),(8,21,3,'2019-12-13','en Cours',NULL),(9,21,11,'2019-12-13','Confirmé',NULL),(10,4,13,'2019-12-13','en Cours',NULL),(11,4,9,'2019-12-15','en Cours',NULL),(12,4,1,'2019-12-27','Confirmé',16),(13,4,9,'2019-12-27','Confirmé',32),(14,20,13,'2019-12-27','en Cours',9),(15,20,11,'2019-12-27','en Cours',7),(16,4,8,'2019-12-27','Confirmé',19),(17,4,3,'2019-12-27','Refusé',18);
/*!40000 ALTER TABLE `Reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Responsable`
--

DROP TABLE IF EXISTS `Responsable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Responsable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `login` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Responsable`
--

LOCK TABLES `Responsable` WRITE;
/*!40000 ALTER TABLE `Responsable` DISABLE KEYS */;
INSERT INTO `Responsable` VALUES (1,'Anouar','sayadi','0000-00-00','azerty','asayadi246'),(2,'Anouar','sayadi','1995-07-30','f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9','asayadi246');
/*!40000 ALTER TABLE `Responsable` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Salle`
--

LOCK TABLES `Salle` WRITE;
/*!40000 ALTER TABLE `Salle` DISABLE KEYS */;
INSERT INTO `Salle` VALUES (1,10,'lebellegou'),(2,42,'Salle a bourboul'),(3,1,'bac a asable'),(8,2,'pekinParisNewyork'),(9,66,'Salle vide'),(11,99,'masalleouPas'),(12,111,'Q522'),(13,22,'U458');
/*!40000 ALTER TABLE `Salle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Seance`
--

DROP TABLE IF EXISTS `Seance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Seance` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `debut_seance` time DEFAULT NULL,
  `fin_seance` time DEFAULT NULL,
  `id_matiere` int(11) DEFAULT NULL,
  `id_enseignant` int(10) unsigned DEFAULT NULL,
  `id_Salle` int(10) unsigned DEFAULT NULL,
  `etat` varchar(1) DEFAULT NULL,
  `id_formation` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Seance`
--

LOCK TABLES `Seance` WRITE;
/*!40000 ALTER TABLE `Seance` DISABLE KEYS */;
INSERT INTO `Seance` VALUES (1,'2019-09-30','08:00:48','12:00:48',4,4,1,NULL,1),(2,'2019-09-09','08:00:05','12:00:05',4,4,1,NULL,1),(4,'2020-03-19','11:00:35','13:00:35',4,4,1,NULL,1),(5,'2019-10-01','11:00:01','15:00:01',4,4,1,NULL,1),(9,'2019-12-30','08:00:42','13:00:42',4,4,1,NULL,1),(11,'2020-02-26','08:00:39','13:00:39',4,4,1,NULL,1),(12,'2019-10-02','08:00:21','13:00:21',4,4,1,NULL,1),(15,'2019-10-08','08:00:26','10:00:26',3,20,1,NULL,1),(16,'2019-10-09','08:00:33','10:00:33',3,20,1,NULL,1),(17,'2019-10-31','08:00:40','10:00:40',3,20,1,NULL,1),(20,'2019-10-15','08:00:01','10:00:01',2,34,1,NULL,1),(21,'2019-10-17','10:00:06','13:00:06',2,34,1,NULL,1),(22,'2020-01-15','10:00:12','13:00:12',2,34,1,NULL,1),(23,'2020-03-04','10:00:17','13:00:17',4,4,1,NULL,1),(24,'2019-10-22','08:00:59','12:00:59',3,20,1,NULL,1),(26,'2019-09-19','08:00:14','12:00:14',3,20,1,NULL,1),(27,'2019-10-01','08:00:04','10:00:04',2,34,1,NULL,1),(28,'2019-10-01','08:00:12','10:00:12',2,34,1,NULL,1),(29,'2019-10-01','08:00:13','10:00:13',2,34,1,NULL,1),(30,'2019-09-24','08:00:21','10:00:21',2,34,1,NULL,1),(31,'2019-09-27','08:00:30','12:00:30',2,34,1,NULL,1),(32,'2019-09-12','08:00:36','12:00:36',2,34,1,NULL,1),(34,'2019-09-25','08:00:07','12:00:07',2,34,1,NULL,1),(35,'2019-09-26','10:00:12','12:00:12',2,34,1,NULL,1),(36,'2019-10-02','08:00:08','11:00:08',2,34,1,NULL,1),(37,'2019-10-01','08:00:01','12:00:01',3,20,1,NULL,1),(39,'2019-09-10','08:00:03','13:00:03',3,20,1,NULL,1),(42,'2019-09-11','09:00:14','12:00:14',4,4,1,NULL,1),(43,'2019-09-02','08:00:46','12:00:46',4,4,1,NULL,1),(44,'2019-12-31','08:00:40','12:00:40',3,20,1,NULL,1),(47,'2020-01-03','08:00:09','11:00:09',3,20,1,NULL,1),(48,'2020-01-02','08:00:13','11:00:13',3,20,1,NULL,1),(54,'2020-01-01','14:00:42','19:00:42',3,20,1,NULL,1),(55,'2020-01-01','08:00:15','12:00:15',4,4,1,NULL,1),(56,'2020-01-02','14:00:23','18:00:23',3,20,3,NULL,1),(57,'2019-10-16','14:00:38','18:00:38',3,20,1,NULL,1),(59,'2019-09-05','08:00:05','10:00:05',4,4,1,NULL,3),(60,'2019-09-03','08:00:34','10:00:34',4,4,1,NULL,1),(61,'2019-09-04','08:00:03','10:00:03',1,32,1,NULL,1),(62,'2019-09-12','14:00:01','18:00:01',4,4,1,NULL,1),(64,'2020-01-01','08:00:39','13:00:39',4,4,1,NULL,2),(65,'2019-12-31','08:00:16','12:00:16',4,4,1,NULL,1),(66,'2020-01-01','14:00:32','17:30:32',3,20,1,NULL,1);
/*!40000 ALTER TABLE `Seance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personne`
--

DROP TABLE IF EXISTS `personne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-02 15:58:03
