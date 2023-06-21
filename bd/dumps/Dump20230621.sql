CREATE DATABASE  IF NOT EXISTS `sysadocao` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sysadocao`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sysadocao
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `adocoes`
--

DROP TABLE IF EXISTS `adocoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adocoes` (
  `ID_Adocao` int NOT NULL AUTO_INCREMENT,
  `ID_Animal` int NOT NULL,
  `ID_Pessoa` int NOT NULL,
  `Data_Adocao` date NOT NULL,
  PRIMARY KEY (`ID_Adocao`,`ID_Animal`,`ID_Pessoa`),
  KEY `ID_animal` (`ID_Animal`),
  KEY `ID_adotante` (`ID_Pessoa`),
  CONSTRAINT `adoções_ibfk_1` FOREIGN KEY (`ID_Animal`) REFERENCES `animais` (`ID`),
  CONSTRAINT `adoções_ibfk_2` FOREIGN KEY (`ID_Pessoa`) REFERENCES `pessoa` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adocoes`
--

LOCK TABLES `adocoes` WRITE;
/*!40000 ALTER TABLE `adocoes` DISABLE KEYS */;
INSERT INTO `adocoes` VALUES (6,10,6,'2023-05-01'),(7,9,6,'2023-05-02'),(9,7,8,'2023-06-21'),(10,8,8,'2023-06-21');
/*!40000 ALTER TABLE `adocoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animais`
--

DROP TABLE IF EXISTS `animais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animais` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(255) NOT NULL,
  `Sexo` varchar(10) NOT NULL,
  `Descricao` text,
  `ID_Cor` int NOT NULL,
  `ID_Raca` int NOT NULL,
  `Nascimento` date NOT NULL DEFAULT (curdate()),
  PRIMARY KEY (`ID`,`ID_Cor`,`ID_Raca`),
  KEY `fk_animais_cores1_idx` (`ID_Cor`),
  KEY `fk_animais_raças1_idx` (`ID_Raca`),
  CONSTRAINT `fk_animais_cores1` FOREIGN KEY (`ID_Cor`) REFERENCES `cores` (`ID`),
  CONSTRAINT `fk_animais_raças1` FOREIGN KEY (`ID_Raca`) REFERENCES `racas` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animais`
--

LOCK TABLES `animais` WRITE;
/*!40000 ALTER TABLE `animais` DISABLE KEYS */;
INSERT INTO `animais` VALUES (6,'Rex','Macho','Cachorro brincalhão e amoroso',6,6,'2020-10-10'),(7,'Luna','Fêmea','Gatinha tranquila e carinhosa',6,6,'2020-10-10'),(8,'Bolinha','Macho','Coelhinho dócil e brincalhão',6,6,'2020-10-10'),(9,'Frajola','Macho','Hamster curioso e ativo',6,6,'2020-10-10'),(10,'Piolho','Fêmea','Canário cantor e colorido',6,6,'2020-10-10');
/*!40000 ALTER TABLE `animais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bairro`
--

DROP TABLE IF EXISTS `bairro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bairro` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ID_Cidade` int NOT NULL,
  `Nome` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_cidade_bairro_idx` (`ID_Cidade`),
  CONSTRAINT `fk_cidade_bairro` FOREIGN KEY (`ID_Cidade`) REFERENCES `cidade` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bairro`
--

LOCK TABLES `bairro` WRITE;
/*!40000 ALTER TABLE `bairro` DISABLE KEYS */;
INSERT INTO `bairro` VALUES (1,1,'Centro'),(2,1,'Vila Madalena'),(3,2,'Centro'),(4,2,'Cambuí'),(18,1,'Centro');
/*!40000 ALTER TABLE `bairro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cidade` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Estado_id` int NOT NULL,
  `Nome` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_Cidade_Estado_idx` (`Estado_id`),
  CONSTRAINT `fk_cidade_estado` FOREIGN KEY (`Estado_id`) REFERENCES `estado` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
INSERT INTO `cidade` VALUES (1,1,'São Paulo'),(2,1,'Campinas'),(11,5,'Porto Alegre'),(13,1,'Santos');
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cores`
--

DROP TABLE IF EXISTS `cores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cores` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cores`
--

LOCK TABLES `cores` WRITE;
/*!40000 ALTER TABLE `cores` DISABLE KEYS */;
INSERT INTO `cores` VALUES (6,'Preto'),(7,'Branco'),(8,'Marrom'),(9,'Amarelo'),(10,'Cinza');
/*!40000 ALTER TABLE `cores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doacoes`
--

DROP TABLE IF EXISTS `doacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doacoes` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Valor` double NOT NULL,
  `Data` date NOT NULL,
  `id_pessoa` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_pessoa_doacao_idx` (`id_pessoa`),
  CONSTRAINT `fk_doacao_pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doacoes`
--

LOCK TABLES `doacoes` WRITE;
/*!40000 ALTER TABLE `doacoes` DISABLE KEYS */;
INSERT INTO `doacoes` VALUES (1,100.5,'2023-05-01',6),(2,50.75,'2023-05-02',6),(3,200,'2023-05-03',6);
/*!40000 ALTER TABLE `doacoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereco` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ID_Bairro` int NOT NULL,
  `Logradouro` varchar(100) NOT NULL,
  `Complemento` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_endereco_bairro_idx` (`ID_Bairro`),
  CONSTRAINT `fk_endereco_bairro` FOREIGN KEY (`ID_Bairro`) REFERENCES `bairro` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,1,'Rua A','Ap 101'),(2,1,'Leo','Leo'),(3,3,'Avenida X','Sala 200'),(4,4,'Rua Y','Ap 302'),(9,1,'Leo','Leo'),(10,1,'Leo','Leo');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especies`
--

DROP TABLE IF EXISTS `especies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especies` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nome_Especie` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especies`
--

LOCK TABLES `especies` WRITE;
/*!40000 ALTER TABLE `especies` DISABLE KEYS */;
INSERT INTO `especies` VALUES (6,'Cachorro'),(7,'Gato'),(8,'Coelho'),(9,'Hamster'),(10,'Pássaro');
/*!40000 ALTER TABLE `especies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Sigla` varchar(2) NOT NULL,
  `Nome` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1,'SP','São Paulo'),(5,'RS','Rio grande do sul');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(255) NOT NULL,
  `Numero_telefone` varchar(20) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `ID_Endereco` int NOT NULL,
  PRIMARY KEY (`ID`,`ID_Endereco`),
  KEY `fk_pessoa_endereco_idx` (`ID_Endereco`),
  CONSTRAINT `fk_pessoa_endereco` FOREIGN KEY (`ID_Endereco`) REFERENCES `endereco` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (6,'João','999999999','joao@example.com',1),(8,'Pedro','777777777','pedro@example.com',3);
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `racas`
--

DROP TABLE IF EXISTS `racas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `racas` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(255) NOT NULL,
  `ID_Especies` int NOT NULL,
  PRIMARY KEY (`ID`,`ID_Especies`),
  KEY `fk_raças_espécies1_idx` (`ID_Especies`),
  CONSTRAINT `fk_raças_espécies1` FOREIGN KEY (`ID_Especies`) REFERENCES `especies` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `racas`
--

LOCK TABLES `racas` WRITE;
/*!40000 ALTER TABLE `racas` DISABLE KEYS */;
INSERT INTO `racas` VALUES (6,'Golden Retriever',6),(7,'Persa',7),(8,'Holland Lop',8),(9,'Sírio',9),(10,'Canário',10);
/*!40000 ALTER TABLE `racas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-21  3:57:12
