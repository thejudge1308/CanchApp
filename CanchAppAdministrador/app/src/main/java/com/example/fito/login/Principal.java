package com.example.fito.login;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Principal extends AppCompatActivity {

    private ArrayList<Button> botonesCancha;
    private int id, cantidadCanchas;
    private String rutAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        rutAdmin = getIntent().getStringExtra("rut");
        id=1;
        cantidadCanchas = 2;

        Log.d("Rut",rutAdmin);

        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = fecha.format(new Date());

        TextView fechaActual = (TextView) findViewById(R.id.txt1);

        fechaActual.setText(fechaString);

        botonesCancha = new ArrayList<Button>();

        /*for(int i=1;i<8;i++)
        {
            String n = i+"";
            String b = "btn"+n;
            botonesCancha.add((Button)findViewById(R.id.b));
        }*/
        //botonesCancha.add((Button) findViewById(R.id.btn1));
        //botonesCancha.add((Button) findViewById(R.id.btn2));


        LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayoutbtns);
        layout.setOrientation(LinearLayout.VERTICAL);  //Can also be done in xml by android:orientation="vertical"

        for (int i = 0; i < cantidadCanchas; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            //for (int j = 0; j < 4; j++ )
            //{
                Button btnTag = new Button(this);
                btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                //btnTag.setText("Button " + (j + 1 + (i * 4)));
                //btnTag.setId(j + 1 + (i * 4));
                btnTag.setText("Button " + (i + 1));
                btnTag.setId(i + 1);
                btnTag.setBackgroundColor(Color.LTGRAY);
                botonesCancha.add(btnTag);
                row.addView(btnTag);
            //}

            layout.addView(row);
        }

        CapturaCanchas();

        clickBtoBotonCancha();

        /*final Button btnIngresa = (Button) findViewById(R.id.btn3);

        btnIngresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Entra", "entra");

                id=1;

                for(int i=0; i <2; i++)
                {
                    final int j=i;

                    Response.Listener<String> respuesta = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.v("JS", response);
                            try {
                                JSONObject res = new JSONObject(response);
                                boolean ok = res.getBoolean("success");

                                Log.v("Json", ok + "");

                                if (ok == true) {
                                    //Intent adminPrin = new Intent(Principal.this, LoginActivity.class);
                                    //Principal.this.startActivity(adminPrin);
                                    String nombre = res.getString("nombre");
                                    Log.d("Nombre", nombre);
                                    botonesCancha.get(j).setText(nombre);
                                } else {
                                    AlertDialog.Builder alerta = new AlertDialog.Builder(Principal.this);
                                    alerta.setMessage("Fallo en el Registro")
                                            .setNegativeButton("Reintentar", null)
                                            .create().show();
                                }
                            } catch (JSONException e) {
                                Log.v("JSon", e.getMessage() + e.toString());
                            }
                        }
                    };

                    Log.d("Respuesta", respuesta + "");

                    PrincipalRequest r = new PrincipalRequest(id+"", rutAdmin, respuesta);
                    RequestQueue cola = Volley.newRequestQueue(Principal.this);
                    cola.add(r);

                    id++;
                }
            }
        });*/

    }

    public void CapturaCanchas()
    {
        Log.d("Entra", "entra");

        id=1;

        for(int i=0; i <cantidadCanchas; i++)
        {
            final int j=i;

            Response.Listener<String> respuesta = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.v("JS", response);
                    try {
                        JSONObject res = new JSONObject(response);
                        boolean ok = res.getBoolean("success");

                        Log.v("Json", ok + "");

                        if (ok == true) {
                            //Intent adminPrin = new Intent(Principal.this, LoginActivity.class);
                            //Principal.this.startActivity(adminPrin);
                            String nombre = res.getString("nombre");
                            Log.d("Nombre", nombre);
                            botonesCancha.get(j).setText(nombre);
                        }
                        else
                        {
                            /*AlertDialog.Builder alerta = new AlertDialog.Builder(Principal.this);
                            alerta.setMessage("Fallo en el Registro")
                                    .setNegativeButton("Reintentar", null)
                                    .create().show();*/
                            cantidadCanchas=0;
                        }
                    } catch (JSONException e) {
                        Log.v("JSon", e.getMessage() + e.toString());
                    }
                }
            };

            PrincipalRequest r = new PrincipalRequest(id+"", rutAdmin, respuesta);
            RequestQueue cola = Volley.newRequestQueue(Principal.this);
            cola.add(r);

            id++;

            Log.d("id",id+"");
        }

    }

    public void clickBtoBotonCancha()
    {
        for (final Button btn:botonesCancha)
        {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    //btn.setVisibility(View.INVISIBLE);
                    Intent intent =new Intent(Principal.this, HorarioCancha.class);
                    intent.putExtra("nombreCancha",btn.getText());
                    Principal.this.startActivity(intent);
                }
            });
        }
    }
}