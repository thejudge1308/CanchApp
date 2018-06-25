package com.example.patin.usuariocanchas.Model.Objetos;


import java.util.ArrayList;

public class UsuarioPagaEvento {
    int cuota;
    boolean estaPagado; //1 si el usuario pago la cuenta, 0 en caso contrario

    public UsuarioPagaEvento(int cuota, boolean estaPagado) {
        this.cuota = cuota;
        this.estaPagado = estaPagado;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    public boolean isEstaPagado() {
        return estaPagado;
    }

    public void setEstaPagado(boolean estaPagado) {
        this.estaPagado = estaPagado;
    }
}
