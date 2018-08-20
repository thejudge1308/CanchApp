package com.example.patin.usuariocanchas.Fragment;


import android.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Activities.SportActivity;
import com.google.android.gms.common.images.internal.LoadingImageView;


public class HomeFragment extends Fragment {

    private Button crearEvento, reservas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container,false);

        this.crearEvento=(Button) rootView.findViewById(R.id.boton_crea_evento);
        this.crearEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("fito","algo");
                Intent i = new Intent(getActivity().getApplicationContext(),SportActivity.class);
                getActivity().startActivity(i);
                //CreaEventoFragment crearEvento = new CreaEventoFragment();
                //rgetFragmentManager().beginTransaction().replace(R.id.frangment_content,crearEvento).commit();
            }
        });

        this.reservas=(Button) rootView.findViewById(R.id.mis_reservas);
        this.reservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MisReservasFragment misReservasFragment=new MisReservasFragment();
                getFragmentManager().beginTransaction().replace(R.id.frangment_content,misReservasFragment).commit();
            }
        });

        return rootView;

    }


}
