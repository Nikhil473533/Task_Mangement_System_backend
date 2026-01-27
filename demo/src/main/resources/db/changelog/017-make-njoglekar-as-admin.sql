--liquibase formatted sql

--changeset njoglekar:017-make-njoglekar-as-admin

INSERT INTO user_role (user_id, role_id)
SELECT      u.id, r.id
FROM        users u
INNER JOIN  role r
ON          r.code = 'ADMIN'
WHERE       u.username = 'njoglekar'
AND NOT EXISTS (
    SELECT 1
    FROM user_role ur
    WHERE ur.user_id = u.id
      AND ur.role_id = r.id
);