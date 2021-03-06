
CREATE TABLE IF NOT EXISTS PAZIENTE(
	ID_PAZIENTE serial,
	NOME varchar(30),
	COGNOME varchar(30),
	DATA_NASCITA TIMESTAMP,
	INDIRIZZO varchar(30),
	CF varchar(16) unique,
	
	PRIMARY KEY(ID_PAZIENTE)
);

CREATE TABLE IF NOT EXISTS VISITA(
	id serial,
	nome varchar(50),
	tipologia varchar(50),
	descrizione varchar(50),
	esami_necessari varchar(50),
	note text,
	stato boolean,
	data_cancellazione timestamp,
	
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS PRENOTAZIONE(
	id serial,
	codice varchar(50) unique,
	data_appuntamento timestamp,
	stato boolean,
	data_cancellazione timestamp,
	id_paziente integer,
	id_visita integer,
	
	PRIMARY KEY(id),
	CONSTRAINT prenotazione_fk1 FOREIGN KEY(id_paziente) REFERENCES paziente(id_paziente),
	CONSTRAINT prenotazione_fk2 FOREIGN KEY(id_visita) REFERENCES visita(id)
);

CONSTRAINT prenotazione_fk3 FOREIGN KEY(id_struttura) REFERENCES struttura(id);

CREATE TABLE IF NOT EXISTS STRUTTURA(
	id serial,
	nome varchar(50),
	asl varchar(50),
	citta varchar(50),
	provincia varchar(50),
	cap int,
	indirizzo varchar(50),
	reparto varchar(50),
	n_piani int,
	capacita_max int,
	stato boolean,
	data_cancellazione timestamp,

	PRIMARY KEY(id),
);


