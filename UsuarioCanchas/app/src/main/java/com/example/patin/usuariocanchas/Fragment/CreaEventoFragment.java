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
    private Spinner spinner;
    private Button activarGps;
    private Button creaEquipo;


    public static String fechaEvento=null;
    public static String horaInicio=null;
    public static String horaTermino=null;
    public static String nombreCancha=null;


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
        //codigo para spinner o comboBox
        spinner= (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> arrayAdapter=ArrayAdapter.createFromResource(view.getContext(),R.array.opciones,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        activarGps=view.findViewById(R.id.button_activar_gps);
        activarGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                //prev.setTargetFragment(getParentFragment(), 0);

                MapsCanchaFragment dialogFragment = new MapsCanchaFragment();
                //dialogFragment.setDialogoResult(this);
                dialogFragment.show(ft, "dialog");

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
