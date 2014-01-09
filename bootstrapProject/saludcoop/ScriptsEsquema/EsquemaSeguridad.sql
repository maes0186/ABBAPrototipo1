/*
Script generado por Aqua Data Studio 10.0.7 en ene-09-2014 02:22:47 PM
Base de datos: db_saludcoop
Esquema: <Todos los esquemas>
Objetos: TABLE
*/
ALTER TABLE "security"."usuario_entidad"
	DROP CONSTRAINT "fk_usuario_entidad_usuario"
GO
ALTER TABLE "security"."usuario_entidad"
	DROP CONSTRAINT "fk_usuario_entidad_user"
GO
ALTER TABLE "security"."usuario_entidad"
	DROP CONSTRAINT "fk_usuario_entidad_sede_ips"
GO
ALTER TABLE "security"."usuario_entidad"
	DROP CONSTRAINT "fk_usuario_entidad_profesional_especialidad"
GO
ALTER TABLE "security"."usuario_entidad"
	DROP CONSTRAINT "fk_usuario_entidad_linea_de_frente"
GO
ALTER TABLE "security"."usuario_entidad"
	DROP CONSTRAINT "FK6B6BE5D85767757B"
GO
ALTER TABLE "security"."user_role"
	DROP CONSTRAINT "fk_user_role_user"
GO
ALTER TABLE "security"."user_role"
	DROP CONSTRAINT "fk_user_role_role"
GO
ALTER TABLE "security"."user_role"
	DROP CONSTRAINT "FK143BF46AC0A9DBBE"
GO
ALTER TABLE "security"."role_authority"
	DROP CONSTRAINT "fk_role_authority_role"
GO
ALTER TABLE "security"."role_authority"
	DROP CONSTRAINT "fk_role_authority_authority"
GO
ALTER TABLE "security"."recovery_token"
	DROP CONSTRAINT "fk_recovery_token_user"
GO
ALTER TABLE "security"."recovery_token"
	DROP CONSTRAINT "FK3BF6D8CFC0A9DBBE"
GO
ALTER TABLE "security"."user"
	DROP CONSTRAINT "uq_user_username"
GO
DROP TABLE "security"."usuario_entidad"
GO
DROP TABLE "security"."user_role"
GO
DROP TABLE "security"."user"
GO
DROP TABLE "security"."role_authority"
GO
DROP TABLE "security"."role"
GO
DROP TABLE "security"."recovery_token"
GO
DROP TABLE "menu"."menu"
GO
DROP TABLE "security"."authority"
GO

CREATE TABLE "security"."authority"  ( 
	"id"       	int NOT NULL,
	"authority"	varchar(255) NULL,
	"inserted" 	datetime NOT NULL,
	"deleted"  	bit NOT NULL,
	CONSTRAINT "pk_authority" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "menu"."menu"  ( 
	"id"          	int NOT NULL,
	"authority_id"	int NULL,
	"icon"        	varchar(255) NULL,
	"parent_id"   	int NULL,
	"link"        	varchar(255) NULL,
	"title"       	varchar(255) NULL,
	"tooltip"     	varchar(255) NULL,
	CONSTRAINT "pk_menu" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "security"."recovery_token"  ( 
	"id"      	int IDENTITY NOT NULL,
	"user_id" 	int NOT NULL,
	"token"   	varchar(255) NOT NULL,
	"inserted"	datetime NOT NULL,
	"deleted" 	bit NOT NULL,
	CONSTRAINT "pk_recovery_token" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "security"."role"  ( 
	"id"      	int NOT NULL,
	"deleted" 	bit NOT NULL,
	"inserted"	datetime NOT NULL,
	"role"    	varchar(30) NOT NULL,
	CONSTRAINT "pk_role" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "security"."role_authority"  ( 
	"role_id"     	int NOT NULL,
	"authority_id"	int NOT NULL,
	CONSTRAINT "pk_role_authority" PRIMARY KEY CLUSTERED("role_id","authority_id")
)
GO
CREATE TABLE "security"."user"  ( 
	"id"                         	int IDENTITY NOT NULL,
	"username"                   	varchar(25) NOT NULL,
	"password"                   	varchar(32) NOT NULL,
	"name"                       	varchar(50) NOT NULL,
	"email"                      	varchar(80) NOT NULL,
	"enabled"                    	bit NOT NULL,
	"credentials_expiration_date"	datetime NOT NULL,
	"failed_logins"              	int NOT NULL,
	"inserted"                   	datetime NOT NULL,
	"deleted"                    	bit NOT NULL,
	CONSTRAINT "pk_user" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "security"."user_role"  ( 
	"user_id"	int NOT NULL,
	"role_id"	int NOT NULL,
	CONSTRAINT "pk_user_role" PRIMARY KEY CLUSTERED("user_id","role_id")
)
GO
CREATE TABLE "security"."usuario_entidad"  ( 
	"id"                         	int IDENTITY NOT NULL,
	"ldf_id"                     	int NULL,
	"profesional_especialidad_id"	int NULL,
	"sede_ips_id"                	int NULL,
	"usuario_id"                 	int NULL,
	CONSTRAINT "pk_usuario_entidad" PRIMARY KEY CLUSTERED("id")
)
GO

ALTER TABLE "security"."user"
	ADD CONSTRAINT "uq_user_username"
	UNIQUE ("username")
GO
ALTER TABLE "security"."recovery_token"
	ADD CONSTRAINT "fk_recovery_token_user"
	FOREIGN KEY("user_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."recovery_token"
	ADD CONSTRAINT "FK3BF6D8CFC0A9DBBE"
	FOREIGN KEY("user_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."role_authority"
	ADD CONSTRAINT "fk_role_authority_role"
	FOREIGN KEY("role_id")
	REFERENCES "security"."role"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."role_authority"
	ADD CONSTRAINT "fk_role_authority_authority"
	FOREIGN KEY("authority_id")
	REFERENCES "security"."authority"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."user_role"
	ADD CONSTRAINT "fk_user_role_user"
	FOREIGN KEY("user_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."user_role"
	ADD CONSTRAINT "fk_user_role_role"
	FOREIGN KEY("role_id")
	REFERENCES "security"."role"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."user_role"
	ADD CONSTRAINT "FK143BF46AC0A9DBBE"
	FOREIGN KEY("user_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."usuario_entidad"
	ADD CONSTRAINT "fk_usuario_entidad_usuario"
	FOREIGN KEY("usuario_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."usuario_entidad"
	ADD CONSTRAINT "fk_usuario_entidad_user"
	FOREIGN KEY("usuario_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."usuario_entidad"
	ADD CONSTRAINT "fk_usuario_entidad_sede_ips"
	FOREIGN KEY("sede_ips_id")
	REFERENCES "maestros"."sede_ips"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."usuario_entidad"
	ADD CONSTRAINT "fk_usuario_entidad_profesional_especialidad"
	FOREIGN KEY("profesional_especialidad_id")
	REFERENCES "maestros"."profesional_especialidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."usuario_entidad"
	ADD CONSTRAINT "fk_usuario_entidad_linea_de_frente"
	FOREIGN KEY("ldf_id")
	REFERENCES "maestros"."linea_de_frente"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."usuario_entidad"
	ADD CONSTRAINT "FK6B6BE5D85767757B"
	FOREIGN KEY("usuario_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
