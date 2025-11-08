-- 1. Adiciona a coluna category_id à tabela movies
ALTER TABLE movies
ADD COLUMN category_id BIGINT;

-- 2. Adiciona a restrição de chave estrangeira
ALTER TABLE movies
ADD CONSTRAINT fk_movie_category
FOREIGN KEY (category_id)
REFERENCES categories (id);