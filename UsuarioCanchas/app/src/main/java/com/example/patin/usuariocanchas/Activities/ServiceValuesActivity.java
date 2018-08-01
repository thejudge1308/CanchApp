package com.example.patin.usuariocanchas.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patin.usuariocanchas.R;

public class ServiceValuesActivity extends AppCompatActivity {
    private TextView nameTextView;
    private EditText priceEditText;
    private Spinner stateSpinner;
    private Button saveButton;
    private final static String[] states = { "No habilitado", "Habilitado"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_values);
        this.nameTextView = findViewById(R.id.name_textview_servicevalues_activity);
        this.priceEditText = findViewById(R.id.price_edittext_servicevalues_activity);
        this.stateSpinner  = findViewById(R.id.avalaible_spinner_servicevalues_activity);
        this.saveButton = findViewById(R.id.save_button_servicevalues_activity);

        this.stateSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, states));

    
        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        String avalilable = getIntent().getStringExtra("avaliable");
        //Toast.makeText(this, "precio "+price, Toast.LENGTH_SHORT).show();
        nameTextView.setText(name);
        priceEditText.setText(price);
        stateSpinner.setSelection(Integer.parseInt(avalilable));
    }
}
