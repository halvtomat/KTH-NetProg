CREATE DATABASE IF NOT EXISTS netprog_project;
USE netprog_project;

CREATE TABLE IF NOT EXISTS users (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(32) UNIQUE NOT NULL,
	password VARCHAR(32) NOT NULL
);
INSERT INTO users (username,password) VALUES ('daniel', md5('qwerty'));
INSERT INTO users (username,password) VALUES ('erik', md5('qwerty'));

CREATE TABLE IF NOT EXISTS queues (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) UNIQUE NOT NULL
);

INSERT INTO queues (name) VALUES ('ID1212');
INSERT INTO queues (name) VALUES ('Allmanhandledning');
INSERT INTO queues (name) VALUES ('ADK');

CREATE TABLE IF NOT EXISTS participants (
	id INT AUTO_INCREMENT PRIMARY KEY,
	queue_id INT NOT NULL,
	user_id INT NOT NULL,
	location VARCHAR(255) NOT NULL,
	comment VARCHAR(255) NOT NULL DEFAULT '',
	help BOOLEAN NOT NULL DEFAULT 1,
	receiving_help BOOLEAN NOT NULL DEFAULT 0,
	time_joined TIMESTAMP NOT NULL DEFAULT NOW(),
	FOREIGN KEY (queue_id) REFERENCES queues(id),
	FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO participants (queue_id,user_id,location,comment,help) VALUES (1, 1,'hemma', 'sho de benim jag e hemma', 1);
INSERT INTO participants (queue_id,user_id,location,comment,help) VALUES (1, 2, 'ute', 'kom hit', 0);
INSERT INTO participants (queue_id,user_id,location,comment,help) VALUES (2, 1, 'stockholm', 'jag vill ha hjelp', 1);
