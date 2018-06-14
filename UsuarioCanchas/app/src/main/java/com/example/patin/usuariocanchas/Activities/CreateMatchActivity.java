package com.example.patin.usuariocanchas.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.patin.usuariocanchas.R;

public class CreateMatchActivity extends AppCompatActivity {

    private Button openMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_match);

        this.openMapButton = (Button)findViewById(R.id.openmaps_button_creatematchactivity);
        this.openMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateMatchActivity.this,MapsCanchaActivity.class);
                CreateMatchActivity.this.startActivity(i);
            }
        });



    }
}
