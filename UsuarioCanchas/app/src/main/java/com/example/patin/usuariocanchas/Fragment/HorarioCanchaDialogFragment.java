package com.example.patin.usuariocanchas.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Activities.HorarioCanchaActivity;
import com.example.patin.usuariocanchas.Activities.HorarioYReserva;
import com.example.patin.usuariocanchas.Model.Cancha;
import com.example.patin.usuariocanchas.Model.DatePickerFragment;
import com.example.patin.usuariocanchas.Model.HorarioCancha;
import com.example.patin.usuariocanchas.Model.Reserva;
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
import java.util.HashMap;
import java.util.Map;


public class HorarioCanchaDialogFragment extends DialogFragment {
    String nombreCancha;
    Button[] dynamicButtons;
    ArrayList<HorarioCancha> horariosCancha=new ArrayList<HorarioCancha>();
    ArrayList<Reserva> reservas=new ArrayList<Reserva>();
    LinearLayout layout,contenedorBoton;
    private DatabaseReference mDatabase;
    EditText etPlannedDate;
    View vista;
    String fechaActual;
    int idBotonPresionado;
    Cancha cancha;
    Long valorCancha;
    Long idAdmin;

    public HorarioCanchaDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombreCancha=getArguments().getString("btn");
            idAdmin=(long) getArguments().getLong("idAdmin");
        }
        Log.v("nombre", nombreCancha);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_horarios_cancha, container, false);
        TextView e= vista.findViewById(R.id.textView15);
        e.setText("Horario"+" "+ nombreCancha);
        etPlannedDate = (EditText) vista.findViewById(R.id.etPlannedDatee);
        //Aquí colocas tu objeto tipo Date
        Date myDate = new Date();
        //Aquí obtienes el formato que deseas
        fechaActual=(new SimpleDateFormat("dd/MM/yyyy").format(myDate));
        Log.v("datee",fechaActual);
        etPlannedDate.setText(fechaActual);
        etPlannedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("entre","entre ctmmmmmmmmmmmmmmmmmmm");
                showDatePickerDialog(nombreCancha);
            }
        });
        botonesDinamicos(nombreCancha);
        return vista;
    }


    private void showDatePickerDialog(final String nombreCancha) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                String sDay="";
                String sMonth="";
                if(day<10){sDay="0"+day;}
                else{sDay=Integer.toString(day);}
                if(month<10){sMonth="0"+(month+1);}
                else{sMonth=Integer.toString(month+1);}
                final String selectedDate = sDay + "/" + sMonth + "/" + year;
                vista.clearAnimation();
                etPlannedDate.setText(selectedDate);
                botonesDinamicos(nombreCancha);
            }
        });
        newFragment.show(getActivity().getFragmentManager(), "datePicker");
    }
    public void botonesDinamicos(final String nombreCancha){
        /**
         * crea botones dinamicamente
         */
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference horarioCanchaDatebase=database.getReference("HorarioCancha");
        //cancha1 es el id del boton debe ser pasado en algun lugar
        horarioCanchaDatebase.child(nombreCancha).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                horariosCancha.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    HorarioCancha horarioCancha=snapshot.getValue(HorarioCancha.class);
                    horariosCancha.add(horarioCancha);
                }
                layout = vista.findViewById(R.id.layout);
                int iNumberOfButtons = horariosCancha.size();
                dynamicButtons = new Button[iNumberOfButtons];
                LinearLayout.LayoutParams paramsButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout.removeAllViews();
                for (int i = 0; i < iNumberOfButtons; i++) {
                    LinearLayout row = new LinearLayout(vista.getContext());
                    row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    dynamicButtons[i] = new Button(vista.getContext());
                    dynamicButtons[i].setText(horariosCancha.get(i).getHora_inicio()+" - "+horariosCancha.get(i).getHora_termino());
                    dynamicButtons[i].setId(i);
                    dynamicButtons[i].setTextSize(15.0f);
                    dynamicButtons[i].setText(dynamicButtons[i].getText()+" - "+"Disponible");
                    dynamicButtons[i].setBackgroundColor(0xff99cc00);
                    dynamicButtons[i].setWidth(600);
                    dynamicButtons[i].setLayoutParams(paramsButton);

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) dynamicButtons[i].getLayoutParams();
                    params.setMargins(5, 20, 10, 0); //left, top, right, bottom
                    row.setLayoutParams(params);

                    row.addView(dynamicButtons[i]);

                    layout.addView(row); // dynamicButtonsLinearLayout is the container of the buttons

                    dynamicButtons[i].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            idBotonPresionado=((Button) view).getId();
                            String hora=horariosCancha.get(idBotonPresionado).getHora_inicio()+" - "+horariosCancha.get(idBotonPresionado).getHora_termino();
                            /*ReservaCanchaFragment reservaCanchaFragment=new ReservaCanchaFragment();
                            Bundle bundle=new Bundle();
                            bundle.putString("nombreCancha",nombreCancha);
                            reservaCanchaFragment.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.contenedorFragment,reservaCanchaFragment).commit();
                            //Toast.makeText(getContext(),"presiona"+idBoton,Toast.LENGTH_LONG).show();*/
                            AlertDialog.Builder builder = new AlertDialog.Builder(vista.getContext());
                            builder.setTitle("Reserva de Horas");
                            builder.setMessage("¿Realmente quiere reservar cancha "+"'"+ nombreCancha +"'"+"?\n\n"+"Fecha: "+ etPlannedDate.getText().toString()+" - "+hora+
                            "\n\n Precio Cancha: "+valorCancha);



                            //builder.setIcon(R.drawable.ic_launcher);
                            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    DatabaseReference reservaCancha=database.getReference(FireBaseReferences.RESERVA_REFERENCE).child(SingletonUser.getInstance().getId()).child(nombreCancha);
                                    String fechaEvento=etPlannedDate.getText().toString();
                                    String fechaReserva=fechaActual;
                                    String estado="Reservado";
                                    String horaInicio=horariosCancha.get(idBotonPresionado).getHora_inicio();
                                    String horaTermino=horariosCancha.get(idBotonPresionado).getHora_termino();

                                    if(Date.parse(fechaReserva)>Date.parse(fechaEvento)){
                                        Toast.makeText(vista.getContext(),"Fecha de evento debe se mayor a la fecha actual",Toast.LENGTH_LONG).show();
                                    }else{
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
                                        Toast.makeText(vista.getContext(),"Reservado con exito",Toast.LENGTH_LONG).show();

                                        /*CreaEventoFragment reservaCanchaFragment=new CreaEventoFragment();
                                        Bundle bundle=new Bundle();
                                        bundle.putString("nombreCancha",nombreCancha);
                                        reservaCanchaFragment.setArguments(bundle);
                                        getFragmentManager().beginTransaction().replace(R.id.frangment_content,reservaCanchaFragment).commit();*/
                                    }
                                    dialog.dismiss();
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }
                    });
                }
                DatabaseReference reservaCancha=database.getReference(FireBaseReferences.RESERVA_REFERENCE).child(SingletonUser.getInstance().getId()).child(nombreCancha);
                //Query query=reservaDB.orderByChild("fecha").equalTo(etPlannedDate.getText().toString());
                Query query=reservaCancha.orderByChild(FireBaseReferences.RESERVA_FECHAEVENTO_REFERENCE).equalTo(etPlannedDate.getText().toString());
                Log.v("query",query.getRef().getKey());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reservas.clear();
                        int i=0;
                        for (DataSnapshot snapshot:dataSnapshot.getChildren())
                        {
                            i++;
                            Reserva reserva=snapshot.getValue(Reserva.class);
                            reservas.add(reserva);
                        }
                        Log.v("ggg", String.valueOf(i));
                        comparaHorario(dynamicButtons);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ERROR", databaseError.getMessage());
            }
        });
        DatabaseReference db=database.getReference(FireBaseReferences.CANCHA_REFERENCE).orderByChild("nombre").equalTo(nombreCancha).getRef();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    cancha=snapshot.getValue(Cancha.class);
                    if (cancha.getNombre().equalsIgnoreCase(nombreCancha))
                    {
                        Log.v("Funciona",snapshot.getValue().toString());
                        cancha=snapshot.getValue(Cancha.class);
                        TextView precio=vista.findViewById(R.id.textView_precio);
                        precio.setText(String.valueOf(cancha.getValor()));
                        valorCancha=cancha.getValor();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void comparaHorario(Button[] botones){
        Log.v("lll", String.valueOf(this.horariosCancha.size()));
        Log.v("jjj", String.valueOf(this.reservas.size()));
        for (int i=0;i<this.horariosCancha.size();i++){
            for (int j=0; j<this.reservas.size();j++)
            {
                if(horariosCancha.get(i).getHora_inicio().equalsIgnoreCase(reservas.get(j).getHora_inicio())){
                    if(reservas.get(j).getEstado().equalsIgnoreCase("Reservado")){
                        botones[i].setText(this.horariosCancha.get(i).getHora_inicio()+" - "+this.horariosCancha.get(i).getHora_termino()+" - "+reservas.get(j).getEstado());
                        botones[i].setBackgroundColor(Color.rgb(255, 255, 128));
                        botones[i].setClickable(false);
                    }
                    if(reservas.get(j).getEstado().equalsIgnoreCase("Ocupado")){
                        botones[i].setText(this.horariosCancha.get(i).getHora_inicio()+" - "+this.horariosCancha.get(i).getHora_termino()+" - "+reservas.get(j).getEstado());
                        dynamicButtons[i].setBackgroundColor(Color.rgb(255, 128, 128));
                        botones[i].setClickable(false);
                    }
                }
            }
        }

    }

    public interface OnFragmentInteractionListener {
    }
}
