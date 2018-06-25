package com.example.patin.usuariocanchas.Model.Objetos;



public class Equipo {
    String nombre;
    Usuario jefeEquipo;//objeto usuario que crea el equipo

    public Equipo(String nombre, Usuario jefeEquipo) {
        this.nombre = nombre;
        this.jefeEquipo = jefeEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    Usuario getJefeEquipo() {
        return jefeEquipo;
    }

    public void setJefeEquipo(Usuario jefeEquipo) {
        this.jefeEquipo = jefeEquipo;
    }
}
