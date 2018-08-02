package com.example.patin.usuariocanchas.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Fragment.HorariosCanchaFragment;
import com.example.patin.usuariocanchas.Model.Cancha;
import com.example.patin.usuariocanchas.Model.HorarioCancha;
import com.example.patin.usuariocanchas.R;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreateMatchActivity extends AppCompatActivity {

    private Button openMapButton;
    private Button cancha1;
    List<Cancha> canchas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        canchas=new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_match);

        this.openMapButton = (Button)findViewById(R.id.openmaps_button_creatematchactivity);
        this.openMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateMatchActivity.this,MapsCanchaActivity.class);
                CreateMatchActivity.this.startActivity(i);
            }
        });
        /**
         * crea botones dinamicamente
         */
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        //cancha es el id del boton debe ser pasado en algun lugar
        DatabaseReference canchaDB=database.getReference("Cancha");
        canchaDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("aaa",dataSnapshot.toString());
                int j=0;
                canchas.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    j++;
                    Log.v("iii", String.valueOf(j));
                    Cancha cancha=snapshot.getValue(Cancha.class);
                    canchas.add(cancha);
                    Log.v("aaa",cancha.toString());
                }
                Log.e("aaa", String.valueOf(canchas.size()));
                LinearLayout layout = findViewById(R.id.layout);
                int iNumberOfButtons = canchas.size();
                Button[] dynamicButtons = new Button[iNumberOfButtons];
                LinearLayout.LayoutParams paramsButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout.removeAllViews();
                for (int i = 0; i < iNumberOfButtons; i++) {
                    dynamicButtons[i] = new Button(CreateMatchActivity.this);
                    dynamicButtons[i].setText(canchas.get(i).getNombre());
                    dynamicButtons[i].setId(i);
                    dynamicButtons[i].setTextSize(15.0f);
                    dynamicButtons[i].setLayoutParams(paramsButton);
                    layout.addView(dynamicButtons[i]); // dynamicButtonsLinearLayout is the container of the buttons
                    dynamicButtons[i].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            String nombreBoton=((Button) view).getText().toString();
                            //Log.v("boton",nombreBoton);
                            Intent intent = new Intent(CreateMatchActivity.this,HorarioYReserva.class);
                            intent.putExtra("nombreCancha",nombreBoton);
                            CreateMatchActivity.this.startActivity(intent);
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("aaa", databaseError.getMessage());
            }
        });



    }
}
