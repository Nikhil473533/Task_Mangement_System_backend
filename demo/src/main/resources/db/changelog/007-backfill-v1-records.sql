--changeset njoglekar:007-backfill-v1-records
UPDATE audit_log
SET audit_version = 'V1'
WHERE id BETWEEN 14 AND 38;
