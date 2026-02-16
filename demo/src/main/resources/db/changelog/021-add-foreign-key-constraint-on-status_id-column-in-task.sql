--liquibase formatted sql

--changeset njoglekar:021-add-foreign-key-constraint-on-status_id-column-in-task
ALTER TABLE     task
ADD CONSTRAINT  fk_task_status
FOREIGN KEY (status_id) REFERENCES task_status(id);