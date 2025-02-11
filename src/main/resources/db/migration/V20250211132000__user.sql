CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    login    TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL
);