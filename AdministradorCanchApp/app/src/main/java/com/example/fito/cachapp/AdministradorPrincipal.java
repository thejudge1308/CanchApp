package com.example.fito.cachapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdministradorPrincipal extends AppCompatActivity
{

    private ArrayList<Button> botonesCancha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminstrador_principal);

        String rutAdmin="1";

        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = fecha.format(new Date());

        TextView fechaActual = (TextView)findViewById(R.id.txt1);

        fechaActual.setText(fechaString);

        botonesCancha = new ArrayList<Button>();

        /*for(int i=1;i<8;i++)
        {
            String n = i+"";
            String b = "btn"+n;
            botonesCancha.add((Button)findViewById(R.id.btn1));
        }*/
        botonesCancha.add((Button)findViewById(R.id.btn1));
        botonesCancha.add((Button)findViewById(R.id.btn2));

        //CapturaCanchas(rutAdmin);

        final Button btnIngresa = (Button)findViewById(R.id.btn7);

        btnIngresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Entra", "entra");

                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("JS",response);
                        try {
                            JSONObject res = new JSONObject(response);
                            boolean ok = res.getBoolean("success");

                            Log.v("Json",ok+"");

                            if(ok == true)
                            {
                                Intent adminPrin =new Intent(AdministradorPrincipal.this, HorarioCancha.class);
                                AdministradorPrincipal.this.startActivity(adminPrin);
                            }
                            else
                            {
                                AlertDialog.Builder alerta= new AlertDialog.Builder(AdministradorPrincipal.this);
                                alerta.setMessage( "Fallo en el Registro")
                                        .setNegativeButton("Reintentar",null)
                                        .create().show();
                            }
                        }
                        catch (JSONException e)
                        {
                            Log.v("JSon", e.getMessage()+e.toString());
                        }
                    }
                };

                Log.d("Respuesta",respuesta+"");

                AdministradorRequest r = new AdministradorRequest("1", respuesta);
                RequestQueue cola = Volley.newRequestQueue(AdministradorPrincipal.this);
                cola.add(r);
            }
        });

    }

    public void CapturaCanchas(String rutAdm)
    {
        final String rut = rutAdm;

        Log.d("rut",rut);
        Response.Listener<String> respuesta = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("Entra","entra");
                Log.d("JS",response);
                try {

                    String nombre = "";
                    String direccion = "";
                    String estado = "";

                    int cont = 0;

                    //JSONObject jsonResponse = new JSONObject(response);
                    JSONObject res = new JSONObject(response);
                    boolean ok = res.getBoolean("success");

                    Log.d("Json",ok+"");

                    if(ok == true)
                    {


                        //botonesCancha.get(cont).setText(nombre);

                        //cont++;

                        //Intent login =new Intent(LoginActivity.this, OrdenNivel1.class);
                        //LoginActivity.this.startActivity(login);
                    }
                    else
                    {
                        AlertDialog.Builder alerta= new AlertDialog.Builder(AdministradorPrincipal.this);
                        alerta.setMessage( "Fallo en el Registro")
                                .setNegativeButton("Reintentar",null)
                                .create().show();
                    }
                }
                catch (JSONException e)
                {
                    Log.v("JSon", e.getMessage()+e.toString());
                }
            }
        };

        AdministradorRequest r = new AdministradorRequest(rut, respuesta);
        RequestQueue cola= Volley.newRequestQueue(AdministradorPrincipal.this);
        cola.add(r);
    }

}
