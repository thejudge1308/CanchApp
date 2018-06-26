package com.example.patin.usuariocanchas.Model;

import java.sql.Date;

public class ContactNotification {
    private boolean enable;
    private boolean acepted;
    private boolean sender;
    private boolean destinity;
    private Date date;

    public ContactNotification(boolean enable, boolean acepted, boolean sender, boolean destinity, Date date) {
        this.enable = enable;
        this.acepted = acepted;
        this.sender = sender;
        this.destinity = destinity;
        this.date = date;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isAcepted() {
        return acepted;
    }

    public void setAcepted(boolean acepted) {
        this.acepted = acepted;
    }

    public boolean isSender() {
        return sender;
    }

    public void setSender(boolean sender) {
        this.sender = sender;
    }

    public boolean isDestinity() {
        return destinity;
    }

    public void setDestinity(boolean destinity) {
        this.destinity = destinity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
