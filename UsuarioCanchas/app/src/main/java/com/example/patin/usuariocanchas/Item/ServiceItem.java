package com.example.patin.usuariocanchas.Item;

import android.graphics.drawable.Drawable;

public class ServiceItem {
    public final int USUARIO_HABILITADO=1;
    public final int USUARIO_INHABILITADO=0;

    private String name;
    private int price;
    private int state;
    private Drawable imagen;

    public ServiceItem(String name, int price, int state, Drawable imagen) {
        this.name = name;
        this.price = price;
        this.state = state;
        this.imagen = imagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Drawable getImagen() {
        return imagen;
    }

    public void setImagen(Drawable imagen) {
        this.imagen = imagen;
    }
}
