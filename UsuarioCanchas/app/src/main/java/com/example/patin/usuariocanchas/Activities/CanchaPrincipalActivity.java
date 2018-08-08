package com.example.patin.usuariocanchas.Activities;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import com.example.patin.usuariocanchas.Fragment.CanchaPrincipalFragment;
import com.example.patin.usuariocanchas.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Intent.getIntent;

public class CanchaPrincipalActivity extends AppCompatActivity implements View.OnClickListener, CanchaPrincipalFragment.OnFragmentInteractionListener {

    Button btnFr1, btnFr2;

    long idAdmin;

    String nombreClub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancha_principal);

        idAdmin = getIntent().getLongExtra("idAdmin",0);

        nombreClub = getIntent().getStringExtra("nombreClub");



        //Log.d("IdAdmin",idAdmin+"");

        CanchaPrincipalFragment fr1 = new CanchaPrincipalFragment();
        //Fragment2 fr2 = new Fragment2();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.idContenedor, fr1);
        transaction.commit();

        Log.d("IdAdmin",idAdmin+"");

        Bundle data = new Bundle();
        data.putLong("idAmdin",  idAdmin);
        data.putString("nombreClub",nombreClub);
        fr1.setArguments(data);

        getSupportFragmentManager().beginTransaction().add(R.id.idContenedor, fr1);

        //btnFr1 = (Button) findViewById(R.id.btnFrag1);
        //btnFr2 = (Button) findViewById(R.id.btnFrag2);

        //btnFr1.setOnClickListener(this);
        //btnFr2.setOnClickListener(this);

        //CanchaPrincipalFragment fr1 = new CanchaPrincipalFragment();



    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            //case R.id.btnFrag1:



                //break;

            /*case R.id.btnFrag2:

                Fragment2 fr2 = new Fragment2();

                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                transaction2.add(R.id.idContenedor, fr2);
                transaction2.commit();

                break;*/
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
