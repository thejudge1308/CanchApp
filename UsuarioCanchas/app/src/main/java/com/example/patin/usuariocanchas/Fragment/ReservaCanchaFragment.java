package com.example.patin.usuariocanchas.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patin.usuariocanchas.R;

public class ReservaCanchaFragment extends Fragment {

    View vista;
    String nombreCancha;

    public ReservaCanchaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombreCancha=getArguments().getString("nombreCancha");
        }
        Log.v("nombre", nombreCancha);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista=inflater.inflate(R.layout.fragment_reserva_cancha, container, false);
        return vista;
    }


}
