CREATE TABLE insurance(
                         id BIGINT PRIMARY KEY NOT NULL,
                         insurance_type VARCHAR(100),
                         insurance_limit FLOAT,
                         account_id BIGINT REFERENCES accounts(id)
);