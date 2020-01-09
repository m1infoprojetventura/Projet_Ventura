DROP TABLE IF EXISTS Enseignant;
CREATE TABLE Enseignant (
  id int  NOT NULL IDENTITY,
  nom varchar(40) NOT NULL,
  prenom varchar(40) NOT NULL,
  date_naissance date NOT NULL,
  mdp varchar(64) DEFAULT NULL,
  login varchar(40) DEFAULT NULL,
  id_departement int  DEFAULT NULL,
  PRIMARY KEY (id)
);

LOCK TABLE Enseignant WRITE;
INSERT INTO Enseignant VALUES (4,'Jack','Jean','2019-11-14','f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9','JJack788',1),(18,'miss','mister','2013-02-15','2134782028','mmiss-28',1),(20,'peter','parker','2016-11-19','-166493559','ppeter482',1),(21,'Adrienbg','Guard','2019-01-15','1563063939','GAdrienbg288',1),(26,'zaada','adazdyttt','2019-11-09','1686191058','azaada518',1),(32,'ezrt','azeaze','2022-11-06','-1056429475','aezrt772',1),(33,'eeeeerico','eeeea','2019-11-10','-651991297','eeeee164',1),(34,'eeee','eeeea','2019-11-29','1299163719','eeeee133',1),(35,'eeeeerico','eeeea','2019-11-10','-651991297','eeeeeerico603',1);

DROP TABLE IF EXISTS Administrateur;
CREATE TABLE Administrateur (
  id int NOT NULL IDENTITY,
  nom varchar(30) DEFAULT NULL,
  prenom varchar(30) DEFAULT NULL,
  date_naissance date DEFAULT NULL,
  password varchar(64) DEFAULT NULL,
  login varchar(30) DEFAULT NULL,
  PRIMARY KEY (id)
);

LOCK TABLE Administrateur WRITE;
INSERT INTO Administrateur VALUES (1,NULL,NULL,'1998-06-03','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','admin');


DROP TABLE IF EXISTS Contrainte;
CREATE TABLE Contrainte (
  id int  NOT NULL IDENTITY,
  date date DEFAULT NULL,
  debut_contrainte time DEFAULT NULL,
  fin_contrainte time DEFAULT NULL,
  id_enseignant int  DEFAULT NULL,
  motif LONGVARCHAR DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_enseignant) REFERENCES Enseignant (id)
);


LOCK TABLE Contrainte WRITE;
INSERT INTO Contrainte VALUES (2,'2019-12-31','13:00:01','16:00:01',4,''),(6,'2020-01-22','17:00:33','20:00:33',4,''),(7,'2020-01-29','17:00:33','20:00:33',4,''),(8,'2020-02-05','17:00:33','20:00:33',4,''),(9,'2020-02-12','17:00:33','20:00:33',4,''),(10,'2020-02-19','17:00:34','20:00:34',4,''),(11,'2020-02-26','17:00:34','20:00:34',4,''),(12,'2020-03-04','17:00:34','20:00:34',4,''),(13,'2020-03-11','17:00:34','20:00:34',4,''),(17,'2020-01-01','16:00:44','20:19:44',4,'J en ai marre des élèves'),(18,'2020-01-02','08:00:26','20:00:26',4,'Par flemme.'),(19,'2020-01-09','16:00:08','18:00:08',4,'Je suis en vancances'),(20,'2020-01-16','16:00:08','18:00:08',4,'Je suis en vancances'),(21,'2020-01-23','16:00:08','18:00:08',4,'Je suis en vancances'),(22,'2020-01-30','16:00:08','18:00:08',4,'Je suis en vancances'),(23,'2020-02-06','16:00:08','18:00:08',4,'Je suis en vancances');


DROP TABLE IF EXISTS Departement;
CREATE TABLE Departement (
  id int  NOT NULL IDENTITY,
  nom varchar(40) NOT NULL,
  id_responsable int  DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_responsable) REFERENCES Enseignant (id)
);


LOCK TABLE Departement WRITE;
INSERT INTO Departement VALUES (1,'Sciences et Techniques',NULL);

DROP TABLE IF EXISTS Formation;
CREATE TABLE Formation (
  id int  NOT NULL IDENTITY,
  intitule varchar(40) DEFAULT NULL,
  id_departement int  DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_departement) REFERENCES Departement (id)
);


LOCK TABLE Formation WRITE;
INSERT INTO Formation VALUES (1,'M1 Informatique',NULL),(2,'Sciences Humaines',NULL),(3,'M1 Maths',NULL);

DROP TABLE IF EXISTS Etudiant;
CREATE TABLE Etudiant (
  id int  NOT NULL IDENTITY,
  date_naissance date DEFAULT NULL,
  ufr varchar(40) DEFAULT NULL,
  id_formation int  DEFAULT NULL,
  groupe tinyint  DEFAULT NULL,
  nom varchar(40) NOT NULL,
  prenom varchar(40) NOT NULL,
  mdp varchar(64) DEFAULT NULL,
  login varchar(40) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_formation) REFERENCES Formation (id)
);


LOCK TABLE Etudiant WRITE;
INSERT INTO Etudiant VALUES (6,'2019-11-14',NULL,1,NULL,'Jack','Paul','1682802253','PJack875'),(7,'2019-11-23',NULL,1,NULL,'Perecic','Georgesano','-1906414871','GPerecic-778'),(12,'2019-11-15',NULL,1,NULL,'az','aze','-1873814567','aaz286'),(13,'2019-12-20',NULL,1,NULL,'bourboul','chomeur','-524537801','cbourboul223'),(15,'2019-11-15',NULL,1,NULL,'chameau','poisson','-1785752953','pchameau460'),(17,'2013-02-15',NULL,1,NULL,'miss','mister','2134782028','mmiss-28'),(18,'2019-01-15',NULL,1,NULL,'Adrien','Guard','-1952878488','GAdrien288'),(19,'2019-11-22',NULL,1,NULL,'100','99','721137975','9100-726'),(20,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg','-598542565','yninbin773'),(21,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg','-598542565','yninbin742'),(22,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg','-598542565','yninbin711'),(23,'2019-11-29',NULL,1,NULL,'ninbin','edrfgty','226724619','yninbin680'),(24,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg','-598542565','yninbin649'),(25,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg','-598542565','yninbin618'),(26,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg','-598542565','yninbin587'),(27,'2019-11-29',NULL,1,NULL,'ninbin','yvuvgyvg','-598542565','yninbin556');


DROP TABLE IF EXISTS Matiere;
CREATE TABLE Matiere (
  id int  NOT NULL IDENTITY,
  nom varchar(40) DEFAULT NULL,
  PRIMARY KEY (id)
);


LOCK TABLE Matiere WRITE;
INSERT INTO Matiere VALUES (1,'Physique'),(2,'Maths'),(3,'Informatique'),(4,'Anglais');


DROP TABLE IF EXISTS Matiere_Enseignant;
CREATE TABLE Matiere_Enseignant (
  id int  NOT NULL IDENTITY,
  id_matiere int  DEFAULT NULL,
  id_enseignant int  DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_enseignant) REFERENCES Enseignant (id),
  FOREIGN KEY (id_matiere) REFERENCES Matiere (id)
);


LOCK TABLE Matiere_Enseignant WRITE;
INSERT INTO Matiere_Enseignant VALUES (4,1,32),(5,2,34),(6,4,4),(7,3,20);

DROP TABLE IF EXISTS Salle;
CREATE TABLE Salle (
  id int  NOT NULL IDENTITY,
  capacite tinyint  DEFAULT NULL,
  nom varchar(40) DEFAULT NULL,
  PRIMARY KEY (id)
);


LOCK TABLE Salle WRITE;
INSERT INTO Salle VALUES (1,10,'lebellegou'),(2,42,'Salle a bourboul'),(3,1,'bac a asable'),(8,2,'pekinParisNewyork'),(9,66,'Salle vide'),(11,99,'masalleouPas'),(12,111,'Q522'),(13,22,'U458');


DROP TABLE IF EXISTS Materiel;
CREATE TABLE Materiel (
  id int  NOT NULL IDENTITY,
  id_salle int  DEFAULT NULL,
  type varchar(40) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_salle) REFERENCES Salle (id) ON DELETE CASCADE ON UPDATE CASCADE
);


LOCK TABLE Materiel WRITE;
INSERT INTO Materiel VALUES (1,1,'ORDINATEUR'),(2,1,'VIDEO_PROJECTEUR'),(3,2,'IMPRIMANTE'),(4,2,'VIDEO_PROJECTEUR'),(5,3,'TABLEAU_TACTIL'),(18,9,'ORDINATEUR'),(29,11,'VIDEO_PROJECTEUR'),(30,12,'ORDINATEUR'),(31,13,'ORDINATEUR'),(32,13,'TABLEAU_TACTIL');


DROP TABLE IF EXISTS Reservation;
CREATE TABLE Reservation (
  id int NOT NULL IDENTITY,
  id_enseignant int  DEFAULT NULL,
  id_salle int  DEFAULT NULL,
  date_reservation date NOT NULL,
  etat_reservation varchar(30) DEFAULT 'en Cours',
  id_seance int  DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_salle) REFERENCES Salle (id)
);

LOCK TABLE Reservation WRITE;
INSERT INTO Reservation VALUES (1,4,13,'2019-12-10','Confirmé',NULL),(2,4,12,'2019-12-14','en Cours',NULL),(3,4,12,'2019-12-10','en Cours',NULL),(4,4,13,'2019-12-14','en Cours',NULL),(8,21,3,'2019-12-13','en Cours',NULL),(9,21,11,'2019-12-13','Confirmé',NULL),(10,4,13,'2019-12-13','en Cours',NULL),(11,4,9,'2019-12-15','en Cours',NULL),(12,4,1,'2019-12-27','Confirmé',16),(13,4,9,'2019-12-27','Confirmé',32),(14,20,13,'2019-12-27','en Cours',9),(15,20,11,'2019-12-27','en Cours',7),(16,4,8,'2019-12-27','Confirmé',19),(17,4,3,'2019-12-27','Refusé',18);


DROP TABLE IF EXISTS Responsable;
CREATE TABLE Responsable (
  id int NOT NULL IDENTITY,
  nom varchar(50) DEFAULT NULL,
  prenom varchar(50) DEFAULT NULL,
  date_naissance date DEFAULT NULL,
  password varchar(64) DEFAULT NULL,
  login varchar(50) NOT NULL,
  PRIMARY KEY (id)
);


LOCK TABLE Responsable WRITE;
INSERT INTO Responsable VALUES (1,'Anouar','sayadi','1995-07-30','f2d81a260dea8a100dd517984e53c56a7523d96942a834b9cdc249bd4e8c7aa9','asayadi246');

DROP TABLE IF EXISTS Seance;
CREATE TABLE Seance (
  id int  NOT NULL IDENTITY,
  date date DEFAULT NULL,
  debut_seance time DEFAULT NULL,
  fin_seance time DEFAULT NULL,
  id_matiere int  DEFAULT NULL,
  id_enseignant int  DEFAULT NULL,
  id_Salle int  DEFAULT NULL,
  id_formation int  DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_Salle) REFERENCES Salle (id),
  FOREIGN KEY (id_enseignant) REFERENCES Enseignant (id),
  FOREIGN KEY (id_formation) REFERENCES Formation (id),
  FOREIGN KEY (id_matiere) REFERENCES Matiere (id)
);


LOCK TABLE Seance WRITE;
INSERT INTO Seance VALUES (1,'2019-09-30','08:00:48','12:00:48',4,4,1,1),(2,'2019-09-09','08:00:05','12:00:05',4,4,1,1),(4,'2020-03-19','11:00:35','13:00:35',4,4,1,1),(5,'2019-10-01','11:00:01','15:00:01',4,4,1,1),(9,'2019-12-30','08:00:42','13:00:42',4,4,1,1),(11,'2020-02-26','08:00:39','13:00:39',4,4,1,1),(12,'2019-10-02','08:00:21','13:00:21',4,4,1,1),(15,'2019-10-08','08:00:26','10:00:26',3,20,1,1),(16,'2019-10-09','08:00:33','10:00:33',3,20,1,1),(17,'2019-10-31','08:00:40','10:00:40',3,20,1,1),(20,'2019-10-15','08:00:01','10:00:01',2,34,1,1),(21,'2019-10-17','10:00:06','13:00:06',2,34,1,1),(22,'2020-01-15','10:00:12','13:00:12',2,34,1,1),(23,'2020-03-04','10:00:17','13:00:17',4,4,1,1),(24,'2019-10-22','08:00:59','12:00:59',3,20,1,1),(26,'2019-09-19','08:00:14','12:00:14',3,20,1,1),(27,'2019-10-01','08:00:04','10:00:04',2,34,1,1),(28,'2019-10-01','08:00:12','10:00:12',2,34,1,1),(29,'2019-10-01','08:00:13','10:00:13',2,34,1,1),(30,'2019-09-24','08:00:21','10:00:21',2,34,1,1),(31,'2019-09-27','08:00:30','12:00:30',2,34,1,1),(32,'2019-09-12','08:00:36','12:00:36',2,34,1,1),(34,'2019-09-25','08:00:07','12:00:07',2,34,1,1),(35,'2019-09-26','10:00:12','12:00:12',2,34,1,1),(36,'2019-10-02','08:00:08','11:00:08',2,34,1,1),(37,'2019-10-01','08:00:01','12:00:01',3,20,1,1),(39,'2019-09-10','08:00:03','13:00:03',3,20,1,1),(42,'2019-09-11','09:00:14','12:00:14',4,4,1,1),(43,'2019-09-02','08:00:46','12:00:46',4,4,1,1),(47,'2020-01-03','08:00:09','11:00:09',3,20,1,1),(48,'2020-01-02','08:00:13','11:00:13',3,20,1,1),(56,'2020-01-02','14:00:05','18:00:05',3,20,1,1),(57,'2019-10-16','14:00:38','18:00:38',3,20,1,1),(59,'2019-09-05','08:00:05','10:00:05',4,4,1,3),(60,'2019-09-03','08:00:34','10:00:34',4,4,1,1),(61,'2019-09-04','08:00:03','10:00:03',1,32,1,1),(62,'2019-09-12','14:00:01','18:00:01',4,4,1,1),(64,'2020-01-01','08:00:39','13:00:39',4,4,1,2),(65,'2019-12-31','08:00:16','12:00:16',4,4,1,1),(66,'2020-01-01','14:00:11','17:00:11',3,20,1,1),(68,'2020-01-14','08:00:00','11:00:00',2,34,1,2),(69,'2020-01-14','08:00:07','11:00:07',2,34,1,1),(70,'2019-12-31','08:00:17','11:00:17',2,34,1,2),(71,'2020-01-23','08:00:57','11:00:57',3,20,1,1),(72,'2020-01-15','15:00:00','18:00:00',3,20,1,1),(73,'2020-01-22','15:00:00','18:00:00',3,20,1,1),(75,'2020-01-01','16:00:45','19:00:45',2,34,1,2),(77,'2019-12-30','15:00:59','18:00:59',4,4,1,1);


