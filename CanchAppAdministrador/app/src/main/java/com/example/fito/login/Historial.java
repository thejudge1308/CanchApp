package com.example.fito.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Historial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        ClickVolver();
    }

    public void ClickVolver()
    {
        TextView txtVolver = (TextView) findViewById(R.id.txtVolver);
        txtVolver.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Historial.this.finish();
            }
        });

    }
}
