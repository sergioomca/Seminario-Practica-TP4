-- Consultar datos turnos 
 SELECT 
	turno.turno_id AS "Turno",
    turno.hora AS "Fecha",
    jugador.nombre AS "Jugador",
    campo.c_nombre AS "Lugar",
    campo.precio AS "Precio",
    campo.cant_jug AS "Cant Jug",
    jugador.contacto AS "Contacto"
FROM
	turno,
    jugadorTurno,
    campo,
    jugador
WHERE
	JugadorTurno.turno_id  = turno.turno_id
    AND turno.campo_id = campo.campo_id
    AND jugador.jugador_id = jugadorTurno.jugador_id

;
