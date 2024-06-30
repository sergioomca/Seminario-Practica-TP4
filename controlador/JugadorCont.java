package controlador;


import modelo.Jugador;
import modelo.JugadorDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JugadorCont {
    private JugadorDatos jugadorDatos;

    public JugadorCont(Connection connection) {
        this.jugadorDatos = new JugadorDatos(connection);
    }

    public List<Jugador> getAllJugadores() {
        try {
            return jugadorDatos.getAllJugadores();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
