package com.example.patin.usuariocanchas.Model;


import java.util.ArrayList;

public class RegistroEventos {
    String idEvento;
    boolean realizado;//1 si se llevo a cabo, 0 caso contrario

    public RegistroEventos(String idEvento, boolean realizado) {
        this.idEvento = idEvento;
        this.realizado = realizado;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }
}
