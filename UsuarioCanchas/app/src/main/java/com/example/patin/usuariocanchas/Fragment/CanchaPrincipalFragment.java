package com.example.patin.usuariocanchas.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.patin.usuariocanchas.Activities.CanchaPrincipalActivity;
import com.example.patin.usuariocanchas.Activities.HorarioCanchaActivity;
import com.example.patin.usuariocanchas.Activities.MapsCanchaActivity;
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
public class CanchaPrincipalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ArrayList<Button> botonesCancha;

    private long idAdmin;

    public long cantidadCancha;

    public View view;

    public TextView fechaActual,nombreClubCanchas;

    public CanchaPrincipalFragment() {
        // Required empty public constructor

        botonesCancha = new ArrayList<Button>();

        cantidadCancha = 0;

        idAdmin=0;


    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CanchaPrincipalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CanchaPrincipalFragment newInstance(String param1, String param2) {
        CanchaPrincipalFragment fragment = new CanchaPrincipalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_cancha_principal, container, false);

        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = fecha.format(new Date());

        fechaActual = (TextView) view.findViewById(R.id.txt1);

        fechaActual.setText(fechaString);

        nombreClubCanchas = (TextView) view.findViewById(R.id.txtNombreCLubCanchas);


        String nombreClub =getArguments().getString("nombreClub");

        Log.d("nombreClub",nombreClub);

        nombreClubCanchas.setText(nombreClub);

        idAdmin =getArguments().getLong("idAmdin");

        //String id =getArguments().getString("idAmdin");

        Log.d("IdAdmin",idAdmin+"");

        CantidadCanchas(idAdmin);

        //Log.v("EntraBtn","entra");
        clickBotonCancha();



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


                    /*for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Log.d("_Firebase","Id: "+issue.getKey());
                        //Cancha cancha = issue.getValue(Cancha.class);
                        //Double latitud=0.0;
                        //Double longitud=0.0;
                        long id =0;
                        for(DataSnapshot values : issue.getChildren()){
                            Log.v("_Firebase",values.getKey()+":"+values.getValue());
                            if(values.getKey().contains("idAdministrador")){
                                idAdmin = (Long) values.getValue();
                            }else if(values.getKey().contains("longitud")){
                                longitud = (Double)values.getValue();
                            }
                            else if(values.getKey().contains("nombre"))
                            {
                                //String direccionAux2= conversorDireccion(latitud, longitud);
                                String direccionAux2=(String)values.getValue();
                                direccion.add(direccionAux2);
                            }

                        }


                        //LatLng curicoAux = new LatLng(latitud, longitud);

                        //curico.add(curicoAux);

                    }*/
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //Log.d("cantidadCancha",cantidadCancha+"");
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
            LinearLayout row = new LinearLayout(getContext());
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            Button btnTag = new Button(getContext());
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            btnTag.setText("Button " + (i + 1));
            btnTag.setId(i + 1);
            btnTag.setBackgroundColor(Color.parseColor("#94bd79"));

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

                    HorariosCanchaFragment horarioCanchaFragment=new HorariosCanchaFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("nombreCancha",btn.getText().toString());
                    horarioCanchaFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.idContenedor,horarioCanchaFragment).commit();

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
                        //Cancha cancha = issue.getValue(Cancha.class);
                        //Double latitud=0.0;
                        //Double longitud=0.0;

                        for(DataSnapshot values : issue.getChildren()){
                            Log.v("_Firebase2",values.getKey()+":"+values.getValue());
                            if(values.getKey().contains("nombre")){
                                String nombreCancha = (String) values.getValue();
                                Log.d("nombre",nombreCancha);
                                botonesCancha.get(contador).setText(nombreCancha);
                                Log.d("contador",contador+"");
                                contador++;
                            }/*else if(values.getKey().contains("longitud")){
                                longitud = (Double)values.getValue();
                            }
                            else if(values.getKey().contains("nombre"))
                            {
                                //String direccionAux2= conversorDireccion(latitud, longitud);
                                String direccionAux2=(String)values.getValue();
                                direccion.add(direccionAux2);
                            }*/

                        }


                        //LatLng curicoAux = new LatLng(latitud, longitud);

                        //curico.add(curicoAux);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void clickBotonCancha()
    {


        /*for (final Button btn:botonesCancha)
        {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {

                    Log.v("EntraBtn","entra");

                    Log.d("btn",btn.getText().toString());
                    HorariosCanchaFragment fr1 = new HorariosCanchaFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    Bundle data = new Bundle();
                    data.putString("nombreCancha",  btn.getText().toString());
                    fr1.setArguments(data);

                    transaction.replace(R.id.contenedorFragment, fr1);
                    transaction.commit();

                }
            });
        }*/
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
