package com.example.fito.login;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView registro = (TextView)findViewById(R.id.txtRegistrar);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intRegistro = new Intent(LoginActivity.this, RegistroActivity.class);
                LoginActivity.this.startActivity(intRegistro);
                LoginActivity.this.finish();
            }
        });

        final EditText usuario = (EditText)findViewById(R.id.txtUsuario);
        final EditText clave = (EditText)findViewById(R.id.txtContrasena);

        final Button btnIngresa = (Button)findViewById(R.id.btnIngresar);

        btnIngresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usuarioR=usuario.getText().toString();
                String claveR=clave.getText().toString();

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
                                Intent login =new Intent(LoginActivity.this, Principal.class);
                                login.putExtra("rut",usuarioR);
                                LoginActivity.this.startActivity(login);
                            }
                            else
                            {
                                AlertDialog.Builder alerta= new AlertDialog.Builder(LoginActivity.this);
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

                LoginRequest r = new LoginRequest(usuarioR, claveR, respuesta);
                RequestQueue cola= Volley.newRequestQueue(LoginActivity.this);
                cola.add(r);

            }
        });
    }
}

