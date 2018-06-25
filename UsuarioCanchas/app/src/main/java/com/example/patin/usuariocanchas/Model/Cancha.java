package com.example.patin.usuariocanchas.Model;


import java.util.ArrayList;

public class Cancha {
    String nombreCancha;
    String direccion;
    String ubicacion;
    int precio;
    String idAdministrador;//rut administrador

    public Cancha(String nombreCancha, String direccion, String ubicacion, int precio, String idAdministrador) {
        this.nombreCancha = nombreCancha;
        this.direccion = direccion;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.idAdministrador = idAdministrador;
    }

    public String getNombreCancha() {
        return nombreCancha;
    }

    public void setNombreCancha(String nombreCancha) {
        this.nombreCancha = nombreCancha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(String idAdministrador) {
        this.idAdministrador = idAdministrador;
    }
}
