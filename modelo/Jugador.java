package modelo;

public class Jugador {
    private int jugadorId;
    private String nombre;
    private int edad;
    private String contacto;
    private String posicion;
    private int valoracion;
    private int grupoId;
        
    public int getJugadorId() {
        return jugadorId;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getContacto() {
        return contacto;
    }

    public String getPosicion() {
        return posicion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setJugadorId(int jugadorId) {
        this.jugadorId = jugadorId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }
}
