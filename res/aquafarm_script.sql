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

CREATE TABLE fishTbl(
fish_ID int NOT NULL,
fish_type varchar(255) NOT NULL,
CONSTRAINT con_fish_ID_PK PRIMARY KEY (fish_ID)
);

CREATE TABLE userFishInvTbl(
userFishID int NOT NULL,
user_Name varchar(255) NOT NULL,
fish_ID int NOT NULL,
added_date date,
CONSTRAINT con_userFishID_PK PRIMARY KEY (userFishID),
CONSTRAINT fk_userTable_user_Name FOREIGN KEY (user_Name) REFERENCES userTable(user_Name) ON UPDATE CASCADE, 
CONSTRAINT fk_fishTbl_fish_ID FOREIGN KEY (fish_ID) REFERENCES fishTbl(fish_ID) ON UPDATE CASCADE
);

CREATE TABLE userFishStatusTbl(
userFishID int NOT NULL,
fish_name varchar(255), 
fish_gender varchar(255),
fish_size int NOT NULL, 
CONSTRAINT con_fish_name_PK PRIMARY KEY (fish_name),
CONSTRAINT fk_userFishInvTbl_userFishID FOREIGN KEY (userFishID) REFERENCES userFishInvTbl(userFishID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE userInvTbl(
itemID int NOT NULL, 
user_Name varchar(255) NOT NULL,
money int NOT NULL,
CONSTRAINT con_itemIDe_PK PRIMARY KEY (itemID),
CONSTRAINT fk_userTable_userInvTbl FOREIGN KEY (user_Name) REFERENCES userTable(user_Name) ON UPDATE CASCADE 
);

INSERT INTO userInvTbl VALUES(
1, 'userdel', 100
);

UPDATE userInvTbl
SET money = 150
WHERE user_Name = 'user0';

SELECT  money FROM userInvTbl WHERE user_Name = 'userdel';

SELECT * FROM userInvTbl WHERE itemID=(SELECT max(itemID) FROM userInvTbl);

SELECT * FROM userFishInvTbl WHERE userFishID=(SELECT max(userFishID) FROM userFishInvTbl);
SELECT fish_ID AS fishType FROM fishtbl WHERE fish_type = 'Guppy';

SELECT userFishInvTbl.userFishID, fishTbl.fish_type, userFishStatusTbl.fish_name, userFishStatusTbl.fish_gender, userFishStatusTbl.fish_size
FROM userfishinvtbl 
JOIN fishtbl
ON userfishinvtbl.fish_ID = fishtbl.fish_ID
LEFT JOIN userFishStatusTbl 
ON userFishInvTbl.userFishID = userFishStatusTbl.userFishID
WHERE user_Name = 'user0'
ORDER BY userFishID;

INSERT INTO userFishInvTbl VALUES (
0, 'user0', 4, '2023-11-27'
);
INSERT INTO userFishStatusTbl VALUES (
0, 'Pierre Guppy', 'they', 0	
);
INSERT INTO userFishInvTbl VALUES (
1, 'user0', 2, '2023-11-27'
);
INSERT INTO userFishStatusTbl VALUES (
1, 'Clown Leclerc', 'male', 2	
);
INSERT INTO userFishInvTbl VALUES (
2, 'user0', 2, '2023-11-27'
);
INSERT INTO userFishStatusTbl VALUES (
2, 'Guppy Russel', 'they', 0	
);

UPDATE userFishInvTbl 
SET fish_ID = 2
WHERE userFishID = 1;

CREATE TABLE sessionTbl(
user_Name varchar(255) NOT NULL,
session_ID int NOT NULL, 
login_date date,
logout_date date,
CONSTRAINT con_session_ID_PK PRIMARY KEY (session_ID),
CONSTRAINT fk_userTable_user_Name FOREIGN KEY (user_Name) REFERENCES userTable(user_Name) ON UPDATE CASCADE
);


ALTER TABLE `userTable`
DROP CONSTRAINT fk_users_user_ID;

ALTER TABLE `userTable`
ADD CONSTRAINT `fk_users_user_ID`
FOREIGN KEY (`user_ID`) REFERENCES `users` (`user_ID`)
ON UPDATE CASCADE;

ALTER TABLE `userFishStatusTbl`
DROP CONSTRAINT fk_userFishInvTbl_userFishID;

ALTER TABLE `userFishStatusTbl`
ADD CONSTRAINT `fk_userFishInvTbl_userFishID`
FOREIGN KEY (userFishID) REFERENCES userFishInvTbl(userFishID)
ON UPDATE CASCADE ON DELETE CASCADE;


ALTER TABLE userTable
ADD pass_salt varchar(255);

ALTER TABLE userTable
MODIFY COLUMN pass_hash varchar(255) NOT NULL;

SHOW FULL COLUMNS FROM users;

SHOW TABLES;
DESC users;
DESC userTable; 
DESC sessionTbl; 
DESC fishTbl;
DESC userInvTbl;


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


DELETE FROM userTable WHERE user_ID = 1008095481;
DELETE FROM users WHERE user_ID = 631953536;
DELETE FROM users WHERE ID = 0;
DELETE FROM  userFishInvTbl WHERE userFishID = 6;


DELETE tinv FROM userfishinvtbl tinv INNER JOIN userFishstatusTbl tstat ON tinv.userFishid = tstat.userFishid WHERE fish_name = 'Guppy Russel';

UPDATE userFishstatusTbl 
SET fish_gender = 'they'
WHERE userFishID = 0;

SELECT * FROM users;
SELECT * FROM userTable;
SELECT * FROM userInvTbl;
SELECT * FROM sessionTbl;
SELECT * FROM fishTbl;
SELECT * FROM userFishInvTbl;
SELECT * FROM userFishstatusTbl;
SELECT * FROM userFishInvTbl WHERE userFishID = 2;

INSERT INTO fishTbl VALUES 
(0, 'AtlanticBass'), 
(1, 'BlueGill'), 
(2, 'Clownfish'), 
(3, 'GoldenTench'), 
(4, 'Guppy'), 
(5, 'HIghFinBandedShark')
;

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
DROP TABLE sessusersionTbl;
