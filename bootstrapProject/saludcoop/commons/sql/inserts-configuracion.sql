insert into configuracion.properties (id, clave, valor, aplicacion) values (1, 'rest_client', 'http://localhost:8080/Validador/', 'web');
INSERT INTO configuracion.properties(id, aplicacion, clave, valor) VALUES(2, 'validador', 'timeout', '15000');
INSERT INTO configuracion.properties(id, aplicacion, clave, valor) VALUES(3, 'web', 'bandeja_max_items_pagina', '10');

-- Configuración de la gestión del repositorio de archivos

INSERT INTO configuracion.properties (id, aplicacion, clave, valor) VALUES (4, 'web', 'rep_arch_encodedFileNameLength', '20');
INSERT INTO configuracion.properties (id, aplicacion, clave, valor) VALUES (5, 'web', 'rep_arch_encryptionEnabled', 'false');
INSERT INTO configuracion.properties (id, aplicacion, clave, valor) VALUES (6, 'web', 'rep_arch_encryptionKeyLength', '0');
INSERT INTO configuracion.properties (id, aplicacion, clave, valor) VALUES (7, 'web', 'rep_arch_fileCacheEnabled', 'true');
INSERT INTO configuracion.properties (id, aplicacion, clave, valor) VALUES (8, 'web', 'rep_arch_maxFilesCached', '50');
INSERT INTO configuracion.properties (id, aplicacion, clave, valor) VALUES (9, 'web', 'rep_arch_fileRepositoryPath', 'C:\\saludcoop-file-repository\\');

-- Configuración del envío de correos electrónicos

INSERT INTO configuracion.properties (id, aplicacion, clave, valor) VALUES (10, 'web', 'email_config_username', 'igen.conexia@gmail.com');
INSERT INTO configuracion.properties (id, aplicacion, clave, valor) VALUES (11, 'web', 'email_config_password', 'producto2011');
INSERT INTO configuracion.properties (id, aplicacion, clave, valor) VALUES (12, 'web', 'email_config_hostAddress', 'smtp.gmail.com');
INSERT INTO configuracion.properties (id, aplicacion, clave, valor) VALUES (13, 'web', 'email_config_port', '465');
INSERT INTO configuracion.properties (id, aplicacion, clave, valor) VALUES (14, 'web', 'email_config_sslRequired', 'true');

-- Cache control
insert into configuracion.properties (id,aplicacion, clave, valor) values (15,'web', 'cache_control','201310251627');



CREATE SEQUENCE configuracion.mail_content_id_seq
GO

CREATE TABLE configuracion.mail_content  ( 
	id     	int4 NOT NULL DEFAULT nextval('configuracion.mail_content_id_seq'::regclass),
	body   	varchar(700) NOT NULL,
	subject	varchar(100) NOT NULL,
	PRIMARY KEY(id)
)
WITHOUT OIDS 
TABLESPACE pg_default
GO
GRANT SELECT(subject), INSERT(subject), UPDATE(subject), REFERENCES(subject) ON configuracion.mail_content TO app_saludcoop_owner WITH GRANT OPTION
GO
GRANT SELECT(id), INSERT(id), UPDATE(id), REFERENCES(id) ON configuracion.mail_content TO app_saludcoop_owner WITH GRANT OPTION
GO
GRANT SELECT(body), INSERT(body), UPDATE(body), REFERENCES(body) ON configuracion.mail_content TO app_saludcoop_owner WITH GRANT OPTION
GO

INSERT INTO configuracion.mail_content (id, subject, body)
VALUES (1,'Proyecto Conecta Saludcoop: No se han encontrado IPS para direccionamiento de Procedimiento','
Señor Director Médico:

El día [dd/mm/aaaa] el paciente  [NOMBRE COMPLETO PACIENTE] identificado con [TIPO DOCUMENTO] No [NUMERO DOCUMENTO] requirio del procedimiento [CUPS NOMBRE PROCEDIMIENTO] desde la IPS [NOMBRE IPS] el cual no se encuentra cubierto por la Red de SaludCoop, razón por la cual solicitamos la gestión inmediata de su parte.

Esta solicitud es generada por el médico tratante [NOMBRE MEDICO]');