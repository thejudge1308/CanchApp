package com.example.patin.usuariocanchas.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.example.patin.usuariocanchas.Values.SingletonUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private TextView registerTextView;
    private Button loginButton;
    private EditText userEditText;
    private EditText passEditText;
    FirebaseAuth.AuthStateListener fireAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //this.userEditText = findViewById
        this.userEditText = findViewById(R.id.email_edittext_loginactivity);
        this.passEditText = findViewById(R.id.pass_edittext_loginactivity);
        this.registerTextView = findViewById(R.id.register_textview_loginactivity);

        //Register
        this.registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(register);
            }
        });


        fireAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //Compruerba el inicio de sesion
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    Log.i("fire","Sesion iniciada");
                    loadUserData(user.getEmail());
                }else{
                    Log.i("fire","no iniciada");
                }
            }
        };


        this.loginButton = (Button)findViewById(R.id.button_login);
        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(theFieldsAreValids()){
                    login();

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


    private void login(){
        final String email = this.userEditText.getText().toString().trim();
        String pass=this.passEditText.getText().toString().trim();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    loadUserData(email);

                }else{
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                    alertDialogBuilder.setTitle("No se ha podido realizar la conexión");
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
            }
        });
    }

    public void loadUserData(String email){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userReference = database.getReference(); //Obtiene la referencia de la bd
        Query query = userReference.child(FireBaseReferences.USER_REFERENCE).orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User user = SingletonUser.getInstance();
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        User userR = issue.getValue(User.class);
                        userR.setPassword("");
                        SingletonUser.user = userR;
                        SingletonUser.getInstance().setId(issue.getKey());
                    }
                }
                Intent main = new Intent(LoginActivity.this,HomeActivity.class);
                LoginActivity.this.startActivity(main);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(this.fireAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(this.fireAuthStateListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(this.fireAuthStateListener);
        }
    }


}
