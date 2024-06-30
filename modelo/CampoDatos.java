package modelo;

import modelo.Campo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CampoDatos {
    private Connection connection;

    public CampoDatos(Connection connection) {
        this.connection = connection;
    }

    public List<Campo> getAllCampos() throws SQLException {
        List<Campo> campos = new ArrayList<>();
        String sql = "SELECT * FROM campo";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Campo campo = new Campo();
                campo.setCampoId(rs.getInt("campo_id"));
                campo.setNombre(rs.getString("c_nombre"));
                campo.setPrecio(rs.getBigDecimal("precio"));
                campo.setCantidadJugadores(rs.getInt("cant_jug"));
                campo.setPiso(rs.getString("piso"));
                campos.add(campo);
            }
        }
        return campos;
    }

   
    
    public void agregarCampo(Campo campo) throws SQLException {
        String sql = "INSERT INTO campo (c_nombre, precio, cant_jug, piso) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, campo.getNombre());
            stmt.setBigDecimal(2, campo.getPrecio());
            stmt.setInt(3, campo.getCantidadJugadores());
            stmt.setString(4, campo.getPiso());
            stmt.executeUpdate();
            
            // Para recuperar el ID que se genera automaticamente 
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) { 
                    campo.setCampoId(generatedKeys.getInt(1)); 
                } else { 
                    throw new SQLException("No se pudo obtener el ID."); 
                } 
            } catch (Exception e) {
                
            }
        }
    }

    public void modificarCampo(Campo campo) throws SQLException {
        String sql = "UPDATE campo SET c_nombre = ?, precio = ?, cant_jug = ?, piso = ? WHERE campo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, campo.getNombre());
            stmt.setBigDecimal(2, campo.getPrecio());
            stmt.setInt(3, campo.getCantidadJugadores());
            stmt.setString(4, campo.getPiso());
            stmt.setInt(5, campo.getCampoId());
            stmt.executeUpdate();
        }
    }

    public void eliminarCampo(int campoId) throws SQLException {
        String sql = "DELETE FROM campo WHERE campo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, campoId);
            stmt.executeUpdate();
        System.out.println("Cancha Eliminada."); 
        }  catch (SQLException e) { 
            e.printStackTrace(); 
            System.out.println("Error al eliminar la cancha. Cancha en uso por algun jugador o turno."); 
        }
    }
}
