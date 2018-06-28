package com.example.patin.usuariocanchas.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.patin.usuariocanchas.R;

public class MyServiceActivity extends AppCompatActivity {

    private ListView servicesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

        this.servicesListView = findViewById(R.id.services_listview_myserviceactivity);
    }
}
