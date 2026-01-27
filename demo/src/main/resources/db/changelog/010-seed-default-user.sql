--liquibase formatted sql

--changeset njoglekar:010-seed-default-user
INSERT INTO users(
	              username,
	              display_name,
	              email,
	              active,
	              created_at
                  )
VALUES           (
	             'njoglekar',
	             'Nikhil Joglekar',
	             'example@test.com',
	             1,
	             SYSDATETIME()
                 );