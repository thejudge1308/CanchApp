package com.example.patin.usuariocanchas.Model;


import java.util.Date;

public class Evento {
    String nombreEvento;
    Date fechaCreacion; //fecha de creacion del evento
    String idHorarioReserva;//id HorarioCancha que guarda la reserva de un evento
    String idUsuario; //id usuario que crea el evento
    String idEquipo1;
    String idEquipo2;
    String idCancha;
    String idDeporte;

    public Evento(String nombreEvento, Date fechaCreacion, String idHorarioReserva, String idUsuario, String idEquipo1, String idEquipo2, String idCancha, String idDeporte) {
        this.nombreEvento = nombreEvento;
        this.fechaCreacion = fechaCreacion;
        this.idHorarioReserva = idHorarioReserva;
        this.idUsuario = idUsuario;
        this.idEquipo1 = idEquipo1;
        this.idEquipo2 = idEquipo2;
        this.idCancha = idCancha;
        this.idDeporte = idDeporte;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getIdHorarioReserva() {
        return idHorarioReserva;
    }

    public void setIdHorarioReserva(String idHorarioReserva) {
        this.idHorarioReserva = idHorarioReserva;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdEquipo1() {
        return idEquipo1;
    }

    public void setIdEquipo1(String idEquipo1) {
        this.idEquipo1 = idEquipo1;
    }

    public String getIdEquipo2() {
        return idEquipo2;
    }

    public void setIdEquipo2(String idEquipo2) {
        this.idEquipo2 = idEquipo2;
    }

    public String getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(String idCancha) {
        this.idCancha = idCancha;
    }

    public String getIdDeporte() {
        return idDeporte;
    }

    public void setIdDeporte(String idDeporte) {
        this.idDeporte = idDeporte;
    }
}
