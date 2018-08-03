package com.example.patin.usuariocanchas.Model;


public class AdministradorCancha {


    String apellido;
    String nombre;
    int password;
    String rut;

    public AdministradorCancha() {
    }
    public AdministradorCancha(String rut, String nombre, String apellido, int password) {
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

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
