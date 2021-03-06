package com.example.patin.usuariocanchas.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patin.usuariocanchas.FirebaseIdService;
import com.example.patin.usuariocanchas.Model.TelefonoID;
import com.example.patin.usuariocanchas.Model.User;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.example.patin.usuariocanchas.Values.SingletonUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class SplashActivity extends AppCompatActivity {

    private TextView versionTextView;
    public static String VERSIONAPP="1.5";

    FirebaseAuth.AuthStateListener fireAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Oculta ActionBar (Barra superior)
        getSupportActionBar().hide();

        this.versionTextView = findViewById(R.id.version_textview_splashactivity);
        this.versionTextView.setText("Versión "+SplashActivity.VERSIONAPP);

        fireAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //Compruerba el inicio de sesion
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    Log.i("Fire","Sesion iniciada");
                    loadUserData(user.getEmail());

                }else{
                    Log.i("Fire","no iniciada");
                    Intent main = new Intent(SplashActivity.this,LoginActivity.class);
                    SplashActivity.this.startActivity(main);
                }
            }
        };


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
                        SingletonUser.getInstance().setId(issue.getKey()+"");
                    }
                }
                setPhoneId();
                Intent main = new Intent(SplashActivity.this,HomeActivity.class);
                SplashActivity.this.startActivity(main);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SplashActivity.this.getApplicationContext(),"Problemas de conexión",Toast.LENGTH_LONG).show();
                FirebaseAuth.getInstance().signOut();
                Intent main = new Intent(SplashActivity.this,LoginActivity.class);
                SplashActivity.this.startActivity(main);
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


    public void setPhoneId(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userReference = database.getReference(); //Obtiene la referencia de la bd
        Query query = userReference.child(FireBaseReferences.USER_IDFIREBASE).equalTo(SingletonUser.getInstance().getId());
        Log.v("MESSAGING_FIREBASE","entro");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*if(!dataSnapshot.exists()){
                    Log.v("MESSAGING_FIREBASE", "Cambio "+FirebaseInstanceId.getInstance().getToken());

                }*/
                //Log.v("MESSAGING_FIREBASE","id: "+SingletonUser.getInstance().getId());
                //Log.v("MESSAGING_FIREBASE", FirebaseInstanceId.getInstance().getToken());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference id_phone_reference = database.getReference(); //Obtiene la referencia de la bd
                id_phone_reference.child(FireBaseReferences.USER_IDFIREBASE).child(SingletonUser.getInstance().getId()).push();
                id_phone_reference.child(FireBaseReferences.USER_IDFIREBASE).child(SingletonUser.getInstance().getId()).setValue(new TelefonoID(SingletonUser.getInstance().getEmail(), FirebaseInstanceId.getInstance().getToken()));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
