--liquibase formatted sql

--changeset njoglekar:013-make-password-not-null

ALTER TABLE users
ALTER COLUMN password VARCHAR(255) NOT NULL;
