--------------------------------------------------------
--  File creato - marted�-dicembre-05-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table TB_CASELLO
--------------------------------------------------------

  CREATE TABLE "TELEPASS"."TB_CASELLO" 
   (	"ID_CASELLO_PK" NUMBER(8,0), 
	"KM" NUMBER(5,0), 
	"INGRESSI" NUMBER(3,0), 
	"AUTOSTRADA" VARCHAR2(30 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TB_TRANSPONDER
--------------------------------------------------------

  CREATE TABLE "TELEPASS"."TB_TRANSPONDER" 
   (	"CODICE_TRANSP_PK" VARCHAR2(10 BYTE), 
	"CF_UTENTE_FK" VARCHAR2(16 BYTE), 
	"METODO_PAG" VARCHAR2(30 BYTE), 
	"ATTIVO" NUMBER(1,0) DEFAULT 1, 
	"PLUS" NUMBER(1,0) DEFAULT 0
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TB_UTENTE
--------------------------------------------------------

  CREATE TABLE "TELEPASS"."TB_UTENTE" 
   (	"CODICE_FISCALE_PK" VARCHAR2(16 BYTE), 
	"NOME" VARCHAR2(40 BYTE), 
	"COGNOME" VARCHAR2(40 BYTE), 
	"EMAIL" VARCHAR2(40 BYTE), 
	"INDIRIZZO_FATT" VARCHAR2(50 BYTE), 
	"CITTA_FATT" VARCHAR2(40 BYTE), 
	"REGIONE_FATT" VARCHAR2(40 BYTE), 
	"AMMINISTRATORE" NUMBER(1,0)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TB_VEICOLO
--------------------------------------------------------

  CREATE TABLE "TELEPASS"."TB_VEICOLO" 
   (	"TARGA_PK" VARCHAR2(10 BYTE), 
	"MODELLO" VARCHAR2(30 BYTE), 
	"BRAND" VARCHAR2(30 BYTE), 
	"TIPOLOGIA_VE" VARCHAR2(30 BYTE), 
	"COLORE" VARCHAR2(30 BYTE), 
	"TRANSPONDER_FK" VARCHAR2(10 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TB_VIAGGIO
--------------------------------------------------------

  CREATE TABLE "TELEPASS"."TB_VIAGGIO" 
   (	"TARGA_VE_PK" VARCHAR2(10 BYTE), 
	"CASELLO_ENTRY_FK_PK" NUMBER(8,0), 
	"TIME_ENTRY_PK" DATE, 
	"CASELLO_EXIT_FK" NUMBER(8,0), 
	"TIME_EXIT" DATE, 
	"PEDAGGIO" NUMBER(8,2), 
	"PAGATO_FLAG" NUMBER(1,0) DEFAULT 0
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into TELEPASS.TB_CASELLO
SET DEFINE OFF;
REM INSERTING into TELEPASS.TB_TRANSPONDER
SET DEFINE OFF;
REM INSERTING into TELEPASS.TB_UTENTE
SET DEFINE OFF;
REM INSERTING into TELEPASS.TB_VEICOLO
SET DEFINE OFF;
REM INSERTING into TELEPASS.TB_VIAGGIO
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index PK_VIAGGIO
--------------------------------------------------------

  CREATE UNIQUE INDEX "TELEPASS"."PK_VIAGGIO" ON "TELEPASS"."TB_VIAGGIO" ("TARGA_VE_PK", "CASELLO_ENTRY_FK_PK", "TIME_ENTRY_PK") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table TB_VEICOLO
--------------------------------------------------------

  ALTER TABLE "TELEPASS"."TB_VEICOLO" MODIFY ("TIPOLOGIA_VE" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_VEICOLO" MODIFY ("COLORE" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_VEICOLO" MODIFY ("TRANSPONDER_FK" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_VEICOLO" MODIFY ("MODELLO" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_VEICOLO" MODIFY ("BRAND" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_VEICOLO" ADD CHECK (LENGTH(TARGA_PK)=10) ENABLE;
  ALTER TABLE "TELEPASS"."TB_VEICOLO" ADD CONSTRAINT "TIPOLOGIA_VEICOLO_AMMESSE" CHECK (UPPER(TIPOLOGIA_VE) IN (
    'CLASSE A', 'CLASSE B', 'CLASSE 3', 'CLASSE 4', 'CLASSE 5'
    )) ENABLE;
  ALTER TABLE "TELEPASS"."TB_VEICOLO" ADD PRIMARY KEY ("TARGA_PK")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table TB_CASELLO
--------------------------------------------------------

  ALTER TABLE "TELEPASS"."TB_CASELLO" MODIFY ("KM" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_CASELLO" MODIFY ("INGRESSI" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_CASELLO" MODIFY ("AUTOSTRADA" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_CASELLO" ADD CONSTRAINT "TIPOLOGIA_AUTOSTRADA_AMMESSE" CHECK (UPPER(AUTOSTRADA) IN (
    'A1', 'A24', 'A25', 'TANGENZIALE', 'A5', 'A11', 'A12', 'A13'
    )) ENABLE;
  ALTER TABLE "TELEPASS"."TB_CASELLO" ADD PRIMARY KEY ("ID_CASELLO_PK")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table TB_VIAGGIO
--------------------------------------------------------

  ALTER TABLE "TELEPASS"."TB_VIAGGIO" ADD CONSTRAINT "PAGATO_VALUE" CHECK (PAGATO_FLAG IN (0,1)) ENABLE;
  ALTER TABLE "TELEPASS"."TB_VIAGGIO" ADD CONSTRAINT "PK_VIAGGIO" PRIMARY KEY ("TARGA_VE_PK", "CASELLO_ENTRY_FK_PK", "TIME_ENTRY_PK")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table TB_TRANSPONDER
--------------------------------------------------------

  ALTER TABLE "TELEPASS"."TB_TRANSPONDER" MODIFY ("CF_UTENTE_FK" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_TRANSPONDER" MODIFY ("METODO_PAG" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_TRANSPONDER" ADD CHECK (LENGTH(CODICE_TRANSP_PK)=10) ENABLE;
  ALTER TABLE "TELEPASS"."TB_TRANSPONDER" ADD CONSTRAINT "PLUS_VALUE" CHECK (PLUS IN (0,1)) ENABLE;
  ALTER TABLE "TELEPASS"."TB_TRANSPONDER" ADD CONSTRAINT "ATTIVO_VALUE" CHECK (ATTIVO IN (0,1)) ENABLE;
  ALTER TABLE "TELEPASS"."TB_TRANSPONDER" ADD PRIMARY KEY ("CODICE_TRANSP_PK")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table TB_UTENTE
--------------------------------------------------------

  ALTER TABLE "TELEPASS"."TB_UTENTE" MODIFY ("NOME" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_UTENTE" MODIFY ("COGNOME" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_UTENTE" MODIFY ("EMAIL" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_UTENTE" MODIFY ("INDIRIZZO_FATT" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_UTENTE" MODIFY ("CITTA_FATT" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_UTENTE" MODIFY ("REGIONE_FATT" NOT NULL ENABLE);
  ALTER TABLE "TELEPASS"."TB_UTENTE" ADD CHECK (LENGTH(CODICE_FISCALE_PK)=16) ENABLE;
  ALTER TABLE "TELEPASS"."TB_UTENTE" ADD CONSTRAINT "AMMINISTRATORE_VALUE" CHECK (AMMINISTRATORE IN (0,1)) ENABLE;
  ALTER TABLE "TELEPASS"."TB_UTENTE" ADD CONSTRAINT "EMAIL_FORMAT" CHECK (REGEXP_LIKE (EMAIL, '^\w+(\.\w+)*+@\w+(\.\w+)+$')) ENABLE;
  ALTER TABLE "TELEPASS"."TB_UTENTE" ADD PRIMARY KEY ("CODICE_FISCALE_PK")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TB_TRANSPONDER
--------------------------------------------------------

  ALTER TABLE "TELEPASS"."TB_TRANSPONDER" ADD CONSTRAINT "CF_UTENTE_FK" FOREIGN KEY ("CF_UTENTE_FK")
	  REFERENCES "TELEPASS"."TB_UTENTE" ("CODICE_FISCALE_PK") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TB_VEICOLO
--------------------------------------------------------

  ALTER TABLE "TELEPASS"."TB_VEICOLO" ADD CONSTRAINT "TRANSP_VEICOLO" FOREIGN KEY ("TRANSPONDER_FK")
	  REFERENCES "TELEPASS"."TB_TRANSPONDER" ("CODICE_TRANSP_PK") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TB_VIAGGIO
--------------------------------------------------------

  ALTER TABLE "TELEPASS"."TB_VIAGGIO" ADD CONSTRAINT "VIAGGIO_VEICOLO" FOREIGN KEY ("TARGA_VE_PK")
	  REFERENCES "TELEPASS"."TB_VEICOLO" ("TARGA_PK") ON DELETE CASCADE ENABLE;
  ALTER TABLE "TELEPASS"."TB_VIAGGIO" ADD CONSTRAINT "VIAGGIO_CASELLO" FOREIGN KEY ("CASELLO_ENTRY_FK_PK")
	  REFERENCES "TELEPASS"."TB_CASELLO" ("ID_CASELLO_PK") ON DELETE CASCADE ENABLE;
  ALTER TABLE "TELEPASS"."TB_VIAGGIO" ADD CONSTRAINT "VIAGGIO_CASELLO_2" FOREIGN KEY ("CASELLO_EXIT_FK")
	  REFERENCES "TELEPASS"."TB_CASELLO" ("ID_CASELLO_PK") ON DELETE CASCADE ENABLE;
