package com.example.patin.usuariocanchas.Model;



/*public class Equipo {
    String nombre;
    String idUsuario;// id de usuario que crea el equipo

    public Equipo(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}*/

public class  Equipo{
    public String nombreAdminEquipo;
    public String idAdminEquipo;
    public String correoAdminEquipo;
    public String nombreEquipo;
    public String fechaActual;

    public Equipo(String nombreAdminEquipo, String idAdminEquipo, String correoAdminEquipo, String nombreEquipo, String fechaActual) {
        this.nombreAdminEquipo = nombreAdminEquipo;
        this.idAdminEquipo = idAdminEquipo;
        this.correoAdminEquipo = correoAdminEquipo;
        this.nombreEquipo = nombreEquipo;
        this.fechaActual = fechaActual;
    }

    public String getNombreAdminEquipo() {
        return nombreAdminEquipo;
    }

    public void setNombreAdminEquipo(String nombreAdminEquipo) {
        this.nombreAdminEquipo = nombreAdminEquipo;
    }

    public String getIdAdminEquipo() {
        return idAdminEquipo;
    }

    public void setIdAdminEquipo(String idAdminEquipo) {
        this.idAdminEquipo = idAdminEquipo;
    }

    public String getCorreoAdminEquipo() {
        return correoAdminEquipo;
    }

    public void setCorreoAdminEquipo(String correoAdminEquipo) {
        this.correoAdminEquipo = correoAdminEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }
}


