package com.example.patin.usuariocanchas.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.patin.usuariocanchas.Activities.CreateMatchActivity;
import com.example.patin.usuariocanchas.Adapter.AdapterSport;
import com.example.patin.usuariocanchas.Item.SportItem;
import com.example.patin.usuariocanchas.R;

import java.util.ArrayList;

public class SportsFragment extends Fragment {
    private ListView sportsListView;
    private ArrayList<SportItem> sportItemArrayList;
    public  View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sports, container,false);

        this.sportsListView = rootView.findViewById(R.id.sports_listview_sport_activity);
        this.sportItemArrayList = new ArrayList<>();
        this.createSports();

        AdapterSport adapterSport = new AdapterSport(getActivity(),this.sportItemArrayList);
        sportsListView.setAdapter(adapterSport);
        this.sportsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                switch (pos){
                    case 0:
                        CreaEventoFragment crearEvento = new CreaEventoFragment();
                        getFragmentManager().beginTransaction().replace(R.id.content_sport_activity,crearEvento).commit();
                        break;
                }
            }
        });


        return rootView;

    }

    public void createSports(){
        Drawable footballDrawable  = ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.logo_inicial);
        SportItem football = new SportItem("Football",footballDrawable);
        this.sportItemArrayList.add(football);

    }
}
