package com.example.patin.usuariocanchas.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Model.Service;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.example.patin.usuariocanchas.Values.SingletonUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ServiceValuesActivity extends AppCompatActivity {
    private TextView nameTextView;
    private EditText priceEditText;
    private Spinner stateSpinner;
    private Button saveButton;
    private final static String[] states = { "No habilitado", "Habilitado"};
    public HashMap<String ,Service> services;
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
        this.services = (HashMap<String ,Service>)getIntent().getSerializableExtra("array");
        //Toast.makeText(this, "precio "+price, Toast.LENGTH_SHORT).show();
        nameTextView.setText(name);
        priceEditText.setText(price);
        stateSpinner.setSelection(Integer.parseInt(avalilable));
        this.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((priceEditText.getText().toString().trim()).equals("")){
                    Toast.makeText(ServiceValuesActivity.this.getApplicationContext(),"Debe ingresar un monto para poder guardar",Toast.LENGTH_SHORT).show();
                }else{
                    for (Map.Entry<String ,Service> value : ServiceValuesActivity.this.services.entrySet()) {
                        Service service =  (Service)value.getValue();

                        if(service.getNombre().equals(ServiceValuesActivity.this.nameTextView.getText().toString())){
                                service.setValor(Integer.parseInt(ServiceValuesActivity.this.priceEditText.getText().toString()));
                                service.setEstado(ServiceValuesActivity.this.stateSpinner.getSelectedItemPosition());
                            ServiceValuesActivity.this.services.put(value.getKey(),service);
                        }
                        //System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
                           ServiceValuesActivity.this.save();
                    }

                }
            }
        });
    }

    public void save(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        DatabaseReference ref = reference.child(FireBaseReferences.USER_SERVICE_REFERENCE).child(SingletonUser.getInstance().getId());

        ref.setValue(ServiceValuesActivity.this.services,new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    Toast.makeText(getApplicationContext(),"No se ha podido guardar.",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Guardado con éxito.",Toast.LENGTH_SHORT).show();
                    ServiceValuesActivity.this.finish();
                }
            }
        });
        
    }
}
