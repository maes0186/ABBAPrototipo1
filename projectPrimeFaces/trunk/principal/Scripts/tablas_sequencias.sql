create table PERSONA
(
  ID        NUMBER primary key,
  NOMBRE1   VARCHAR2(40),
  NOMBRE2   VARCHAR2(40),
  APELLIDO1 VARCHAR2(40),
  APELLIDO2 VARCHAR2(40)
);

create sequence PERSONA_SEQ
minvalue 1
maxvalue 999999999999999999
start with 41
increment by 1
cache 20
cycle;

