package com.example.patin.usuariocanchas.Model;

public class NotificacionAmistad {
    String nombreSolicitante;
    String apellidoSolicitante;
    String nombreSolicitado;
    String apellidoSolicitado;
    String correoSolicitado;


    public NotificacionAmistad() {
    }

    public NotificacionAmistad(String nombreSolicitante, String apellidoSolicitante, String nombreSolicitado, String apellidoSolicitado, String correoSolicitado) {
        this.nombreSolicitante = nombreSolicitante;
        this.apellidoSolicitante = apellidoSolicitante;
        this.nombreSolicitado = nombreSolicitado;
        this.apellidoSolicitado = apellidoSolicitado;
        this.correoSolicitado = correoSolicitado;

    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public void setApellidoSolicitante(String apellidoSolicitante) {
        this.apellidoSolicitante = apellidoSolicitante;
    }

    public void setNombreSolicitado(String nombreSolicitado) {
        this.nombreSolicitado = nombreSolicitado;
    }

    public void setApellidoSolicitado(String apellidoSolicitado) {
        this.apellidoSolicitado = apellidoSolicitado;
    }

    public void setCorreoSolicitado(String correoSolicitado) {
        this.correoSolicitado = correoSolicitado;
    }


    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public String getApellidoSolicitante() {
        return apellidoSolicitante;
    }

    public String getNombreSolicitado() {
        return nombreSolicitado;
    }

    public String getApellidoSolicitado() {
        return apellidoSolicitado;
    }

    public String getCorreoSolicitado() {
        return correoSolicitado;
    }

}


