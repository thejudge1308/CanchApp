package com.example.patin.usuariocanchas.Model;

public class NotificacionAmistad {
    String nombreSolicitante;
    String apellidoSolicitante;
    String nombreSolicitado;
    String apellidoSolicitado;
    String correoSolicitado;
    String correoSolicitante;
    String idSolicitado;
    int estado=0;


    public NotificacionAmistad() {
    }

    public NotificacionAmistad(String nombreSolicitante, String apellidoSolicitante, String nombreSolicitado, String apellidoSolicitado, String correoSolicitado, String correoSolicitante, String idSolicitado) {
        this.nombreSolicitante = nombreSolicitante;
        this.apellidoSolicitante = apellidoSolicitante;
        this.nombreSolicitado = nombreSolicitado;
        this.apellidoSolicitado = apellidoSolicitado;
        this.correoSolicitado = correoSolicitado;
        this.correoSolicitante = correoSolicitante;
        this.idSolicitado = idSolicitado;
    }

    public String getIdSolicitado() {
        return idSolicitado;
    }

    public void setIdSolicitado(String idSolicitado) {
        this.idSolicitado = idSolicitado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getCorreoSolicitante() {
        return correoSolicitante;
    }

    public void setCorreoSolicitante(String correoSolicitante) {
        this.correoSolicitante = correoSolicitante;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public String getApellidoSolicitante() {
        return apellidoSolicitante;
    }

    public void setApellidoSolicitante(String apellidoSolicitante) {
        this.apellidoSolicitante = apellidoSolicitante;
    }

    public String getNombreSolicitado() {
        return nombreSolicitado;
    }

    public void setNombreSolicitado(String nombreSolicitado) {
        this.nombreSolicitado = nombreSolicitado;
    }

    public String getApellidoSolicitado() {
        return apellidoSolicitado;
    }

    public void setApellidoSolicitado(String apellidoSolicitado) {
        this.apellidoSolicitado = apellidoSolicitado;
    }

    public String getCorreoSolicitado() {
        return correoSolicitado;
    }

    public void setCorreoSolicitado(String correoSolicitado) {
        this.correoSolicitado = correoSolicitado;
    }

}


