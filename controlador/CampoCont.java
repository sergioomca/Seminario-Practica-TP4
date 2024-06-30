package controlador;

import modelo.Campo;
import modelo.CampoDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CampoCont {
    private CampoDatos campoDatos;

    public CampoCont(Connection connection) {
        this.campoDatos = new CampoDatos(connection);
    }

    public List<Campo> getAllCampos() {
        try {
            return campoDatos.getAllCampos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
