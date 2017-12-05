DROP TABLE IF EXISTS groepen;
DROP TABLE IF EXISTS groepsindeling;
DROP TABLE IF EXISTS gebruikers;


CREATE TABLE gebruikers (
	gebruiker_id varchar(20),
        paswoord varchar(20),						
	voornaam varchar(20),
	achternaam varchar(20),
	PRIMARY KEY(gebruiker_id)
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


INSERT INTO gebruikers VALUES ('111','111','Nick','Todts');
INSERT INTO gebruikers VALUES ('222','222','Kevin','Todts');
INSERT INTO gebruikers VALUES ('333','333','Wouter','Symons');
INSERT INTO gebruikers VALUES ('444','444','Jan','Stappers');
INSERT INTO gebruikers VALUES ('999','999','Herman','Crauwels');

INSERT INTO groepen VALUES ('111','student');
INSERT INTO groepen VALUES ('222','student');
INSERT INTO groepen VALUES ('999','docent');

INSERT INTO groepsindeling (groepnummer,gebruiker_id) VALUES (1,'111');
INSERT INTO groepsindeling (groepnummer,gebruiker_id) VALUES (1,'222');
INSERT INTO groepsindeling (groepnummer,gebruiker_id) VALUES (1,'333');