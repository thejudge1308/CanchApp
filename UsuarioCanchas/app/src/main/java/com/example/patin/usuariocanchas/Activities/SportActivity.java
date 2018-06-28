package com.example.patin.usuariocanchas.Activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Activities.CreateMatchActivity;
import com.example.patin.usuariocanchas.Adapter.AdapterContact;
import com.example.patin.usuariocanchas.Adapter.AdapterSport;
import com.example.patin.usuariocanchas.Item.SportItem;
import com.example.patin.usuariocanchas.R;

import java.util.ArrayList;

public class SportActivity extends AppCompatActivity {

    private ListView sportsListView;
    private ArrayList<SportItem> sportItemArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        this.sportsListView = findViewById(R.id.sports_listview_sport_activity);
        this.sportItemArrayList = new ArrayList<>();
        this.createSports();

        AdapterSport adapterSport = new AdapterSport(this,this.sportItemArrayList);
        sportsListView.setAdapter(adapterSport);
        this.sportsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                switch (pos){
                    case 0:
                        Intent intent = new Intent(getApplicationContext(),CreateMatchActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }


    public void createSports(){
        Drawable footballDrawable  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.logo_inicial);
        SportItem football = new SportItem("Football",footballDrawable);


        this.sportItemArrayList.add(football);

    }

}
