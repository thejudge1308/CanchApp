package com.example.patin.usuariocanchas.Model.Objetos;


public class AdministradorCancha {
    String rut;
    String nombre;
    String apellido;
    String password;

    public AdministradorCancha(String rut, String nombre, String apellido, String password) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
