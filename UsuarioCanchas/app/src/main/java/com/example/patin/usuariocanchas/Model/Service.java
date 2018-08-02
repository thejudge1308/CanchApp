package com.example.patin.usuariocanchas.Model;

import java.io.Serializable;

public class Service implements Serializable {
    private String nombre;
    private int valor;
    private int estado;

    public Service(String nombre, int value, int estado) {
        this.nombre = nombre;
        this.valor = value;
        this.estado = estado;
    }
    public Service(){
        this.valor = 0;
        this.estado=0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int value) {
        this.valor = value;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
