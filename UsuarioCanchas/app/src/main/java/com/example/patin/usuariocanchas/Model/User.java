package com.example.patin.usuariocanchas.Model;

public class User {
    private String email;
    private String name;
    private String surname;
    private String nickname;
    private Double score;

    /**
     *
     * @param email
     * @param name
     * @param surname
     * @param nickname
     * @param score
     */
    public User(String email, String name, String surname, String nickname, Double score) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.score = score;
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
}
