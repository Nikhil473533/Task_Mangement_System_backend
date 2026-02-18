--liquibase formatted sql

--changeset njoglekar:026-Add-Foreign-Key-constraint-in-assigned_user_id-column
ALTER TABLE     task
ADD CONSTRAINT  fk_task_assigned_users
FOREIGN KEY     (assigned_user_id)
REFERENCES       users(id);