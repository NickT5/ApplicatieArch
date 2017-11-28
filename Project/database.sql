DROP TABLE IF EXISTS groepen;
DROP TABLE IF EXISTS groepsindeling;
DROP TABLE IF EXISTS gebruikers;


CREATE TABLE gebruikers (
	gebruiker_id int,						
	voornaam varchar(20),
	achternaam varchar(20),
	PRIMARY KEY(gebruiker_id)
);

CREATE TABLE groepsindeling (
	indeling_id int AUTO_INCREMENT,
	gebruiker_id int,
	PRIMARY KEY(indeling_id),
	FOREIGN KEY(gebruiker_id) REFERENCES gebruikers(gebruiker_id)
	
);

CREATE TABLE groepen (
	id int,
	soort	varchar(20),
	PRIMARY KEY(id),
	FOREIGN KEY(id) REFERENCES gebruikers(gebruiker_id)
);






