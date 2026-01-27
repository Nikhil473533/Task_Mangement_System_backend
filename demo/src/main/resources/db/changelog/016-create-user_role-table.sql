--liquibase formatted sql

--changeset njoglekar:016-create-user_role-table

CREATE TABLE user_role (
	                   user_id     BIGINT NOT NULL,
	                   role_id     BIGINT NOT NULL,
	                   assigned_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
CONSTRAINT pk_user_role      PRIMARY KEY (user_id, role_id),
CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES users(id),
CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES role(id)	                   
                       );