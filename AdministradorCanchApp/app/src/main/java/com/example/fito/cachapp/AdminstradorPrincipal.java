package com.example.fito.cachapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminstradorPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminstrador_principal);

        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = fecha.format(new Date());

        TextView fechaActual = (TextView)findViewById(R.id.txt1);

        fechaActual.setText(fechaString);

    }

}
