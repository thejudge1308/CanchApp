package com.example.patin.usuariocanchas.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.patin.usuariocanchas.Activities.SportActivity;
import com.example.patin.usuariocanchas.R;


public class CreaEventoFragment extends Fragment {
    View view;
    private Button newMatch,creaEquipo;

    private OnFragmentInteractionListener mListener;

    public CreaEventoFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.removeAllViews();
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_crea_evento, container, false);

        this.newMatch = (Button) view.findViewById(R.id.reservarcancha_button_fragmenthome);
        this.newMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SportActivity.class);
                startActivity(intent);
            }
        });

        this.creaEquipo = (Button) view.findViewById(R.id.botonCrearEquipo);
        this.creaEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreaEquipoFragment crearEquipo = new CreaEquipoFragment();
                getFragmentManager().beginTransaction().replace(R.id.frangment_content,crearEquipo).commit();
            }
        });






        return view;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
