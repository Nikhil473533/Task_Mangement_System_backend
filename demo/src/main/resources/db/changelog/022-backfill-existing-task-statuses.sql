--liquibase formatted sql

--changeset njoglekar:022-backfill-existing-task-statuses
UPDATE task
SET    status_id = (SELECT id FROM task_status WHERE code = 'OPEN')
WHERE  status_id IS NULL;