package com.example.patin.usuariocanchas.Model;



public class Equipo {
    String nombre;
    String idUsuario;// id de usuario que crea el equipo

    public Equipo(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
