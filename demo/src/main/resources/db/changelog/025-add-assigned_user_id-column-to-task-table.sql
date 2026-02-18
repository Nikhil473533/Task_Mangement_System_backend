--liquibase formatted sql

--changeset njoglekar:025-add-assigned_user_id-column-to-task-table
ALTER TABLE task 
ADD         assigned_user_id BIGINT;
