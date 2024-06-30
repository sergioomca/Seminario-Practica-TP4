package modelo;

import java.math.BigDecimal;

public class Campo {
    private int campoId;
    private String nombre;
    private BigDecimal precio;
    private int cantidadJugadores;
    private String piso;

    public int getCampoId() {
        return campoId;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public String getPiso() {
        return piso;
    }

    public void setCampoId(int campoId) {
        this.campoId = campoId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void setCantidadJugadores(int cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }
}
