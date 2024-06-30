package modelo;

import modelo.Jugador;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JugadorDatos {
    private Connection connection;

    public JugadorDatos(Connection connection) {
        this.connection = connection;
    }

    public List<Jugador> getAllJugadores() throws SQLException {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT * FROM jugador";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Jugador jugador = new Jugador();
                jugador.setJugadorId(rs.getInt("jugador_id"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setEdad(rs.getInt("edad"));
                jugador.setContacto(rs.getString("contacto"));
                jugador.setPosicion(rs.getString("posicion"));
                jugador.setValoracion(rs.getInt("valoracion"));
                jugador.setGrupoId(rs.getInt("grupo_id"));
                jugadores.add(jugador);
            }
        }
        return jugadores;
    }

    public void agregarJugador(Jugador jugador) throws SQLException {
        String sql = "INSERT INTO jugador (nombre, edad, contacto, posicion, valoracion, grupo_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, jugador.getNombre());
            stmt.setInt(2, jugador.getEdad());
            stmt.setString(3, jugador.getContacto());
            stmt.setString(4, jugador.getPosicion());
            stmt.setInt(5, jugador.getValoracion());
            stmt.setInt(6, jugador.getGrupoId());
            stmt.executeUpdate();
            
            // Para recuperar el ID que se genera automáticamente 
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) { 
                    jugador.setJugadorId(generatedKeys.getInt(1)); 
                } else { 
                    throw new SQLException("No se pudo obtener el ID."); 
                } 
            } catch (Exception e) {
                
            }
            
        }
    }

    public void modificarJugador(Jugador jugador) throws SQLException {
        String sql = "UPDATE jugador SET nombre = ?, edad = ?, contacto = ?, posicion = ?, valoracion = ?, grupo_id = ? WHERE jugador_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, jugador.getNombre());
            stmt.setInt(2, jugador.getEdad());
            stmt.setString(3, jugador.getContacto());
            stmt.setString(4, jugador.getPosicion());
            stmt.setInt(5, jugador.getValoracion());
            stmt.setInt(6, jugador.getGrupoId());
            stmt.setInt(7, jugador.getJugadorId());
            stmt.executeUpdate();
        }
    }

    public void eliminarJugador(int jugadorId) throws SQLException {
        String sql = "DELETE FROM jugador WHERE jugador_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, jugadorId);
            stmt.executeUpdate();
            System.out.println("Jugador Eliminado.");
        }  catch (SQLException e) { 
            e.printStackTrace(); 
            System.out.println("Error al eliminar jugador. Jugador inscripto en turno o grupo."); 
        }
    }
    
}
