--liquibase formatted sql

--changeset njoglekar:027-Create-password_reset_token-table
CREATE TABLE password_reset_token (
  id BIGINT IDENTITY PRIMARY KEY ,
  token VARCHAR(255) NOT NULL UNIQUE,
  user_id BIGINT NOT NULL,
  expiry DATETIME2 NOT NULL,
  created_at DATETIME2 DEFAULT SYSDATETIME(),

 CONSTRAINT fk_password_reset_token_user
 FOREIGN KEY (user_id)
 REFERENCES users(id)
 ON DELETE CASCADE
);