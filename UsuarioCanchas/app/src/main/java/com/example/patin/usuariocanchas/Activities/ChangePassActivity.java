package com.example.patin.usuariocanchas.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.SingletonUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassActivity extends AppCompatActivity {

    private Button confirmButton;
    private EditText pass1EditText;
    private EditText pass2EditText;
    private EditText oldPassEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        this.confirmButton = findViewById(R.id.confirmar_hutton_recovery);
        this.pass1EditText = findViewById(R.id.pass1_edittext_recovery);
        this.pass2EditText = findViewById(R.id.pass2_edittext_recovery);
        this.oldPassEditText = findViewById(R.id.oldpass_edittext_recovery);

        this.confirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(ValidFields()){
                    //finish();
                    changePassword();
                }
                else{
                    //finish();
                }
            }
        });
    }

    public boolean ValidFields(){
        if(this.pass1EditText.getText().toString().equals("") || this.pass2EditText.getText().toString().equals("") || this.oldPassEditText.getText().toString().equals("")){
            Toast.makeText(ChangePassActivity.this, "Campos erroneos",Toast.LENGTH_LONG);
            return false;
        }
        if(!this.pass1EditText.getText().toString().equals(this.pass1EditText.getText().toString())){

            return true;
        }
        return false;
    }

    public void changePassword(){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // Get auth credentials from the user for re-authentication. The example below shows
        // email and password credentials but there are multiple possible providers,
        // such as GoogleAuthProvider or FacebookAuthProvider.
                AuthCredential credential = EmailAuthProvider
                        .getCredential(SingletonUser.getInstance().getEmail(), this.oldPassEditText.getText().toString().trim());

        // Prompt the user to re-provide their sign-in credentials
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    user.updatePassword("").addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(ChangePassActivity.this, "Contraseña cambiada",Toast.LENGTH_LONG);
                                               ChangePassActivity.this.finish();
                                            } else {
                                                Toast.makeText(ChangePassActivity.this, "No se ha podido cambiar la contraseña",Toast.LENGTH_LONG);
                                            }
                                        }
                                    });
                                } else {
                                   // Log.d(TAG, "Error auth failed")
                                }
                            }
                        });
    }


}
