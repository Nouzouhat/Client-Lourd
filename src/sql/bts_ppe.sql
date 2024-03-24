drop database if exists bts_ppe; 
create database bts_ppe; 
use bts_ppe; 

CREATE TABLE IF NOT EXISTS client (
  IDCLIENT int(11) NOT NULL AUTO_INCREMENT,
  NOM char(32) DEFAULT NULL,
  EMAIL char(32) DEFAULT NULL,
  TELEPHONE char(12) DEFAULT NULL,
  acteur enum('entreprise','particulier') DEFAULT NULL,
  ADRESSE char(32) DEFAULT NULL,
  CP char(5) NOT NULL,
  ville char(30) DEFAULT NULL,
  PRIMARY KEY (IDCLIENT)
);

CREATE TABLE IF NOT EXISTS contrat (
  idcontrat int(11) NOT NULL AUTO_INCREMENT,
  DEBUTCONTRAT char(32) DEFAULT NULL,
  FINCONTRAT char(32) DEFAULT NULL,
  MONTANT char(32) DEFAULT NULL,
  statut enum('en cours','termine') DEFAULT NULL,
  idclient int(11) DEFAULT NULL,
  PRIMARY KEY (idcontrat)
);

CREATE TABLE IF NOT EXISTS datedebut (
  DATE_D char(32) NOT NULL,
  PRIMARY KEY (DATE_D)
);

CREATE TABLE IF NOT EXISTS entreprise (
  identreprise int(11) NOT NULL AUTO_INCREMENT,
  num_siret char(32) NOT NULL,
  contact char(32) DEFAULT NULL,
  nom char(32) DEFAULT NULL,
  email char(32) DEFAULT NULL,
  adresse char(32) DEFAULT NULL,
  cp char(5) DEFAULT NULL,
  ville char(32) DEFAULT NULL,
  idclient int(11) DEFAULT NULL,
  PRIMARY KEY (identreprise),
  KEY idclient (idclient)
);


CREATE TABLE IF NOT EXISTS facture (
  idfacture int(11) NOT NULL AUTO_INCREMENT,
  IDCONTRAT char(32) NOT NULL UNIQUE,
  DATEFAC char(32) DEFAULT NULL,
  MONTANT char(32) DEFAULT NULL,
  TEMPSTOTAL char(32) DEFAULT NULL,
  JOUROUVRABLE char(32) DEFAULT NULL,
  PRIMARY KEY (idfacture)
);

CREATE TABLE IF NOT EXISTS intervention (
  idintervention int(11) NOT NULL AUTO_INCREMENT,
  DATEINTER char(32) DEFAULT NULL,
  TEMPSTOTAL char(32) DEFAULT NULL,
  COMMENTAIRE char(32) DEFAULT NULL,
  MOTIF char(32) DEFAULT NULL,
  ETATINTERVENTION char(32) DEFAULT NULL,
  idmateriel int(11) DEFAULT NULL,
  PRIMARY KEY (idintervention)
);

CREATE TABLE IF NOT EXISTS materiel (
  idmateriel int(11) NOT NULL AUTO_INCREMENT,
  type enum('peinture','ponçage','perforation','sciage','nettoyage','soudure','terrassement','plomberie') DEFAULT NULL,
  NBREJOURLOUES char(32) DEFAULT NULL,
  MONTANT char(32) DEFAULT NULL,
  NOTICEENTRETIEN char(32) DEFAULT NULL,
  idcontrat int(11) DEFAULT NULL,
  PRIMARY KEY (idmateriel),
  KEY idcontrat (idcontrat)
);

CREATE TABLE IF NOT EXISTS origine (
  IDORIGINE char(32) NOT NULL,
  IDINTERVENTION char(32) DEFAULT NULL,
  LIBELLE char(32) DEFAULT NULL,
  PRIMARY KEY (IDORIGINE),
  KEY IDINTERVENTION (IDINTERVENTION)
);

CREATE TABLE IF NOT EXISTS particulier (
  idparticulier int(11) NOT NULL AUTO_INCREMENT,
  PRENOM char(32) DEFAULT NULL,
  NOM char(32) DEFAULT NULL,
  EMAIL char(32) DEFAULT NULL,
  ADRESSE char(32) DEFAULT NULL,
  TELEPHONE char(32) DEFAULT NULL,
  PRIMARY KEY (idparticulier)
);

CREATE TABLE IF NOT EXISTS technicien (
  idtechnicien int(3) NOT NULL AUTO_INCREMENT,
  NOM char(32) DEFAULT NULL,
  PRENOM char(32) DEFAULT NULL,
  ADRESSE char(32) DEFAULT NULL,
  CP char(5) DEFAULT NULL,
  ville char(32) DEFAULT NULL,
  TELEPHONE char(32) DEFAULT NULL,
  EMAIL char(32) DEFAULT NULL,
  PRIMARY KEY (idtechnicien)
);

CREATE TABLE IF NOT EXISTS type_materiel (
  IDTYPE char(32) NOT NULL,
  DESIGNATION char(32) DEFAULT NULL,
  PRIMARY KEY (IDTYPE)
);

CREATE TABLE IF NOT EXISTS user (
  iduser int(3) NOT NULL AUTO_INCREMENT,
  nom varchar(50) DEFAULT NULL,
  prenom varchar(50) DEFAULT NULL,
  email varchar(50) DEFAULT NULL,
  mdp varchar(255) DEFAULT NULL,
  role enum('admin','user') DEFAULT NULL,
  PRIMARY KEY (iduser)
);


insert into user values 
	(null, "Nouzhati","Athoumani", "a@gmail.com", "123", "admin"),
	(null, "Aramata", "Adja", "celine@gmail.com", "456", "user");

