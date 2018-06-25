package com.example.patin.usuariocanchas.Model.Objetos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Usuario {
    String email;
    String nickName;
    String nombre;
    String apellido;
    String password;
    Date fechaNacimiento;
    int puntuacion;
    boolean estado;//1 si esta conectado online, 0 si esta offline
    Timestamp creado; //fecha de creacion
    ArrayList<Servicio> servicios;
    ArrayList<Equipo> equipos;
    ArrayList<UsuarioPagaEvento> usuarioPagaEventos;
    ArrayList<Evento> eventos;
    ArrayList<Usuario> amigos;

    public Usuario(String email, String nickName, String nombre, String apellido, String password, Date fechaNacimiento, int puntuacion, boolean estado, Timestamp creado) {
        this.email = email;
        this.nickName = nickName;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.puntuacion = puntuacion;
        this.estado = estado;
        this.creado = creado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Timestamp getCreado() {
        return creado;
    }

    public void setCreado(Timestamp creado) {
        this.creado = creado;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public ArrayList<UsuarioPagaEvento> getUsuarioPagaEventos() {
        return usuarioPagaEventos;
    }

    public void setUsuarioPagaEventos(ArrayList<UsuarioPagaEvento> usuarioPagaEventos) {
        this.usuarioPagaEventos = usuarioPagaEventos;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    public ArrayList<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<Usuario> amigos) {
        this.amigos = amigos;
    }
}
