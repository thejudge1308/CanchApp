package com.example.patin.usuariocanchas.Model.Objetos;


import java.util.ArrayList;

public class RegistroEventos {
    int idEvento;
    boolean realizado;//1 si se llevo a cabo, 0 caso contrario

    public RegistroEventos(int idEvento, boolean realizado) {
        this.idEvento = idEvento;
        this.realizado = realizado;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }
}
