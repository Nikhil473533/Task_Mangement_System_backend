--liquibase formatted sql

--changeset njoglekar:020-add-status_id-column-to-task
ALTER TABLE task
ADD         status_id BIGINT NULL;