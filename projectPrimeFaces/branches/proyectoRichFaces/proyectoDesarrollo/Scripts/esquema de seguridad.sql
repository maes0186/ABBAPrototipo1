CREATE TABLE security.users (
username varchar(20) NOT NULL PRIMARY KEY,
password varchar(20) NOT NULL
);
CREATE TABLE security.roles (
rolename varchar(20) NOT NULL PRIMARY KEY
);
CREATE TABLE security.users_roles (
username varchar(20) NOT NULL,
rolename varchar(20) NOT NULL,
PRIMARY KEY (username, rolename),
CONSTRAINT users_roles_fk1 FOREIGN KEY (username) REFERENCES security.users (username),
CONSTRAINT users_roles_fk2 FOREIGN KEY (rolename) REFERENCES security.roles (rolename)
);


--Datos

INSERT INTO security.users (username, password) VALUES ('prasad', 'kharkar');
INSERT INTO security.roles (rolename) VALUES ('user');
INSERT INTO security.users_roles (username, rolename) VALUES ('prasad', 'user');
COMMIT;