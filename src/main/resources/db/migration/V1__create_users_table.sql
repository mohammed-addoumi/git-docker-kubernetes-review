-- Create the sequence users_seq
CREATE SEQUENCE users_seq
    START WITH 1
    INCREMENT BY 1;

-- Create the users_table
CREATE TABLE users_table (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255)
);


