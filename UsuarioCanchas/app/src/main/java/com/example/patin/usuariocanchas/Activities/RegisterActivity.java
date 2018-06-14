package com.example.patin.usuariocanchas.Activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Request.RegisterRequest;
import com.example.patin.usuariocanchas.Segurity.Validation;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText emailEditText;
    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText nicknameEditText;
    private EditText dateEditText;
    private EditText pass1EditText;
    private EditText pass2EditText;
    private Button registerButton;
    private DatePickerDialog datePickerDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.emailEditText = (EditText)findViewById(R.id.email_edittext_registeractivity);
        nameEditText = (EditText)findViewById(R.id.name_edittext_registeractivity);
        surnameEditText = (EditText)findViewById(R.id.surname_edittext_registeractivity);
        nicknameEditText = (EditText)findViewById(R.id.nickname_edittext_registeractivity);
        dateEditText= (EditText)findViewById(R.id.date_edittext_registeractivity);
        pass1EditText = (EditText)findViewById(R.id.pass1_edittext_registeractivity);
        pass2EditText = (EditText)findViewById(R.id.pass2_edittext_registeractivity);
        registerButton = (Button) findViewById(R.id.register_button_registeractivity);
        this.dateEditText.setFocusable(false);

        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, RegisterActivity.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        this.dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RegisterActivity.this.gotoRegister()){
                    String emailR = emailEditText.getText().toString().trim();
                    String nameR =  nameEditText.getText().toString().trim();
                    String surnameR = surnameEditText.getText().toString().trim();
                    String nicknameR = nicknameEditText.getText().toString().trim();
                    String passR = pass1EditText.getText().toString().trim();

                    Response.Listener<String> respuesta = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject res = new JSONObject(response);
                                String mensaje = res.getString("mensaje");
                                if(mensaje.equals("true")){
                                    Toast.makeText(RegisterActivity.this, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegisterActivity.this);
                                    // set title
                                    alertDialogBuilder.setTitle("Fallo en el registro");

                                    // set dialog message
                                    alertDialogBuilder
                                            .setMessage("Click continuar, para salir")
                                            .setCancelable(false)
                                            .setPositiveButton("continuar",new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog,int id) {
                                                    // if this button is clicked, close
                                                    // current activity
                                                    RegisterActivity.this.finish();
                                                }
                                            })
                                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog,int id) {
                                                    // if this button is clicked, just close
                                                    // the dialog box and do nothing
                                                    dialog.cancel();
                                                }
                                            });
                                    // create alert dialog
                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    // show it
                                    alertDialog.show();
                                }
                                Log.v("Json",mensaje );


                            } catch (JSONException e) {
                                Log.v("Json",e +"");

                            }

                        }
                    };

                    RegisterRequest r = new RegisterRequest(emailR,nicknameR,nameR,surnameR,passR,new Date(),respuesta);
                    RequestQueue cola = Volley.newRequestQueue(RegisterActivity.this);
                    cola.add(r);
                }
            }
        });




    }

    public boolean gotoRegister(){
        boolean flag = true;
        if(!Validation.isCorrectEmail(this.emailEditText.getText().toString())){
            Toast.makeText(this, "Correo invalido", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(this.nicknameEditText.getText().toString().equals("")){
            Toast.makeText(this, "Debe ingresar un nickname", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(this.dateEditText.getText().toString().equals("")){
            Toast.makeText(this, "Debe ingresar una fecha valida", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(this.pass1EditText.getText().toString().equals("") || this.pass2EditText.getText().toString().equals("")){
            Toast.makeText(this, "Debe ingresar una contraseña", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!this.pass1EditText.getText().toString().equals(this.pass2EditText.getText().toString())){
            Toast.makeText(this, "Ambas contraseñas deben ser identicas", Toast.LENGTH_SHORT).show();
            return false;
        }

        return flag;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.dateEditText.setText(dayOfMonth + " / " + (month + 1) + " / "
                + year);
    }
}
