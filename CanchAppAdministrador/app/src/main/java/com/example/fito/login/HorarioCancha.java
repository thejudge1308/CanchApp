package com.example.fito.login;

import android.app.AlertDialog;
import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HorarioCancha extends AppCompatActivity
{
    private String nombreCancha;
    private ArrayList<Button> botonesHorarios;
    EditText etPlannedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_cancha);
        botonesHorarios = new ArrayList<Button>();
        nombreCancha = getIntent().getStringExtra("nombreCancha");

        TextView txtNombreCancha = (TextView) findViewById(R.id.txtNombreCancha);
        txtNombreCancha.setText(nombreCancha);
        createDatePicker();
        SimpleDateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
        String fechaString = fecha.format(new Date());
        CrearBotones(fechaString);
    }

    private void createDatePicker(){
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = fecha.format(new Date());
        etPlannedDate = (EditText) findViewById(R.id.etPlannedDate);
        etPlannedDate.setText(fechaString);
        etPlannedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etPlannedDate:
                        showDatePickerDialog();
                        break;
                }
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = day + "/" + (month+1) + "/" + year;
                final String dateConsult=year + "/" + (month+1) + "/" + day;
                etPlannedDate.setText(selectedDate);
                CrearBotones(dateConsult);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void CrearBotones(String date){
        final LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayoutbtns);
        layout.setOrientation(LinearLayout.VERTICAL);  //Can also be done in xml by android:orientation="vertical"
        Response.Listener<String> respuesta = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.v("SALIDA","Cantidad >> ");
                try {
                    JSONObject res = new JSONObject(response);
                    //boolean ok = res.getBoolean("success");
                            //boolean ok = res.getBoolean("success");
                            int cantidad = res.getInt("cantidad");

                    if (cantidad==0){
                                botonesHorarios.clear();
                                layout.removeAllViews();
                                android.support.v7.app.AlertDialog.Builder alerta= new android.support.v7.app.AlertDialog.Builder(HorarioCancha.this);
                                alerta.setMessage( "No hay registro para la fecha seleccionada")
                                        .setNegativeButton("Aceptar",null)
                                        .create().show();
                            }
                            Log.v("SALIDA","Cantidad >> "+cantidad);
                            for (int j = 0;j<cantidad;j++){
                                Log.v("SALIDA",""+res.getString("hora_inicio"+j));
                                Log.v("SALIDA",""+res.getString("hora_termino"+j));
                                Log.v("SALIDA",""+res.getString("estado"+j));
                                LinearLayout row = new LinearLayout(HorarioCancha.super.getApplicationContext());
                                row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                Button btnTag = new Button(HorarioCancha.super.getApplicationContext());
                                btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                btnTag.setText(res.getString("hora_inicio"+j)+"-"+res.getString("hora_termino"+j)+", "+"Estado: "+res.getString("estado"+j));
                                btnTag.setId(j + 1);
                                if(res.getString("estado"+j).equalsIgnoreCase("Disponible"))
                                {
                                    btnTag.setBackgroundColor(Color.parseColor("#5cce2f"));
                                }
                                if(res.getString("estado"+j).equalsIgnoreCase("Reservado"))
                                {
                                    btnTag.setBackgroundColor(Color.parseColor("#e2e216"));
                                }
                                if(res.getString("estado"+j).equalsIgnoreCase("Ocupado"))
                                {
                                    btnTag.setBackgroundColor(Color.parseColor("#ef041b"));
                                }
                                /*Margen para los botones*/
                                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btnTag.getLayoutParams();
                                params.setMargins(5, 20, 10, 0); //left, top, right, bottom
                                row.setLayoutParams(params);
                                botonesHorarios.add(btnTag);
                                row.addView(btnTag);
                                layout.addView(row);
                            }
                } catch (JSONException e) {
                    Log.v("JSon", e.getMessage() + e.toString());
                }
            }
        };
        HorarioCanchaRequest r = new HorarioCanchaRequest( ""+nombreCancha,date, respuesta);
        RequestQueue cola = Volley.newRequestQueue(HorarioCancha.this);
        cola.add(r);
    }

    /*public void ClickVolver()
    {
        TextView txtVolver = (TextView) findViewById(R.id.txtVolver);
        txtVolver.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                HorarioCancha.this.finish();
            }
        });
    }*/
}
