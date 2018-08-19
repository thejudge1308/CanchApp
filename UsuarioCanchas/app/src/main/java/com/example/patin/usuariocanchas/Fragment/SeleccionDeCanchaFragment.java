package com.example.patin.usuariocanchas.Fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.patin.usuariocanchas.Activities.CanchaPrincipalActivity;
import com.example.patin.usuariocanchas.Activities.HorarioCanchaActivity;
import com.example.patin.usuariocanchas.Activities.MapsCanchaActivity;
import com.example.patin.usuariocanchas.Model.Cancha;
import com.example.patin.usuariocanchas.Model.Reserva;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CanchaPrincipalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CanchaPrincipalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeleccionDeCanchaFragment extends DialogFragment {
    private ArrayList<Button> botonesCancha;
    private long idAdmin;
    public long cantidadCancha;
    private  long valorCancha;
    private String nombreCancha;
    public View view;
    public TextView fechaActual,nombreClubCanchas;
    public SeleccionDeCanchaFragment() {


        botonesCancha = new ArrayList<Button>();
    }


    public static CanchaPrincipalFragment newInstance(String param1, String param2) {
        CanchaPrincipalFragment fragment = new CanchaPrincipalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_seleccion_de_cancha, container, false);
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = fecha.format(new Date());
        //fechaActual = (TextView) view.findViewById(R.id.txt1);
        //fechaActual.setText(fechaString);
        nombreClubCanchas = (TextView) view.findViewById(R.id.txtNombreCLubCanchas);
        String nombreClub = getArguments().getString("nombreClub");
        Log.d("nombreClub",nombreClub);
        nombreClubCanchas.setText(nombreClub);
         idAdmin = (long) getArguments().get("idAdmin");
        //String id =getArguments().getString("idAmdin");
        Log.d("IdAdmin",idAdmin+" ctm");
        Log.d("CTM",nombreClub+" ctm");
        CantidadCanchas(idAdmin);
        return view;
    }

    public void CantidadCanchas(long id)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference clubCanchaReference = database.getReference(); //Obtiene la referencia de la bd

        Query query = clubCanchaReference.child(FireBaseReferences.CANCHA_REFERENCE).orderByChild("idAdministrador").equalTo(id);

        //Log.d("_Firebase","Query: "+query.toString()+"");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //Log.v("_Firebase","Entro");
                    //User user = SingletonUser.getInstance();
                    //Log.v("_Firebase","cantidad: "+dataSnapshot.getChildrenCount());
                    cantidadCancha = (Long) dataSnapshot.getChildrenCount();


                    Log.d("cantidadCancha",cantidadCancha+"");




                    CrearBotones(cantidadCancha);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        agregarNombreCanchaBotones(id);


    }

    public void CrearBotones(long cantidad)
    {

        botonesCancha.clear();
        //view.clearAnimation();
        Log.d("cantidad",cantidad+"");
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.linearlayoutbtns);
        layout.setOrientation(LinearLayout.VERTICAL);  //Can also be done in xml by android:orientation="vertical"
        layout.removeAllViews();


        for (int i = 0; i < cantidad; i++)
        {

            LinearLayout row = new LinearLayout(view.getContext());
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            Button btnTag = new Button(view.getContext());
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            btnTag.setText("Button " + (i + 1) );
            btnTag.setId(i + 1);
            btnTag.setBackgroundColor(0xff99cc00);
            btnTag.setWidth(600);
            Log.d("cantidad",cantidad+" fsdfsdf");
            /*Margen para los botones*/
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btnTag.getLayoutParams();
            params.setMargins(5, 20, 10, 0); //left, top, right, bottom
            row.setLayoutParams(params);


            botonesCancha.add(btnTag);
            row.addView(btnTag);
            //}

            layout.addView(row);
        }

        for (final Button btn:botonesCancha)
        {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {

                    Log.v("EntraBtn","entra");

                    Log.d("btn",btn.getText().toString());


                    android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                    if (prev != null) {
                        ft.remove(prev);
                    }
                    ft.addToBackStack(null);
                    DialogFragment dialogFragment = new HorarioCanchaDialogFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("btn",btn.getText().toString());
                    bundle.putString("idAdmin",String.valueOf(idAdmin));
                    dialogFragment.setArguments(bundle);

                    dialogFragment.show(ft, "dialog");
                    /*HorariosCanchaFragment horarioCanchaFragment=new HorariosCanchaFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("nombreCancha",btn.getText().toString());
                    horarioCanchaFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.idContenedor,horarioCanchaFragment).commit();*/

                }
            });
        }

    }

    public void agregarNombreCanchaBotones(long id)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference clubCanchaReference = database.getReference(); //Obtiene la referencia de la bd

        Query query = clubCanchaReference.child(FireBaseReferences.CANCHA_REFERENCE).orderByChild("idAdministrador").equalTo(id);

        Log.d("_Firebase2","Query: "+query.toString()+"");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Log.v("_Firebase2","Entro");
                    //User user = SingletonUser.getInstance();
                    Log.v("_Firebase2","cantidad: "+dataSnapshot.getChildrenCount());
                    //cantidadCancha = (Long) dataSnapshot.getChildrenCount();

                    //Log.d("cantidadCancha",cantidadCancha+"");
                    int contador = 0;

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Log.d("_Firebase2","Id: "+issue.getKey());
                        for(DataSnapshot values : issue.getChildren()){
                            Log.v("_Firebase2fdfd",values.getKey()+":"+values.getValue());
                            //valorCancha=(Long) values.getValue();
                            //Log.v("Valorcancha",String.valueOf(valorCancha));
                            if(values.getKey().contains("nombre")){
                                nombreCancha = (String) values.getValue();
                                Log.d("nombre",nombreCancha);
                                botonesCancha.get(contador).setText(nombreCancha);

                                Log.d("contador",contador+"");
                                contador++;
                            }
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
