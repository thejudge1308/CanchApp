package com.example.patin.usuariocanchas.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.patin.usuariocanchas.R;

public class ChangePassActivity extends AppCompatActivity {

    private Button confirmButton;
    private EditText pass1EditText;
    private EditText pass2EditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        this.confirmButton = findViewById(R.id.confirmar_hutton_recovery);
        this.pass1EditText = findViewById(R.id.pass1_edittext_recovery);
        this.pass2EditText = findViewById(R.id.pass2_edittext_recovery);

        this.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ValidFields()){
                    finish();
                }
                else{
                    finish();
                }
            }
        });
    }

    public boolean ValidFields(){
        if(this.pass1EditText.getText().toString().equals("") || this.pass2EditText.getText().toString().equals("")){
            return false;
        }
        if(!this.pass1EditText.getText().toString().equals(this.pass1EditText.getText().toString())){
            return false;
        }
        return true;
    }
}
