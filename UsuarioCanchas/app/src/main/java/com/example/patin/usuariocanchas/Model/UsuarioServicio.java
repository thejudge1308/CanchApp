package com.example.patin.usuariocanchas.Model;


public class UsuarioServicio {
    boolean estado;// 1 estado activo da servicio, 0 estado inactivo no da servicio
    int valor; //valor a cobrar por el servicio

    public UsuarioServicio(int valor, boolean estado) {
        this.valor = valor;
        this.estado = estado;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
