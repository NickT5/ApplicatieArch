DROP TABLE IF EXISTS groepen;
DROP TABLE IF EXISTS groepsindeling;
DROP TABLE IF EXISTS nietvoorkeur;
DROP TABLE IF EXISTS voorkeur;
DROP TABLE IF EXISTS gebruikers;

CREATE TABLE gebruikers (
	gebruiker_id varchar(20),
        paswoord varchar(20),						
	voornaam varchar(20),
	achternaam varchar(20),
	PRIMARY KEY(gebruiker_id)
);

CREATE TABLE voorkeur (
        id int NOT NULL AUTO_INCREMENT,
        gebruiker_id varchar(20),
        vk varchar(20),
        PRIMARY KEY(id)
);

CREATE TABLE nietvoorkeur (
        id int NOT NULL AUTO_INCREMENT,
        gebruiker_id varchar(20),
        nvk varchar(20),
        PRIMARY KEY(id)
);

CREATE TABLE groepsindeling (
        id int NOT NULL AUTO_INCREMENT,
	groepnummer int,
	gebruiker_id varchar(20),
	PRIMARY KEY(id),
	FOREIGN KEY(gebruiker_id) REFERENCES gebruikers(gebruiker_id)
	
);

CREATE TABLE groepen (
	id varchar(20),
	soort	varchar(20),
	PRIMARY KEY(id),
	FOREIGN KEY(id) REFERENCES gebruikers(gebruiker_id)
);


INSERT INTO gebruikers VALUES ('1000','1000','Nick','Todts');
INSERT INTO gebruikers VALUES ('1001','1001','Kevin','Todts');
INSERT INTO gebruikers VALUES ('1002','1002','Wouter','Symons');
INSERT INTO gebruikers VALUES ('1003','1003','Jan','Stappers');
INSERT INTO gebruikers VALUES ('1004','1004','Arno','Van Hoorebeeck');
INSERT INTO gebruikers VALUES ('1005','1005','Jan','Stappers');
INSERT INTO gebruikers VALUES ('1006','1006','Wouter','Van de Wiele');
INSERT INTO gebruikers VALUES ('1007','1007','Bert','Gadisseur');
INSERT INTO gebruikers VALUES ('1008','1008','Siebren','Mylle');
INSERT INTO gebruikers VALUES ('1009','1009','Robin','Maes');
INSERT INTO gebruikers VALUES ('1010','1010','Jonas','Smet');
INSERT INTO gebruikers VALUES ('1011','1011','Niels','De Bruyne');
INSERT INTO gebruikers VALUES ('1012','1012','Michiel','Beke');
INSERT INTO gebruikers VALUES ('1013','1013','Dave','Bueds');
INSERT INTO gebruikers VALUES ('1014','1014','Daan','Huyghe');
INSERT INTO gebruikers VALUES ('1015','1015','Tom','Van Bogaert');
INSERT INTO gebruikers VALUES ('1016','1016','Steven','De Jonghe');
INSERT INTO gebruikers VALUES ('1017','1017','Donny','Polfliet');
INSERT INTO gebruikers VALUES ('9001','9001','Herman','Crauwels');
INSERT INTO gebruikers VALUES ('9002','9002','Joost','Vennekens');

INSERT INTO groepen VALUES ('1000','student');
INSERT INTO groepen VALUES ('1001','student');
INSERT INTO groepen VALUES ('1002','student');
INSERT INTO groepen VALUES ('1003','student');
INSERT INTO groepen VALUES ('1004','student');
INSERT INTO groepen VALUES ('1005','student');
INSERT INTO groepen VALUES ('1006','student');
INSERT INTO groepen VALUES ('1007','student');
INSERT INTO groepen VALUES ('1008','student');
INSERT INTO groepen VALUES ('1009','student');
INSERT INTO groepen VALUES ('1010','student');
INSERT INTO groepen VALUES ('1011','student');
INSERT INTO groepen VALUES ('1012','student');
INSERT INTO groepen VALUES ('1013','student');
INSERT INTO groepen VALUES ('1014','student');
INSERT INTO groepen VALUES ('1015','student');
INSERT INTO groepen VALUES ('1016','student');
INSERT INTO groepen VALUES ('1017','student');
INSERT INTO groepen VALUES ('9001','docent');
INSERT INTO groepen VALUES ('9002','docent');

INSERT INTO groepsindeling (groepnummer,gebruiker_id) VALUES (1,'1000');
INSERT INTO groepsindeling (groepnummer,gebruiker_id) VALUES (1,'1001');
INSERT INTO groepsindeling (groepnummer,gebruiker_id) VALUES (1,'1002');
INSERT INTO groepsindeling (groepnummer,gebruiker_id) VALUES (2,'1005');
INSERT INTO groepsindeling (groepnummer,gebruiker_id) VALUES (2,'1006');

INSERT INTO voorkeur (gebruiker_id,vk) VALUES ('1000','1003');

INSERT INTO nietvoorkeur (gebruiker_id,nvk) VALUES ('1006','1011');