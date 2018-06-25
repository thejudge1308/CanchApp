package com.example.patin.usuariocanchas.Model.Objetos;

public class UsuarioTieneEquipo {
    int idEquipo;
    String idUsuario;

    public UsuarioTieneEquipo(int idEquipo, String idUsuario) {
        this.idEquipo = idEquipo;
        this.idUsuario = idUsuario;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
