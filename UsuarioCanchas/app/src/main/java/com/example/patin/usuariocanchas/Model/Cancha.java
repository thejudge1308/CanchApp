package com.example.patin.usuariocanchas.Model;


import java.util.ArrayList;

public class Cancha {
    String nombre;
    String direccion;
    Integer precio;
    Integer idAdministrador;//rut administrador
    Double latitud;
    Double longitud;
    String estado;

    public Cancha(String nombre, String direccion, Integer precio, Integer idAdministrador, Double latitud, Double longitud, String estado) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.precio = precio;
        this.idAdministrador = idAdministrador;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
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

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
