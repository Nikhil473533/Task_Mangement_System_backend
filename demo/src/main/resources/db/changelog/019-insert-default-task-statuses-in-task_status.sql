--liquibase formatted sql

--changeset njoglekar:019-insert-default-task-statuses-in-task_status
INSERT INTO task_status (code, display_name, is_terminal, sort_order)
VALUES
('OPEN', 'open', 0, 1),
('IN_PROGRESS', 'In Progress', 0, 2),
('BLOCKED', 'Blocked', 0, 3),
('DONE', 'Done', 1, 4);