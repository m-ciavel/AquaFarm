SHOW DATABASES;
USE aquafarm;

SHOW TABLES;
DESC users;

CREATE TABLE users(
ID int NOT NULL, 
user_ID int NOT NULL,
is_active bool NOT NULL, 
CONSTRAINT con_id_pk PRIMARY KEY(ID)
);

ALTER TABLE users
ADD CONSTRAINT UQ_users_user_ID UNIQUE(user_ID);

ALTER TABLE users
ADD is_active bool;

ALTER TABLE users
MODIFY is_active bool NOT NULL;

CREATE TABLE userTable(
user_ID int NOT NULL, 
user_Name varchar(255) NOT NULL,
age tinyint NOT NULL,
pass_salt varchar(255) NOT NULL, 
pass_hash varchar(255) NOT NULL, 
created_date date,
updated_date date,
logged_in boolean,
CONSTRAINT con_user_Name_PK PRIMARY KEY (user_Name), 
CONSTRAINT fk_users_user_ID FOREIGN KEY (user_ID) REFERENCES users(user_ID)
);


ALTER TABLE `userTable`
DROP CONSTRAINT fk_users_user_ID;

ALTER TABLE `userTable`
ADD CONSTRAINT `fk_users_user_ID`
FOREIGN KEY (`user_ID`) REFERENCES `users` (`user_ID`)
ON UPDATE CASCADE;


ALTER TABLE userTable
ADD pass_salt varchar(255);

ALTER TABLE userTable
MODIFY COLUMN pass_hash varchar(1024) NOT NULL;

SHOW FULL COLUMNS FROM users;

SHOW TABLES;
DESC users;
DESC userTable; 


INSERT INTO users VALUES (
0, 1234567890, TRUE
);

INSERT INTO userTable VALUES (
1234567890, 'user0', 81, 'salt', 'hash', '2023-10-26', NULL, FALSE
);

INSERT INTO users VALUES (
0, 1234567890, TRUE
);

INSERT INTO userTable VALUES (
1234567890, 'user0', 81, 'salt', 'hash', '2023-10-26', NULL, FALSE
);

UPDATE users 
SET user_Name = 'user'
WHERE ID = 0;


DELETE FROM userTable WHERE user_ID = 823497376;
DELETE FROM users WHERE user_ID = 823497376;
DELETE FROM users WHERE ID = 0;

SELECT * FROM users;
SELECT * FROM userTable;

SELECT user_Name FROM userTable WHERE user_Name = 'user';
SELECT pass_salt FROM userTable where user_name = 'user0';
SELECT pass_hash FROM userTable where user_name = 'user0';
SELECT * FROM userTable where user_name = 'user0';
SELECT ID FROM users;
SELECT * FROM users WHERE id=(SELECT max(ID) FROM users);

SELECT ID FROM users
ORDER BY ID DESC
LIMIT 1;

DROP TABLE users;
DROP TABLE userTable;
