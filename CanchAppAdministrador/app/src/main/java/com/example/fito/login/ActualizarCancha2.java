package com.example.fito.login;

import android.content.DialogInterface;
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

public class ActualizarCancha2 extends AppCompatActivity {

    private String rutAdmin, nombreCancha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_cancha2);

        rutAdmin = getIntent().getStringExtra("rut");
        nombreCancha = getIntent().getStringExtra("cancha");

        ClickVolver();

        ClickBotonActualizar();
    }

    public void ClickBotonActualizar()
    {
        Button btnAgregar = (Button) findViewById(R.id.btnActualizar);

        btnAgregar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ActualizarCancha2.this);
                    dialogo1.setTitle("Advertencia");
                    dialogo1.setMessage("¿ Confirma actualizar datos cancha ?");
                    dialogo1.setCancelable(false);
                    dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {

                            AactualizarCanchas();

                        }
                    });
                    dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            //No hace nada
                        }
                    });
                    dialogo1.show();

            }
        });
    }


    public void AactualizarCanchas()
    {
        //final TextView txtNombreCancha = (TextView) findViewById(R.id.txtNombreCancha);
        final TextView txtDireccionCancha = (TextView) findViewById(R.id.txtDireccion);
        final TextView txtUbicacionCancha = (TextView) findViewById(R.id.txtUbicacion);
        //String nombre = txtNombreCancha.getText().toString();
        String direccion = txtDireccionCancha.getText().toString();
        String ubicacion = txtUbicacionCancha.getText().toString();
        //String estado = "disponible";
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

                        AlertDialog.Builder alerta= new AlertDialog.Builder(ActualizarCancha2.this);
                        alerta.setMessage( "Se actualizó correctamente la cancha")
                                .setPositiveButton("Actualización Exitosa!!!",null)
                                .create().show();

                        Intent intent =new Intent(ActualizarCancha2.this, Principal.class);
                        intent.putExtra("rut",rut);
                        ActualizarCancha2.this.startActivity(intent);

                        ActualizarCancha2.this.finish();
                    }
                    else
                    {
                        AlertDialog.Builder alerta= new AlertDialog.Builder(ActualizarCancha2.this);
                        alerta.setMessage( "Fallo en la actualización")
                                .setNegativeButton("Reintentar",null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    Log.v("JSon", e.getMessage() + e.toString());
                }
            }
        };

        ActualizarCanchaRequest r = new ActualizarCanchaRequest( nombreCancha, rutAdmin, direccion, ubicacion, respuesta);
        RequestQueue cola = Volley.newRequestQueue(ActualizarCancha2.this);
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
                Intent intent =new Intent(ActualizarCancha2.this, ActualizarCancha.class);
                intent.putExtra("rut",rut);
                ActualizarCancha2.this.startActivity(intent);

                ActualizarCancha2.this.finish();
            }
        });
    }

}
