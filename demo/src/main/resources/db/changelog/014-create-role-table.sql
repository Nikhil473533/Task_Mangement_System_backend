--liquibase formatted sql

--changeset njoglekar:014-create-role-table
CREATE TABLE role (
	             id          BIGINT IDENTITY PRIMARY KEY,
	             code        VARCHAR(50) NOT NULL UNIQUE,
	             name        VARCHAR(100) NOT NULL,
	             description VARCHAR(150),
	             created_at  DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
	             created_by  BIGINT NULL
                  );