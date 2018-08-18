package com.example.patin.usuariocanchas.Model;

public class Reserva {
    String fecha_evento;
    String fecha_reserva;
    String estado;
    String hora_inicio;
    String hora_termino;
    String id;
    String idUsuarioQueReserva;
    String idCancha;

    public Reserva() {
    }

    public Reserva(String fecha_evento, String fecha_reserva, String estado, String hora_inicio, String hora_termino, String idUsuarioQueReserva, String idCancha) {
        this.fecha_evento = fecha_evento;
        this.fecha_reserva = fecha_reserva;
        this.estado = estado;
        this.hora_inicio = hora_inicio;
        this.hora_termino = hora_termino;
        this.idUsuarioQueReserva = idUsuarioQueReserva;
        this.idCancha = idCancha;
    }

    public String getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(String idCancha) {
        this.idCancha = idCancha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuarioQueReserva() {
        return idUsuarioQueReserva;
    }

    public void setIdUsuarioQueReserva(String idUsuarioQueReserva) {
        this.idUsuarioQueReserva = idUsuarioQueReserva;
    }

    public String getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(String fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    public String getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(String fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
}
