SHOW DATABASES;
USE aquafarm;

SHOW TABLES;

CREATE TABLE users(
ID int NOT NULL, 
user_ID int NOT NULL,
is_active bool NOT NULL, 
CONSTRAINT con_id_pk PRIMARY KEY(ID)
);

ALTER TABLE users
ADD CONSTRAINT UQ_users_user_ID UNIQUE(user_ID);

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
CONSTRAINT fk_userTable_user_Name FOREIGN KEY (user_Name) REFERENCES userTable(user_Name) ON UPDATE CASCADE ON DELETE CASCADE, 
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
CONSTRAINT fk_userTable_userInvTbl FOREIGN KEY (user_Name) REFERENCES userTable(user_Name) ON UPDATE CASCADE ON DELETE CASCADE
);

SHOW FULL COLUMNS FROM users;

SHOW TABLES;
DESC users;
DESC userTable; 
DESC userInvTbl; 
DESC fishTbl; 
DESC userFishInvTbl; 
DESC userFishStatusTbl; 


SELECT * FROM users;
SELECT * FROM userTable;
SELECT * FROM userInvTbl;
SELECT * FROM fishTbl;
SELECT * FROM userFishInvTbl;
SELECT * FROM userFishStatusTbl;


SELECT userFishInvTbl.userFishID, fishTbl.fish_type, userFishStatusTbl.fish_name, userFishStatusTbl.fish_gender, userFishStatusTbl.fish_size
FROM userfishinvtbl 
JOIN fishtbl
ON userfishinvtbl.fish_ID = fishtbl.fish_ID
LEFT JOIN userFishStatusTbl 
ON userFishInvTbl.userFishID = userFishStatusTbl.userFishID
WHERE user_Name = 'user0'
ORDER BY userFishID;


INSERT INTO users VALUES (
0, 1234567890, TRUE
);

INSERT INTO userTable VALUES (
1234567890, 'user0', 81, 'salt', 'hash', '2023-10-26', NULL, FALSE
);

INSERT INTO fishTbl VALUES 
(0, 'AtlanticBass', 10), 
(1, 'BlueGill', 20), 
(2, 'Clownfish', 30), 
(3, 'GoldenTench', 40), 
(4, 'Guppy', 50), 
(5, 'HIghFinBandedShark', 60)
;

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

UPDATE userTable 
SET user_Name = 'user'
WHERE ID = 1234567890;

DELETE FROM userTable WHERE user_ID = 1245504461;
DELETE FROM users WHERE user_ID = 1245504461;
DELETE FROM users WHERE ID = 0;

