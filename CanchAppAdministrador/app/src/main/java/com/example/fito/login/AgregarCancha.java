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

public class AgregarCancha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cancha);

        ClickVolver();

        ClickBotonAgregar();


    }



    public void ClickBotonAgregar()
    {
        Button btnAgregar = (Button) findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //CantidadCanchas();
                AgregarCanchas();
            }
        });
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

                        AumentarCantidadCanchas(cantidadCanchas+1);

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
        RequestQueue cola = Volley.newRequestQueue(AgregarCancha.this);
        cola.add(r);
    }

    public void AgregarCanchas()
    {
        final TextView txtNombreCancha = (TextView) findViewById(R.id.txtNombreCancha);
        final TextView txtDireccionCancha = (TextView) findViewById(R.id.txtDireccion);
        final TextView txtUbicacionCancha = (TextView) findViewById(R.id.txtUbicacion);
        String nombre = txtNombreCancha.getText().toString();
        String direccion = txtDireccionCancha.getText().toString();
        String ubicacion = txtUbicacionCancha.getText().toString();
        String estado = "disponible";
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

                        AlertDialog.Builder alerta= new AlertDialog.Builder(AgregarCancha.this);
                        alerta.setMessage( "Se agregó correctamente la cancha")
                                .setPositiveButton("Registro Exitoso!!!",null)
                                .create().show();

                        Intent intent =new Intent(AgregarCancha.this, Principal.class);
                        intent.putExtra("rut",rut);
                        AgregarCancha.this.startActivity(intent);

                        AgregarCancha.this.finish();
                    }
                    else
                    {
                        AlertDialog.Builder alerta= new AlertDialog.Builder(AgregarCancha.this);
                        alerta.setMessage( "Fallo en el Registro")
                                .setNegativeButton("Reintentar",null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    Log.v("JSon", e.getMessage() + e.toString());
                }
            }
        };

        AgregarCanchaRequest r = new AgregarCanchaRequest( nombre, direccion, ubicacion, estado,rut, respuesta);
        RequestQueue cola = Volley.newRequestQueue(AgregarCancha.this);
        cola.add(r);
    }



    public void AumentarCantidadCanchas(int cantidadCanchas)
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
        RequestQueue cola = Volley.newRequestQueue(AgregarCancha.this);
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
                Intent intent =new Intent(AgregarCancha.this, Principal.class);
                intent.putExtra("rut",rut);
                AgregarCancha.this.startActivity(intent);


                AgregarCancha.this.finish();
            }
        });
    }

    public void Volver()
    {
        final String rut = getIntent().getStringExtra("rut");

        Intent intent =new Intent(AgregarCancha.this, Principal.class);
        intent.putExtra("rut",rut);
        AgregarCancha.this.startActivity(intent);


        AgregarCancha.this.finish();
    }
}
