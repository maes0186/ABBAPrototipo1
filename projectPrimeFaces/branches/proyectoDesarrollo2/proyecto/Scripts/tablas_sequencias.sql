create table PERSONA
(
  ID        INTEGER primary key,
  NOMBRE1   VARCHAR(40),
  NOMBRE2   VARCHAR(40),
  APELLIDO1 VARCHAR(40),
  APELLIDO2 VARCHAR(40)
);

create sequence PERSONA_SEQ
minvalue 1
maxvalue 999999999999999999
start with 41
increment by 1

