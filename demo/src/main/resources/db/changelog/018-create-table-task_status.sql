--liquibase formatted sql

--changeset njoglekar:018-create-table-task_status
CREATE TABLE task_status (
	  id            BIGINT IDENTITY PRIMARY KEY,
	  code          VARCHAR(50) NOT NULL UNIQUE,
	  display_name  VARCHAR(100) NOT NULL,
	  is_terminal   BIT NOT NULL DEFAULT 0,
	  sort_order    INT NOT NULL
                         );