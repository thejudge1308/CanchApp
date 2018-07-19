package com.example.patin.usuariocanchas.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Segurity.Validation;

public class AddFriendActivity extends AppCompatActivity {
    private EditText emailEditText;
    private Button sendRequestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        this.emailEditText = findViewById(R.id.email_addfriend_activity);
        this.sendRequestButton = findViewById(R.id.sendrequest_addfriend_activity);

        this.sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validation.isCorrectEmail(AddFriendActivity.this.emailEditText.getText().toString())){

                }else {
                    Toast.makeText(getApplicationContext(),"Error de formato: Es incorrecto el email",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




}
