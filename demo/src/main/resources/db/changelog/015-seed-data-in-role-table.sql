--liquibase formatted sql

--changeset njoglekar:015-seed-data-in-role-table

INSERT INTO role (code, name, description, created_by)
VALUES            (
	               'ADMIN',
	               'Administrator',
	               'System Administrator',
	               1 
                  ),
                  ('USER',
                    'Standard User',
                    'Regular User',
                    1);