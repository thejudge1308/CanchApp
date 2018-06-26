package com.example.patin.usuariocanchas.Item;

import android.graphics.drawable.Drawable;

public class ContactItem {
    //This class should be include Contacts and

    private String name;
    private String email;
    private Drawable imagen;

    public ContactItem(){
        super();
    }

    /**
     * @param name of contact or team
     * @param email of contact or Information of Team
     * @param imagen of Contact of Team
     */
    public ContactItem(String name, String email, Drawable imagen) {
        super();
        this.name = name;
        this.email = email;
        this.imagen = imagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Drawable getImagen() {
        return imagen;
    }

    public void setImagen(Drawable imagen) {
        this.imagen = imagen;
    }
}
