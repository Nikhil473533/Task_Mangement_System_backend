
-- Changeset njoglekar:006-add-audit-version-column
--1. Add the column with a default for future rows
ALTER TABLE   audit_log
ADD           audit_version VARCHAR(10) NOT NULL DEFAULT 'V2';


