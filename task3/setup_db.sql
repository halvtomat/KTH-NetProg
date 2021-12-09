CREATE DATABASE IF NOT EXISTS netprog_task3;
USE netprog_task3;

DROP TABLE results;
DROP TABLE selector;
DROP TABLE questions;
DROP TABLE users;
DROP TABLE quizzes;


CREATE TABLE IF NOT EXISTS users (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(32) UNIQUE NOT NULL,
	password VARCHAR(32) NOT NULL
);
INSERT INTO users (username,password) VALUES ('ada@kth.se', md5('qwerty'));
INSERT INTO users (username,password) VALUES ('beda@kth.se', md5('123456'));

CREATE TABLE IF NOT EXISTS questions (
	id INT AUTO_INCREMENT PRIMARY KEY,
	text VARCHAR(255) NOT NULL,
	options VARCHAR(255) NOT NULL,
	answer VARCHAR(255) NOT NULL
);
INSERT INTO questions (text,options,answer) VALUES ('Which planets are larger than earth?', 'Mercury/Mars/Saturn', '0/0/1');
INSERT INTO questions (text,options,answer) VALUES ('Which planets are farther away from the sun than earth?', 'Mercury/Mars/Saturn', '0/1/1');
INSERT INTO questions (text,options,answer) VALUES ('Which planets have rings?', 'Mercury/Mars/Saturn', '0/0/1');

CREATE TABLE IF NOT EXISTS quizzes (
	id INT AUTO_INCREMENT PRIMARY KEY,
	subject VARCHAR(255) NOT NULL
);
INSERT INTO quizzes (subject) VALUES ('Astronomy');


CREATE TABLE IF NOT EXISTS selector(
	quiz_id INT NOT NULL,
	question_id INT NOT NULL,
	FOREIGN KEY (quiz_id) REFERENCES quizzes(id),
	FOREIGN KEY (question_id) REFERENCES questions(id)
);
INSERT INTO selector (quiz_id, question_id) VALUES (1,1);
INSERT INTO selector (quiz_id, question_id) VALUES (1,2);
INSERT INTO selector (quiz_id, question_id) VALUES (1,3);

CREATE TABLE IF NOT EXISTS results (
	id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,
	quiz_id INT NOT NULL,
	score INT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);
INSERT INTO results (user_id,quiz_id,score) VALUES (1,1,0);
