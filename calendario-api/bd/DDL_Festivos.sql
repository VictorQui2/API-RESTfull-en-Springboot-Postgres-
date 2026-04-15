--Ejecutar primero
DROP DATABASE IF EXISTS CalendarioLaboral1 WITH (FORCE);
--Ejecutar segundo
CREATE DATABASE CalendarioLaboral1;

--Para las siguientes instrucciones, se debe cambiar la conexión

--Crear la tabla TIPO
CREATE TABLE Tipo(
	Id SERIAL PRIMARY KEY,
	Tipo VARCHAR(100) NOT NULL
	);

-- Crear indice para TIPO
CREATE UNIQUE INDEX ixTipo
	ON Tipo(Tipo);

--Crear la tabla CALENDARIO
CREATE TABLE Calendario(
	Id SERIAL PRIMARY KEY,
	Fecha DATE NOT NULL,
	IdTipo INT NOT NULL,
    CONSTRAINT fkCalendario_Tipo FOREIGN KEY (IdTipo) REFERENCES Tipo(Id),
    Descripcion VARCHAR(100) NULL
	);

/* Crear indice para CALENDARIO
	ordenado por FECHA */
CREATE UNIQUE INDEX ixCalendario
	ON Calendario(Fecha);

/* Crear tabla USUARIO */
CREATE TABLE Usuario(
	Id SERIAL PRIMARY KEY,
	Usuario VARCHAR(100) NOT NULL,
    Nombre VARCHAR(100) NOT NULL,
	Clave VARCHAR(100) NOT NULL,
    Activo BOOL DEFAULT(true) NOT NULL,
	Foto BYTEA NULL,
    Roles VARCHAR(100) NULL
	);

/* Crear indice para USUARIO
	ordenado por USUARIO */
CREATE UNIQUE INDEX ixUsuario_Usuario
	ON Usuario(Usuario);

--crear el secuenciador para la tabla TIPO
CREATE SEQUENCE secuencia_tipo
    START WITH 1;

--asignar el secuenciador a la tabla TIPO
ALTER TABLE tipo
    ALTER COLUMN id SET DEFAULT nextval('secuencia_tipo');

--crear el secuenciador para la tabla CALENDARIO
CREATE SEQUENCE secuencia_calendario
    START WITH 1;

--asignar el secuenciador a la tabla CALENDARIO
ALTER TABLE calendario
    ALTER COLUMN id SET DEFAULT nextval('secuencia_calendario');
