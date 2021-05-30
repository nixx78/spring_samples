DROP TABLE transaction_tbl IF EXISTS;

CREATE TABLE transaction_tbl  (
    transaction_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    account_id VARCHAR(20),
    currency VARCHAR(20),
    amount NUMERIC(10,2)
);
