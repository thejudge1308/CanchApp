package com.example.patin.usuariocanchas.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.SingletonUser;

public class MyPerfilActivity extends AppCompatActivity {

    private TextView emailTextView;
    private EditText nickEditText;
    private TextView scoreTextView;
    private Button changePassButton;
    private Button myServiceButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_perfil);
        this.emailTextView = findViewById(R.id.email_textview_myperfil);
        this.nickEditText = findViewById(R.id.nick_edittext_myperfil);
        this.scoreTextView = findViewById(R.id.score_textview_myperfil);


        this.emailTextView.setText(SingletonUser.getInstance().getEmail());
        this.nickEditText.setText(SingletonUser.getInstance().getNickname());
        this.scoreTextView.setText(SingletonUser.getInstance().getScore()+"");

        this.changePassButton = findViewById(R.id.changepass_button_myperfil);
        this.changePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MyPerfilActivity.this,ChangePassActivity.class);
                MyPerfilActivity.this.startActivity(i);
            }
        });

        this.myServiceButton = findViewById(R.id.myservice_button_myservice_activity);
        this.myServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MyPerfilActivity.this,MyServiceActivity.class);
                MyPerfilActivity.this.startActivity(i);
            }
        });
    }
}
