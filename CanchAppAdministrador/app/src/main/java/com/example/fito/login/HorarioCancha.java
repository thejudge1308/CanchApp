package com.example.fito.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HorarioCancha extends AppCompatActivity
{
    private String nombreCancha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_cancha);

        nombreCancha = getIntent().getStringExtra("nombreCancha");

        TextView txtNombreCancha = (TextView) findViewById(R.id.txtNombreCancha);
        txtNombreCancha.setText(nombreCancha);

        ClickBotonVolver();

    }

    public void ClickBotonVolver()
    {
        TextView txtVolver = (TextView) findViewById(R.id.txtVolver);
        txtVolver.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                HorarioCancha.this.finish();
            }
        });

    }
}
