CREATE TABLE watch_history (
    id BIGSERIAL PRIMARY KEY,
    profile_id BIGINT NOT NULL REFERENCES profiles(id) ON DELETE CASCADE,
    movie_id BIGINT NOT NULL REFERENCES movies(id) ON DELETE CASCADE,
    watched_at TIMESTAMP DEFAULT NOW(),
    progress DECIMAL(5,2) DEFAULT 0.0, -- porcentagem assistida do filme (0 a 100)
    finished BOOLEAN DEFAULT FALSE
);
