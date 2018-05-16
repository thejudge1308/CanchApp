package com.example.patin.usuariocanchas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class EquipoActivity extends AppCompatActivity {
    private Button createEquipoButton;
    private ListView myEquipoListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);
        this.createEquipoButton = findViewById(R.id.createequipo_button_equipoactivity);
        this.createEquipoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        this.myEquipoListView = findViewById(R.id.myequipo_listview_equipoactivity);

    }



}
