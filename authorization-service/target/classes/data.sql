--DROP TABLE IF EXISTS users;
--DROP TABLE IF EXISTS user_roles;
--DROP TABLE IF EXISTS user_orgs;
--
--CREATE  TABLE users (
--  username VARCHAR(100) NOT NULL ,
--  password VARCHAR(100) NOT NULL ,
--  enabled boolean NOT NULL ,
--  roles VARCHAR(100) NOT NULL, 
--  PRIMARY KEY (username));

INSERT INTO users(id, username,password,active, roles) VALUES (1,'john.carnell','pass1', true, 'Role_USER');
INSERT INTO users(id, username,password,active, roles) VALUES (2,'william.woodward','pass2', true, 'Role_ADMIN');

--INSERT INTO user_roles (user_name, role) VALUES ('john.carnell', 'ROLE_USER');
--INSERT INTO user_roles (user_name, role) VALUES ('william.woodward', 'ROLE_ADMIN');
--INSERT INTO user_roles (user_name, role) VALUES ('william.woodward', 'ROLE_USER');
--
--INSERT INTO user_orgs (organization_id, user_name) VALUES ('d1859f1f-4bd7-4593-8654-ea6d9a6a626e', 'john.carnell');
--INSERT INTO user_orgs (organization_id, user_name) VALUES ('42d3d4f5-9f33-42f4-8aca-b7519d6af1bb', 'william.woodward');

INSERT INTO client_details
	(id, client_id, client_secret, scope, authorized_grant_types, redirecturi, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES
	(1, 'client', 'secret', 'read,write',
	'password,authorization_code,refresh_token', null, null, 36000, 36000, 'Client Details', true);
	
INSERT INTO client_details
	(id, client_id, client_secret, scope, authorized_grant_types, redirecturi, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES
	(2, 'client1', 'secret1', 'read',
	'password,authorization_code,refresh_token', null, null, 36000, 36000, 'Client1 Details', true);

