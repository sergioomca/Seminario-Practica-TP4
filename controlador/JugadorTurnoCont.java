package controlador;

import modelo.JugadorTurno;
import modelo.JugadorTurnoDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JugadorTurnoCont {
    private JugadorTurnoDatos jugadorTurnoDatos;

    public JugadorTurnoCont(Connection connection) {
        this.jugadorTurnoDatos = new JugadorTurnoDatos(connection);
    }

    public List<JugadorTurno> getAllJugadorTurnos() {
        try {
            return jugadorTurnoDatos.getAllJugadorTurnos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
