package com.example.patin.usuariocanchas.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.example.patin.usuariocanchas.Values.SingletonUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class CreaEquipoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ArrayList<CheckBox> checkBoxes;

    private View view;

    private String miID = SingletonUser.getInstance().getId();

    private String miCorreo = SingletonUser.getInstance().getEmail();

    private int cantidadAmigosSeleccionados;

    private Button btnAcpertarEquipo;

    private long cantidadAmigos;

    private TextView fechaActual;

    private String fechaString;

    private EditText nombreEquipo;

    private ArrayList<String> correoIntegranteEquipo, nombreIntegranteEquipo,apellidoIntegranteEquipo,
            correoIntegranteEquipoFinal, nombreIntegranteEquipoFinal,apellidoIntegranteEquipoFinal;

    public CreaEquipoFragment() {
        checkBoxes = new ArrayList<CheckBox>();
        cantidadAmigos = 0;
        cantidadAmigosSeleccionados=0;
        correoIntegranteEquipo = new ArrayList<String>();
        nombreIntegranteEquipo = new ArrayList<String>();
        apellidoIntegranteEquipo = new ArrayList<String>();

        correoIntegranteEquipoFinal = new ArrayList<String>();
        nombreIntegranteEquipoFinal = new ArrayList<String>();
        apellidoIntegranteEquipoFinal = new ArrayList<String>();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreaEquipoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreaEquipoFragment newInstance(String param1, String param2) {
        CreaEquipoFragment fragment = new CreaEquipoFragment();
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
        view = inflater.inflate(R.layout.fragment_crea_equipo, container, false);


        CantidadAmigos();

        agregarNombreAmigosCheckBoxes();

        //CrearCheckBox(3);

        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        fechaString = fecha.format(new Date());

        btnAcpertarEquipo = (Button) view.findViewById(R.id.btnAceptarEquipo);

        fechaActual = (TextView) view.findViewById(R.id.txtFecha);

        fechaActual.setText(fechaString);

        nombreEquipo = (EditText) view.findViewById(R.id.editNombreEquipo);

        correoIntegranteEquipo.add(SingletonUser.getInstance().getEmail());
        nombreIntegranteEquipo.add(SingletonUser.getInstance().getName());
        apellidoIntegranteEquipo.add(SingletonUser.getInstance().getSurname());

        correoIntegranteEquipoFinal.add(SingletonUser.getInstance().getEmail());
        nombreIntegranteEquipoFinal.add(SingletonUser.getInstance().getName());
        apellidoIntegranteEquipoFinal.add(SingletonUser.getInstance().getSurname());

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

    public void CantidadAmigos()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference clubCanchaReference = database.getReference("Amigos"); //Obtiene la referencia de la bd

        Query query = clubCanchaReference.child(miID);

        //Log.d("_Firebase","Query: "+query.toString()+"");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //Log.v("_Firebase","Entro");
                    //User user = SingletonUser.getInstance();
                    //Log.v("_Firebase","cantidad: "+dataSnapshot.getChildrenCount());
                    cantidadAmigos = (Long) dataSnapshot.getChildrenCount();

                    Log.d("cantidadAmigos",cantidadAmigos+"");

                    CrearCheckBox(cantidadAmigos);

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
        //agregarNombreCanchaBotones(id);


    }

    public void CrearCheckBox(long cantidad)
    {
        checkBoxes.clear();
        //view.clearAnimation();
        Log.d("cantidad",cantidad+"");
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.linearlayoutcheckbox);
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
                        nombreIntegranteEquipoFinal.add(nombreIntegranteEquipo.get(id));
                        correoIntegranteEquipoFinal.add(correoIntegranteEquipo.get(id));
                        apellidoIntegranteEquipoFinal.add(apellidoIntegranteEquipo.get(id));
                        cantidadAmigosSeleccionados++;
                        // el código que quieras (mostrar un mensaje por pantalla por ejemplo)
                    }

                    if (chkbox.isChecked()==false) {
                        Log.d("chkbox","No Clikc");
                        nombreIntegranteEquipoFinal.remove(nombreIntegranteEquipo.get(id));
                        correoIntegranteEquipoFinal.remove(correoIntegranteEquipo.get(id));
                        apellidoIntegranteEquipoFinal.remove(apellidoIntegranteEquipo.get(id));
                        cantidadAmigosSeleccionados--;
                        // el código que quieras (mostrar un mensaje por pantalla por ejemplo)
                    }

                    Log.d("chkbox","cantidadAmigosSeleccionados: "+cantidadAmigosSeleccionados);

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
        DatabaseReference clubCanchaReference = database.getReference("Amigos"); //Obtiene la referencia de la bd

        Query query = clubCanchaReference.child(miID);

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

                    String nombreAmigo = "";

                    String apellidoAmigo = "";

                    String correoAmigo = "";

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Log.d("_Firebase2","Id: "+issue.getKey());
                        //Cancha cancha = issue.getValue(Cancha.class);
                        //Double latitud=0.0;
                        //Double longitud=0.0;

                        for(DataSnapshot values : issue.getChildren()){

                            Log.v("_Firebase2",values.getKey()+":"+values.getValue());
                            if(values.getKey().contains("nombreAmigo")){
                                nombreAmigo = (String) values.getValue();
                                Log.d("nombreAmigo",nombreAmigo);
                                checkBoxes.get(contador).setText(nombreAmigo+" "+apellidoAmigo);
                                Log.d("contador",contador+"");
                                contador++;
                                nombreIntegranteEquipo.add(nombreAmigo);
                            }
                            if(values.getKey().contains("apellidoAmigo")){
                                apellidoAmigo = (String) values.getValue();
                                Log.d("apellidoAmigo",apellidoAmigo);
                                apellidoIntegranteEquipo.add(apellidoAmigo);

                            }
                            if(values.getKey().contains("corrreoAmigo")){
                                correoAmigo = (String) values.getValue();
                                Log.d("corrreoAmigo",correoAmigo);
                                correoIntegranteEquipo.add(correoAmigo);

                            }
                            /*else if(values.getKey().contains("longitud")){
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

    private void ClickBotonAceptar()
     {
        btnAcpertarEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("cantidadAmigosSe",cantidadAmigosSeleccionados+"");
                if(cantidadAmigosSeleccionados<4 || cantidadAmigosSeleccionados>10)
                {
                    Toast toast = Toast.makeText(view.getContext(), "Cantidad de integrantes debe ser entre 5 y 10 jugadores", Toast.LENGTH_LONG);
                    toast.show();
                }
                if(cantidadAmigosSeleccionados>=4 && cantidadAmigosSeleccionados<=9)
                {


                    if(!nombreEquipo.getText().toString().equals("") && !nombreEquipo.getText().toString().equals(" "))
                    {

                        FirebaseDatabase database = FirebaseDatabase.getInstance();

                        Query query = database.getReference("Equipo").child(nombreEquipo.getText().toString());

                        Log.d("Query",query.toString()+"");

                        query.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if (dataSnapshot.exists()) {
                                    Toast toast = Toast.makeText(view.getContext(), "Ya existe equipo cambie de nombre de equipo", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                                else
                                {
                                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(view.getContext());
                                    dialogo1.setTitle("CONFIRMACIÓN");
                                    dialogo1.setMessage("¿ Confirma crear Equipo ?");
                                    dialogo1.setCancelable(false);
                                    dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogo1, int id) {
                                            //aceptar();

                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            database.getReference("Equipo").child(nombreEquipo.getText().toString()).push(); //Obtiene la referencia de la bd

                                            database.getReference("Equipo").child(nombreEquipo.getText().toString()).setValue(new Equipo(SingletonUser.getInstance().getName(),
                                                    SingletonUser.getInstance().getId(), SingletonUser.getInstance().getEmail(),nombreEquipo.getText().toString(), fechaString));


                                            Log.d("correoIntegrante",correoIntegranteEquipo.size()+"");

                                            for(int i=0; i<correoIntegranteEquipoFinal.size();i++)
                                            {
                                                Log.d("Integrante: ","Correo: "+correoIntegranteEquipoFinal.get(i));
                                                Log.d("Integrante: ","nombre: "+nombreIntegranteEquipoFinal.get(i));
                                                Log.d("Integrante: ","Apellido: "+apellidoIntegranteEquipoFinal.get(i));
                                                String integrante = "Intregrante "+(i+1)+"";
                                                FirebaseDatabase databaseIntegrante = FirebaseDatabase.getInstance();

                                                databaseIntegrante.getReference("Equipo").child(nombreEquipo.getText().toString()).child(integrante).push();

                                                //databaseIntegrante.getReference(nombreEquipo.getText().toString()).child(integrante).push();

                                                databaseIntegrante.getReference("Equipo").child(nombreEquipo.getText().toString()).child(integrante).setValue(new Integrante(correoIntegranteEquipoFinal.get(i),
                                                        nombreIntegranteEquipoFinal.get(i),apellidoIntegranteEquipoFinal.get(i)));
                                            }

                                            Toast toast = Toast.makeText(view.getContext(), "Equipo Creado con éxito", Toast.LENGTH_SHORT);
                                            toast.show();

                                        }
                                    });
                                    dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogo1, int id) {
                                            //cancelar();
                                            Toast toast = Toast.makeText(view.getContext(), "No se ha creado Equipo", Toast.LENGTH_LONG);
                                            toast.show();
                                        }
                                    });
                                    dialogo1.show();

                                }
                            }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    }
                    else
                    {
                        Toast toast = Toast.makeText(view.getContext(), "Por favor ingrese nombre de su equipo", Toast.LENGTH_SHORT);
                        toast.show();
                    }


                }
            }
        });

    }

    public class  Equipo{
        public String nombreAdminEquipo;
        public String idAdminEquipo;
        public String correoAdminEquipo;
        public String nombreEquipo;
        public String fechaActual;

        public Equipo(String nombreAdminEquipo, String idAdminEquipo, String correoAdminEquipo, String nombreEquipo, String fechaActual) {
            this.nombreAdminEquipo = nombreAdminEquipo;
            this.idAdminEquipo = idAdminEquipo;
            this.correoAdminEquipo = correoAdminEquipo;
            this.nombreEquipo = nombreEquipo;
            this.fechaActual = fechaActual;
        }

        public String getNombreAdminEquipo() {
            return nombreAdminEquipo;
        }

        public void setNombreAdminEquipo(String nombreAdminEquipo) {
            this.nombreAdminEquipo = nombreAdminEquipo;
        }

        public String getIdAdminEquipo() {
            return idAdminEquipo;
        }

        public void setIdAdminEquipo(String idAdminEquipo) {
            this.idAdminEquipo = idAdminEquipo;
        }

        public String getCorreoAdminEquipo() {
            return correoAdminEquipo;
        }

        public void setCorreoAdminEquipo(String correoAdminEquipo) {
            this.correoAdminEquipo = correoAdminEquipo;
        }

        public String getNombreEquipo() {
            return nombreEquipo;
        }

        public void setNombreEquipo(String nombreEquipo) {
            this.nombreEquipo = nombreEquipo;
        }

        public String getFechaActual() {
            return fechaActual;
        }

        public void setFechaActual(String fechaActual) {
            this.fechaActual = fechaActual;
        }
    }

    public class Integrante
    {
        public String correIntegrante, nombreIntgrante, apellido;

        public Integrante(String correIntegrante, String nombreIntgrante, String apellido) {
            this.correIntegrante = correIntegrante;
            this.nombreIntgrante = nombreIntgrante;
            this.apellido = apellido;
        }

        public String getCorreIntegrante() {
            return correIntegrante;
        }

        public void setCorreIntegrante(String correIntegrante) {
            this.correIntegrante = correIntegrante;
        }

        public String getNombreIntgrante() {
            return nombreIntgrante;
        }

        public void setNombreIntgrante(String nombreIntgrante) {
            this.nombreIntgrante = nombreIntgrante;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }
    }
}
