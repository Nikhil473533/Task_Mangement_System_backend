--changeset njoglekar:008-backfill-v2-records
UPDATE audit_log
SET audit_version = 'V2'
WHERE id BETWEEN 39 AND 42;
