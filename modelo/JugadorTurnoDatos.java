package modelo;

import modelo.JugadorTurno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorTurnoDatos {
    private Connection connection;

    public JugadorTurnoDatos(Connection connection) {
        this.connection = connection;
    }

    public List<JugadorTurno> getAllJugadorTurnos() throws SQLException {
        List<JugadorTurno> jugadorTurnos = new ArrayList<>();
        String sql = "SELECT * FROM jugadorTurno";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                JugadorTurno jugadorTurno = new JugadorTurno();
                jugadorTurno.setJtId(rs.getInt("jt_id"));
                jugadorTurno.setTurnoId(rs.getInt("turno_id"));
                jugadorTurno.setJugadorId(rs.getInt("jugador_id"));
                jugadorTurnos.add(jugadorTurno);
            }
        }
        return jugadorTurnos;
    }
    
    public void agregarJugadorTurno (JugadorTurno jugadorTurno) throws SQLException {
        String sql = "INSERT INTO jugadorTurno (turno_id, jugador_id) VALUES (?, ?)"; 
        try (PreparedStatement stmt = connection.prepareStatement(sql)) { 
            stmt.setInt(1, jugadorTurno.getTurnoId()); 
            stmt.setInt(2, jugadorTurno.getJugadorId());
            stmt.executeUpdate(); 
            // Para recuperar el ID que se genera automáticamente 
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) { 
                    jugadorTurno.setJtId(generatedKeys.getInt(1)); 
                } else { 
                    throw new SQLException("No se pudo obtener el ID."); 
                } 
            } catch (Exception e) {
                
            }
        } 
    }
   
    public void eliminarJugadorTurno(int jtId) throws SQLException { 
        String sql = "DELETE FROM jugadorTurno WHERE jt_id = ?"; 
        try (PreparedStatement stmt = connection.prepareStatement(sql)) { 
            stmt.setInt(1, jtId); 
            stmt.executeUpdate(); 
            System.out.println("Jugador eliminado del turno.");
        } catch (Exception e) {
            System.out.println("No se pudo eliminar el jugador");        
        } 
    }  
    
}
