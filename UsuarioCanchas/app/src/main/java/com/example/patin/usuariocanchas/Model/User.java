package com.example.patin.usuariocanchas.Model;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class User {
    private String id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String nickname;
    private Double score;
    private boolean isActive;
    private String birthday;
    private ArrayList<Service> services;

    /**
     *
     * @param email Correo electronico del usuario
     * @param name Nombre
     * @param surname Apellido
     * @param nickname Apodo dentro del juego
     * @param birthday Fecha de cumpleaños
     *
     */
    public User(String email,String password, String name, String surname, String nickname,String birthday) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.surname = surname;
        this.nickname = nickname;
        this.score = 5.0;
        this.isActive =true;
        this.birthday = birthday;
    }




    public User() {
        this.email = "Unsigned";
        this.name = "Unsigned";
        this.surname = "Unsigned";
        this.nickname = "Unsigned";
        this.score = 5.0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public boolean isActive() { return isActive; }

    public void setActive(boolean active) { isActive = active; }

    public String getBirthday() { return birthday; }

    public void setBirthday(String birthday) { this.birthday = birthday; }

    public ArrayList<Service> getServices() { return services; }

    public void setServices(ArrayList<Service> services) { this.services = services; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }
}
