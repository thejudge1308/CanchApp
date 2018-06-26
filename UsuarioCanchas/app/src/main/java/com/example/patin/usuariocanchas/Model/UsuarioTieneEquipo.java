package com.example.patin.usuariocanchas.Model;

public class UsuarioTieneEquipo {
    String idEquipo;
    String idUsuario;

    public UsuarioTieneEquipo(String idEquipo, String idUsuario) {
        this.idEquipo = idEquipo;
        this.idUsuario = idUsuario;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
