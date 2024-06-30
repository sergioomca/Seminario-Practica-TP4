package controlador;

import modelo.Grupo;
import modelo.GrupoDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GrupoCont {
    private GrupoDatos grupoDatos;

    public GrupoCont(Connection connection) {
        this.grupoDatos = new GrupoDatos(connection);
    }

    public List<Grupo> getAllGrupos() {
        try {
            return grupoDatos.getAllGrupos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
