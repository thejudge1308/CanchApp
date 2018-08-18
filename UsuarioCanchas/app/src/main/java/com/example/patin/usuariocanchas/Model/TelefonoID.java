package com.example.patin.usuariocanchas.Model;

public class TelefonoID {
    private String email;
    private String phone_id;

    public TelefonoID() {

    }

    public TelefonoID(String email, String phone_id) {
        this.email = email;
        this.phone_id = phone_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(String phone_id) {
        this.phone_id = phone_id;
    }
}
