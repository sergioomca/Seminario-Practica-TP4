package modelo;

public class Grupo {
    private int grupoId;
    private String nombre;
    private int edadMax;
    private int edadMin;

    public int getGrupoId() {
        return grupoId;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdadMax() {
        return edadMax;
    }

    public int getEdadMin() {
        return edadMin;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdadMax(int edadMax) {
        this.edadMax = edadMax;
    }

    public void setEdadMin(int edadMin) {
        this.edadMin = edadMin;
    }
}
