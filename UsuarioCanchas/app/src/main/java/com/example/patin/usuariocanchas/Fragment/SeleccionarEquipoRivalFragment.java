package com.example.patin.usuariocanchas.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Activities.HomeActivity;
import com.example.patin.usuariocanchas.Activities.LoginActivity;
import com.example.patin.usuariocanchas.Activities.SportActivity;
import com.example.patin.usuariocanchas.CreateMessage;
import com.example.patin.usuariocanchas.MessagingService;
import com.example.patin.usuariocanchas.Model.Reserva;
import com.example.patin.usuariocanchas.Model.User;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.example.patin.usuariocanchas.Values.SingletonUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SeleccionarEquipoRivalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SeleccionarEquipoRivalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeleccionarEquipoRivalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private String miID = SingletonUser.getInstance().getId();

    private long cantidadEquipos;

    private ArrayList<CheckBox> checkBoxes;

    private View view;

    private String miEquipo,nombreEquipo;

    private int cantidadEquiposSeleccionados;

    private Button btnAcpertarPartido;

    private long valorCancha;

    String fechaEvento;
    String fechaReserva;
    String estado;
    String horaInicio;
    String horaTermino;
    String nombreCancha;

    public SeleccionarEquipoRivalFragment() {
        // Required empty public constructor
        checkBoxes = new ArrayList<CheckBox>();
        cantidadEquipos = 0;
        nombreEquipo = "";
        miEquipo="";
        cantidadEquiposSeleccionados = 0;

        fechaEvento="";
        fechaReserva="";
        estado="";
        horaInicio="";
        horaTermino="";
        nombreCancha="";

        valorCancha = 0;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SeleccionarEquipoRivalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SeleccionarEquipoRivalFragment newInstance(String param1, String param2) {
        SeleccionarEquipoRivalFragment fragment = new SeleccionarEquipoRivalFragment();
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


            miEquipo=SportActivity.nombreEquipoR;
            fechaEvento=SportActivity.fechaEventoR;
            fechaReserva=SportActivity.fechaReservaR;
            estado=SportActivity.estadoR;
            horaInicio=SportActivity.horaInicioR;
            horaTermino=SportActivity.horaTerminoR;
            nombreCancha=SportActivity.nombreCanchaR;
            valorCancha=Long.parseLong(SportActivity.valorCanchH);


            //miEquipo=getArguments().getString("miEquipo");

            //fechaEvento=getArguments().getString("fechaEvento");
            //fechaReserva=getArguments().getString("fechaReserva");
            //estado=getArguments().getString("estado");
            //horaInicio=getArguments().getString("horaInicio");
            //horaTermino=getArguments().getString("horaTermino");
            //nombreCancha=getArguments().getString("nombreCancha");

            //valorCancha=(long) getArguments().getLong("valorCancha");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_seleccionar_equipo_rival, container, false);

        btnAcpertarPartido = (Button) view.findViewById(R.id.btnAceptarPartido);

        CantidadAmigos();

        agregarNombreAmigosCheckBoxes();

        ClickBotonAceptar();

        return view;
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
            //throw new RuntimeException(context.toString()
              //      + " must implement OnFragmentInteractionListener");
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

    public void CantidadAmigos()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference clubCanchaReference = database.getReference();

        Query query = clubCanchaReference.child("Equipo").orderByChild("idAdminEquipo"); //Obtiene la referencia de la bd;

        //Log.d("_Firebase","Query: "+query.toString()+"");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String idAdmin = "";
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Log.d("_Firebase2", "Id: " + issue.getKey());
                        //Cancha cancha = issue.getValue(Cancha.class);
                        //Double latitud=0.0;
                        //Double longitud=0.0;

                        for (DataSnapshot values : issue.getChildren()) {
                            if(values.getKey().contains("idAdminEquipo")){
                                idAdmin = (String) values.getValue();
                                Log.d("idAdmin",idAdmin);

                                if(!idAdmin.equals(miID))
                                {
                                    cantidadEquipos++;
                                }
                            }
                        }
                    }

                    Log.d("cantidadEquipos", cantidadEquipos + "");
                    CrearCheckBox(cantidadEquipos);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //Log.d("cantidadCancha",cantidadCancha+"");
        //agregarNombreCanchaBotones(id);


    }

    public void CrearCheckBox(long cantidad)
    {
        checkBoxes.clear();
        //view.clearAnimation();
        Log.d("cantidad",cantidad+"");
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.linearlayoutcheckboxEquipos);
        layout.setOrientation(LinearLayout.VERTICAL);  //Can also be done in xml by android:orientation="vertical"
        layout.removeAllViews();


        for (int i = 0; i < cantidad; i++)
        {
            LinearLayout row = new LinearLayout(view.getContext());
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            CheckBox btnTag = new CheckBox(view.getContext());
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            btnTag.setText("Button " + (i + 1));
            btnTag.setId(i + 1);
            btnTag.setBackgroundColor(Color.parseColor("#94bd79"));

            /*Margen para los botones*/
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btnTag.getLayoutParams();
            params.setMargins(5, 20, 10, 0); //left, top, right, bottom
            row.setLayoutParams(params);

            checkBoxes.add(btnTag);
            row.addView(btnTag);
            //}

            layout.addView(row);
        }

        for (final CheckBox chkbox:checkBoxes)
        {

            chkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {

                    Log.v("EntraBtn","entra");

                    Log.d("chkbox","Texto: "+chkbox.getText().toString());

                    Log.d("chkbox","Id:"+chkbox.getId()+"");


                    int id = chkbox.getId();
                    if (chkbox.isChecked()==true) {
                        Log.d("chkbox","Clikc");
                        /*nombreIntegranteEquipoFinal.add(nombreIntegranteEquipo.get(id));
                        correoIntegranteEquipoFinal.add(correoIntegranteEquipo.get(id));
                        apellidoIntegranteEquipoFinal.add(apellidoIntegranteEquipo.get(id));*/
                        nombreEquipo = chkbox.getText().toString();
                        cantidadEquiposSeleccionados++;
                        // el código que quieras (mostrar un mensaje por pantalla por ejemplo)
                    }

                    if (chkbox.isChecked()==false) {
                        Log.d("chkbox","No Clikc");
                        /*nombreIntegranteEquipoFinal.remove(nombreIntegranteEquipo.get(id));
                        correoIntegranteEquipoFinal.remove(correoIntegranteEquipo.get(id));
                        apellidoIntegranteEquipoFinal.remove(apellidoIntegranteEquipo.get(id));*/
                        cantidadEquiposSeleccionados--;
                        // el código que quieras (mostrar un mensaje por pantalla por ejemplo)
                    }

                    Log.d("chkbox","cantidadEquiposSeleccionados: "+cantidadEquiposSeleccionados);

                    /*HorariosCanchaFragment horarioCanchaFragment=new HorariosCanchaFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("nombreCancha",btn.getText().toString());
                    horarioCanchaFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.idContenedor,horarioCanchaFragment).commit();*/



                }
            });
        }
    }

    public void agregarNombreAmigosCheckBoxes()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference clubCanchaReference = database.getReference();

        Query query = clubCanchaReference.child("Equipo").orderByChild("idAdminEquipo");

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

                    String nombreEquipo = "";

                    String idAdmin = miID;


                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Log.d("_Firebase2","Id: "+issue.getKey());
                        //Cancha cancha = issue.getValue(Cancha.class);
                        //Double latitud=0.0;
                        //Double longitud=0.0;

                        for(DataSnapshot values : issue.getChildren()){

                            Log.v("_Firebase2",values.getKey()+":"+values.getValue());
                            if(values.getKey().contains("idAdminEquipo")) {

                                idAdmin = (String) values.getValue();
                            }

                            if (values.getKey().contains("nombreEquipo")) {

                                if(!idAdmin.equals(miID)) {
                                    nombreEquipo = (String) values.getValue();
                                    Log.d("nombreEquipo", nombreEquipo);
                                    checkBoxes.get(contador).setText(nombreEquipo);
                                    Log.d("contador", contador + "");
                                    contador++;
                                    idAdmin=miID;
                                }
                                //nombreIntegranteEquipo.add(nombreAmigo);
                            }

                            /*if(values.getKey().contains("apellidoAmigo")){
                                apellidoAmigo = (String) values.getValue();
                                Log.d("apellidoAmigo",apellidoAmigo);
                                apellidoIntegranteEquipo.add(apellidoAmigo);

                            }
                            if(values.getKey().contains("corrreoAmigo")){
                                correoAmigo = (String) values.getValue();
                                Log.d("corrreoAmigo",correoAmigo);
                                correoIntegranteEquipo.add(correoAmigo);

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

    private void ClickBotonAceptar()
    {
        btnAcpertarPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("cantidadEuiposSe",cantidadEquiposSeleccionados+"");
                if(cantidadEquiposSeleccionados != 1)
                {
                    Toast toast = Toast.makeText(view.getContext(), "Se debe selccionar 1 equipo", Toast.LENGTH_LONG);
                    toast.show();
                }
                if(cantidadEquiposSeleccionados == 1)
                {


                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(view.getContext());
                    dialogo1.setTitle("CONFIRMACIÓN");
                    dialogo1.setMessage("¿ Confirma Equipo ?");
                    dialogo1.setCancelable(false);
                    dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            //aceptar();

                            Log.d("NombreEquipo","Mi Equipo: "+miEquipo);
                            Log.d("NombreEquipo","Equipo Rival: "+nombreEquipo);

                            FirebaseDatabase database=FirebaseDatabase.getInstance();
                            DatabaseReference horarioCanchaDatebase=database.getReference("HorarioCancha");
                            DatabaseReference reservaCancha=database.getReference(FireBaseReferences.RESERVA_REFERENCE).child(SingletonUser.getInstance().getId()).child(nombreCancha);


                            Reserva reserva=new Reserva(fechaEvento,fechaReserva,estado,horaInicio,horaTermino, SingletonUser.getInstance().getId(),nombreCancha);
                            //codigo necesario para almacenar el id dentro del objeto
                            DatabaseReference newReserva = reservaCancha.push();
                            newReserva.setValue(reserva);
                            String keyUser = newReserva.getKey();
                            Log.v("keyy",keyUser);
                            Map<String, Object> reservaUpdates = new HashMap<>();
                            reservaUpdates.put(keyUser+"/id", keyUser);
                            reservaCancha.updateChildren(reservaUpdates);
                            //end
                            //reservaCancha.push().setValue(reserva);
                            //Toast.makeText(view.getContext(),"Reservado con exito",Toast.LENGTH_LONG).show();
                            //SeleccionarEquipoRivalFragment seleccionarEquipoRivalFragment = new SeleccionarEquipoRivalFragment();
                            //getFragmentManager().beginTransaction().replace(R.id.content_sport_activity,seleccionarEquipoRivalFragment).commit();

                            Toast toast = Toast.makeText(view.getContext(), "Partido creado con éxito", Toast.LENGTH_SHORT);
                            toast.show();

                           //Envio de notificaciones.
                            sendNotification(SportActivity.nombreEquipoR);
                            sendNotification(miEquipo);

                            WebpayFragment webpayFragment = new WebpayFragment();
                            Bundle bundle=new Bundle();


                            SportActivity.amount= Integer.parseInt(valorCancha+"");
                            Log.v("_WEBPAY","Valor de la cancha "+SportActivity.amount);
                            //bundle.putInt("amount",Integer.parseInt(valorCancha+""));
                            SportActivity.keyUser = keyUser;
                            //bundle.putString("eventId",keyUser);

                            webpayFragment.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.content_sport_activity,webpayFragment).commit();

                            //HorarioCanchaDialogFragment horarioCanchaDialogFragment = new HorarioCanchaDialogFragment();
                            //Bundle bundle=new Bundle();
                            //bundle.putString("idAdmin",SingletonUser.getInstance().getId());
                            //bundle.putString("btn",nombreCancha);
                            //horarioCanchaDialogFragment.setArguments(bundle);
                            //getFragmentManager().beginTransaction().replace(R.id.content_sport_activity,horarioCanchaDialogFragment).commit();

                        }
                    });
                    dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            //cancelar();
                            Toast toast = Toast.makeText(view.getContext(), "No se ha confirmado equipo", Toast.LENGTH_LONG);
                            toast.show();

                            HorarioCanchaDialogFragment horarioCanchaDialogFragment = new HorarioCanchaDialogFragment();
                            Bundle bundle=new Bundle();
                            bundle.putString("idAdmin",SingletonUser.getInstance().getId());
                            bundle.putString("btn",nombreCancha);
                            horarioCanchaDialogFragment.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.content_sport_activity,horarioCanchaDialogFragment).commit();
                        }
                    });
                    dialogo1.show();


                }

            }
        });

    }

    public void sendNotification(String equipo){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userReference = database.getReference(); //Obtiene la referencia de la bd
        Query query = userReference.child("Equipo").orderByChild("nombreEquipo").equalTo(equipo);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                   // Log.v("MESSAGING_FIREBASE",dataSnapshot.toString());

                    for(DataSnapshot d : dataSnapshot.getChildren()){
                     //   Log.v("MESSAGING_FIREBASE",d.getValue().toString());
                        for (DataSnapshot x : d.getChildren()){
                            for(DataSnapshot in : x.getChildren()){
                                //Log.v("MESSAGING_FIREBASE",x.getValue().toString());
                                if(in.getKey().contains("Integrante")){
                                    Log.v("MESSAGING_FIREBASE",in.getValue().toString());
                                    new CreateMessage(in.getValue().toString(),CreateMessage.NOTIFICACION_PARTIDO);
                                }
                            }

                        }

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
