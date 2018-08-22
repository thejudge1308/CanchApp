package com.example.patin.usuariocanchas.Model;


import java.util.ArrayList;

public class Cancha {
    String nombre;
    String direccion;
    Long idAdministrador;//rut administrador
    String latitud;
    String longitud;
    Long valor;

    public Cancha() {
    }

    public Cancha(String nombre, String direccion, Long idAdministrador, String latitud, String longitud, long valor) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.idAdministrador = idAdministrador;
        this.latitud = latitud;
        this.longitud = longitud;
        this.valor = valor;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
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
