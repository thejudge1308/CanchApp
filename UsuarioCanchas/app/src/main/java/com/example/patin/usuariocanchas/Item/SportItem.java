package com.example.patin.usuariocanchas.Item;

import android.graphics.drawable.Drawable;

public class SportItem {
    private String name;
    private Drawable imagen;

    public SportItem(String name, Drawable imagen) {
        this.name = name;
        this.imagen = imagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImagen() {
        return imagen;
    }

    public void setImagen(Drawable imagen) {
        this.imagen = imagen;
    }
}
