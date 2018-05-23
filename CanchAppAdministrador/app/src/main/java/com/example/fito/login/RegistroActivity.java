package com.example.fito.login;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        final EditText nombre = (EditText)findViewById(R.id.txtNombre);
        final EditText usuario = (EditText)findViewById(R.id.txtUsuario);
        final EditText clave = (EditText)findViewById(R.id.Clave);
        final EditText edad = (EditText)findViewById(R.id.txtEdad);

        final Button btnRegistro = (Button)findViewById(R.id.btnRegistrar);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreR=nombre.getText().toString();
                String usuarioR=usuario.getText().toString();
                String claveR=clave.getText().toString();
                int edadR=Integer.parseInt(edad.getText().toString());

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
                                Intent login =new Intent(RegistroActivity.this, LoginActivity.class);
                                RegistroActivity.this.startActivity(login);
                            }
                            else
                            {
                                AlertDialog.Builder alerta= new AlertDialog.Builder(RegistroActivity.this);
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

                RegistroRequest r = new RegistroRequest(nombreR,usuarioR, claveR, edadR, respuesta);
                RequestQueue cola= Volley.newRequestQueue(RegistroActivity.this);
                cola.add(r);

            }
        });
    }
}
