package modelo;

import modelo.Grupo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoDatos {
    private Connection connection;

    public GrupoDatos(Connection connection) {
        this.connection = connection;
    }

    public List<Grupo> getAllGrupos() throws SQLException {
        List<Grupo> grupos = new ArrayList<>();
        String sql = "SELECT * FROM grupo";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Grupo grupo = new Grupo();
                grupo.setGrupoId(rs.getInt("grupo_id"));
                grupo.setNombre(rs.getString("g_nombre"));
                grupo.setEdadMax(rs.getInt("edad_max"));
                grupo.setEdadMin(rs.getInt("edad_min"));
                grupos.add(grupo);
            }
        }
        return grupos;
    }

    // Métodos para agregar, modificar y eliminar grupos
    public void agregarGrupo(Grupo grupo) throws SQLException {
        String sql = "INSERT INTO grupo (g_nombre, edad_max, edad_min) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, grupo.getNombre());
            stmt.setInt(2, grupo.getEdadMax());
            stmt.setInt(3, grupo.getEdadMin());
            stmt.executeUpdate();
            
            // Recuperar el ID generado automáticamente 
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) { 
                    grupo.setGrupoId(generatedKeys.getInt(1)); 
                } else { 
                    throw new SQLException("No se pudo obtener el ID."); 
                } 
            } catch (Exception e) {
                
            }
        }
    }

    public void modificarGrupo(Grupo grupo) throws SQLException {
        String sql = "UPDATE grupo SET g_nombre = ?, edad_max = ?, edad_min = ? WHERE grupo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, grupo.getNombre());
            stmt.setInt(2, grupo.getEdadMax());
            stmt.setInt(3, grupo.getEdadMin());
            stmt.setInt(4, grupo.getGrupoId());
            stmt.executeUpdate();
        }
    }

    public void eliminarGrupo(int grupoId) throws SQLException {
        String sql = "DELETE FROM grupo WHERE grupo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, grupoId);
            stmt.executeUpdate();
        System.out.println("Grupo Eliminado."); 
        }  catch (SQLException e) { 
            e.printStackTrace(); 
            System.out.println("Error al eliminar grupo. Grupo en uso por algun jugador."); 
        }
    }
    
}
