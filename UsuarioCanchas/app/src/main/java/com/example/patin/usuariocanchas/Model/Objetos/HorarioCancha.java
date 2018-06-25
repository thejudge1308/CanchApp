package com.example.patin.usuariocanchas.Model.Objetos;

import java.util.Date;

public class HorarioCancha {
    Date fecha;
    String hora_inicio;
    String hora_termino;
    String estado; //Disponible, Ocupado, Reservado
    int idCancha;

    public HorarioCancha(Date fecha, String hora_inicio, String hora_termino, String estado, int idCancha) {
        this.fecha = fecha;
        this.hora_inicio = hora_inicio;
        this.hora_termino = hora_termino;
        this.estado = estado;
        this.idCancha = idCancha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_termino() {
        return hora_termino;
    }

    public void setHora_termino(String hora_termino) {
        this.hora_termino = hora_termino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }
}
