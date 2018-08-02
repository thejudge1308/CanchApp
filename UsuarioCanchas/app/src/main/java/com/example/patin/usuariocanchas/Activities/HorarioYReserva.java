package com.example.patin.usuariocanchas.Activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.patin.usuariocanchas.Fragment.HorariosCanchaFragment;
import com.example.patin.usuariocanchas.R;

public class HorarioYReserva extends AppCompatActivity implements HorariosCanchaFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        HorariosCanchaFragment horariosCanchaFragment;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_horario);

        horariosCanchaFragment=new HorariosCanchaFragment();
        final String nombreCancha = getIntent().getExtras().getString("nombreCancha");
        Bundle bundle=new Bundle();
        bundle.putString("nombreCancha",nombreCancha);
        horariosCanchaFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragment,horariosCanchaFragment).commit();
    }


}
