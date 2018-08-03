package com.example.patin.usuariocanchas.Model;

public class ClubCancha
{
    String nombre;
    String direccion;
    Integer idAdministrador;//rut administrador
    Double latitud;
    Double longitud;

    public ClubCancha(String nombre, String direccion, Integer idAdministrador, Double latitud, Double longitud) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.idAdministrador = idAdministrador;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
