--liquibase formatted sql

--changeset njoglekar:023-make-status_id-column-not-null-in-task
ALTER TABLE  task
ALTER COLUMN status_id BIGINT NOT NULL;
