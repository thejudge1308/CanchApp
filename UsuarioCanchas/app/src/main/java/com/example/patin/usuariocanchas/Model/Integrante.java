package com.example.patin.usuariocanchas.Model;

public class Integrante
{
    public String correIntegrante;
    public String nombreIntgrante;
    public String apellido;

    public Integrante(String correIntegrante, String nombreIntgrante, String apellido) {
        this.correIntegrante = correIntegrante;
        this.nombreIntgrante = nombreIntgrante;
        this.apellido = apellido;
    }

    public String getCorreIntegrante() {
        return correIntegrante;
    }

    public void setCorreIntegrante(String correIntegrante) {
        this.correIntegrante = correIntegrante;
    }

    public String getNombreIntgrante() {
        return nombreIntgrante;
    }

    public void setNombreIntgrante(String nombreIntgrante) {
        this.nombreIntgrante = nombreIntgrante;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



}

