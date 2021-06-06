-- Faculty load

INSERT INTO epam_tests.faculty (name, count) VALUES('Math', 5);
INSERT INTO epam_tests.faculty (name, count) VALUES('Web', 3);
INSERT INTO epam_tests.faculty (name, count) VALUES('Economy', 2);
INSERT INTO epam_tests.faculty (name, count) VALUES('Leng', 2);
INSERT INTO epam_tests.faculty (name, count) VALUES('Hist', 2);

-- Exam load

INSERT INTO epam_tests.exams (name) VALUES('RU_LENG');
INSERT INTO epam_tests.exams (name) VALUES('BY_LENG');
INSERT INTO epam_tests.exams (name) VALUES('MATH');
INSERT INTO epam_tests.exams (name) VALUES('PHITH');

-- Speciality requirements load

INSERT INTO epam_tests.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(1, 1, 10, 1);
INSERT INTO epam_tests.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(1, 2, 10, 1);
INSERT INTO epam_tests.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(1, 3, 20, null);
INSERT INTO epam_tests.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(2, 1, 20, null);
INSERT INTO epam_tests.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(2, 3, 30, null);
INSERT INTO epam_tests.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(3, 1, 10, null);
INSERT INTO epam_tests.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(3, 4, 10, null);
INSERT INTO epam_tests.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(4, 1, 20, null);
INSERT INTO epam_tests.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(4, 2, 20, null);
INSERT INTO epam_tests.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(5, 1, 30, null);
INSERT INTO epam_tests.speciality_requirements (spec_id, exam_id, min, `group`) VALUES(5, 2, 50, null);

-- User load

INSERT INTO epam_tests.users (login, password, `type`) VALUES('Victor', '1111', 'USER');
INSERT INTO epam_tests.users (login, password, `type`) VALUES('Oleg', '1111', 'USER');
INSERT INTO epam_tests.users (login, password, `type`) VALUES('Petro', '1111', 'USER');
INSERT INTO epam_tests.users (login, password, `type`) VALUES('Mikola', '1111', 'USER');
INSERT INTO epam_tests.users (login, password, `type`) VALUES('Nikola', '1111', 'USER');
INSERT INTO epam_tests.users (login, password, `type`) VALUES('Vladimir', '1111', 'USER');
INSERT INTO epam_tests.users (login, password, `type`) VALUES('Kot', '1111', 'USER');
INSERT INTO epam_tests.users (login, password, `type`) VALUES('Pes', '1111', 'USER');


-- Aplicant load
INSERT INTO epam_tests.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(1 ,'Виктор', 'Иванов', 15, 1);
INSERT INTO epam_tests.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(2 ,'Олег', 'Петров', 15, 1);
INSERT INTO epam_tests.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(3 ,'Петро', 'Петровский', 16, 2);
INSERT INTO epam_tests.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(4 ,'Mikola', 'Polsky', 26, 2);
INSERT INTO epam_tests.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(5 ,'Николай', 'Моленов', 32, 3);
INSERT INTO epam_tests.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(6 ,'Владимир', 'Средний', 35, 3);
INSERT INTO epam_tests.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(7 ,'Кот', 'Домашний', 36, 3);
INSERT INTO epam_tests.aplicant (user_id, name, surname, certificate, faculty_id) VALUES(8 ,'Пес', 'Домашний', 35, 3);

-- Aplicant result load

INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(1, 1, 15);
INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(1, 3, 10);
INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(2, 2, 20);
INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(2, 3, 20);
INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(3, 1, 30);
INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(3, 3, 30);
INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(4, 1, 50);
INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(4, 4, 50);
INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(5, 1, 40);
INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(5, 4, 10);
INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(6, 1, 50);
INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(6, 4, 10);
INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(7, 1, 10);
INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(7, 2, 50);
INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(8, 1, 60);
INSERT INTO epam_tests.aplicant_result (aplicant_id, exa_id, `result`) VALUES(8, 2, 10);

