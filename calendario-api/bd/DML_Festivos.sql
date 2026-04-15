--Registros tabla TIPO
INSERT INTO Tipo(Id, Tipo) VALUES(1, 'Día laboral');
INSERT INTO Tipo(Id, Tipo) VALUES(2, 'Fin de Semana');
INSERT INTO Tipo(Id, Tipo) VALUES(3, 'Día festivo');

ALTER SEQUENCE secuencia_tipo RESTART WITH 4;

/* Agregar registros de USUARIO */
INSERT INTO Usuario (Usuario, Nombre, Clave) VALUES ('frayosorio', 'Fray León Osorio Rivera', '123');
