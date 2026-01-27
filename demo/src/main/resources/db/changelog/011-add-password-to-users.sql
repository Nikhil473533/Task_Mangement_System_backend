--liquibase formatted sql

--changeset njoglekar:011-add-password-to-users

ALTER TABLE users
ADD         password VARCHAR(255)  NULL;