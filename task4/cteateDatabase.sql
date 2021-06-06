DROP DATABASE IF EXISTS `epam_tests`;
CREATE DATABASE `epam_tests`;
USE `epam_tests`;

-- epam_tests.exams definition

CREATE TABLE `exams` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- epam_tests.faculty definition

CREATE TABLE `faculty` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `count` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- epam_tests.users definition

CREATE TABLE `users` (
	id BIGINT auto_increment NOT NULL,
	login varchar(100) NOT NULL,
	password varchar(100) NOT NULL,
	`type` varchar(100) NULL,
	CONSTRAINT users_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

-- test.aplicant definition

CREATE TABLE `aplicant` (
  `user_id` bigint NOT NULL,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `certificate` int NOT NULL,
  `faculty_id` int NOT NULL,
  UNIQUE KEY `aplicant_UN` (`user_id`),
  KEY `aplicant_FK` (`faculty_id`),
  KEY `aplicant_FK_1` (`user_id`),
  CONSTRAINT `aplicant_FK` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`),
  CONSTRAINT `aplicant_FK_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- epam_tests.aplicant_result definition

CREATE TABLE `aplicant_result` (
  `aplicant_id` bigint NOT NULL,
  `exa_id` int NOT NULL,
  `result` int NOT NULL,
  `checked` tinyint(1) NOT NULL DEFAULT '0',
  KEY `aplicant_result_FK` (`aplicant_id`),
  KEY `aplicant_result_FK_1` (`exa_id`),
  CONSTRAINT `aplicant_result_FK` FOREIGN KEY (`aplicant_id`) REFERENCES `aplicant` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `aplicant_result_FK_1` FOREIGN KEY (`exa_id`) REFERENCES `exams` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- epam_tests.aplication definition

-- CREATE TABLE `aplication` (
  -- `aplicant_id` int NOT NULL,
  -- `speciality_id` int NOT NULL,
  -- `status` text NOT NULL,
  -- KEY `aplication_FK` (`aplicant_id`),
  -- KEY `aplication_FK_1` (`speciality_id`),
  -- CONSTRAINT `aplication_FK` FOREIGN KEY (`aplicant_id`) REFERENCES `aplicant` (`id`),
  -- CONSTRAINT `aplication_FK_1` FOREIGN KEY (`speciality_id`) REFERENCES `faculty` (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- epam_tests.speciality_requirements definition
CREATE TABLE `speciality_requirements` (
  `spec_id` int NOT NULL,
  `exam_id` int NOT NULL,
  `min` int DEFAULT NULL,
  `group` int DEFAULT NULL,
  KEY `speciality_requirements_FK` (`spec_id`),
  KEY `speciality_requirements_FK_1` (`exam_id`),
  CONSTRAINT `speciality_requirements_FK` FOREIGN KEY (`spec_id`) REFERENCES `faculty` (`id`),
  CONSTRAINT `speciality_requirements_FK_1` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;