package modelo;

import modelo.Turno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnoDatos {
    private Connection connection;

    public TurnoDatos(Connection connection) {
        this.connection = connection;
    }

    public List<Turno> getAllTurnos() throws SQLException {
        List<Turno> turnos = new ArrayList<>();
        String sql = "SELECT * FROM turno";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Turno turno = new Turno();
                turno.setTurnoId(rs.getInt("turno_id"));
                turno.setHora(rs.getTimestamp("hora").toLocalDateTime());
                turno.setDuracion(rs.getInt("duracion"));
                turno.setGrupoId(rs.getInt("grupo"));
                turno.setCampoId(rs.getInt("campo_id"));
                turnos.add(turno);
            }
        }
        return turnos;
    }

    public void agregarTurno(Turno turno) throws SQLException {
        String sql = "INSERT INTO turno (hora, duracion, grupo, campo_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(turno.getHora()));
            stmt.setInt(2, turno.getDuracion());
            stmt.setInt(3, turno.getGrupoId());
            stmt.setInt(4, turno.getCampoId());
            stmt.executeUpdate();
            
            // Para recuperar el ID que se genera automáticamente 
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) { 
                    turno.setTurnoId(generatedKeys.getInt(1)); 
                } else { 
                    throw new SQLException("No se pudo obtener el ID."); 
                } 
            } catch (Exception e) {
                
            }
        }
    }

    public void modificarTurno(Turno turno) throws SQLException {
        String sql = "UPDATE turno SET hora = ?, duracion = ?, grupo = ?, campo_id = ? WHERE turno_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(turno.getHora()));
            stmt.setInt(2, turno.getDuracion());
            stmt.setInt(3, turno.getGrupoId());
            stmt.setInt(4, turno.getCampoId());
            stmt.setInt(5, turno.getTurnoId());
            stmt.executeUpdate();
            System.out.println("Turno Modificado.");
        } catch (SQLException e) { 
            System.out.println("Error al modificar turno."); 
        }
    }

    public void eliminarTurno(int turnoId) throws SQLException {
        String sql = "DELETE FROM turno WHERE turno_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, turnoId);
            stmt.executeUpdate();
            System.out.println("Turno Eliminado."); 
        }  catch (SQLException e) { 
            e.printStackTrace(); 
            System.out.println("Error al eliminar turno. Turno en uso por algun jugador."); 
        }
    }
}
