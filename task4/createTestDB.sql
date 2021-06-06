DROP DATABASE IF EXISTS `test`;
CREATE DATABASE `test`;
USE `test`;

-- test.exams definition

CREATE TABLE `exams` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test.faculty definition

CREATE TABLE `faculty` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `count` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- test.users definition

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

-- test.aplicant_result definition

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
-- test.aplication definition

-- CREATE TABLE `aplication` (
  -- `aplicant_id` int NOT NULL,
  -- `speciality_id` int NOT NULL,
  -- `status` text NOT NULL,
  -- KEY `aplication_FK` (`aplicant_id`),
  -- KEY `aplication_FK_1` (`speciality_id`),
  -- CONSTRAINT `aplication_FK` FOREIGN KEY (`aplicant_id`) REFERENCES `aplicant` (`id`),
  -- CONSTRAINT `aplication_FK_1` FOREIGN KEY (`speciality_id`) REFERENCES `faculty` (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test.speciality_requirements definition
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

-- Faculty load

INSERT INTO test.faculty (name, count) VALUES('Math', 5);
INSERT INTO test.faculty (name, count) VALUES('Web', 3);
INSERT INTO test.faculty (name, count) VALUES('Economy', 2);
INSERT INTO test.faculty (name, count) VALUES('Leng', 2);
INSERT INTO test.faculty (name, count) VALUES('Hist', 2);

-- Exam load

INSERT INTO test.exams (name) VALUES('RU_LENG');
INSERT INTO test.exams (name) VALUES('BY_LENG');
INSERT INTO test.exams (name) VALUES('MATH');
INSERT INTO test.exams (name) VALUES('PHITH');

-- Speciality requirements load

INSERT INTO test.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(1, 1, 10, 1);
INSERT INTO test.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(1, 2, 10, 1);
INSERT INTO test.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(1, 3, 20, null);
INSERT INTO test.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(2, 1, 20, null);
INSERT INTO test.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(2, 3, 30, null);
INSERT INTO test.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(3, 1, 10, null);
INSERT INTO test.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(3, 4, 10, null);
INSERT INTO test.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(4, 1, 20, null);
INSERT INTO test.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(4, 2, 20, null);
INSERT INTO test.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(5, 1, 30, null);
INSERT INTO test.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(5, 2, 50, null);

-- User load

INSERT INTO test.users (login, password, `type`) VALUES('Victor', '1111', 'USER');
INSERT INTO test.users (login, password, `type`) VALUES('Oleg', '1111', 'USER');
INSERT INTO test.users (login, password, `type`) VALUES('Petro', '1111', 'USER');
INSERT INTO test.users (login, password, `type`) VALUES('Mikola', '1111', 'USER');
INSERT INTO test.users (login, password, `type`) VALUES('Nikola', '1111', 'USER');
INSERT INTO test.users (login, password, `type`) VALUES('Vladimir', '1111', 'USER');
INSERT INTO test.users (login, password, `type`) VALUES('Kot', '1111', 'USER');
INSERT INTO test.users (login, password, `type`) VALUES('Pes', '1111', 'USER');
INSERT INTO test.users (login, password, `type`) VALUES('Den', '1111', 'USER');


-- Aplicant load
INSERT INTO test.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(1 ,'Виктор', 'Иванов', 15, 1);
INSERT INTO test.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(2 ,'Олег', 'Петров', 15, 1);
INSERT INTO test.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(3 ,'Петро', 'Петровский', 16, 2);
INSERT INTO test.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(4 ,'Mikola', 'Polsky', 26, 2);
INSERT INTO test.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(5 ,'Николай', 'Моленов', 32, 3);
INSERT INTO test.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(6 ,'Владимир', 'Средний', 35, 3);
INSERT INTO test.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(7 ,'Кот', 'Домашний', 36, 3);
INSERT INTO test.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(8 ,'Пес', 'Домашний', 35, 3);

-- Aplicant result load

INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(1, 1, 15);
INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(1, 3, 10);
INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(2, 2, 20);
INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(2, 3, 20);
INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(3, 1, 30);
INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(3, 3, 30);
INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(4, 1, 50);
INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(4, 4, 50);
INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(5, 1, 40);
INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(5, 4, 10);
INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(6, 1, 50);
INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(6, 4, 10);
INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(7, 1, 10);
INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(7, 2, 50);
INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(8, 1, 60);
INSERT INTO test.aplicant_result (aplicant_id, exa_id, `result`) VALUES(8, 2, 10);

