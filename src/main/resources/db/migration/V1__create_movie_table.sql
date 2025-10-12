CREATE TABLE movie (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    year VARCHAR(10),
    old INTEGER,
    description TEXT,
    duracao VARCHAR(20)
);
