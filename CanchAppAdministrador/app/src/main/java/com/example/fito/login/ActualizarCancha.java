package com.example.fito.login;

import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.Iterator;

public class ActualizarCancha extends AppCompatActivity {

    private ArrayList<Button> botonesCancha;

    private int id, cantidadCanchas, contador, total;

    private String rutAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_cancha);botonesCancha = new ArrayList<Button>();

        contador = 0;
        id = 0;

        cantidadCanchas = 0;
        total = 0;

        rutAdmin = getIntent().getStringExtra("rut");

        CantidadCanchas();

        //ClickBotonEliminar();

        ClickVolver();
    }


    public void CantidadCanchas()
    {
        Response.Listener<String> respuesta = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("JS", response);
                try {
                    JSONObject res = new JSONObject(response);
                    boolean ok = res.getBoolean("success");

                    Log.v("Json", ok + "");

                    if (ok == true)
                    {
                        //cantidadCanchas++;

                        //cont++;
                        cantidadCanchas = res.getInt("cantidadcancha");
                        Log.d("CantidadCanchas",cantidadCanchas+"");

                        TotalCanchas(cantidadCanchas);



                    }
                    else
                    {

                    }
                } catch (JSONException e) {
                    Log.v("JSon", e.getMessage() + e.toString());
                }
            }
        };

        CantidadCanchaRequest r = new CantidadCanchaRequest( rutAdmin, respuesta);
        RequestQueue cola = Volley.newRequestQueue(ActualizarCancha.this);
        cola.add(r);
    }

    public void TotalCanchas(int CantCancha)
    {
        Log.d("CantCancha",CantCancha+"");
        cantidadCanchas = CantCancha;
        Response.Listener<String> respuesta = new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Log.v("JS", response);
                try {
                    JSONObject res = new JSONObject(response);
                    Iterator<String> temp = res.keys();
                    //int i =0;
                    CrearBotones(cantidadCanchas);

                    clickBotonCancha(cantidadCanchas);

                    //while(temp.hasNext()){
                    for(int i=0; i<cantidadCanchas; i++)
                    {

                        //String val = temp.next();
                        //Object ob = res.get(val);
                        //CapturaCanchas(res.getInt("id"+i));
                        //Log.v("DATA",""+res.getString("nombre"+i));

                        Log.v("I",i+"");


                        botonesCancha.get(i).setText(res.getString("nombre" + i));

                        //i++;

                    }


                    /*boolean ok = res.getBoolean("success");

                    Log.v("JsonTotal", ok + "");

                    if (ok == true)
                    {
                        total = res.getInt("id");

                        Log.d("totalCancha",total+"");

                        CrearBotones(cantidadCanchas);

                        contador = 0;

                        for(int i=0; i <15; i++)
                        {
                            try
                            {
                                Log.v("IDaca",res.getInt("id")+"");
                                CapturaCanchas((res.getInt("id")));
                            }
                            catch (Exception e)
                            {
                                Log.d("Error",e+"");
                            }
                        }

                        clickBotonCancha();


                    }
                    else
                    {*/
                        /*AlertDialog.Builder alerta = new AlertDialog.Builder(Principal.this);
                        alerta.setMessage("Fallo en el Registro")
                                .setNegativeButton("Reintentar", null)
                                .create().show();*/
                    //}
                } catch (JSONException e) {
                    Log.v("JSon", e.getMessage() + e.toString());
                }
            }
        };

        CapturarUltimaCanchaRequest r = new CapturarUltimaCanchaRequest(rutAdmin,respuesta);
        RequestQueue cola = Volley.newRequestQueue(ActualizarCancha.this);
        cola.add(r);

    }

    public void CrearBotones(int cantidad)
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayoutbtns);
        layout.setOrientation(LinearLayout.VERTICAL);  //Can also be done in xml by android:orientation="vertical"

        for (int i = 0; i < cantidad; i++)
        {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            Button btnTag = new Button(this);
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
    }

    public void CapturaCanchas(int id)
    {
        Log.d("Entra", "entra");

        //contador =0;

        //id=1;

        //for(int i=0; i <cantidad; i++)
        // {

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

                            Log.d("contador", contador+"");

                            botonesCancha.get(contador).setText(nombre);

                            contador++;
                        }
                        else
                        {
                            /*AlertDialog.Builder alerta = new AlertDialog.Builder(Principal.this);
                            alerta.setMessage("Fallo")
                                    .setNegativeButton("Reintentar", null)
                                    .create().show();*/
                        }
                    } catch (JSONException e) {
                        Log.v("JSon", e.getMessage() + e.toString());
                    }
                }
            };

            PrincipalRequest r = new PrincipalRequest(id+"", rutAdmin, respuesta);
            RequestQueue cola = Volley.newRequestQueue(ActualizarCancha.this);
            cola.add(r);

            //id++;

            Log.d("id",id+"");
        //}
    }

    public void clickBotonCancha(int cantidad)
    {
        cantidadCanchas = cantidad;
        for (final Button btn:botonesCancha)
        {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {

                    Intent intent =new Intent(ActualizarCancha.this, ActualizarCancha2.class);
                    intent.putExtra("rut",rutAdmin);
                    String nombreCancha = (String) btn.getText();
                    intent.putExtra("cancha",nombreCancha);
                    ActualizarCancha.this.startActivity(intent);

                    ActualizarCancha.this.finish();

                }
            });
        }
    }

    public void ClickVolver()
    {
        final String rut = getIntent().getStringExtra("rut");

        TextView txtVolver = (TextView) findViewById(R.id.txtVolver);
        txtVolver.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent =new Intent(ActualizarCancha.this, Principal.class);
                intent.putExtra("rut",rut);
                ActualizarCancha.this.startActivity(intent);

                ActualizarCancha.this.finish();
            }
        });
    }

    public void Volver()
    {
        final String rut = getIntent().getStringExtra("rut");

        Intent intent =new Intent(ActualizarCancha.this, Principal.class);
        intent.putExtra("rut",rut);
        ActualizarCancha.this.startActivity(intent);


        ActualizarCancha.this.finish();
    }
}
