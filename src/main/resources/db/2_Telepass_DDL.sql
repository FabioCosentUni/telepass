--------------------------------------------------------
--  File creato - marted�-dicembre-05-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table TB_CASELLO
--------------------------------------------------------

CREATE SEQUENCE seq_viaggio
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 9999
    NOCYCLE
  NOCACHE;

CREATE SEQUENCE seq_transponder
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 9999
    NOCYCLE
  NOCACHE;


CREATE SEQUENCE seq_casello
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 9999
    NOCYCLE
  NOCACHE;

  CREATE TABLE TELEPASS.TB_CASELLO(
    ID_CASELLO_PK NUMBER(8,0) PRIMARY KEY NOT NULL,
	KM NUMBER(5,0),
	INGRESSI NUMBER(3,0),
	AUTOSTRADA VARCHAR(30 ),

	CONSTRAINT CHECK_AUTOSTRADA CHECK (UPPER(AUTOSTRADA) IN (
    'A1', 'A24', 'A25', 'TANGENZIALE', 'A5', 'A11', 'A12', 'A13'
    ))
   );

--------------------------------------------------------
--  DDL for Table TB_UTENTE
--------------------------------------------------------
    CREATE TABLE TELEPASS.TB_UTENTE(
    CODICE_FISCALE_PK VARCHAR(16) NOT NULL PRIMARY KEY,
    EMAIL VARCHAR(40) NOT NULL UNIQUE,
    NOME VARCHAR(40) NOT NULL,
    COGNOME VARCHAR(40) NOT NULL,
    PASSWORD VARCHAR(40) NOT NULL,
    INDIRIZZO_FATT VARCHAR(50) NOT NULL,
    CITTA_FATT VARCHAR(40) NOT NULL,
    REGIONE_FATT VARCHAR(40) NOT NULL,
    AMMINISTRATORE NUMBER(1,0) DEFAULT 0 NOT NULL,

    CONSTRAINT CHECK_EMAIL CHECK (REGEXP_LIKE (EMAIL, '^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$')),
    CONSTRAINT CHECK_AMMINISTRATORE CHECK (AMMINISTRATORE IN (0,1)),
    CONSTRAINT CHECK_CF CHECK (REGEXP_LIKE (CODICE_FISCALE_PK, '^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$'))
    );

--------------------------------------------------------
--  DDL for Table TB_METODO_PAG
--------------------------------------------------------
    CREATE TABLE TELEPASS.TB_METODO_PAG (
        NUM_CARTA_PK VARCHAR(16) PRIMARY KEY NOT NULL,
        NOME_PRP VARCHAR(30) NOT NULL,
        COGNOME_PRP VARCHAR(30) NOT NULL,
        SCADENZA DATE NOT NULL,
        CVC VARCHAR(3) NOT NULL,
        TIPOLOGIA VARCHAR(30) NOT NULL,

        CONSTRAINT TIPOLOGIA_CARTE_AMMESSE CHECK (UPPER(TIPOLOGIA) IN (
           'BANCOMAT', 'CARTA DI CREDITO'
        ))
    );
--------------------------------------------------------
--  DDL for Table TB_TRANSPONDER
--------------------------------------------------------
  CREATE TABLE TELEPASS.TB_TRANSPONDER(
    CODICE_TRANSP_PK NUMBER(10) PRIMARY KEY NOT NULL ,
	CF_UTENTE_FK VARCHAR(16) NOT NULL,
	METODO_PAG VARCHAR(16),
	ATTIVO NUMBER(1,0) DEFAULT 0 NOT NULL,
	PLUS NUMBER(1,0) DEFAULT 0 NOT NULL,

    CONSTRAINT FK_TB_TRANSPONDER_TB_UTENTE FOREIGN KEY (CF_UTENTE_FK) REFERENCES TB_UTENTE(CODICE_FISCALE_PK) ON DELETE CASCADE,
    CONSTRAINT FK_TRANSPONDER_METODO_PAG FOREIGN KEY (METODO_PAG) REFERENCES TB_METODO_PAG(NUM_CARTA_PK) ON DELETE CASCADE,
    CONSTRAINT CHECK_PLUS CHECK (PLUS IN (0,1)),
    CONSTRAINT CHECK_ATTIVO CHECK (ATTIVO IN (0,1))
   );
--------------------------------------------------------
--  DDL for Table TB_VEICOLO
--------------------------------------------------------
  CREATE TABLE TELEPASS.TB_VEICOLO(
    TARGA_PK VARCHAR(10) NOT NULL PRIMARY KEY ,
	MODELLO VARCHAR(30) NOT NULL,
	BRAND VARCHAR(30) NOT NULL,
	TIPOLOGIA_VE VARCHAR(30) NOT NULL,
	COLORE VARCHAR(30) NOT NULL,
	TRANSPONDER_FK NUMBER(10) NOT NULL,

    CONSTRAINT FK_TB_VEICOLO_TB_TRANSPONDER FOREIGN KEY (TRANSPONDER_FK) REFERENCES TB_TRANSPONDER(CODICE_TRANSP_PK) ON DELETE CASCADE,
    CONSTRAINT TARGA_LENGTH CHECK (LENGTH(TARGA_PK)=7),
    CONSTRAINT TIPOLOGIA_VEICOLO_AMMESSE CHECK (UPPER(TIPOLOGIA_VE) IN (
    'CLASSE A', 'CLASSE B', 'CLASSE 3', 'CLASSE 4', 'CLASSE 5'
    ))
   );
--------------------------------------------------------
--  DDL for Table TB_VIAGGIO
--------------------------------------------------------

  CREATE TABLE TELEPASS.TB_VIAGGIO (
    ID_VIAGGIO_PK NUMBER(8,0) PRIMARY KEY NOT NULL,
    TARGA_VE_FK VARCHAR(10) NOT NULL,
	CASELLO_ENTRY_FK NUMBER(8,0) NOT NULL,
	TIME_ENTRY DATE NOT NULL,
	CASELLO_EXIT_FK NUMBER(8,0),
	TIME_EXIT DATE,
	PEDAGGIO NUMBER(8,2),
	PAGATO_FLAG NUMBER(1,0) DEFAULT 0 NOT NULL,

    CONSTRAINT FK_TB_VIAGGIO_TB_VEICOLO FOREIGN KEY (TARGA_VE_FK) REFERENCES TB_VEICOLO(TARGA_PK) ON DELETE CASCADE,
    CONSTRAINT FK_TB_VIAGGIO_TB_CASELLO FOREIGN KEY (CASELLO_ENTRY_FK) REFERENCES TB_CASELLO(ID_CASELLO_PK) ON DELETE CASCADE,
    CONSTRAINT FK_TB_VIAGGIO_TB_CASELLO_2 FOREIGN KEY (CASELLO_EXIT_FK) REFERENCES TB_CASELLO(ID_CASELLO_PK) ON DELETE CASCADE,
    CONSTRAINT CHECK_DATE_VIAGGIO CHECK (TIME_ENTRY < TIME_EXIT),
    CONSTRAINT CHECK_PAGATO CHECK (PAGATO_FLAG IN (0,1))
   );
