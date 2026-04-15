-- Script de inicialización automática para el contenedor Docker de Postgres.
-- El contenedor crea la BD "CalendarioLaboral1" desde la variable POSTGRES_DB.
-- Este archivo solo crea tablas, secuencias y datos semilla.

--Crear la tabla TIPO
CREATE TABLE IF NOT EXISTS Tipo(
	Id SERIAL PRIMARY KEY,
	Tipo VARCHAR(100) NOT NULL
	);

CREATE UNIQUE INDEX IF NOT EXISTS ixTipo ON Tipo(Tipo);

--Crear la tabla CALENDARIO
CREATE TABLE IF NOT EXISTS Calendario(
	Id SERIAL PRIMARY KEY,
	Fecha DATE NOT NULL,
	IdTipo INT NOT NULL,
    CONSTRAINT fkCalendario_Tipo FOREIGN KEY (IdTipo) REFERENCES Tipo(Id),
    Descripcion VARCHAR(100) NULL
	);

CREATE UNIQUE INDEX IF NOT EXISTS ixCalendario ON Calendario(Fecha);

--Crear la tabla USUARIO
CREATE TABLE IF NOT EXISTS Usuario(
	Id SERIAL PRIMARY KEY,
	Usuario VARCHAR(100) NOT NULL,
    Nombre VARCHAR(100) NOT NULL,
	Clave VARCHAR(100) NOT NULL,
    Activo BOOL DEFAULT(true) NOT NULL,
	Foto BYTEA NULL,
    Roles VARCHAR(100) NULL
	);

CREATE UNIQUE INDEX IF NOT EXISTS ixUsuario_Usuario ON Usuario(Usuario);

--Secuenciadores
CREATE SEQUENCE IF NOT EXISTS secuencia_tipo START WITH 1;
ALTER TABLE tipo ALTER COLUMN id SET DEFAULT nextval('secuencia_tipo');

CREATE SEQUENCE IF NOT EXISTS secuencia_calendario START WITH 1;
ALTER TABLE calendario ALTER COLUMN id SET DEFAULT nextval('secuencia_calendario');

--Datos semilla
INSERT INTO Tipo(Id, Tipo) VALUES(1, 'Día laboral')    ON CONFLICT (id) DO NOTHING;
INSERT INTO Tipo(Id, Tipo) VALUES(2, 'Fin de Semana')  ON CONFLICT (id) DO NOTHING;
INSERT INTO Tipo(Id, Tipo) VALUES(3, 'Día festivo')    ON CONFLICT (id) DO NOTHING;

ALTER SEQUENCE secuencia_tipo RESTART WITH 4;

INSERT INTO Usuario (Usuario, Nombre, Clave)
    VALUES ('frayosorio', 'Fray León Osorio Rivera', '123')
    ON CONFLICT DO NOTHING;
