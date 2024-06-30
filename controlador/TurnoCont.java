package controlador;

import modelo.Turno;
import modelo.TurnoDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TurnoCont {
    private TurnoDatos turnoDatos;

    public TurnoCont(Connection connection) {
        this.turnoDatos = new TurnoDatos(connection);
    }

    public List<Turno> getAllTurnos() {
        try {
            return turnoDatos.getAllTurnos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
