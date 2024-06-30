package modelo;

import java.time.LocalDateTime;

public class Turno {
    private int turnoId;
    private LocalDateTime hora;
    private int duracion;
    private int grupoId;
    private int campoId;

        public int getTurnoId() {
        return turnoId;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public int getCampoId() {
        return campoId;
    }

    public void setTurnoId(int turnoId) {
        this.turnoId = turnoId;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public void setCampoId(int campoId) {
        this.campoId = campoId;
    }
}
