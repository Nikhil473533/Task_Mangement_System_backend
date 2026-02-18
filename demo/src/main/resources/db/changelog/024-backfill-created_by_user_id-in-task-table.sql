--liquibase formatted sql

--changeset njoglekar:024-backfill-created_by_user_id-in-task-table
UPDATE task
SET    created_by_user_id = 1
WHERE  id IN (3,4,5,6,7,8,9,10,11,12,13,14,15,16,17);