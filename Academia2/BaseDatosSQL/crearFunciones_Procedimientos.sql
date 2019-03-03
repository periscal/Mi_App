use academia;

# ============= Procedimientos =================
/*
*/
#----------------------------------------------------------------//
DELIMITER $$
CREATE PROCEDURE altaPersona(
	IN condicion		tinyint,						# alumno =0; exalumno=1; familiar =2; profesor=3
	IN nombre_pers		varchar(20),
	IN apellido1		varchar(20),
	IN apellido2		varchar(20),
	IN nacimiento		datetime,
	IN telefono			varchar(9),
	IN dni				varchar(9),
	IN direccion		varchar(50),
	IN email			varchar(30))

BEGIN 
	INSERT INTO Persona (persona_ID,condicion,nombre_pers,apellido1,apellido2,nacimiento,telefono,dni,direccion,email)
    VALUES(NULL,condicion,nombre_pers,apellido1,apellido2,nacimiento,telefono,dni,direccion,email);
END$$
DELIMITER ;

#----------------------------------------------------------------//
DELIMITER $$
CREATE PROCEDURE altaProfesorPersona(
    IN condicion		tinyint,						# alumno =0; exalumno=1; familiar =2; profesor=3
	IN nombre_pers		varchar(20),
	IN apellido1		varchar(20),
	IN apellido2		varchar(20),
	IN nacimiento		datetime,
	IN telefono			varchar(9),
	IN dni				varchar(9),
	IN direccion		varchar(50),
	IN email			varchar(30),
    IN fecha_inicio		date/*,
	IN tasa			numeric (5,2)*/
    )
    
BEGIN 
	CALL regsitrarPersona(condicion,nombre_pers,apellido1,apellido2,nacimiento,telefono,dni,direccion,email);
    INSERT INTO Profesor(persona_ID,fecha_inicio) VALUES(LAST_INSERT_ID(),fecha_inicio);
END$$
DELIMITER ;

#----------------------------------------------------------------//
DELIMITER $$
CREATE PROCEDURE altaAlumnoPersona(
    IN condicion		tinyint,						# alumno =0; exalumno=1; familiar =2; profesor=3
	IN nombre_pers		varchar(20),
	IN apellido1		varchar(20),
	IN apellido2		varchar(20),
	IN nacimiento		datetime,
	IN telefono			varchar(9),
	IN dni				varchar(9),
	IN direccion		varchar(50),
	IN email			varchar(30),
    IN fecha_inicio		date,
    IN nivel			enum('1º Primaria','2º Primaria','3º Primaria','4º Primaria','5º Primaria','6º Primaria','1º ESO','2º ESO','3º ESO','4º ESO','1º Bachillerato','2º Bachillerato','Ciclo','Universidad','Otro'),
	IN tasa				numeric (5,2))
    
BEGIN 
	CALL regsitrarPersona(condicion,nombre_pers,apellido1,apellido2,nacimiento,telefono,dni,direccion,email);
    INSERT INTO Alumno(persona_ID,fecha_inicio,nivel) VALUES(LAST_INSERT_ID(),fecha_inicio,nivel);
END$$
DELIMITER ;


/*
//----------------------------------------------------------------//

// Consultas //
// Examenes que un alumno en concreto tiene a partir de  “HOY”
create or replace precedure
*/