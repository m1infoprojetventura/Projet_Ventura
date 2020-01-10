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
  `motif` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`),
  KEY `fk_id_enseignant_contrainte` (`id_enseignant`),
  CONSTRAINT `fk_id_enseignant_contrainte` FOREIGN KEY (`id_enseignant`) REFERENCES `Enseignant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Contrainte`
--

LOCK TABLES `Contrainte` WRITE;
/*!40000 ALTER TABLE `Contrainte` DISABLE KEYS */;
INSERT INTO `Contrainte` VALUES (2,'2019-12-31','08:00:57','20:00:57',4,'reunion profesionelle'),(3,'2020-01-02','08:00:26','10:00:26',4,'rdv avec mon dieteticien'),(4,'2020-01-09','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(5,'2020-01-16','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(6,'2020-01-23','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(7,'2020-01-30','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(8,'2020-02-06','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(9,'2020-02-13','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(10,'2020-02-20','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(11,'2020-02-27','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(12,'2020-03-05','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(13,'2020-03-12','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(14,'2020-03-19','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(15,'2020-03-26','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(16,'2020-04-02','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(17,'2020-04-09','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(18,'2020-04-16','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(19,'2020-04-23','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(20,'2020-04-30','08:00:27','10:00:27',4,'rdv avec mon dieteticien'),(21,'2020-05-07','08:00:28','10:00:28',4,'rdv avec mon dieteticien'),(22,'2020-05-14','08:00:28','10:00:28',4,'rdv avec mon dieteticien'),(23,'2020-05-21','08:00:28','10:00:28',4,'rdv avec mon dieteticien'),(24,'2020-05-28','08:00:29','10:00:29',4,'rdv avec mon dieteticien'),(25,'2020-06-04','08:00:29','10:00:29',4,'rdv avec mon dieteticien'),(26,'2020-06-11','08:00:29','10:00:29',4,'rdv avec mon dieteticien'),(27,'2020-06-18','08:00:29','10:00:29',4,'rdv avec mon dieteticien'),(28,'2020-06-25','08:00:29','10:00:29',4,'rdv avec mon dieteticien'),(29,'2020-07-02','08:00:29','10:00:29',4,'rdv avec mon dieteticien'),(30,'2020-07-09','08:00:29','10:00:29',4,'rdv avec mon dieteticien'),(31,'2020-07-16','08:00:29','10:00:29',4,'rdv avec mon dieteticien'),(32,'2020-07-23','08:00:29','10:00:29',4,'rdv avec mon dieteticien'),(33,'2020-07-30','08:00:29','10:00:29',4,'rdv avec mon dieteticien'),(34,'2020-08-06','08:00:29','10:00:29',4,'rdv avec mon dieteticien'),(35,'2020-08-13','08:00:29','10:00:29',4,'rdv avec mon dieteticien'),(36,'2020-08-20','08:00:29','10:00:29',4,'rdv avec mon dieteticien'),(37,'2020-08-27','08:00:29','10:00:29',4,'rdv avec mon dieteticien'),(38,'2020-09-03','08:00:29','10:00:29',4,'rdv avec mon dieteticien'),(39,'2020-01-02','14:00:26','16:00:26',36,'chercher les enfants a l\'ecole'),(40,'2020-01-09','14:00:26','16:00:26',36,'chercher les enfants a l\'ecole'),(41,'2020-01-16','14:00:26','16:00:26',36,'chercher les enfants a l\'ecole'),(42,'2020-01-23','14:00:26','16:00:26',36,'chercher les enfants a l\'ecole'),(43,'2020-01-30','14:00:26','16:00:26',36,'chercher les enfants a l\'ecole'),(44,'2020-02-06','14:00:26','16:00:26',36,'chercher les enfants a l\'ecole'),(45,'2020-02-13','14:00:26','16:00:26',36,'chercher les enfants a l\'ecole'),(46,'2020-02-20','14:00:26','16:00:26',36,'chercher les enfants a l\'ecole'),(47,'2020-02-27','14:00:26','16:00:26',36,'chercher les enfants a l\'ecole'),(48,'2020-03-05','14:00:26','16:00:26',36,'chercher les enfants a l\'ecole'),(49,'2020-03-12','14:00:26','16:00:26',36,'chercher les enfants a l\'ecole'),(50,'2020-03-19','14:00:26','16:00:26',36,'chercher les enfants a l\'ecole'),(51,'2020-03-26','14:00:27','16:00:27',36,'chercher les enfants a l\'ecole'),(52,'2020-04-02','14:00:27','16:00:27',36,'chercher les enfants a l\'ecole'),(53,'2020-04-09','14:00:27','16:00:27',36,'chercher les enfants a l\'ecole'),(54,'2020-04-16','14:00:27','16:00:27',36,'chercher les enfants a l\'ecole'),(55,'2020-04-23','14:00:27','16:00:27',36,'chercher les enfants a l\'ecole'),(56,'2020-04-30','14:00:27','16:00:27',36,'chercher les enfants a l\'ecole'),(57,'2020-05-07','14:00:27','16:00:27',36,'chercher les enfants a l\'ecole'),(58,'2020-05-14','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(59,'2020-05-21','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(60,'2020-05-28','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(61,'2020-06-04','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(62,'2020-06-11','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(63,'2020-06-18','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(64,'2020-06-25','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(65,'2020-07-02','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(66,'2020-07-09','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(67,'2020-07-16','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(68,'2020-07-23','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(69,'2020-07-30','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(70,'2020-08-06','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(71,'2020-08-13','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(72,'2020-08-20','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(73,'2020-08-27','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(74,'2020-09-03','14:00:28','16:00:28',36,'chercher les enfants a l\'ecole'),(75,'2019-12-31','14:00:51','16:00:51',36,'chercher les enfants a l\'ecole (exceptionel)'),(76,'2020-01-03','08:00:43','20:00:43',38,'rdv avec mon banquier + deprime suite a ce rdv'),(77,'2019-12-31','11:00:58','13:00:58',43,'pas de vehicule pour venir'),(78,'2020-01-02','11:00:18','13:00:18',43,'metro fermé pour venir'),(79,'2020-01-09','11:00:18','13:00:18',43,'metro fermé pour venir'),(80,'2020-01-16','11:00:18','13:00:18',43,'metro fermé pour venir'),(81,'2020-01-23','11:00:18','13:00:18',43,'metro fermé pour venir'),(82,'2020-01-30','11:00:18','13:00:18',43,'metro fermé pour venir'),(83,'2020-02-06','11:00:18','13:00:18',43,'metro fermé pour venir'),(84,'2020-02-13','11:00:18','13:00:18',43,'metro fermé pour venir'),(85,'2020-02-20','11:00:18','13:00:18',43,'metro fermé pour venir'),(86,'2020-02-27','11:00:18','13:00:18',43,'metro fermé pour venir'),(87,'2020-03-05','11:00:19','13:00:19',43,'metro fermé pour venir'),(88,'2020-03-12','11:00:19','13:00:19',43,'metro fermé pour venir'),(89,'2020-03-19','11:00:19','13:00:19',43,'metro fermé pour venir'),(90,'2020-03-26','11:00:19','13:00:19',43,'metro fermé pour venir'),(91,'2020-04-02','11:00:19','13:00:19',43,'metro fermé pour venir'),(92,'2020-04-09','11:00:19','13:00:19',43,'metro fermé pour venir'),(93,'2020-04-16','11:00:19','13:00:19',43,'metro fermé pour venir'),(94,'2020-04-23','11:00:19','13:00:19',43,'metro fermé pour venir'),(95,'2020-04-30','11:00:19','13:00:19',43,'metro fermé pour venir'),(96,'2020-05-07','11:00:19','13:00:19',43,'metro fermé pour venir'),(97,'2020-05-14','11:00:19','13:00:19',43,'metro fermé pour venir'),(98,'2020-05-21','11:00:19','13:00:19',43,'metro fermé pour venir'),(99,'2020-05-28','11:00:19','13:00:19',43,'metro fermé pour venir'),(100,'2020-06-04','11:00:19','13:00:19',43,'metro fermé pour venir'),(101,'2020-06-11','11:00:19','13:00:19',43,'metro fermé pour venir'),(102,'2020-06-18','11:00:19','13:00:19',43,'metro fermé pour venir'),(103,'2020-06-25','11:00:19','13:00:19',43,'metro fermé pour venir'),(104,'2020-07-02','11:00:19','13:00:19',43,'metro fermé pour venir'),(105,'2020-07-09','11:00:19','13:00:19',43,'metro fermé pour venir'),(106,'2020-07-16','11:00:19','13:00:19',43,'metro fermé pour venir'),(107,'2020-07-23','11:00:19','13:00:19',43,'metro fermé pour venir'),(108,'2020-07-30','11:00:19','13:00:19',43,'metro fermé pour venir'),(109,'2020-08-06','11:00:19','13:00:19',43,'metro fermé pour venir'),(110,'2020-08-13','11:00:19','13:00:19',43,'metro fermé pour venir'),(111,'2020-08-20','11:00:20','13:00:20',43,'metro fermé pour venir'),(112,'2020-08-27','11:00:20','13:00:20',43,'metro fermé pour venir'),(113,'2020-09-03','11:00:20','13:00:20',43,'metro fermé pour venir');
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
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Enseignant`
--

LOCK TABLES `Enseignant` WRITE;
/*!40000 ALTER TABLE `Enseignant` DISABLE KEYS */;
INSERT INTO `Enseignant` VALUES (4,'Jack','Jean','2019-11-14','f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9','JJack788',1),(18,'Misser','Monique','2013-02-15','1329469239','mmiss-28',1),(20,'peter','parker','2016-11-19','-166493559','ppeter482',1),(32,'Aziz','Ballack','2022-11-06','-6438788','aezrt772',1),(34,'Elrich','Eric','2019-11-29','-98974906','eeeee133',1),(36,'Martinez','Rodrigo','2020-02-01','1483264754','RMartinez957',1),(37,'Leluc','Pierre','1981-06-12','-81939402','PLeluc367',1),(38,'Papin','Sandrine','1981-06-12','-1623731022','SPapin398',1),(39,'Zino','Samuel','1981-06-12','-1787895927','SZino429',1),(40,'Oliaq','Michel','1981-06-12','560915648','MOliaq460',1),(41,'Perec','Rachelle','1981-06-12','825206315','RPerec491',1),(42,'Martin','Gabriel','1981-06-12','2117028935','GMartin522',1),(43,'Jent','Orianne','1981-06-12','1923961701','OJent553',1),(44,'Martin','Maryline','1981-06-12','-640684794','MMartin584',1),(45,'Morose','Patrice','1981-06-12','465678375','PMorose615',1),(46,'Benaouid','Sofiane','1981-06-12','1737900414','SBenaouid646',1),(47,'Markovich','Vladimir','1981-06-12','347368892','VMarkovich677',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Etudiant`
--

LOCK TABLES `Etudiant` WRITE;
/*!40000 ALTER TABLE `Etudiant` DISABLE KEYS */;
INSERT INTO `Etudiant` VALUES (6,'2019-11-14',NULL,1,NULL,'Jack','Paul','1682802253','PJack875'),(28,'1981-06-12',NULL,1,NULL,'Ozone','Richard','600894968','ROzone88'),(29,'1981-06-12',NULL,1,NULL,'Rix','Thomas','-108351007','TRix119'),(30,'1981-06-12',NULL,3,NULL,'Poric','Maelle','-1454284451','MPoric150'),(31,'1981-06-12',NULL,3,NULL,'Poric','Hugo','-806671650','HPoric181'),(32,'1981-06-12',NULL,3,NULL,'Lagrave','Oscar','-1830016836','OLagrave212'),(33,'1981-06-12',NULL,6,NULL,'Sazerion','Sarah','464947032','SSazerion243'),(34,'1981-06-12',NULL,5,NULL,'Pinson','Robinson','-1124814527','RPinson274'),(35,'1981-06-12',NULL,9,NULL,'Gyllui','Patricia','400589859','PGyllui305');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Formation`
--

LOCK TABLES `Formation` WRITE;
/*!40000 ALTER TABLE `Formation` DISABLE KEYS */;
INSERT INTO `Formation` VALUES (1,'M1 Informatique',NULL),(2,'Sciences Humaines',NULL),(3,'M1 Maths',NULL),(4,'L3 Informatique',NULL),(5,'Biologie',NULL),(6,'Physique Chimie',NULL),(7,'Sciences De l ingenieur',NULL),(8,'Electronicien',NULL),(9,'Langues Appliqués',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Materiel`
--

LOCK TABLES `Materiel` WRITE;
/*!40000 ALTER TABLE `Materiel` DISABLE KEYS */;
INSERT INTO `Materiel` VALUES (1,1,'ORDINATEUR'),(2,1,'VIDEO_PROJECTEUR'),(5,3,'TABLEAU_TACTIL'),(18,9,'ORDINATEUR'),(29,11,'VIDEO_PROJECTEUR'),(30,12,'ORDINATEUR'),(31,13,'ORDINATEUR'),(32,13,'TABLEAU_TACTIL'),(33,14,'VIDEO_PROJECTEUR'),(34,15,'VIDEO_PROJECTEUR'),(35,16,'ORDINATEUR'),(36,17,'ORDINATEUR'),(37,17,'IMPRIMANTE'),(38,17,'VIDEO_PROJECTEUR'),(39,18,'IMPRIMANTE'),(40,18,'TABLEAU_TACTIL'),(41,18,'VIDEO_PROJECTEUR'),(42,19,'ORDINATEUR'),(43,19,'IMPRIMANTE'),(44,19,'TABLEAU_TACTIL'),(45,19,'VIDEO_PROJECTEUR'),(46,20,'ORDINATEUR'),(47,20,'IMPRIMANTE'),(48,20,'TABLEAU_TACTIL'),(49,20,'VIDEO_PROJECTEUR'),(50,21,'ORDINATEUR'),(51,21,'IMPRIMANTE'),(52,21,'TABLEAU_TACTIL'),(53,21,'VIDEO_PROJECTEUR'),(56,22,'VIDEO_PROJECTEUR');
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Matiere`
--

LOCK TABLES `Matiere` WRITE;
/*!40000 ALTER TABLE `Matiere` DISABLE KEYS */;
INSERT INTO `Matiere` VALUES (1,'Physique'),(2,'Maths'),(3,'Informatique'),(4,'Anglais'),(5,'Phylosophie'),(6,'Electronique'),(7,'Français'),(8,'Programation Avancé'),(9,'Chimie'),(10,'Mécanique'),(11,'Gestion de Projet'),(12,'Economie'),(13,'Latin');
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Matiere_Enseignant`
--

LOCK TABLES `Matiere_Enseignant` WRITE;
/*!40000 ALTER TABLE `Matiere_Enseignant` DISABLE KEYS */;
INSERT INTO `Matiere_Enseignant` VALUES (1,4,4),(2,2,18),(3,1,18),(4,5,20),(5,2,32),(6,2,37),(7,2,38),(8,2,45),(9,6,34),(10,8,34),(11,3,34),(12,1,39),(13,7,39),(14,12,43),(15,13,46),(16,10,46),(17,11,36),(18,10,40),(19,6,40),(20,8,41),(21,7,42),(22,13,42),(23,5,42),(24,5,44),(25,2,47),(26,1,47),(27,8,47);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation`
--

LOCK TABLES `Reservation` WRITE;
/*!40000 ALTER TABLE `Reservation` DISABLE KEYS */;
INSERT INTO `Reservation` VALUES (1,4,21,'2020-01-09','en Cours',103),(2,34,14,'2020-01-09','en Cours',314),(3,34,16,'2020-01-09','en Cours',316);
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Salle`
--

LOCK TABLES `Salle` WRITE;
/*!40000 ALTER TABLE `Salle` DISABLE KEYS */;
INSERT INTO `Salle` VALUES (1,10,'T110'),(3,1,'X007'),(8,2,'V101'),(9,66,'D220'),(11,99,'U202'),(12,111,'Q522'),(13,22,'U458'),(14,60,'T201'),(15,60,'T202'),(16,12,'Y066'),(17,30,'Z000'),(18,100,'K444'),(19,24,'P111'),(20,24,'P112'),(21,24,'P113'),(22,7,'A222');
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
) ENGINE=InnoDB AUTO_INCREMENT=1063 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Seance`
--

LOCK TABLES `Seance` WRITE;
/*!40000 ALTER TABLE `Seance` DISABLE KEYS */;
INSERT INTO `Seance` VALUES (1,'2019-12-30','08:00:24','12:00:24',8,47,20,NULL,1),(2,'2020-01-06','08:00:24','12:00:24',8,47,20,NULL,1),(3,'2020-01-13','08:00:24','12:00:24',8,47,20,NULL,1),(4,'2020-01-20','08:00:24','12:00:24',8,47,20,NULL,1),(5,'2020-01-27','08:00:24','12:00:24',8,47,20,NULL,1),(6,'2020-02-03','08:00:24','12:00:24',8,47,20,NULL,1),(7,'2020-02-10','08:00:24','12:00:24',8,47,20,NULL,1),(8,'2020-02-17','08:00:24','12:00:24',8,47,20,NULL,1),(9,'2020-02-24','08:00:24','12:00:24',8,47,20,NULL,1),(10,'2020-03-02','08:00:24','12:00:24',8,47,20,NULL,1),(11,'2020-03-09','08:00:24','12:00:24',8,47,20,NULL,1),(12,'2020-03-16','08:00:24','12:00:24',8,47,20,NULL,1),(13,'2020-03-23','08:00:24','12:00:24',8,47,20,NULL,1),(14,'2020-03-30','08:00:24','12:00:24',8,47,20,NULL,1),(15,'2020-04-06','08:00:24','12:00:24',8,47,20,NULL,1),(16,'2020-04-13','08:00:24','12:00:24',8,47,20,NULL,1),(17,'2020-04-20','08:00:24','12:00:24',8,47,20,NULL,1),(18,'2020-04-27','08:00:24','12:00:24',8,47,20,NULL,1),(19,'2020-05-04','08:00:24','12:00:24',8,47,20,NULL,1),(20,'2020-05-11','08:00:24','12:00:24',8,47,20,NULL,1),(21,'2020-05-18','08:00:25','12:00:25',8,47,20,NULL,1),(22,'2020-05-25','08:00:25','12:00:25',8,47,20,NULL,1),(23,'2020-06-01','08:00:25','12:00:25',8,47,20,NULL,1),(24,'2020-06-08','08:00:25','12:00:25',8,47,20,NULL,1),(25,'2020-06-15','08:00:25','12:00:25',8,47,20,NULL,1),(26,'2020-06-22','08:00:25','12:00:25',8,47,20,NULL,1),(27,'2020-06-29','08:00:25','12:00:25',8,47,20,NULL,1),(28,'2020-07-06','08:00:25','12:00:25',8,47,20,NULL,1),(29,'2020-07-13','08:00:25','12:00:25',8,47,20,NULL,1),(30,'2020-07-20','08:00:25','12:00:25',8,47,20,NULL,1),(31,'2020-07-27','08:00:25','12:00:25',8,47,20,NULL,1),(32,'2020-08-03','08:00:25','12:00:25',8,47,20,NULL,1),(33,'2020-08-10','08:00:25','12:00:25',8,47,20,NULL,1),(34,'2020-08-17','08:00:25','12:00:25',8,47,20,NULL,1),(35,'2020-08-24','08:00:25','12:00:25',8,47,20,NULL,1),(36,'2020-08-31','08:00:25','12:00:25',8,47,20,NULL,1),(37,'2019-12-31','08:30:00','11:00:00',2,32,1,NULL,1),(38,'2019-12-31','13:00:37','15:00:37',6,34,18,NULL,1),(39,'2020-01-01','08:00:41','11:00:41',1,39,22,NULL,1),(40,'2020-01-08','08:00:42','11:00:42',1,39,22,NULL,1),(41,'2020-01-15','08:00:42','11:00:42',1,39,22,NULL,1),(42,'2020-01-22','08:00:42','11:00:42',1,39,22,NULL,1),(43,'2020-01-29','08:00:42','11:00:42',1,39,22,NULL,1),(44,'2020-02-05','08:00:42','11:00:42',1,39,22,NULL,1),(45,'2020-02-12','08:00:42','11:00:42',1,39,22,NULL,1),(46,'2020-02-19','08:00:42','11:00:42',1,39,22,NULL,1),(47,'2020-02-26','08:00:42','11:00:42',1,39,22,NULL,1),(48,'2020-03-04','08:00:42','11:00:42',1,39,22,NULL,1),(49,'2020-03-11','08:00:42','11:00:42',1,39,22,NULL,1),(50,'2020-03-18','08:00:42','11:00:42',1,39,22,NULL,1),(51,'2020-03-25','08:00:42','11:00:42',1,39,22,NULL,1),(52,'2020-04-01','08:00:42','11:00:42',1,39,22,NULL,1),(53,'2020-04-08','08:00:42','11:00:42',1,39,22,NULL,1),(54,'2020-04-15','08:00:42','11:00:42',1,39,22,NULL,1),(55,'2020-04-22','08:00:42','11:00:42',1,39,22,NULL,1),(56,'2020-04-29','08:00:42','11:00:42',1,39,22,NULL,1),(57,'2020-05-06','08:00:42','11:00:42',1,39,22,NULL,1),(58,'2020-05-13','08:00:42','11:00:42',1,39,22,NULL,1),(59,'2020-05-20','08:00:42','11:00:42',1,39,22,NULL,1),(60,'2020-05-27','08:00:42','11:00:42',1,39,22,NULL,1),(61,'2020-06-03','08:00:43','11:00:43',1,39,22,NULL,1),(62,'2020-06-10','08:00:43','11:00:43',1,39,22,NULL,1),(63,'2020-06-17','08:00:43','11:00:43',1,39,22,NULL,1),(64,'2020-06-24','08:00:43','11:00:43',1,39,22,NULL,1),(65,'2020-07-01','08:00:43','11:00:43',1,39,22,NULL,1),(66,'2020-07-08','08:00:43','11:00:43',1,39,22,NULL,1),(67,'2020-07-15','08:00:43','11:00:43',1,39,22,NULL,1),(68,'2020-07-22','08:00:43','11:00:43',1,39,22,NULL,1),(69,'2020-07-29','08:00:43','11:00:43',1,39,22,NULL,1),(70,'2020-08-05','08:00:43','11:00:43',1,39,22,NULL,1),(71,'2020-08-12','08:00:43','11:00:43',1,39,22,NULL,1),(72,'2020-08-19','08:00:44','11:00:44',1,39,22,NULL,1),(73,'2020-08-26','08:00:44','11:00:44',1,39,22,NULL,1),(74,'2020-09-02','08:00:44','11:00:44',1,39,22,NULL,1),(89,'2020-04-08','11:00:26','12:15:26',4,4,1,NULL,1),(90,'2020-04-15','11:00:09','12:00:09',4,4,17,NULL,1),(91,'2020-04-22','11:00:09','12:00:09',4,4,17,NULL,1),(92,'2020-04-29','11:00:09','12:00:09',4,4,17,NULL,1),(93,'2020-05-06','11:00:09','12:00:09',4,4,17,NULL,1),(94,'2020-05-13','11:00:09','12:00:09',4,4,17,NULL,1),(95,'2020-05-20','11:00:09','12:00:09',4,4,17,NULL,1),(96,'2020-05-27','11:00:09','12:00:09',4,4,17,NULL,1),(97,'2020-06-03','11:00:09','12:00:09',4,4,17,NULL,1),(98,'2020-06-10','11:00:09','12:00:09',4,4,17,NULL,1),(99,'2020-06-17','11:00:09','12:00:09',4,4,17,NULL,1),(100,'2020-06-24','11:00:09','12:00:09',4,4,17,NULL,1),(101,'2020-07-01','11:00:09','12:00:09',4,4,17,NULL,1),(102,'2020-07-08','11:00:10','12:00:10',4,4,17,NULL,1),(103,'2020-07-15','11:00:10','12:00:10',4,4,17,NULL,1),(104,'2020-07-22','11:00:10','12:00:10',4,4,17,NULL,1),(105,'2020-07-29','11:00:10','12:00:10',4,4,17,NULL,1),(106,'2020-08-05','11:00:10','12:00:10',4,4,17,NULL,1),(107,'2020-08-12','11:00:10','12:00:10',4,4,17,NULL,1),(108,'2020-08-19','11:00:11','12:00:11',4,4,17,NULL,1),(109,'2020-08-26','11:00:11','12:00:11',4,4,17,NULL,1),(110,'2020-09-02','11:00:11','12:00:11',4,4,17,NULL,1),(111,'2020-01-01','16:00:35','18:00:35',10,46,12,NULL,1),(112,'2020-01-08','16:00:35','18:00:35',10,46,12,NULL,1),(113,'2020-01-15','16:00:35','18:00:35',10,46,12,NULL,1),(114,'2020-01-22','16:00:36','18:00:36',10,46,12,NULL,1),(115,'2020-01-29','16:00:36','18:00:36',10,46,12,NULL,1),(116,'2020-02-05','16:00:36','18:00:36',10,46,12,NULL,1),(117,'2020-02-12','16:00:36','18:00:36',10,46,12,NULL,1),(118,'2020-02-19','16:00:36','18:00:36',10,46,12,NULL,1),(119,'2020-02-26','16:00:36','18:00:36',10,46,12,NULL,1),(120,'2020-03-04','16:00:36','18:00:36',10,46,12,NULL,1),(121,'2020-03-11','16:00:36','18:00:36',10,46,12,NULL,1),(122,'2020-03-18','16:00:36','18:00:36',10,46,12,NULL,1),(123,'2020-03-25','16:00:36','18:00:36',10,46,12,NULL,1),(124,'2020-04-01','16:00:36','18:00:36',10,46,12,NULL,1),(125,'2020-04-08','16:00:36','18:00:36',10,46,12,NULL,1),(126,'2020-04-15','16:00:36','18:00:36',10,46,12,NULL,1),(127,'2020-04-22','16:00:36','18:00:36',10,46,12,NULL,1),(128,'2020-04-29','16:00:36','18:00:36',10,46,12,NULL,1),(129,'2020-05-06','16:00:36','18:00:36',10,46,12,NULL,1),(130,'2020-05-13','16:00:36','18:00:36',10,46,12,NULL,1),(131,'2020-05-20','16:00:36','18:00:36',10,46,12,NULL,1),(132,'2020-05-27','16:00:36','18:00:36',10,46,12,NULL,1),(133,'2020-06-03','16:00:37','18:00:37',10,46,12,NULL,1),(134,'2020-06-10','16:00:38','18:00:38',10,46,12,NULL,1),(135,'2020-06-17','16:00:38','18:00:38',10,46,12,NULL,1),(136,'2020-06-24','16:00:38','18:00:38',10,46,12,NULL,1),(137,'2020-07-01','16:00:38','18:00:38',10,46,12,NULL,1),(138,'2020-07-08','16:00:38','18:00:38',10,46,12,NULL,1),(139,'2020-07-15','16:00:38','18:00:38',10,46,12,NULL,1),(140,'2020-07-22','16:00:38','18:00:38',10,46,12,NULL,1),(141,'2020-07-29','16:00:38','18:00:38',10,46,12,NULL,1),(142,'2020-08-05','16:00:38','18:00:38',10,46,12,NULL,1),(143,'2020-08-12','16:00:38','18:00:38',10,46,12,NULL,1),(144,'2020-08-19','16:00:38','18:00:38',10,46,12,NULL,1),(145,'2020-08-26','16:00:38','18:00:38',10,46,12,NULL,1),(146,'2020-09-02','16:00:38','18:00:38',10,46,12,NULL,1),(147,'2020-01-02','09:00:01','12:00:01',10,40,16,NULL,1),(148,'2020-01-09','09:00:01','12:00:01',10,40,16,NULL,1),(149,'2020-01-16','09:00:01','12:00:01',10,40,16,NULL,1),(150,'2020-01-23','09:00:01','12:00:01',10,40,16,NULL,1),(151,'2020-01-30','09:00:01','12:00:01',10,40,16,NULL,1),(152,'2020-02-06','09:00:01','12:00:01',10,40,16,NULL,1),(153,'2020-02-13','09:00:01','12:00:01',10,40,16,NULL,1),(154,'2020-02-20','09:00:01','12:00:01',10,40,16,NULL,1),(155,'2020-02-27','09:00:02','12:00:02',10,40,16,NULL,1),(156,'2020-03-05','09:00:02','12:00:02',10,40,16,NULL,1),(157,'2020-03-12','09:00:02','12:00:02',10,40,16,NULL,1),(158,'2020-03-19','09:00:02','12:00:02',10,40,16,NULL,1),(159,'2020-03-26','09:00:02','12:00:02',10,40,16,NULL,1),(160,'2020-04-02','09:00:02','12:00:02',10,40,16,NULL,1),(161,'2020-04-09','09:00:02','12:00:02',10,40,16,NULL,1),(162,'2020-04-16','09:00:02','12:00:02',10,40,16,NULL,1),(163,'2020-04-23','09:00:02','12:00:02',10,40,16,NULL,1),(164,'2020-04-30','09:00:02','12:00:02',10,40,16,NULL,1),(165,'2020-05-07','09:00:02','12:00:02',10,40,16,NULL,1),(166,'2020-05-14','09:00:02','12:00:02',10,40,16,NULL,1),(167,'2020-05-21','09:00:02','12:00:02',10,40,16,NULL,1),(168,'2020-05-28','09:00:02','12:00:02',10,40,16,NULL,1),(169,'2020-06-04','09:00:02','12:00:02',10,40,16,NULL,1),(170,'2020-06-11','09:00:02','12:00:02',10,40,16,NULL,1),(171,'2020-06-18','09:00:02','12:00:02',10,40,16,NULL,1),(172,'2020-06-25','09:00:02','12:00:02',10,40,16,NULL,1),(173,'2020-07-02','09:00:02','12:00:02',10,40,16,NULL,1),(174,'2020-07-09','09:00:03','12:00:03',10,40,16,NULL,1),(175,'2020-07-16','09:00:03','12:00:03',10,40,16,NULL,1),(176,'2020-07-23','09:00:03','12:00:03',10,40,16,NULL,1),(177,'2020-07-30','09:00:03','12:00:03',10,40,16,NULL,1),(178,'2020-08-06','09:00:03','12:00:03',10,40,16,NULL,1),(179,'2020-08-13','09:00:04','12:00:04',10,40,16,NULL,1),(180,'2020-08-20','09:00:04','12:00:04',10,40,16,NULL,1),(181,'2020-08-27','09:00:04','12:00:04',10,40,16,NULL,1),(182,'2020-09-03','09:00:04','12:00:04',10,40,16,NULL,1),(183,'2020-01-03','09:00:35','12:00:35',8,47,22,NULL,1),(184,'2020-01-10','09:00:35','12:00:35',8,47,22,NULL,1),(185,'2020-01-17','09:00:35','12:00:35',8,47,22,NULL,1),(186,'2020-01-24','09:00:35','12:00:35',8,47,22,NULL,1),(187,'2020-01-31','09:00:35','12:00:35',8,47,22,NULL,1),(188,'2020-02-07','09:00:35','12:00:35',8,47,22,NULL,1),(189,'2020-02-14','09:00:35','12:00:35',8,47,22,NULL,1),(190,'2020-02-21','09:00:36','12:00:36',8,47,22,NULL,1),(191,'2020-02-28','09:00:36','12:00:36',8,47,22,NULL,1),(192,'2020-03-06','09:00:36','12:00:36',8,47,22,NULL,1),(193,'2020-03-13','09:00:36','12:00:36',8,47,22,NULL,1),(194,'2020-03-20','09:00:36','12:00:36',8,47,22,NULL,1),(195,'2020-03-27','09:00:36','12:00:36',8,47,22,NULL,1),(196,'2020-04-03','09:00:36','12:00:36',8,47,22,NULL,1),(197,'2020-04-10','09:00:36','12:00:36',8,47,22,NULL,1),(198,'2020-04-17','09:00:36','12:00:36',8,47,22,NULL,1),(199,'2020-04-24','09:00:36','12:00:36',8,47,22,NULL,1),(200,'2020-05-01','09:00:36','12:00:36',8,47,22,NULL,1),(201,'2020-05-08','09:00:36','12:00:36',8,47,22,NULL,1),(202,'2020-05-15','09:00:36','12:00:36',8,47,22,NULL,1),(203,'2020-05-22','09:00:36','12:00:36',8,47,22,NULL,1),(204,'2020-05-29','09:00:36','12:00:36',8,47,22,NULL,1),(205,'2020-06-05','09:00:36','12:00:36',8,47,22,NULL,1),(206,'2020-06-12','09:00:36','12:00:36',8,47,22,NULL,1),(207,'2020-06-19','09:00:36','12:00:36',8,47,22,NULL,1),(208,'2020-06-26','09:00:37','12:00:37',8,47,22,NULL,1),(209,'2020-07-03','09:00:37','12:00:37',8,47,22,NULL,1),(210,'2020-07-10','09:00:37','12:00:37',8,47,22,NULL,1),(211,'2020-07-17','09:00:37','12:00:37',8,47,22,NULL,1),(212,'2020-07-24','09:00:37','12:00:37',8,47,22,NULL,1),(213,'2020-07-31','09:00:37','12:00:37',8,47,22,NULL,1),(214,'2020-08-07','09:00:37','12:00:37',8,47,22,NULL,1),(215,'2020-08-14','09:00:37','12:00:37',8,47,22,NULL,1),(216,'2020-08-21','09:00:37','12:00:37',8,47,22,NULL,1),(217,'2020-08-28','09:00:37','12:00:37',8,47,22,NULL,1),(218,'2020-09-04','09:00:37','12:00:37',8,47,22,NULL,1),(219,'2020-01-01','11:00:52','13:00:52',4,4,1,NULL,1),(220,'2020-01-08','11:00:58','13:00:58',4,4,1,NULL,1),(221,'2020-01-15','11:00:04','13:00:04',4,4,1,NULL,1),(222,'2020-01-22','11:00:11','13:00:11',4,4,1,NULL,1),(223,'2020-01-29','11:00:17','13:00:17',4,4,1,NULL,1),(224,'2020-02-05','11:00:22','13:00:22',4,4,1,NULL,1),(225,'2020-02-12','11:00:26','13:00:26',4,4,1,NULL,1),(226,'2020-02-19','11:00:45','13:00:45',4,4,1,NULL,1),(227,'2020-02-26','11:00:50','13:00:50',4,4,1,NULL,1),(228,'2020-03-04','11:00:59','12:15:59',4,4,1,NULL,1),(229,'2020-03-11','11:00:59','12:15:59',4,4,1,NULL,1),(230,'2020-03-18','11:00:00','12:15:00',4,4,1,NULL,1),(231,'2020-03-25','11:00:00','12:15:00',4,4,1,NULL,1),(232,'2020-04-01','11:00:00','12:15:00',4,4,1,NULL,1),(233,'2019-12-30','14:00:11','16:15:11',4,4,22,NULL,3),(234,'2019-12-31','08:00:32','11:00:32',2,38,14,NULL,3),(235,'2020-01-01','08:00:36','11:00:36',2,38,1,NULL,3),(236,'2020-01-01','12:00:03','14:00:03',4,4,1,NULL,3),(237,'2020-01-03','12:00:12','14:00:12',3,34,1,NULL,3),(238,'2020-01-03','08:00:21','11:00:21',2,18,1,NULL,3),(239,'2020-01-06','08:00:35','12:00:35',2,18,1,NULL,3),(240,'2020-01-13','08:00:36','12:00:36',2,18,1,NULL,3),(241,'2020-01-20','08:00:36','12:00:36',2,18,1,NULL,3),(242,'2020-01-27','08:00:36','12:00:36',2,18,1,NULL,3),(243,'2020-02-03','08:00:36','12:00:36',2,18,1,NULL,3),(244,'2020-02-10','08:00:36','12:00:36',2,18,1,NULL,3),(245,'2020-02-17','08:00:36','12:00:36',2,18,1,NULL,3),(246,'2020-02-24','08:00:36','12:00:36',2,18,1,NULL,3),(247,'2020-03-02','08:00:36','12:00:36',2,18,1,NULL,3),(248,'2020-03-09','08:00:36','12:00:36',2,18,1,NULL,3),(249,'2020-03-16','08:00:36','12:00:36',2,18,1,NULL,3),(250,'2020-03-23','08:00:36','12:00:36',2,18,1,NULL,3),(251,'2020-03-30','08:00:36','12:00:36',2,18,1,NULL,3),(252,'2020-04-06','08:00:37','12:00:37',2,18,1,NULL,3),(253,'2020-04-13','08:00:37','12:00:37',2,18,1,NULL,3),(254,'2020-04-20','08:00:37','12:00:37',2,18,1,NULL,3),(255,'2020-04-27','08:00:37','12:00:37',2,18,1,NULL,3),(256,'2020-05-04','08:00:37','12:00:37',2,18,1,NULL,3),(257,'2020-05-11','08:00:37','12:00:37',2,18,1,NULL,3),(258,'2020-05-18','08:00:37','12:00:37',2,18,1,NULL,3),(259,'2020-05-25','08:00:37','12:00:37',2,18,1,NULL,3),(260,'2020-06-01','08:00:37','12:00:37',2,18,1,NULL,3),(261,'2020-06-08','08:00:37','12:00:37',2,18,1,NULL,3),(262,'2020-06-15','08:00:37','12:00:37',2,18,1,NULL,3),(263,'2020-06-22','08:00:37','12:00:37',2,18,1,NULL,3),(264,'2020-06-29','08:00:37','12:00:37',2,18,1,NULL,3),(265,'2020-07-06','08:00:37','12:00:37',2,18,1,NULL,3),(266,'2020-07-13','08:00:37','12:00:37',2,18,1,NULL,3),(267,'2020-07-20','08:00:37','12:00:37',2,18,1,NULL,3),(268,'2020-07-27','08:00:37','12:00:37',2,18,1,NULL,3),(269,'2020-08-03','08:00:37','12:00:37',2,18,1,NULL,3),(270,'2020-08-10','08:00:37','12:00:37',2,18,1,NULL,3),(271,'2020-08-17','08:00:37','12:00:37',2,18,1,NULL,3),(272,'2020-08-24','08:00:37','12:00:37',2,18,1,NULL,3),(273,'2020-08-31','08:00:37','12:00:37',2,18,1,NULL,3),(274,'2020-01-07','08:00:05','10:00:05',2,18,18,NULL,3),(275,'2020-01-14','08:00:05','10:00:05',2,18,18,NULL,3),(276,'2020-01-21','08:00:06','10:00:06',2,18,18,NULL,3),(277,'2020-01-28','08:00:06','10:00:06',2,18,18,NULL,3),(278,'2020-02-04','08:00:06','10:00:06',2,18,18,NULL,3),(279,'2020-02-11','08:00:06','10:00:06',2,18,18,NULL,3),(280,'2020-02-18','08:00:06','10:00:06',2,18,18,NULL,3),(281,'2020-02-25','08:00:06','10:00:06',2,18,18,NULL,3),(282,'2020-03-03','08:00:06','10:00:06',2,18,18,NULL,3),(283,'2020-03-10','08:00:06','10:00:06',2,18,18,NULL,3),(284,'2020-03-17','08:00:06','10:00:06',2,18,18,NULL,3),(285,'2020-03-24','08:00:06','10:00:06',2,18,18,NULL,3),(286,'2020-03-31','08:00:06','10:00:06',2,18,18,NULL,3),(287,'2020-04-07','08:00:06','10:00:06',2,18,18,NULL,3),(288,'2020-04-14','08:00:06','10:00:06',2,18,18,NULL,3),(289,'2020-04-21','08:00:06','10:00:06',2,18,18,NULL,3),(290,'2020-04-28','08:00:06','10:00:06',2,18,18,NULL,3),(291,'2020-05-05','08:00:06','10:00:06',2,18,18,NULL,3),(292,'2020-05-12','08:00:06','10:00:06',2,18,18,NULL,3),(293,'2020-05-19','08:00:06','10:00:06',2,18,18,NULL,3),(294,'2020-05-26','08:00:06','10:00:06',2,18,18,NULL,3),(295,'2020-06-02','08:00:06','10:00:06',2,18,18,NULL,3),(296,'2020-06-09','08:00:06','10:00:06',2,18,18,NULL,3),(297,'2020-06-16','08:00:07','10:00:07',2,18,18,NULL,3),(298,'2020-06-23','08:00:07','10:00:07',2,18,18,NULL,3),(299,'2020-06-30','08:00:07','10:00:07',2,18,18,NULL,3),(300,'2020-07-07','08:00:07','10:00:07',2,18,18,NULL,3),(301,'2020-07-14','08:00:07','10:00:07',2,18,18,NULL,3),(302,'2020-07-21','08:00:07','10:00:07',2,18,18,NULL,3),(303,'2020-07-28','08:00:07','10:00:07',2,18,18,NULL,3),(304,'2020-08-04','08:00:07','10:00:07',2,18,18,NULL,3),(305,'2020-08-11','08:00:07','10:00:07',2,18,18,NULL,3),(306,'2020-08-18','08:00:07','10:00:07',2,18,18,NULL,3),(307,'2020-08-25','08:00:07','10:00:07',2,18,18,NULL,3),(308,'2020-09-01','08:00:07','10:00:07',2,18,18,NULL,3),(309,'2020-01-07','13:00:23','15:00:23',3,34,16,NULL,3),(310,'2020-01-14','13:00:23','15:00:23',3,34,16,NULL,3),(311,'2020-01-21','13:00:23','15:00:23',3,34,16,NULL,3),(312,'2020-01-28','13:00:24','15:00:24',3,34,16,NULL,3),(313,'2020-02-04','13:00:24','15:00:24',3,34,16,NULL,3),(314,'2020-02-11','13:00:24','15:00:24',3,34,16,NULL,3),(315,'2020-02-18','13:00:24','15:00:24',3,34,16,NULL,3),(316,'2020-02-25','13:00:24','15:00:24',3,34,16,NULL,3),(317,'2020-03-03','13:00:24','15:00:24',3,34,16,NULL,3),(318,'2020-03-10','13:00:24','15:00:24',3,34,16,NULL,3),(319,'2020-03-17','13:00:24','15:00:24',3,34,16,NULL,3),(320,'2020-03-24','13:00:24','15:00:24',3,34,16,NULL,3),(321,'2020-03-31','13:00:24','15:00:24',3,34,16,NULL,3),(322,'2020-04-07','13:00:24','15:00:24',3,34,16,NULL,3),(323,'2020-04-14','13:00:24','15:00:24',3,34,16,NULL,3),(324,'2020-04-21','13:00:24','15:00:24',3,34,16,NULL,3),(325,'2020-04-28','13:00:24','15:00:24',3,34,16,NULL,3),(326,'2020-05-05','13:00:24','15:00:24',3,34,16,NULL,3),(327,'2020-05-12','13:00:24','15:00:24',3,34,16,NULL,3),(328,'2020-05-19','13:00:25','15:00:25',3,34,16,NULL,3),(329,'2020-05-26','13:00:25','15:00:25',3,34,16,NULL,3),(330,'2020-06-02','13:00:25','15:00:25',3,34,16,NULL,3),(331,'2020-06-09','13:00:26','15:00:26',3,34,16,NULL,3),(332,'2020-06-16','13:00:26','15:00:26',3,34,16,NULL,3),(333,'2020-06-23','13:00:26','15:00:26',3,34,16,NULL,3),(334,'2020-06-30','13:00:26','15:00:26',3,34,16,NULL,3),(335,'2020-07-07','13:00:26','15:00:26',3,34,16,NULL,3),(336,'2020-07-14','13:00:26','15:00:26',3,34,16,NULL,3),(337,'2020-07-21','13:00:26','15:00:26',3,34,16,NULL,3),(338,'2020-07-28','13:00:26','15:00:26',3,34,16,NULL,3),(339,'2020-08-04','13:00:26','15:00:26',3,34,16,NULL,3),(340,'2020-08-11','13:00:26','15:00:26',3,34,16,NULL,3),(341,'2020-08-18','13:00:26','15:00:26',3,34,16,NULL,3),(342,'2020-08-25','13:00:26','15:00:26',3,34,16,NULL,3),(343,'2020-09-01','13:00:26','15:00:26',3,34,16,NULL,3),(344,'2020-01-08','09:00:37','11:00:37',4,4,1,NULL,3),(345,'2020-01-15','09:00:37','11:00:37',4,4,1,NULL,3),(346,'2020-01-22','09:00:37','11:00:37',4,4,1,NULL,3),(347,'2020-01-29','09:00:38','11:00:38',4,4,1,NULL,3),(348,'2020-02-05','09:00:38','11:00:38',4,4,1,NULL,3),(349,'2020-02-12','09:00:38','11:00:38',4,4,1,NULL,3),(350,'2020-02-19','09:00:38','11:00:38',4,4,1,NULL,3),(351,'2020-02-26','09:00:38','11:00:38',4,4,1,NULL,3),(352,'2020-03-04','09:00:38','11:00:38',4,4,1,NULL,3),(353,'2020-03-11','09:00:38','11:00:38',4,4,1,NULL,3),(354,'2020-03-18','09:00:39','11:00:39',4,4,1,NULL,3),(355,'2020-03-25','09:00:39','11:00:39',4,4,1,NULL,3),(356,'2020-04-01','09:00:39','11:00:39',4,4,1,NULL,3),(357,'2020-04-08','09:00:39','11:00:39',4,4,1,NULL,3),(358,'2020-04-15','09:00:39','11:00:39',4,4,1,NULL,3),(359,'2020-04-22','09:00:39','11:00:39',4,4,1,NULL,3),(360,'2020-04-29','09:00:39','11:00:39',4,4,1,NULL,3),(361,'2020-05-06','09:00:39','11:00:39',4,4,1,NULL,3),(362,'2020-05-13','09:00:39','11:00:39',4,4,1,NULL,3),(363,'2020-05-20','09:00:39','11:00:39',4,4,1,NULL,3),(364,'2020-05-27','09:00:39','11:00:39',4,4,1,NULL,3),(365,'2020-06-03','09:00:39','11:00:39',4,4,1,NULL,3),(366,'2020-06-10','09:00:39','11:00:39',4,4,1,NULL,3),(367,'2020-06-17','09:00:39','11:00:39',4,4,1,NULL,3),(368,'2020-06-24','09:00:39','11:00:39',4,4,1,NULL,3),(369,'2020-07-01','09:00:39','11:00:39',4,4,1,NULL,3),(370,'2020-07-08','09:00:39','11:00:39',4,4,1,NULL,3),(371,'2020-07-15','09:00:39','11:00:39',4,4,1,NULL,3),(372,'2020-07-22','09:00:40','11:00:40',4,4,1,NULL,3),(373,'2020-07-29','09:00:40','11:00:40',4,4,1,NULL,3),(374,'2020-08-05','09:00:40','11:00:40',4,4,1,NULL,3),(375,'2020-08-12','09:00:40','11:00:40',4,4,1,NULL,3),(376,'2020-08-19','09:00:40','11:00:40',4,4,1,NULL,3),(377,'2020-08-26','09:00:40','11:00:40',4,4,1,NULL,3),(378,'2020-09-02','09:00:40','11:00:40',4,4,1,NULL,3),(379,'2020-01-09','12:00:03','15:00:03',1,39,17,NULL,3),(380,'2020-01-16','12:00:03','15:00:03',1,39,17,NULL,3),(381,'2020-01-23','12:00:03','15:00:03',1,39,17,NULL,3),(382,'2020-01-30','12:00:03','15:00:03',1,39,17,NULL,3),(383,'2020-02-06','12:00:03','15:00:03',1,39,17,NULL,3),(384,'2020-02-13','12:00:03','15:00:03',1,39,17,NULL,3),(385,'2020-02-20','12:00:03','15:00:03',1,39,17,NULL,3),(386,'2020-02-27','12:00:03','15:00:03',1,39,17,NULL,3),(387,'2020-03-05','12:00:03','15:00:03',1,39,17,NULL,3),(388,'2020-03-12','12:00:03','15:00:03',1,39,17,NULL,3),(389,'2020-03-19','12:00:03','15:00:03',1,39,17,NULL,3),(390,'2020-03-26','12:00:03','15:00:03',1,39,17,NULL,3),(391,'2020-04-02','12:00:03','15:00:03',1,39,17,NULL,3),(392,'2020-04-09','12:00:03','15:00:03',1,39,17,NULL,3),(393,'2020-04-16','12:00:03','15:00:03',1,39,17,NULL,3),(394,'2020-04-23','12:00:03','15:00:03',1,39,17,NULL,3),(395,'2020-04-30','12:00:03','15:00:03',1,39,17,NULL,3),(396,'2020-05-07','12:00:03','15:00:03',1,39,17,NULL,3),(397,'2020-05-14','12:00:04','15:00:04',1,39,17,NULL,3),(398,'2020-05-21','12:00:04','15:00:04',1,39,17,NULL,3),(399,'2020-05-28','12:00:04','15:00:04',1,39,17,NULL,3),(400,'2020-06-04','12:00:04','15:00:04',1,39,17,NULL,3),(401,'2020-06-11','12:00:04','15:00:04',1,39,17,NULL,3),(402,'2020-06-18','12:00:04','15:00:04',1,39,17,NULL,3),(403,'2020-06-25','12:00:04','15:00:04',1,39,17,NULL,3),(404,'2020-07-02','12:00:04','15:00:04',1,39,17,NULL,3),(405,'2020-07-09','12:00:04','15:00:04',1,39,17,NULL,3),(406,'2020-07-16','12:00:04','15:00:04',1,39,17,NULL,3),(407,'2020-07-23','12:00:04','15:00:04',1,39,17,NULL,3),(408,'2020-07-30','12:00:04','15:00:04',1,39,17,NULL,3),(409,'2020-08-06','12:00:04','15:00:04',1,39,17,NULL,3),(410,'2020-08-13','12:00:04','15:00:04',1,39,17,NULL,3),(411,'2020-08-20','12:00:04','15:00:04',1,39,17,NULL,3),(412,'2020-08-27','12:00:04','15:00:04',1,39,17,NULL,3),(413,'2020-09-03','12:00:04','15:00:04',1,39,17,NULL,3),(414,'2020-01-09','16:00:18','19:00:18',2,18,1,NULL,3),(415,'2020-01-16','16:00:18','19:00:18',2,18,1,NULL,3),(416,'2020-01-23','16:00:18','19:00:18',2,18,1,NULL,3),(417,'2020-01-30','16:00:18','19:00:18',2,18,1,NULL,3),(418,'2020-02-06','16:00:18','19:00:18',2,18,1,NULL,3),(419,'2020-02-13','16:00:18','19:00:18',2,18,1,NULL,3),(420,'2020-02-20','16:00:18','19:00:18',2,18,1,NULL,3),(421,'2020-02-27','16:00:18','19:00:18',2,18,1,NULL,3),(422,'2020-03-05','16:00:18','19:00:18',2,18,1,NULL,3),(423,'2020-03-12','16:00:19','19:00:19',2,18,1,NULL,3),(424,'2020-03-19','16:00:19','19:00:19',2,18,1,NULL,3),(425,'2020-03-26','16:00:19','19:00:19',2,18,1,NULL,3),(426,'2020-04-02','16:00:19','19:00:19',2,18,1,NULL,3),(427,'2020-04-09','16:00:19','19:00:19',2,18,1,NULL,3),(428,'2020-04-16','16:00:19','19:00:19',2,18,1,NULL,3),(429,'2020-04-23','16:00:19','19:00:19',2,18,1,NULL,3),(430,'2020-04-30','16:00:19','19:00:19',2,18,1,NULL,3),(431,'2020-05-07','16:00:19','19:00:19',2,18,1,NULL,3),(432,'2020-05-14','16:00:19','19:00:19',2,18,1,NULL,3),(433,'2020-05-21','16:00:19','19:00:19',2,18,1,NULL,3),(434,'2020-05-28','16:00:19','19:00:19',2,18,1,NULL,3),(435,'2020-06-04','16:00:19','19:00:19',2,18,1,NULL,3),(436,'2020-06-11','16:00:19','19:00:19',2,18,1,NULL,3),(437,'2020-06-18','16:00:19','19:00:19',2,18,1,NULL,3),(438,'2020-06-25','16:00:19','19:00:19',2,18,1,NULL,3),(439,'2020-07-02','16:00:19','19:00:19',2,18,1,NULL,3),(440,'2020-07-09','16:00:19','19:00:19',2,18,1,NULL,3),(441,'2020-07-16','16:00:20','19:00:20',2,18,1,NULL,3),(442,'2020-07-23','16:00:20','19:00:20',2,18,1,NULL,3),(443,'2020-07-30','16:00:20','19:00:20',2,18,1,NULL,3),(444,'2020-08-06','16:00:20','19:00:20',2,18,1,NULL,3),(445,'2020-08-13','16:00:20','19:00:20',2,18,1,NULL,3),(446,'2020-08-20','16:00:20','19:00:20',2,18,1,NULL,3),(447,'2020-08-27','16:00:20','19:00:20',2,18,1,NULL,3),(448,'2020-09-03','16:00:20','19:00:20',2,18,1,NULL,3),(449,'2019-12-30','16:00:59','19:00:59',5,42,1,NULL,2),(450,'2020-01-06','16:00:59','19:00:59',5,42,1,NULL,2),(451,'2020-01-13','16:00:59','19:00:59',5,42,1,NULL,2),(452,'2020-01-20','16:00:59','19:00:59',5,42,1,NULL,2),(453,'2020-01-27','16:00:59','19:00:59',5,42,1,NULL,2),(454,'2020-02-03','16:00:59','19:00:59',5,42,1,NULL,2),(455,'2020-02-10','16:00:59','19:00:59',5,42,1,NULL,2),(456,'2020-02-17','16:00:59','19:00:59',5,42,1,NULL,2),(457,'2020-02-24','16:00:00','19:00:00',5,42,1,NULL,2),(458,'2020-03-02','16:00:01','19:00:01',5,42,1,NULL,2),(459,'2020-03-09','16:00:01','19:00:01',5,42,1,NULL,2),(460,'2020-03-16','16:00:01','19:00:01',5,42,1,NULL,2),(461,'2020-03-23','16:00:01','19:00:01',5,42,1,NULL,2),(462,'2020-03-30','16:00:01','19:00:01',5,42,1,NULL,2),(463,'2020-04-06','16:00:01','19:00:01',5,42,1,NULL,2),(464,'2020-04-13','16:00:01','19:00:01',5,42,1,NULL,2),(465,'2020-04-20','16:00:01','19:00:01',5,42,1,NULL,2),(466,'2020-04-27','16:00:01','19:00:01',5,42,1,NULL,2),(467,'2020-05-04','16:00:01','19:00:01',5,42,1,NULL,2),(468,'2020-05-11','16:00:01','19:00:01',5,42,1,NULL,2),(469,'2020-05-18','16:00:01','19:00:01',5,42,1,NULL,2),(470,'2020-05-25','16:00:01','19:00:01',5,42,1,NULL,2),(471,'2020-06-01','16:00:01','19:00:01',5,42,1,NULL,2),(472,'2020-06-08','16:00:01','19:00:01',5,42,1,NULL,2),(473,'2020-06-15','16:00:01','19:00:01',5,42,1,NULL,2),(474,'2020-06-22','16:00:01','19:00:01',5,42,1,NULL,2),(475,'2020-06-29','16:00:01','19:00:01',5,42,1,NULL,2),(476,'2020-07-06','16:00:01','19:00:01',5,42,1,NULL,2),(477,'2020-07-13','16:00:01','19:00:01',5,42,1,NULL,2),(478,'2020-07-20','16:00:01','19:00:01',5,42,1,NULL,2),(479,'2020-07-27','16:00:01','19:00:01',5,42,1,NULL,2),(480,'2020-08-03','16:00:01','19:00:01',5,42,1,NULL,2),(481,'2020-08-10','16:00:01','19:00:01',5,42,1,NULL,2),(482,'2020-08-17','16:00:01','19:00:01',5,42,1,NULL,2),(483,'2020-08-24','16:00:01','19:00:01',5,42,1,NULL,2),(484,'2020-08-31','16:00:02','19:00:02',5,42,1,NULL,2),(485,'2019-12-31','08:00:20','10:00:20',7,42,1,NULL,2),(486,'2020-01-07','08:00:20','10:00:20',7,42,1,NULL,2),(487,'2020-01-14','08:00:20','10:00:20',7,42,1,NULL,2),(488,'2020-01-21','08:00:20','10:00:20',7,42,1,NULL,2),(489,'2020-01-28','08:00:20','10:00:20',7,42,1,NULL,2),(490,'2020-02-04','08:00:20','10:00:20',7,42,1,NULL,2),(491,'2020-02-11','08:00:21','10:00:21',7,42,1,NULL,2),(492,'2020-02-18','08:00:22','10:00:22',7,42,1,NULL,2),(493,'2020-02-25','08:00:22','10:00:22',7,42,1,NULL,2),(494,'2020-03-03','08:00:22','10:00:22',7,42,1,NULL,2),(495,'2020-03-10','08:00:22','10:00:22',7,42,1,NULL,2),(496,'2020-03-17','08:00:22','10:00:22',7,42,1,NULL,2),(497,'2020-03-24','08:00:22','10:00:22',7,42,1,NULL,2),(498,'2020-03-31','08:00:22','10:00:22',7,42,1,NULL,2),(499,'2020-04-07','08:00:22','10:00:22',7,42,1,NULL,2),(500,'2020-04-14','08:00:22','10:00:22',7,42,1,NULL,2),(501,'2020-04-21','08:00:22','10:00:22',7,42,1,NULL,2),(502,'2020-04-28','08:00:22','10:00:22',7,42,1,NULL,2),(503,'2020-05-05','08:00:22','10:00:22',7,42,1,NULL,2),(504,'2020-05-12','08:00:22','10:00:22',7,42,1,NULL,2),(505,'2020-05-19','08:00:22','10:00:22',7,42,1,NULL,2),(506,'2020-05-26','08:00:22','10:00:22',7,42,1,NULL,2),(507,'2020-06-02','08:00:22','10:00:22',7,42,1,NULL,2),(508,'2020-06-09','08:00:23','10:00:23',7,42,1,NULL,2),(509,'2020-06-16','08:00:23','10:00:23',7,42,1,NULL,2),(510,'2020-06-23','08:00:23','10:00:23',7,42,1,NULL,2),(511,'2020-06-30','08:00:23','10:00:23',7,42,1,NULL,2),(512,'2020-07-07','08:00:23','10:00:23',7,42,1,NULL,2),(513,'2020-07-14','08:00:24','10:00:24',7,42,1,NULL,2),(514,'2020-07-21','08:00:24','10:00:24',7,42,1,NULL,2),(515,'2020-07-28','08:00:24','10:00:24',7,42,1,NULL,2),(516,'2020-08-04','08:00:24','10:00:24',7,42,1,NULL,2),(517,'2020-08-11','08:00:24','10:00:24',7,42,1,NULL,2),(518,'2020-08-18','08:00:24','10:00:24',7,42,1,NULL,2),(519,'2020-08-25','08:00:24','10:00:24',7,42,1,NULL,2),(520,'2020-09-01','08:00:24','10:00:24',7,42,1,NULL,2),(521,'2020-01-01','08:00:26','10:00:26',7,42,1,NULL,2),(522,'2020-01-08','08:00:26','10:00:26',7,42,1,NULL,2),(523,'2020-01-15','08:00:26','10:00:26',7,42,1,NULL,2),(524,'2020-01-22','08:00:27','10:00:27',7,42,1,NULL,2),(525,'2020-01-29','08:00:27','10:00:27',7,42,1,NULL,2),(526,'2020-02-05','08:00:27','10:00:27',7,42,1,NULL,2),(527,'2020-02-12','08:00:27','10:00:27',7,42,1,NULL,2),(528,'2020-02-19','08:00:27','10:00:27',7,42,1,NULL,2),(529,'2020-02-26','08:00:27','10:00:27',7,42,1,NULL,2),(530,'2020-03-04','08:00:27','10:00:27',7,42,1,NULL,2),(531,'2020-03-11','08:00:27','10:00:27',7,42,1,NULL,2),(532,'2020-03-18','08:00:27','10:00:27',7,42,1,NULL,2),(533,'2020-03-25','08:00:27','10:00:27',7,42,1,NULL,2),(534,'2020-04-01','08:00:27','10:00:27',7,42,1,NULL,2),(535,'2020-04-08','08:00:27','10:00:27',7,42,1,NULL,2),(536,'2020-04-15','08:00:27','10:00:27',7,42,1,NULL,2),(537,'2020-04-22','08:00:27','10:00:27',7,42,1,NULL,2),(538,'2020-04-29','08:00:27','10:00:27',7,42,1,NULL,2),(539,'2020-05-06','08:00:27','10:00:27',7,42,1,NULL,2),(540,'2020-05-13','08:00:27','10:00:27',7,42,1,NULL,2),(541,'2020-05-20','08:00:28','10:00:28',7,42,1,NULL,2),(542,'2020-05-27','08:00:28','10:00:28',7,42,1,NULL,2),(543,'2020-06-03','08:00:28','10:00:28',7,42,1,NULL,2),(544,'2020-06-10','08:00:28','10:00:28',7,42,1,NULL,2),(545,'2020-06-17','08:00:28','10:00:28',7,42,1,NULL,2),(546,'2020-06-24','08:00:28','10:00:28',7,42,1,NULL,2),(547,'2020-07-01','08:00:28','10:00:28',7,42,1,NULL,2),(548,'2020-07-08','08:00:28','10:00:28',7,42,1,NULL,2),(549,'2020-07-15','08:00:29','10:00:29',7,42,1,NULL,2),(550,'2020-07-22','08:00:29','10:00:29',7,42,1,NULL,2),(551,'2020-07-29','08:00:29','10:00:29',7,42,1,NULL,2),(552,'2020-08-05','08:00:29','10:00:29',7,42,1,NULL,2),(553,'2020-08-12','08:00:29','10:00:29',7,42,1,NULL,2),(554,'2020-08-19','08:00:29','10:00:29',7,42,1,NULL,2),(555,'2020-08-26','08:00:29','10:00:29',7,42,1,NULL,2),(556,'2020-09-02','08:00:29','10:00:29',7,42,1,NULL,2),(557,'2020-01-02','08:00:37','12:00:37',13,46,1,NULL,2),(558,'2020-01-09','08:00:37','12:00:37',13,46,1,NULL,2),(559,'2020-01-16','08:00:37','12:00:37',13,46,1,NULL,2),(560,'2020-01-23','08:00:37','12:00:37',13,46,1,NULL,2),(561,'2020-01-30','08:00:37','12:00:37',13,46,1,NULL,2),(562,'2020-02-06','08:00:37','12:00:37',13,46,1,NULL,2),(563,'2020-02-13','08:00:38','12:00:38',13,46,1,NULL,2),(564,'2020-02-20','08:00:38','12:00:38',13,46,1,NULL,2),(565,'2020-02-27','08:00:38','12:00:38',13,46,1,NULL,2),(566,'2020-03-05','08:00:38','12:00:38',13,46,1,NULL,2),(567,'2020-03-12','08:00:38','12:00:38',13,46,1,NULL,2),(568,'2020-03-19','08:00:38','12:00:38',13,46,1,NULL,2),(569,'2020-03-26','08:00:38','12:00:38',13,46,1,NULL,2),(570,'2020-04-02','08:00:38','12:00:38',13,46,1,NULL,2),(571,'2020-04-09','08:00:38','12:00:38',13,46,1,NULL,2),(572,'2020-04-16','08:00:38','12:00:38',13,46,1,NULL,2),(573,'2020-04-23','08:00:38','12:00:38',13,46,1,NULL,2),(574,'2020-04-30','08:00:38','12:00:38',13,46,1,NULL,2),(575,'2020-05-07','08:00:38','12:00:38',13,46,1,NULL,2),(576,'2020-05-14','08:00:38','12:00:38',13,46,1,NULL,2),(577,'2020-05-21','08:00:38','12:00:38',13,46,1,NULL,2),(578,'2020-05-28','08:00:38','12:00:38',13,46,1,NULL,2),(579,'2020-06-04','08:00:38','12:00:38',13,46,1,NULL,2),(580,'2020-06-11','08:00:38','12:00:38',13,46,1,NULL,2),(581,'2020-06-18','08:00:38','12:00:38',13,46,1,NULL,2),(582,'2020-06-25','08:00:38','12:00:38',13,46,1,NULL,2),(583,'2020-07-02','08:00:39','12:00:39',13,46,1,NULL,2),(584,'2020-07-09','08:00:39','12:00:39',13,46,1,NULL,2),(585,'2020-07-16','08:00:39','12:00:39',13,46,1,NULL,2),(586,'2020-07-23','08:00:40','12:00:40',13,46,1,NULL,2),(587,'2020-07-30','08:00:40','12:00:40',13,46,1,NULL,2),(588,'2020-08-06','08:00:40','12:00:40',13,46,1,NULL,2),(589,'2020-08-13','08:00:40','12:00:40',13,46,1,NULL,2),(590,'2020-08-20','08:00:40','12:00:40',13,46,1,NULL,2),(591,'2020-08-27','08:00:40','12:00:40',13,46,1,NULL,2),(592,'2020-09-03','08:00:40','12:00:40',13,46,1,NULL,2),(593,'2020-01-02','13:00:56','15:00:56',4,4,1,NULL,2),(594,'2020-01-09','13:00:56','15:00:56',4,4,1,NULL,2),(595,'2020-01-16','13:00:56','15:00:56',4,4,1,NULL,2),(596,'2020-01-23','13:00:56','15:00:56',4,4,1,NULL,2),(597,'2020-01-30','13:00:56','15:00:56',4,4,1,NULL,2),(598,'2020-02-06','13:00:56','15:00:56',4,4,1,NULL,2),(599,'2020-02-13','13:00:56','15:00:56',4,4,1,NULL,2),(600,'2020-02-20','13:00:56','15:00:56',4,4,1,NULL,2),(601,'2020-02-27','13:00:56','15:00:56',4,4,1,NULL,2),(602,'2020-03-05','13:00:56','15:00:56',4,4,1,NULL,2),(603,'2020-03-12','13:00:56','15:00:56',4,4,1,NULL,2),(604,'2020-03-19','13:00:56','15:00:56',4,4,1,NULL,2),(605,'2020-03-26','13:00:56','15:00:56',4,4,1,NULL,2),(606,'2020-04-02','13:00:56','15:00:56',4,4,1,NULL,2),(607,'2020-04-09','13:00:57','15:00:57',4,4,1,NULL,2),(608,'2020-04-16','13:00:57','15:00:57',4,4,1,NULL,2),(609,'2020-04-23','13:00:57','15:00:57',4,4,1,NULL,2),(610,'2020-04-30','13:00:57','15:00:57',4,4,1,NULL,2),(611,'2020-05-07','13:00:57','15:00:57',4,4,1,NULL,2),(612,'2020-05-14','13:00:57','15:00:57',4,4,1,NULL,2),(613,'2020-05-21','13:00:57','15:00:57',4,4,1,NULL,2),(614,'2020-05-28','13:00:57','15:00:57',4,4,1,NULL,2),(615,'2020-06-04','13:00:57','15:00:57',4,4,1,NULL,2),(616,'2020-06-11','13:00:57','15:00:57',4,4,1,NULL,2),(617,'2020-06-18','13:00:57','15:00:57',4,4,1,NULL,2),(618,'2020-06-25','13:00:57','15:00:57',4,4,1,NULL,2),(619,'2020-07-02','13:00:57','15:00:57',4,4,1,NULL,2),(620,'2020-07-09','13:00:57','15:00:57',4,4,1,NULL,2),(621,'2020-07-16','13:00:57','15:00:57',4,4,1,NULL,2),(622,'2020-07-23','13:00:57','15:00:57',4,4,1,NULL,2),(623,'2020-07-30','13:00:57','15:00:57',4,4,1,NULL,2),(624,'2020-08-06','13:00:57','15:00:57',4,4,1,NULL,2),(625,'2020-08-13','13:00:57','15:00:57',4,4,1,NULL,2),(626,'2020-08-20','13:00:58','15:00:58',4,4,1,NULL,2),(627,'2020-08-27','13:00:59','15:00:59',4,4,1,NULL,2),(628,'2020-09-03','13:00:59','15:00:59',4,4,1,NULL,2),(629,'2020-01-07','13:00:11','15:00:11',4,4,1,NULL,2),(630,'2020-01-14','13:00:11','15:00:11',4,4,1,NULL,2),(631,'2020-01-21','13:00:11','15:00:11',4,4,1,NULL,2),(632,'2020-01-28','13:00:11','15:00:11',4,4,1,NULL,2),(633,'2020-02-04','13:00:11','15:00:11',4,4,1,NULL,2),(634,'2020-02-11','13:00:11','15:00:11',4,4,1,NULL,2),(635,'2020-02-18','13:00:11','15:00:11',4,4,1,NULL,2),(636,'2020-02-25','13:00:11','15:00:11',4,4,1,NULL,2),(637,'2020-03-03','13:00:11','15:00:11',4,4,1,NULL,2),(638,'2020-03-10','13:00:11','15:00:11',4,4,1,NULL,2),(639,'2020-03-17','13:00:11','15:00:11',4,4,1,NULL,2),(640,'2020-03-24','13:00:11','15:00:11',4,4,1,NULL,2),(641,'2020-03-31','13:00:11','15:00:11',4,4,1,NULL,2),(642,'2020-04-07','13:00:11','15:00:11',4,4,1,NULL,2),(643,'2020-04-14','13:00:12','15:00:12',4,4,1,NULL,2),(644,'2020-04-21','13:00:12','15:00:12',4,4,1,NULL,2),(645,'2020-04-28','13:00:12','15:00:12',4,4,1,NULL,2),(646,'2020-05-05','13:00:12','15:00:12',4,4,1,NULL,2),(647,'2020-05-12','13:00:12','15:00:12',4,4,1,NULL,2),(648,'2020-05-19','13:00:12','15:00:12',4,4,1,NULL,2),(649,'2020-05-26','13:00:12','15:00:12',4,4,1,NULL,2),(650,'2020-06-02','13:00:12','15:00:12',4,4,1,NULL,2),(651,'2020-06-09','13:00:12','15:00:12',4,4,1,NULL,2),(652,'2020-06-16','13:00:12','15:00:12',4,4,1,NULL,2),(653,'2020-06-23','13:00:12','15:00:12',4,4,1,NULL,2),(654,'2020-06-30','13:00:12','15:00:12',4,4,1,NULL,2),(655,'2020-07-07','13:00:12','15:00:12',4,4,1,NULL,2),(656,'2020-07-14','13:00:12','15:00:12',4,4,1,NULL,2),(657,'2020-07-21','13:00:12','15:00:12',4,4,1,NULL,2),(658,'2020-07-28','13:00:12','15:00:12',4,4,1,NULL,2),(659,'2020-08-04','13:00:12','15:00:12',4,4,1,NULL,2),(660,'2020-08-11','13:00:13','15:00:13',4,4,1,NULL,2),(661,'2020-08-18','13:00:13','15:00:13',4,4,1,NULL,2),(662,'2020-08-25','13:00:13','15:00:13',4,4,1,NULL,2),(663,'2020-09-01','13:00:13','15:00:13',4,4,1,NULL,2),(811,'2019-12-30','09:00:36','11:00:36',1,47,14,NULL,4),(812,'2020-01-06','09:00:37','11:00:37',1,47,14,NULL,4),(813,'2020-01-13','09:00:37','11:00:37',1,47,14,NULL,4),(814,'2020-01-20','09:00:37','11:00:37',1,47,14,NULL,4),(815,'2020-01-27','09:00:37','11:00:37',1,47,14,NULL,4),(816,'2020-02-03','09:00:38','11:00:38',1,47,14,NULL,4),(817,'2020-02-10','09:00:38','11:00:38',1,47,14,NULL,4),(818,'2020-02-17','09:00:38','11:00:38',1,47,14,NULL,4),(819,'2020-02-24','09:00:38','11:00:38',1,47,14,NULL,4),(820,'2020-03-02','09:00:38','11:00:38',1,47,14,NULL,4),(821,'2020-03-09','09:00:38','11:00:38',1,47,14,NULL,4),(822,'2020-03-16','09:00:38','11:00:38',1,47,14,NULL,4),(823,'2020-03-23','09:00:38','11:00:38',1,47,14,NULL,4),(824,'2020-03-30','09:00:38','11:00:38',1,47,14,NULL,4),(825,'2020-04-06','09:00:38','11:00:38',1,47,14,NULL,4),(826,'2020-04-13','09:00:38','11:00:38',1,47,14,NULL,4),(827,'2020-04-20','09:00:38','11:00:38',1,47,14,NULL,4),(828,'2020-04-27','09:00:38','11:00:38',1,47,14,NULL,4),(829,'2020-05-04','09:00:38','11:00:38',1,47,14,NULL,4),(830,'2020-05-11','09:00:38','11:00:38',1,47,14,NULL,4),(831,'2020-05-18','09:00:39','11:00:39',1,47,14,NULL,4),(832,'2020-05-25','09:00:39','11:00:39',1,47,14,NULL,4),(833,'2020-06-01','09:00:39','11:00:39',1,47,14,NULL,4),(834,'2020-06-08','09:00:39','11:00:39',1,47,14,NULL,4),(835,'2020-06-15','09:00:39','11:00:39',1,47,14,NULL,4),(836,'2020-06-22','09:00:39','11:00:39',1,47,14,NULL,4),(837,'2020-06-29','09:00:39','11:00:39',1,47,14,NULL,4),(838,'2020-07-06','09:00:39','11:00:39',1,47,14,NULL,4),(839,'2020-07-13','09:00:39','11:00:39',1,47,14,NULL,4),(840,'2020-07-20','09:00:39','11:00:39',1,47,14,NULL,4),(841,'2020-07-27','09:00:39','11:00:39',1,47,14,NULL,4),(842,'2020-08-03','09:00:39','11:00:39',1,47,14,NULL,4),(843,'2020-08-10','09:00:39','11:00:39',1,47,14,NULL,4),(844,'2020-08-17','09:00:39','11:00:39',1,47,14,NULL,4),(845,'2020-08-24','09:00:39','11:00:39',1,47,14,NULL,4),(846,'2020-08-31','09:00:39','11:00:39',1,47,14,NULL,4),(847,'2019-12-30','14:00:56','17:00:56',3,34,19,NULL,4),(848,'2020-01-06','14:00:56','17:00:56',3,34,19,NULL,4),(849,'2020-01-13','14:00:57','17:00:57',3,34,19,NULL,4),(850,'2020-01-20','14:00:57','17:00:57',3,34,19,NULL,4),(851,'2020-01-27','14:00:57','17:00:57',3,34,19,NULL,4),(852,'2020-02-03','14:00:57','17:00:57',3,34,19,NULL,4),(853,'2020-02-10','14:00:57','17:00:57',3,34,19,NULL,4),(854,'2020-02-17','14:00:57','17:00:57',3,34,19,NULL,4),(855,'2020-02-24','14:00:57','17:00:57',3,34,19,NULL,4),(856,'2020-03-02','14:00:57','17:00:57',3,34,19,NULL,4),(857,'2020-03-09','14:00:57','17:00:57',3,34,19,NULL,4),(858,'2020-03-16','14:00:57','17:00:57',3,34,19,NULL,4),(859,'2020-03-23','14:00:57','17:00:57',3,34,19,NULL,4),(860,'2020-03-30','14:00:57','17:00:57',3,34,19,NULL,4),(861,'2020-04-06','14:00:57','17:00:57',3,34,19,NULL,4),(862,'2020-04-13','14:00:58','17:00:58',3,34,19,NULL,4),(863,'2020-04-20','14:00:58','17:00:58',3,34,19,NULL,4),(864,'2020-04-27','14:00:58','17:00:58',3,34,19,NULL,4),(865,'2020-05-04','14:00:58','17:00:58',3,34,19,NULL,4),(866,'2020-05-11','14:00:59','17:00:59',3,34,19,NULL,4),(867,'2020-05-18','14:00:59','17:00:59',3,34,19,NULL,4),(868,'2020-05-25','14:00:59','17:00:59',3,34,19,NULL,4),(869,'2020-06-01','14:00:59','17:00:59',3,34,19,NULL,4),(870,'2020-06-08','14:00:59','17:00:59',3,34,19,NULL,4),(871,'2020-06-15','14:00:59','17:00:59',3,34,19,NULL,4),(872,'2020-06-22','14:00:59','17:00:59',3,34,19,NULL,4),(873,'2020-06-29','14:00:00','17:00:00',3,34,19,NULL,4),(874,'2020-07-06','14:00:00','17:00:00',3,34,19,NULL,4),(875,'2020-07-13','14:00:00','17:00:00',3,34,19,NULL,4),(876,'2020-07-20','14:00:00','17:00:00',3,34,19,NULL,4),(877,'2020-07-27','14:00:00','17:00:00',3,34,19,NULL,4),(878,'2020-08-03','14:00:00','17:00:00',3,34,19,NULL,4),(879,'2020-08-10','14:00:00','17:00:00',3,34,19,NULL,4),(880,'2020-08-17','14:00:00','17:00:00',3,34,19,NULL,4),(881,'2020-08-24','14:00:00','17:00:00',3,34,19,NULL,4),(882,'2020-08-31','14:00:00','17:00:00',3,34,19,NULL,4),(883,'2019-12-31','14:00:10','17:00:10',3,34,18,NULL,4),(884,'2020-01-07','14:00:10','17:00:10',3,34,18,NULL,4),(885,'2020-01-14','14:00:10','17:00:10',3,34,18,NULL,4),(886,'2020-01-21','14:00:11','17:00:11',3,34,18,NULL,4),(887,'2020-01-28','14:00:11','17:00:11',3,34,18,NULL,4),(888,'2020-02-04','14:00:11','17:00:11',3,34,18,NULL,4),(889,'2020-02-11','14:00:11','17:00:11',3,34,18,NULL,4),(890,'2020-02-18','14:00:11','17:00:11',3,34,18,NULL,4),(891,'2020-02-25','14:00:11','17:00:11',3,34,18,NULL,4),(892,'2020-03-03','14:00:11','17:00:11',3,34,18,NULL,4),(893,'2020-03-10','14:00:12','17:00:12',3,34,18,NULL,4),(894,'2020-03-17','14:00:12','17:00:12',3,34,18,NULL,4),(895,'2020-03-24','14:00:12','17:00:12',3,34,18,NULL,4),(896,'2020-03-31','14:00:12','17:00:12',3,34,18,NULL,4),(897,'2020-04-07','14:00:12','17:00:12',3,34,18,NULL,4),(898,'2020-04-14','14:00:12','17:00:12',3,34,18,NULL,4),(899,'2020-04-21','14:00:12','17:00:12',3,34,18,NULL,4),(900,'2020-04-28','14:00:12','17:00:12',3,34,18,NULL,4),(901,'2020-05-05','14:00:12','17:00:12',3,34,18,NULL,4),(902,'2020-05-12','14:00:12','17:00:12',3,34,18,NULL,4),(903,'2020-05-19','14:00:12','17:00:12',3,34,18,NULL,4),(904,'2020-05-26','14:00:12','17:00:12',3,34,18,NULL,4),(905,'2020-06-02','14:00:12','17:00:12',3,34,18,NULL,4),(906,'2020-06-09','14:00:12','17:00:12',3,34,18,NULL,4),(907,'2020-06-16','14:00:12','17:00:12',3,34,18,NULL,4),(908,'2020-06-23','14:00:12','17:00:12',3,34,18,NULL,4),(909,'2020-06-30','14:00:12','17:00:12',3,34,18,NULL,4),(910,'2020-07-07','14:00:12','17:00:12',3,34,18,NULL,4),(911,'2020-07-14','14:00:13','17:00:13',3,34,18,NULL,4),(912,'2020-07-21','14:00:13','17:00:13',3,34,18,NULL,4),(913,'2020-07-28','14:00:14','17:00:14',3,34,18,NULL,4),(914,'2020-08-04','14:00:14','17:00:14',3,34,18,NULL,4),(915,'2020-08-11','14:00:14','17:00:14',3,34,18,NULL,4),(916,'2020-08-18','14:00:14','17:00:14',3,34,18,NULL,4),(917,'2020-08-25','14:00:14','17:00:14',3,34,18,NULL,4),(918,'2020-09-01','14:00:14','17:00:14',3,34,18,NULL,4),(919,'2020-01-01','08:00:32','11:00:32',2,37,17,NULL,4),(920,'2020-01-08','08:00:32','11:00:32',2,37,17,NULL,4),(921,'2020-01-15','08:00:32','11:00:32',2,37,17,NULL,4),(922,'2020-01-22','08:00:32','11:00:32',2,37,17,NULL,4),(923,'2020-01-29','08:00:32','11:00:32',2,37,17,NULL,4),(924,'2020-02-05','08:00:32','11:00:32',2,37,17,NULL,4),(925,'2020-02-12','08:00:32','11:00:32',2,37,17,NULL,4),(926,'2020-02-19','08:00:32','11:00:32',2,37,17,NULL,4),(927,'2020-02-26','08:00:32','11:00:32',2,37,17,NULL,4),(928,'2020-03-04','08:00:32','11:00:32',2,37,17,NULL,4),(929,'2020-03-11','08:00:32','11:00:32',2,37,17,NULL,4),(930,'2020-03-18','08:00:32','11:00:32',2,37,17,NULL,4),(931,'2020-03-25','08:00:32','11:00:32',2,37,17,NULL,4),(932,'2020-04-01','08:00:32','11:00:32',2,37,17,NULL,4),(933,'2020-04-08','08:00:32','11:00:32',2,37,17,NULL,4),(934,'2020-04-15','08:00:32','11:00:32',2,37,17,NULL,4),(935,'2020-04-22','08:00:32','11:00:32',2,37,17,NULL,4),(936,'2020-04-29','08:00:32','11:00:32',2,37,17,NULL,4),(937,'2020-05-06','08:00:32','11:00:32',2,37,17,NULL,4),(938,'2020-05-13','08:00:32','11:00:32',2,37,17,NULL,4),(939,'2020-05-20','08:00:33','11:00:33',2,37,17,NULL,4),(940,'2020-05-27','08:00:33','11:00:33',2,37,17,NULL,4),(941,'2020-06-03','08:00:33','11:00:33',2,37,17,NULL,4),(942,'2020-06-10','08:00:33','11:00:33',2,37,17,NULL,4),(943,'2020-06-17','08:00:33','11:00:33',2,37,17,NULL,4),(944,'2020-06-24','08:00:33','11:00:33',2,37,17,NULL,4),(945,'2020-07-01','08:00:33','11:00:33',2,37,17,NULL,4),(946,'2020-07-08','08:00:33','11:00:33',2,37,17,NULL,4),(947,'2020-07-15','08:00:33','11:00:33',2,37,17,NULL,4),(948,'2020-07-22','08:00:33','11:00:33',2,37,17,NULL,4),(949,'2020-07-29','08:00:33','11:00:33',2,37,17,NULL,4),(950,'2020-08-05','08:00:33','11:00:33',2,37,17,NULL,4),(951,'2020-08-12','08:00:35','11:00:35',2,37,17,NULL,4),(952,'2020-08-19','08:00:35','11:00:35',2,37,17,NULL,4),(953,'2020-08-26','08:00:35','11:00:35',2,37,17,NULL,4),(954,'2020-09-02','08:00:35','11:00:35',2,37,17,NULL,4),(955,'2020-01-02','08:00:43','11:00:43',3,34,19,NULL,4),(956,'2020-01-09','08:00:43','11:00:43',3,34,19,NULL,4),(957,'2020-01-16','08:00:43','11:00:43',3,34,19,NULL,4),(958,'2020-01-23','08:00:43','11:00:43',3,34,19,NULL,4),(959,'2020-01-30','08:00:43','11:00:43',3,34,19,NULL,4),(960,'2020-02-06','08:00:43','11:00:43',3,34,19,NULL,4),(961,'2020-02-13','08:00:43','11:00:43',3,34,19,NULL,4),(962,'2020-02-20','08:00:43','11:00:43',3,34,19,NULL,4),(963,'2020-02-27','08:00:43','11:00:43',3,34,19,NULL,4),(964,'2020-03-05','08:00:44','11:00:44',3,34,19,NULL,4),(965,'2020-03-12','08:00:44','11:00:44',3,34,19,NULL,4),(966,'2020-03-19','08:00:44','11:00:44',3,34,19,NULL,4),(967,'2020-03-26','08:00:44','11:00:44',3,34,19,NULL,4),(968,'2020-04-02','08:00:44','11:00:44',3,34,19,NULL,4),(969,'2020-04-09','08:00:44','11:00:44',3,34,19,NULL,4),(970,'2020-04-16','08:00:44','11:00:44',3,34,19,NULL,4),(971,'2020-04-23','08:00:44','11:00:44',3,34,19,NULL,4),(972,'2020-04-30','08:00:44','11:00:44',3,34,19,NULL,4),(973,'2020-05-07','08:00:44','11:00:44',3,34,19,NULL,4),(974,'2020-05-14','08:00:44','11:00:44',3,34,19,NULL,4),(975,'2020-05-21','08:00:44','11:00:44',3,34,19,NULL,4),(976,'2020-05-28','08:00:44','11:00:44',3,34,19,NULL,4),(977,'2020-06-04','08:00:44','11:00:44',3,34,19,NULL,4),(978,'2020-06-11','08:00:44','11:00:44',3,34,19,NULL,4),(979,'2020-06-18','08:00:44','11:00:44',3,34,19,NULL,4),(980,'2020-06-25','08:00:44','11:00:44',3,34,19,NULL,4),(981,'2020-07-02','08:00:44','11:00:44',3,34,19,NULL,4),(982,'2020-07-09','08:00:44','11:00:44',3,34,19,NULL,4),(983,'2020-07-16','08:00:44','11:00:44',3,34,19,NULL,4),(984,'2020-07-23','08:00:45','11:00:45',3,34,19,NULL,4),(985,'2020-07-30','08:00:45','11:00:45',3,34,19,NULL,4),(986,'2020-08-06','08:00:45','11:00:45',3,34,19,NULL,4),(987,'2020-08-13','08:00:45','11:00:45',3,34,19,NULL,4),(988,'2020-08-20','08:00:45','11:00:45',3,34,19,NULL,4),(989,'2020-08-27','08:00:45','11:00:45',3,34,19,NULL,4),(990,'2020-09-03','08:00:45','11:00:45',3,34,19,NULL,4),(991,'2020-01-03','08:00:02','12:00:02',8,34,16,NULL,4),(992,'2020-01-10','08:00:02','12:00:02',8,34,16,NULL,4),(993,'2020-01-17','08:00:03','12:00:03',8,34,16,NULL,4),(994,'2020-01-24','08:00:03','12:00:03',8,34,16,NULL,4),(995,'2020-01-31','08:00:03','12:00:03',8,34,16,NULL,4),(996,'2020-02-07','08:00:03','12:00:03',8,34,16,NULL,4),(997,'2020-02-14','08:00:03','12:00:03',8,34,16,NULL,4),(998,'2020-02-21','08:00:03','12:00:03',8,34,16,NULL,4),(999,'2020-02-28','08:00:04','12:00:04',8,34,16,NULL,4),(1000,'2020-03-06','08:00:04','12:00:04',8,34,16,NULL,4),(1001,'2020-03-13','08:00:04','12:00:04',8,34,16,NULL,4),(1002,'2020-03-20','08:00:04','12:00:04',8,34,16,NULL,4),(1003,'2020-03-27','08:00:04','12:00:04',8,34,16,NULL,4),(1004,'2020-04-03','08:00:04','12:00:04',8,34,16,NULL,4),(1005,'2020-04-10','08:00:04','12:00:04',8,34,16,NULL,4),(1006,'2020-04-17','08:00:04','12:00:04',8,34,16,NULL,4),(1007,'2020-04-24','08:00:04','12:00:04',8,34,16,NULL,4),(1008,'2020-05-01','08:00:04','12:00:04',8,34,16,NULL,4),(1009,'2020-05-08','08:00:04','12:00:04',8,34,16,NULL,4),(1010,'2020-05-15','08:00:05','12:00:05',8,34,16,NULL,4),(1011,'2020-05-22','08:00:05','12:00:05',8,34,16,NULL,4),(1012,'2020-05-29','08:00:05','12:00:05',8,34,16,NULL,4),(1013,'2020-06-05','08:00:05','12:00:05',8,34,16,NULL,4),(1014,'2020-06-12','08:00:05','12:00:05',8,34,16,NULL,4),(1015,'2020-06-19','08:00:05','12:00:05',8,34,16,NULL,4),(1016,'2020-06-26','08:00:05','12:00:05',8,34,16,NULL,4),(1017,'2020-07-03','08:00:05','12:00:05',8,34,16,NULL,4),(1018,'2020-07-10','08:00:05','12:00:05',8,34,16,NULL,4),(1019,'2020-07-17','08:00:05','12:00:05',8,34,16,NULL,4),(1020,'2020-07-24','08:00:05','12:00:05',8,34,16,NULL,4),(1021,'2020-07-31','08:00:05','12:00:05',8,34,16,NULL,4),(1022,'2020-08-07','08:00:05','12:00:05',8,34,16,NULL,4),(1023,'2020-08-14','08:00:05','12:00:05',8,34,16,NULL,4),(1024,'2020-08-21','08:00:05','12:00:05',8,34,16,NULL,4),(1025,'2020-08-28','08:00:05','12:00:05',8,34,16,NULL,4),(1026,'2020-09-04','08:00:06','12:00:06',8,34,16,NULL,4),(1027,'2020-01-03','14:00:27','16:00:27',4,4,18,NULL,4),(1028,'2020-01-10','14:00:27','16:00:27',4,4,18,NULL,4),(1029,'2020-01-17','14:00:27','16:00:27',4,4,18,NULL,4),(1030,'2020-01-24','14:00:27','16:00:27',4,4,18,NULL,4),(1031,'2020-01-31','14:00:27','16:00:27',4,4,18,NULL,4),(1032,'2020-02-07','14:00:28','16:00:28',4,4,18,NULL,4),(1033,'2020-02-14','14:00:28','16:00:28',4,4,18,NULL,4),(1034,'2020-02-21','14:00:28','16:00:28',4,4,18,NULL,4),(1035,'2020-02-28','14:00:28','16:00:28',4,4,18,NULL,4),(1036,'2020-03-06','14:00:28','16:00:28',4,4,18,NULL,4),(1037,'2020-03-13','14:00:28','16:00:28',4,4,18,NULL,4),(1038,'2020-03-20','14:00:28','16:00:28',4,4,18,NULL,4),(1039,'2020-03-27','14:00:28','16:00:28',4,4,18,NULL,4),(1040,'2020-04-03','14:00:28','16:00:28',4,4,18,NULL,4),(1041,'2020-04-10','14:00:28','16:00:28',4,4,18,NULL,4),(1042,'2020-04-17','14:00:28','16:00:28',4,4,18,NULL,4),(1043,'2020-04-24','14:00:28','16:00:28',4,4,18,NULL,4),(1044,'2020-05-01','14:00:29','16:00:29',4,4,18,NULL,4),(1045,'2020-05-08','14:00:29','16:00:29',4,4,18,NULL,4),(1046,'2020-05-15','14:00:29','16:00:29',4,4,18,NULL,4),(1047,'2020-05-22','14:00:30','16:00:30',4,4,18,NULL,4),(1048,'2020-05-29','14:00:30','16:00:30',4,4,18,NULL,4),(1049,'2020-06-05','14:00:30','16:00:30',4,4,18,NULL,4),(1050,'2020-06-12','14:00:30','16:00:30',4,4,18,NULL,4),(1051,'2020-06-19','14:00:30','16:00:30',4,4,18,NULL,4),(1052,'2020-06-26','14:00:30','16:00:30',4,4,18,NULL,4),(1053,'2020-07-03','14:00:30','16:00:30',4,4,18,NULL,4),(1054,'2020-07-10','14:00:30','16:00:30',4,4,18,NULL,4),(1055,'2020-07-17','14:00:30','16:00:30',4,4,18,NULL,4),(1056,'2020-07-24','14:00:30','16:00:30',4,4,18,NULL,4),(1057,'2020-07-31','14:00:30','16:00:30',4,4,18,NULL,4),(1058,'2020-08-07','14:00:30','16:00:30',4,4,18,NULL,4),(1059,'2020-08-14','14:00:30','16:00:30',4,4,18,NULL,4),(1060,'2020-08-21','14:00:30','16:00:30',4,4,18,NULL,4),(1061,'2020-08-28','14:00:30','16:00:30',4,4,18,NULL,4),(1062,'2020-09-04','14:00:30','16:00:30',4,4,18,NULL,4);
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

-- Dump completed on 2020-01-09 16:35:59
