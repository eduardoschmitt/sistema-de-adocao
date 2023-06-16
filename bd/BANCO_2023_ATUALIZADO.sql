DROP DATABASE SYSADOCAO;

CREATE DATABASE SYSADOCAO;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Table `mydb`.`Estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sysadocao`.`Estado` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Sigla` VARCHAR(2) NOT NULL,
  `Nome` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sysadocao`.`Cidade` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Estado_id` INT NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`, `Estado_id`),
  INDEX `fk_Cidade_Estado_idx` (`Estado_id` ASC) VISIBLE,
  CONSTRAINT `fk_Cidade_Estado`
    FOREIGN KEY (`Estado_id`)
    REFERENCES `mydb`.`Estado` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Bairro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sysadocao`.`Bairro` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ID_Cidade` INT NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`, `ID_Cidade`),
  INDEX `fk_Bairro_Cidade1_idx` (`ID_Cidade` ASC) VISIBLE,
  CONSTRAINT `fk_Bairro_Cidade1`
    FOREIGN KEY (`ID_Cidade`)
    REFERENCES `mydb`.`Cidade` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sysadocao`.`Endereco` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ID_Bairro` INT NOT NULL,
  `Logradouro` VARCHAR(100) NOT NULL,
  `Complemento` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ID`, `ID_Bairro`),
  INDEX `fk_Endereco_Bairro1_idx` (`ID_Bairro` ASC) VISIBLE,
  CONSTRAINT `fk_Endereco_Bairro1`
    FOREIGN KEY (`ID_Bairro`)
    REFERENCES `mydb`.`Bairro` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Doacoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sysadocao`.`Doacoes` (
  `ID` INT NOT NULL,
  `Valor` DOUBLE NOT NULL,
  `Data` DATE NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `sysadocao`.`cores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sysadocao`.`cores` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `sysadocao`.`especies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sysadocao`.`especies` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Nome_Especie` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `sysadocao`.`racas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sysadocao`.`racas` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(255) NOT NULL,
  `ID_Especies` INT NOT NULL,
  PRIMARY KEY (`ID`, `ID_Especies`),
  INDEX `fk_raças_espécies1_idx` (`ID_Especies` ASC) VISIBLE,
  CONSTRAINT `fk_raças_espécies1`
    FOREIGN KEY (`ID_Especies`)
    REFERENCES `sysadocao`.`especies` (`ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `sysadocao`.`animais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sysadocao`.`animais` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(255) NOT NULL,
  `Idade` INT NOT NULL,
  `Sexo` VARCHAR(10) NOT NULL,
  `Descricao` TEXT NULL DEFAULT NULL,
  `ID_Cor` INT NOT NULL,
  `ID_Raca` INT NOT NULL,
  `Adotado` TINYINT NOT NULL,
  PRIMARY KEY (`ID`, `ID_Cor`, `ID_Raca`),
  INDEX `fk_animais_cores1_idx` (`ID_Cor` ASC) VISIBLE,
  INDEX `fk_animais_raças1_idx` (`ID_Raca` ASC) VISIBLE,
  CONSTRAINT `fk_animais_cores1`
    FOREIGN KEY (`ID_Cor`)
    REFERENCES `sysadocao`.`cores` (`ID`),
  CONSTRAINT `fk_animais_raças1`
    FOREIGN KEY (`ID_Raca`)
    REFERENCES `sysadocao`.`racas` (`ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `sysadocao`.`pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sysadocao`.`pessoa` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(255) NOT NULL,
  `Numero_telefone` VARCHAR(20) NOT NULL,
  `Email` VARCHAR(255) NOT NULL,
  `ID_Endereco` INT NOT NULL,
  `Doacoes_ID` INT NOT NULL,
  PRIMARY KEY (`ID`, `ID_Endereco`, `Doacoes_ID`),
  INDEX `fk_adotantes_Endereco1_idx` (`ID_Endereco` ASC) VISIBLE,
  INDEX `fk_pessoa_Doacoes1_idx` (`Doacoes_ID` ASC) VISIBLE,
  CONSTRAINT `fk_adotantes_Endereco1`
    FOREIGN KEY (`ID_Endereco`)
    REFERENCES `mydb`.`Endereco` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pessoa_Doacoes1`
    FOREIGN KEY (`Doacoes_ID`)
    REFERENCES `mydb`.`Doacoes` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 6 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `sysadocao`.`adocoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sysadocao`.`adocoes` (
  `ID_Adocao` INT NOT NULL AUTO_INCREMENT,
  `ID_Animal` INT NOT NULL,
  `ID_Pessoa` INT NOT NULL,
  `Data_Adocao` DATE NOT NULL,
  PRIMARY KEY (`ID_Adocao`, `ID_Animal`, `ID_Pessoa`),
  INDEX `ID_animal` (`ID_Animal` ASC) VISIBLE,
  INDEX `ID_adotante` (`ID_Pessoa` ASC) VISIBLE,
  CONSTRAINT `adoções_ibfk_1`
    FOREIGN KEY (`ID_Animal`)
    REFERENCES `sysadocao`.`animais` (`ID`),
  CONSTRAINT `adoções_ibfk_2`
    FOREIGN KEY (`ID_Pessoa`)
    REFERENCES `sysadocao`.`pessoa` (`ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
