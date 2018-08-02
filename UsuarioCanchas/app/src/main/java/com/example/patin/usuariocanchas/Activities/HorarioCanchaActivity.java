package com.example.patin.usuariocanchas.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.patin.usuariocanchas.Model.DatePickerFragment;
import com.example.patin.usuariocanchas.Model.HorarioCancha;
import com.example.patin.usuariocanchas.Model.Reserva;
import com.example.patin.usuariocanchas.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HorarioCanchaActivity extends AppCompatActivity {
    Button[] dynamicButtons;
    ArrayList<HorarioCancha> horariosCancha=new ArrayList<HorarioCancha>();
    ArrayList<Reserva> reservas=new ArrayList<Reserva>();
    LinearLayout layout,contenedorBoton;
    private DatabaseReference mDatabase;
    EditText etPlannedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_cancha);
        final String nombreCancha = getIntent().getExtras().getString("boton");
        Log.v("nombreCancha", nombreCancha);

        etPlannedDate = (EditText) findViewById(R.id.etPlannedDate);
        //Aquí colocas tu objeto tipo Date
        Date myDate = new Date();

        //Aquí obtienes el formato que deseas
        String date=(new SimpleDateFormat("dd/MM/yyyy").format(myDate));
        Log.v("datee",date);
        etPlannedDate.setText(date);

        //Date currentTime = Calendar.getInstance().getTime();


    //etPlannedDate.setText(currentTime.toString());

        etPlannedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etPlannedDate:
                        Log.v("entre","entre ctmmmmmmmmmmmmmmmmmmm");
                        showDatePickerDialog(nombreCancha);
                        break;
                }
            }
        });

        /**
         * aca inserto un valor en la tabla horarioCancha con un id cancha1 y los valores que asigne al objeto horarioCancha
         */
        /*final FirebaseDatabase database=FirebaseDatabase.getInstance();
        HorarioCancha horarioCancha=new HorarioCancha("10:00","11:00");
        DatabaseReference horarioCanchaDatebase=database.getReference("HorarioCancha");
        //horarioCanchaDatebase.child("cancha1").push().setValue(horarioCancha);*/


        botonesDinamicos(nombreCancha);


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
                    }
                    if(reservas.get(j).getEstado().equalsIgnoreCase("Ocupado")){
                        botones[i].setText(this.horariosCancha.get(i).getHora_inicio()+" - "+this.horariosCancha.get(i).getHora_termino()+" - "+reservas.get(j).getEstado());
                        dynamicButtons[i].setBackgroundColor(Color.rgb(255, 128, 128));
                    }
                }
            }
        }

    }

    /**
     * En el código anterior estoy usando getActivity() porque actualmente estoy en un Fragment.

     Si el código lo vas a añadir a un Activity, entonces puedes usar directamente getSupportFragmentManager().

     Ahora de seguro que te aparece resaltado en rojo DatePickerFragment.

     Eso es porque aun nos falta definir esa clase:
     */
    private void showDatePickerDialog(final String nombreCancha) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = day + "/" + (month+1) + "/" + year;
                etPlannedDate.setText(selectedDate);
                botonesDinamicos(nombreCancha);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");
        //this.getSupportFragmentManager();
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
                layout = findViewById(R.id.layout);
                int iNumberOfButtons = horariosCancha.size();
                dynamicButtons = new Button[iNumberOfButtons];
                LinearLayout.LayoutParams paramsButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER_HORIZONTAL);
                layout.removeAllViews();
                for (int i = 0; i < iNumberOfButtons; i++) {
                    dynamicButtons[i] = new Button(HorarioCanchaActivity.this);
                    dynamicButtons[i].setText(horariosCancha.get(i).getHora_inicio()+" - "+horariosCancha.get(i).getHora_termino());
                    dynamicButtons[i].setId(i);
                    dynamicButtons[i].setTextSize(15.0f);
                    dynamicButtons[i].setText(dynamicButtons[i].getText()+" - "+"Disponible");
                    dynamicButtons[i].setBackgroundColor(0xff99cc00);
                    dynamicButtons[i].setLayoutParams(paramsButton);
                    layout.addView(dynamicButtons[i]); // dynamicButtonsLinearLayout is the container of the buttons
                    dynamicButtons[i].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Intent i = new Intent(HorarioCanchaActivity.this,HorarioYReserva.class);
                            HorarioCanchaActivity.this.startActivity(i);

                        }
                    });
                }
                DatabaseReference reservaDB= database.getReference("Reserva");
               //Query query=reservaDB.orderByChild("fecha").equalTo(etPlannedDate.getText().toString());
                Query query=reservaDB.child(nombreCancha).orderByChild("fecha").equalTo(etPlannedDate.getText().toString());
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
    }
}
