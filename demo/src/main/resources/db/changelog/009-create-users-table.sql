--liquibase formatted sql

--changeset njoglekar:009-create-users-table
CREATE TABLE users(
	id             BIGINT IDENTITY(1,1) NOT NULL,
	username       VARCHAR(50) NOT NULL,
	display_name   VARCHAR(100) NOT NULL,
	email          VARCHAR(100) NOT NULL,
	active         BIT NOT NULL CONSTRAINT df_users_active DEFAULT 1,
	created_at     DATETIME2 NOT NULL,
	updated_at     DATETIME2 NULL,
	CONSTRAINT     pk_users PRIMARY KEY (id),
	CONSTRAINT     uq_users_username UNIQUE (username),
	CONSTRAINT     uq_users_email UNIQUE (email)
);