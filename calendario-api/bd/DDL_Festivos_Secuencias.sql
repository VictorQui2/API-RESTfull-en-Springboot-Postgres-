-- Este script se ejecuta solo si las secuencias no existen (para entornos existentes).
-- En entornos nuevos las secuencias ya se crean desde DDL_Festivos.sql

DO $$
DECLARE
    ultimoID INTEGER;
BEGIN
    IF to_regclass('secuencia_tipo') IS NULL THEN
        SELECT COALESCE(MAX(id), 0) + 1 INTO ultimoID FROM tipo;
        EXECUTE 'CREATE SEQUENCE secuencia_tipo START ' || ultimoID;
        ALTER TABLE tipo ALTER COLUMN id SET DEFAULT nextval('secuencia_tipo');
    END IF;

    IF to_regclass('secuencia_calendario') IS NULL THEN
        SELECT COALESCE(MAX(id), 0) + 1 INTO ultimoID FROM calendario;
        EXECUTE 'CREATE SEQUENCE secuencia_calendario START ' || ultimoID;
        ALTER TABLE calendario ALTER COLUMN id SET DEFAULT nextval('secuencia_calendario');
    END IF;
END $$;
