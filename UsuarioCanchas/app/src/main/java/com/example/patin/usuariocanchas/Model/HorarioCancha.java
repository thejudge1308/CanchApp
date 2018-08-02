package com.example.patin.usuariocanchas.Model;

import java.util.Date;

public class HorarioCancha {
    String hora_inicio;
    String hora_termino;

    public HorarioCancha(){
    }

    public HorarioCancha(String hora_inicio, String hora_termino) {
        this.hora_inicio = hora_inicio;
        this.hora_termino = hora_termino;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_termino() {
        return hora_termino;
    }

    public void setHora_termino(String hora_termino) {
        this.hora_termino = hora_termino;
    }
}
