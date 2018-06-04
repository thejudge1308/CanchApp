package com.example.fito.login;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class EliminarCancha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_cancha);

        ClickBotonEliminar();

        ClickVolver();
    }

    private void ClickBotonEliminar()
    {
        Button btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnEliminar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                EliminarCanchaCanchas();
            }
        });
    }

    private void EliminarCanchaCanchas()
    {
        final TextView txtIdCancha = (TextView) findViewById(R.id.txtIDCancha);
        String id = txtIdCancha.getText().toString();

        final String rut = getIntent().getStringExtra("rut");

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
                        CantidadCanchas();



                    }
                    else
                    {
                        AlertDialog.Builder alerta= new AlertDialog.Builder(EliminarCancha.this);
                        alerta.setMessage( "Fallo en el Registro")
                                .setNegativeButton("Reintentar",null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    Log.v("JSon", e.getMessage() + e.toString());
                }
            }
        };

        EliminarCanchaRequest r = new EliminarCanchaRequest( id, respuesta);
        RequestQueue cola = Volley.newRequestQueue(EliminarCancha.this);
        cola.add(r);
    }

    public void CantidadCanchas()
    {
        final String rutAdmin = getIntent().getStringExtra("rut");

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
                        int cantidadCanchas = res.getInt("cantidadcancha");
                        Log.d("CantidadCanchas",cantidadCanchas+"");

                        DismiuirCantidadCanchas(cantidadCanchas-1);

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
        RequestQueue cola = Volley.newRequestQueue(EliminarCancha.this);
        cola.add(r);
    }

    public void DismiuirCantidadCanchas(int cantidadCanchas)
    {
        final String rutAdmin = getIntent().getStringExtra("rut");

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
                        AlertDialog.Builder alerta= new AlertDialog.Builder(EliminarCancha.this);
                        alerta.setMessage( "Se eliminió correctamente la cancha")
                                .setPositiveButton("Borrado Exitoso!!!",null)
                                .create().show();

                        Volver();

                    }
                    else
                    {

                    }
                } catch (JSONException e) {
                    Log.v("JSon", e.getMessage() + e.toString());
                }
            }
        };

        AumentarCantidadCanchasRequest r = new AumentarCantidadCanchasRequest(cantidadCanchas+"", rutAdmin, respuesta);
        RequestQueue cola = Volley.newRequestQueue(EliminarCancha.this);
        cola.add(r);
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
                Intent intent =new Intent(EliminarCancha.this, Principal.class);
                intent.putExtra("rut",rut);
                EliminarCancha.this.startActivity(intent);


                EliminarCancha.this.finish();
            }
        });
    }

    public void Volver()
    {
        final String rut = getIntent().getStringExtra("rut");

        Intent intent =new Intent(EliminarCancha.this, Principal.class);
        intent.putExtra("rut",rut);
        EliminarCancha.this.startActivity(intent);


        EliminarCancha.this.finish();
    }
}
