-- cria a coluna se não existir
ALTER TABLE users ADD COLUMN IF NOT EXISTS active BOOLEAN;

-- garante que todo mundo tenha valor (evita NOT NULL falhar)
UPDATE users SET active = TRUE WHERE active IS NULL;

-- define default e NOT NULL
ALTER TABLE users ALTER COLUMN active SET DEFAULT TRUE;
ALTER TABLE users ALTER COLUMN active SET NOT NULL;
