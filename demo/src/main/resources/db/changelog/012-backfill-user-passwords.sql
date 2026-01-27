--liquibase formatted sql

--changeset njoglekar:012-backfill-user-passwords

UPDATE users
SET    password = '$2a$12$Ut0ve1SgPDOB5Sikooww1.77TtgHN1ecF86LTK0G0tj3ZNft9t7Va'
WHERE  password IS NULL;