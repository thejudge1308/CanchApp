package com.example.patin.usuariocanchas.Fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Activities.SportActivity;
import com.example.patin.usuariocanchas.R;


public class CreaEventoFragment extends Fragment {
    View view;
    private Button newMatch,reservas;
    //private Spinner spinner;
    private Button activarGps;
    private Button creaEquipoButton;

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
        creaEquipoButton = view.findViewById(R.id.crearequipo_button_creaeventofragment);
        creaEquipoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreaEquipoFragment equipoFragment = new CreaEquipoFragment();
                getFragmentManager().beginTransaction().replace(R.id.content_sport_activity,equipoFragment).commit();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
            }
        });
        activarGps=view.findViewById(R.id.button_activar_gps);
        activarGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * dialog fragment google maps
                 */
                //CreaEventoFragment crearEvento = new CreaEventoFragment();
                MapsCanchaFragment maps = new MapsCanchaFragment();
                getFragmentManager().beginTransaction().replace(R.id.content_sport_activity,maps).commit();

                FragmentTransaction ft = getFragmentManager().beginTransaction();

            }
        });

        return view;
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
