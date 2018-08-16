package com.example.patin.usuariocanchas.Model;

public class Amigo {
    String apellidoAmigo;
    String corrreoAmigo;
    String nombreAmigo;
    String miCorreo;

    public Amigo() {
    }

    public Amigo(String apellidoAmigo, String corrreoAmigo, String nombreAmigo, String miCorreo) {
        this.apellidoAmigo = apellidoAmigo;
        this.corrreoAmigo = corrreoAmigo;
        this.nombreAmigo = nombreAmigo;
        this.miCorreo = miCorreo;
    }

    public void setApellidoAmigo(String apellidoAmigo) {
        this.apellidoAmigo = apellidoAmigo;
    }

    public void setCorrreoAmigo(String corrreoAmigo) {
        this.corrreoAmigo = corrreoAmigo;
    }

    public void setNombreAmigo(String nombreAmigo) {
        this.nombreAmigo = nombreAmigo;
    }

    public void setMiCorreo(String miCorreo) {
        this.miCorreo = miCorreo;
    }

    public String getApellidoAmigo() {
        return apellidoAmigo;
    }

    public String getCorrreoAmigo() {
        return corrreoAmigo;
    }

    public String getNombreAmigo() {
        return nombreAmigo;
    }

    public String getMiCorreo() {
        return miCorreo;
    }
}
