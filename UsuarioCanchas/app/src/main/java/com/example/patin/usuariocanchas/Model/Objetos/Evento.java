package com.example.patin.usuariocanchas.Model.Objetos;


import java.util.Date;

public class Evento {
    String nombreEvento;
    Date fechaCreacion; //fecha de creacion del evento
    int idHorarioReserva;//id HorarioCancha que guarda la reserva de un evento
    String idUsuario; //id usuario que crea el evento
    int idEquipo1;
    int idEquipo2;
    int idCancha;
    int idDeporte;

    public Evento(String nombreEvento, Date fechaCreacion, int idHorarioReserva, String idUsuario, int idEquipo1, int idEquipo2, int idCancha, int idDeporte) {
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

    public int getIdHorarioReserva() {
        return idHorarioReserva;
    }

    public void setIdHorarioReserva(int idHorarioReserva) {
        this.idHorarioReserva = idHorarioReserva;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEquipo1() {
        return idEquipo1;
    }

    public void setIdEquipo1(int idEquipo1) {
        this.idEquipo1 = idEquipo1;
    }

    public int getIdEquipo2() {
        return idEquipo2;
    }

    public void setIdEquipo2(int idEquipo2) {
        this.idEquipo2 = idEquipo2;
    }

    public int getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }

    public int getIdDeporte() {
        return idDeporte;
    }

    public void setIdDeporte(int idDeporte) {
        this.idDeporte = idDeporte;
    }
}
