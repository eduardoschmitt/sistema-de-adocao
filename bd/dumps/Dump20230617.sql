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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adocoes`
--

LOCK TABLES `adocoes` WRITE;
/*!40000 ALTER TABLE `adocoes` DISABLE KEYS */;
INSERT INTO `adocoes` VALUES (6,1,1,'2023-05-01'),(7,2,2,'2023-05-02'),(8,3,3,'2023-05-03');
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
  `Idade` int NOT NULL,
  `Sexo` varchar(10) NOT NULL,
  `Descricao` text,
  `ID_Cor` int NOT NULL,
  `ID_Raca` int NOT NULL,
  `Adotado` tinyint NOT NULL,
  PRIMARY KEY (`ID`,`ID_Cor`,`ID_Raca`),
  KEY `fk_animais_cores1_idx` (`ID_Cor`),
  KEY `fk_animais_raças1_idx` (`ID_Raca`),
  CONSTRAINT `fk_animais_cores1` FOREIGN KEY (`ID_Cor`) REFERENCES `cores` (`ID`),
  CONSTRAINT `fk_animais_raças1` FOREIGN KEY (`ID_Raca`) REFERENCES `racas` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animais`
--

LOCK TABLES `animais` WRITE;
/*!40000 ALTER TABLE `animais` DISABLE KEYS */;
INSERT INTO `animais` VALUES (6,'Rex',3,'Macho','Cachorro brincalhão e amoroso',1,1,0),(7,'Luna',2,'Fêmea','Gatinha tranquila e carinhosa',2,2,0),(8,'Bolinha',1,'Macho','Coelhinho dócil e brincalhão',3,3,0),(9,'Frajola',1,'Macho','Hamster curioso e ativo',4,4,0),(10,'Piolho',2,'Fêmea','Canário cantor e colorido',5,5,0);
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
  KEY `fk_Bairro_Cidade1_idx` (`ID_Cidade`),
  CONSTRAINT `fk_Bairro_Cidade1` FOREIGN KEY (`ID_Cidade`) REFERENCES `mydb`.`cidade` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bairro`
--

LOCK TABLES `bairro` WRITE;
/*!40000 ALTER TABLE `bairro` DISABLE KEYS */;
INSERT INTO `bairro` VALUES (1,1,'Centro'),(2,1,'Vila Madalena'),(3,2,'Centro'),(4,2,'Cambuí'),(5,3,'Centro'),(6,3,'Copacabana'),(7,4,'Centro'),(8,4,'Savassi');
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
  KEY `fk_Cidade_Estado_idx` (`Estado_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
INSERT INTO `cidade` VALUES (1,1,'São Paulo'),(2,1,'Campinas'),(11,5,'São Paulo'),(13,1,'Santos');
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
  `ID` int NOT NULL,
  `Valor` double NOT NULL,
  `Data` date NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doacoes`
--

LOCK TABLES `doacoes` WRITE;
/*!40000 ALTER TABLE `doacoes` DISABLE KEYS */;
INSERT INTO `doacoes` VALUES (1,100.5,'2023-05-01'),(2,50.75,'2023-05-02'),(3,200,'2023-05-03');
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
  KEY `fk_Endereco_Bairro1_idx` (`ID_Bairro`),
  CONSTRAINT `fk_Endereco_Bairro1` FOREIGN KEY (`ID_Bairro`) REFERENCES `mydb`.`bairro` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,1,'Rua A','Ap 101'),(2,2,'Rua B','Casa 10'),(3,3,'Avenida X','Sala 200'),(4,4,'Rua Y','Ap 302'),(5,5,'Avenida Z','Casa 5'),(6,6,'Rua P','Loja 15'),(7,7,'Avenida Q','Ap 501'),(8,8,'Rua R','Casa 20');
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
  `Doacoes_ID` int NOT NULL,
  PRIMARY KEY (`ID`,`ID_Endereco`,`Doacoes_ID`),
  KEY `fk_adotantes_Endereco1_idx` (`ID_Endereco`),
  KEY `fk_pessoa_Doacoes1_idx` (`Doacoes_ID`),
  CONSTRAINT `fk_adotantes_Endereco1` FOREIGN KEY (`ID_Endereco`) REFERENCES `mydb`.`endereco` (`ID`),
  CONSTRAINT `fk_pessoa_Doacoes1` FOREIGN KEY (`Doacoes_ID`) REFERENCES `mydb`.`doacoes` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (6,'João','999999999','joao@example.com',1,1),(7,'Maria','888888888','maria@example.com',2,2),(8,'Pedro','777777777','pedro@example.com',3,3);
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
INSERT INTO `racas` VALUES (6,'Golden Retriever',1),(7,'Persa',2),(8,'Holland Lop',3),(9,'Sírio',4),(10,'Canário',5);
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

-- Dump completed on 2023-06-17 23:50:53
