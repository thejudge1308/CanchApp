package com.example.patin.usuariocanchas.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.patin.usuariocanchas.Model.User;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Request.LoginRequest;
import com.example.patin.usuariocanchas.Values.SingletonUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private TextView registerTextView;
    private Button loginButton;
    private EditText userEditText;
    private EditText passEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //this.userEditText = findViewById
        this.userEditText = findViewById(R.id.email_edittext_loginactivity);
        this.passEditText = findViewById(R.id.pass_edittext_loginactivity);

        this.registerTextView = findViewById(R.id.register_textview_loginactivity);
        this.registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(register);
            }
        });

        this.loginButton = (Button)findViewById(R.id.button_login);
        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(theFieldsAreValids()){
                    isValidTheUser();
                }
            }
        });

    }

    /**
     *
     * @return true if the fields are not void
     */
    public boolean theFieldsAreValids(){
        boolean flag=true;
        if(this.userEditText.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this, "El campo correo no debe estar vacio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(this.passEditText.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this, "El campo contraseña no debe estar vacio", Toast.LENGTH_SHORT).show();
            return false;
        }
        return flag;
    }

    public void isValidTheUser(){
        String userR = this.userEditText.getText().toString();
        String passR= this.passEditText.getText().toString();

        Response.Listener<String> respuesta = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject res = new JSONObject(response);
                    String mensaje = res.getString("mensaje");
                    JSONArray array = res.getJSONArray("datos");
                    if(mensaje.equals("true")){
                        //JSONObject userJson = res.getJSONObject("datos");//email,nickname,nombre,apellido,puntuacion

                        //User currentUser = SingletonUser.createUser(array.getJSONObject(0).getString("email"),array.getJSONObject(0).getString("nombre"),array.getJSONObject(0).getString("apellido"),array.getJSONObject(0).getString("nickname"),array.getJSONObject(0).getDouble("puntuacion"));
                        //Intent main = new Intent(LoginActivity.this,HomeActivity.class);
                        //LoginActivity.this.startActivity(main);
                        //Toast.makeText(LoginActivity.this, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show();
                        //finish();
                    }else{
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                        alertDialogBuilder.setTitle("Problemas al iniciar sesión");
                        alertDialogBuilder
                                .setMessage("Click continuar, para salir")
                                .setCancelable(false)
                                .setPositiveButton("continuar",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // if this button is clicked, close
                                        // current activity
                                        //LoginActivity.this.finish();
                                        dialog.cancel();
                                    }
                                })
                                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // if this button is clicked, just close
                                        // the dialog box and do nothing
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                    Log.v("Json",mensaje );


                } catch (JSONException e) {
                    Log.v("Json",e +"");
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                    alertDialogBuilder.setTitle("No se ha podido iniciar sesión, datos incorrectos.");
                    alertDialogBuilder
                            .setMessage("Click continuar, para salir")
                            .setCancelable(false)
                            .setPositiveButton("continuar",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }

            }
        };

        LoginRequest r = new LoginRequest(userR,passR,respuesta);
        RequestQueue cola = Volley.newRequestQueue(LoginActivity.this);
        cola.add(r);

    }


}
