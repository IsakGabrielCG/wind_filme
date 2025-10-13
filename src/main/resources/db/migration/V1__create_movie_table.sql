CREATE TABLE movies (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    release_year INT,
    age_rating INT,
    description TEXT,
    duration VARCHAR(20),
    image_url VARCHAR(255),
    trailer_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);