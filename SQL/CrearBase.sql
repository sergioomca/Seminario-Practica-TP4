CREATE database Masters;

use Masters;

CREATE TABLE grupo (
	grupo_id INT auto_increment PRIMARY KEY,
    g_nombre VARCHAR(60),
	edad_max INT,
    edad_min INT
);

CREATE TABLE campo (
	campo_id INT auto_increment Primary KEY,
    c_nombre VARCHAR(60),
    precio DECIMAL(10,2),
    cant_jug INT NOT NULL,
    piso VARCHAR(20)
);

CREATE TABLE turno (
	turno_id INT auto_increment Primary KEY,
    hora DATETIME NOT NULL,
    duracion INT,
    grupo INT NOT NULL,
    campo_id INT NOT NULL,
    FOREIGN KEY (grupo) REFERENCES Grupo(grupo_id),
    FOREIGN KEY (campo_id) REFERENCES Campo(campo_id)
);

CREATE TABLE jugador (
	jugador_id INT auto_increment PRIMARY KEY,
    nombre VARCHAR(60),
    edad INT,
    contacto VARCHAR(60),
    posicion VARCHAR(30),
    valoracion INT,
    grupo_id INT,
    FOREIGN KEY (grupo_id) REFERENCES Grupo(grupo_id)
);

CREATE TABLE jugadorTurno (
	jt_id INT auto_increment PRIMARY KEY,
    turno_id INT,
    jugador_id INT,
    FOREIGN KEY (turno_id) REFERENCES Turno(turno_id),
    FOREIGN KEY (jugador_id) REFERENCES Jugador(jugador_id)
);

  -- --------------------------------------------------