package com.example.patin.usuariocanchas.Activities;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Model.User;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Segurity.Validation;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    FirebaseAuth.AuthStateListener fireAuthStateListener;

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

        fireAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //Compruerba el inicio de sesion
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    Log.i("fire","Sesion iniciada");
                }else{
                    Log.i("fire","no iniciada");
                }
            }
        };

        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RegisterActivity.this.gotoRegister()){
                    final String emailR = emailEditText.getText().toString().trim();
                    final String nameR =  nameEditText.getText().toString().trim();
                    final String surnameR = surnameEditText.getText().toString().trim();
                    final String nicknameR = nicknameEditText.getText().toString().trim();
                    final String passR = pass1EditText.getText().toString().trim();
                    final String birthDateR= dateEditText.getText().toString().trim();

                    //Guardar en el Auth de firebase
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailR,passR).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful()){
                                 Toast.makeText(RegisterActivity.this, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show();
                                 //Guardar datos en la BD de firebase
                                 FirebaseDatabase database = FirebaseDatabase.getInstance();
                                 ///database.setPersistenceEnabled(true);
                                 DatabaseReference userReference = database.getReference(FireBaseReferences.USER_REFERENCE); //Obtiene la referencia de la bd
                                 User newUser = new User(emailR,"uwu",nameR,surnameR,nicknameR,birthDateR);

                                 //codigo necesario para almacenar el id dentro del objeto
                                 DatabaseReference newUserReference = userReference.push();
                                 newUserReference.setValue(newUser);
                                 String keyUser = newUserReference.getKey();
                                 Log.v("keyy",keyUser);
                                 Map<String, Object> userUpdates = new HashMap<>();
                                 userUpdates.put(keyUser+"/id", keyUser);
                                 userReference.updateChildren(userUpdates);
                                 //end
                                 finish();
                             }else{
                                 Toast.makeText(RegisterActivity.this, "No se ha podido realizar la acción", Toast.LENGTH_SHORT).show();
                             }
                        }
                    });

                }
            }
        });
    }

    /*
     * @return True if values are correct
     */
    public boolean gotoRegister(){
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

        if(this.pass1EditText.getText().toString().length()<6 || this.pass2EditText.getText().toString().length()<6){
            Toast.makeText(this, "La contraseña debe tener minimo 6 caracteres.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.dateEditText.setText(dayOfMonth + " / " + (month + 1) + " / "
                + year);
    }
}
