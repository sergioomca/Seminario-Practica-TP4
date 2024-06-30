-- -------------------------------------------------------
-- Insertar datos de prueba
INSERT INTO grupo(g_nombre, edad_max, edad_min)
VALUES
	("Regulares", 35, 18),
    ("Veteranos", 45, 36),
    ("Senior", 55, 46);

INSERT INTO campo(c_nombre, precio, cant_jug, piso)
VALUES
	("La escondida 1", 40000.00, 22, "Sintetico"),
    ("La escondida 2", 20000.00, 10, "Sintetico"),
    ("Universitario", 35000, 18, "Gramilla"),
    ("El desvio 1", 25000.00, 10, "Alfombra");

INSERT INTO turno(hora, duracion, grupo, campo_id)
VALUES
	("2024-05-27 20:00:00", 1, 2, 1),
    ("2024-05-29 18:00:00", 1, 3, 2),
    ("2024-05-29 18:00:00", 1.5, 2, 3),
    ("2024-05-29 18:00:00", 1, 1, 1),
    ("2024-05-29 18:00:00",2, 1, 4);

INSERT INTO jugador(nombre, edad, contacto, posicion, valoracion, grupo_id)
VALUES
	("Juan Dominguez", 43, "jdominguez@gmail.com", "Delantero", 7, 2),
    ("Pedro Peres", 41, "133333@gmail.com", "Defensor", 8, 2),
    ("Jose Diaz", 33, "sdarsota@gmail.com", "Delantero", 6, 1),
    ("Juan Gomez", 46, "sadarsa@gmail.com", "Defensor",7,3),
    ("Esteban Cadiz", 47, "ecadiz@gmail.com", "Mediocampista",8,3),
    ("Luis Timen", 47, "ltimen@gmail.com", "Mediocampista",7,3),
    ("Enrique Paez", 51, "epaez@gmail.com", "Delantero",6,3),
    ("Jonas Dorin", 48, "jdorin@gmail.com", "Defensor",9,3),
    ("Diego Tames", 46, "dtamez@gmail.com", "Mediocampista",8,3),
    ("Emanuel Sair", 46, "esair@gmail.com", "Delantero",7,3),
    ("Antonio Gomez", 46, "agomez@gmail.com", "Defensor",9,3),
    ("Paulo Gomez", 46, "pgomez@gmail.com", "Delantero",9,3),
    ("Damian Suarez", 46, "dsuarez@gmail.com", "Delantero",6,3),
    ("Diego Josal", 46, "djosal@gmail.com", "Mediocampista",6,3),
    ("Lucas Araya", 46, "laraya@gmail.com", "Mediocampista",7,3);
    ("Tomas Heit", 25, "291 4455555", "Arquero",8,1);
    
INSERT INTO jugadorTurno(turno_id, jugador_id)
VALUES
	(2, 15),
    (2, 6),
    (2, 7),
    (2, 8),
    (2, 9),
    (2, 10),
    (2, 11),
    (2, 12),
    (2, 13),
    (2, 14),
    (3, 5),
    (1, 2),
    (4, 1);
