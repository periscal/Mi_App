# Creación de la base de datos
create database academia;
use academia;

# Creación de las tablas de datos
create table persona (
	persona_ID		int 		not null AUTO_INCREMENT , 	# Cambiar varchar por samllInt
	condicion		tinyint,						# alumno =0; exalumno=1; familiar =2; profesor=3
	nombre_pers		varchar(20),
	apellido1		varchar(20),
	apellido2		varchar(20),
	nacimiento		datetime,
	telefono		varchar(9),
	dni				varchar(9),
	localidad		varchar(20),
	municipio		varchar(20),
	email			varchar(30),
	primary key (persona_ID)
);

create table alumno (
	fecha_inicio	date,
	tasa			numeric (5,2),
	persona_ID		int,
	foreign key (persona_ID) references persona(persona_ID)
);

create table centro (
	centro_ID		int 		not null AUTO_INCREMENT,# Cambiar varchar por samllInt
	tipo_centro		varchar(10),
	nombre_cent		varchar(30),
	localidad		varchar(20),
	municipio		varchar(20),
	primary key (centro_ID)
);

create table aula (
	aula_ID			int 	not null AUTO_INCREMENT,# Cambiar varchar por samllInt
	curso			tinyint,	# 1 byte
	letra			char(1),
	centro_ID		int,
	primary key (aula_ID),
	foreign key (centro_ID) references centro(centro_ID)
);

create table sesion (
	sesion_ID		int not 	null AUTO_INCREMENT,# Cambiar varchar por samllInt
	horaInicio		time, # formato [hh:mm:ss].El argumento es la parte fraccionaria de los segundos
	horaFin			time,
	dia				tinyint,
	persona_ID		int,
	primary key (sesion_ID),
	foreign key (persona_ID) references persona(persona_ID)
);

create table parentesco (
	parentesco_ID	int 		not null AUTO_INCREMENT,	# Cambiar varchar por samllInt
	tipo_parentesco	varchar(10),
	persona_ID		int,
	primary key (parentesco_ID),
	foreign key (persona_ID) references persona(persona_ID)
);

create table asignatura (
	asignatura_ID	int 		not null AUTO_INCREMENT,# Cambiar varchar por samllInt
	nombre_asig		varchar(20),	
	persona_ID		int 		not null,
	aula_ID			int,
	primary key (asignatura_ID),
	foreign key (persona_ID) references persona(persona_ID),
	foreign key (aula_ID) references aula(aula_ID)
);

create table examen_aula (
	fecha_examen	date 		not null,
	nombre_asig		varchar(20) not null,
	aula_ID			int,
	primary key (fecha_examen),
	foreign key (aula_ID) references aula(aula_ID)
);

create table alumno_sesion(
	sesion_ID		int not null,
	persona_ID		int not null,
	primary key (sesion_ID, persona_ID),
	foreign key (persona_ID) references persona(persona_ID),
	foreign key (sesion_ID) references sesion(sesion_ID)
);

