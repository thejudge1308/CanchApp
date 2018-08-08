package com.example.patin.usuariocanchas.Model;


import java.util.ArrayList;

public class Cancha {
    String nombre;
    String direccion;
    String precio;
    Long idAdministrador;//rut administrador
    String latitud;
    String longitud;

    public Cancha() {
    }

    public Cancha(String nombre, String direccion, String precio, Long idAdministrador, String latitud, String longitud) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.precio = precio;
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Long getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Long idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
