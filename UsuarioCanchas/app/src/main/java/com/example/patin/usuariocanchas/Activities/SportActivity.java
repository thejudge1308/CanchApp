package com.example.patin.usuariocanchas.Activities;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Activities.CreateMatchActivity;
import com.example.patin.usuariocanchas.Adapter.AdapterContact;
import com.example.patin.usuariocanchas.Adapter.AdapterSport;
import com.example.patin.usuariocanchas.Fragment.CreaEquipoFragment;
import com.example.patin.usuariocanchas.Fragment.CreaEventoFragment;
import com.example.patin.usuariocanchas.Fragment.CrearPartidoFragment;
import com.example.patin.usuariocanchas.Fragment.HorarioCanchaDialogFragment;
import com.example.patin.usuariocanchas.Fragment.MapsCanchaFragment;
import com.example.patin.usuariocanchas.Fragment.SeleccionDeCanchaFragment;
import com.example.patin.usuariocanchas.Fragment.SeleccionarEquipoRivalFragment;
import com.example.patin.usuariocanchas.Fragment.SportsFragment;
import com.example.patin.usuariocanchas.Fragment.WebpayFragment;
import com.example.patin.usuariocanchas.Item.SportItem;
import com.example.patin.usuariocanchas.Model.HorarioCancha;
import com.example.patin.usuariocanchas.R;

import java.util.ArrayList;

public class SportActivity extends AppCompatActivity {

    public static String fechaEvento=null;
    public static String horaInicio=null;
    public static String horaTermino=null;
    public static String nombreCancha=null;

    /**
     * Seleccion cancha fragment
     */
    public static long idAdmin;
    public static String nombreClub;

    /**
     *
     * Horario cancha
     */
    public static String btn;
    public static String idAdminSeleccionCancha;

    /*
     Horario cancha fragment
     */
     public static String fechaEventoH;
     public static String fechaReservaH;
     public static String estadoH;
     public static String horaInicioH;
     public static String StringhoraTerminoH;
     public static String nombreCanchaH;
     public static String valorCanchH;

    /**
     * ELECCION EQUIPO RIVAL
     */
    public static String nombreEquipoR;
    public static String fechaEventoR;
    public static String fechaReservaR;
    public static String estadoR;
    public static String horaInicioR;
    public static String horaTerminoR;
    public static String nombreCanchaR;
    public static String valorCanchaR;


    //WebPAY
    public static Integer amount;
    public static String keyUser;


    //private ListView sportsListView;
    //private ArrayList<SportItem> sportItemArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        getSupportActionBar().hide();

        /*this.sportsListView = findViewById(R.id.sports_listview_sport_activity);
        this.sportItemArrayList = new ArrayList<>();
        this.createSports();

        AdapterSport adapterSport = new AdapterSport(this,this.sportItemArrayList);
        sportsListView.setAdapter(adapterSport);
        this.sportsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                switch (pos){
                    case 0:
                        Intent intent = new Intent(getApplicationContext(),CreateMatchActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        Fragment f = this.getFragmentManager().findFragmentById(R.id.content_sport_activity);

        if(f instanceof SportsFragment){
            finish();
        }else if(f instanceof CreaEventoFragment){
            SportsFragment sportFragment = new SportsFragment();
            getFragmentManager().beginTransaction().replace(R.id.content_sport_activity,sportFragment).commit();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
        }else if(f instanceof CreaEquipoFragment){
            CreaEventoFragment fragment = new CreaEventoFragment();
            getFragmentManager().beginTransaction().replace(R.id.content_sport_activity,fragment).commit();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
        }else if(f instanceof MapsCanchaFragment){
            CreaEventoFragment fragment = new CreaEventoFragment();
            getFragmentManager().beginTransaction().replace(R.id.content_sport_activity,fragment).commit();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
        }else if(f instanceof SeleccionDeCanchaFragment){
            MapsCanchaFragment fragment = new MapsCanchaFragment();
            getFragmentManager().beginTransaction().replace(R.id.content_sport_activity,fragment).commit();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
        }else if(f instanceof HorarioCanchaDialogFragment){
            SeleccionDeCanchaFragment fragment = new SeleccionDeCanchaFragment();
            getFragmentManager().beginTransaction().replace(R.id.content_sport_activity,fragment).commit();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
        }else if(f instanceof CrearPartidoFragment){
            HorarioCanchaDialogFragment fragment = new HorarioCanchaDialogFragment();
            getFragmentManager().beginTransaction().replace(R.id.content_sport_activity,fragment).commit();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
        }else if(f instanceof SeleccionarEquipoRivalFragment){
            CrearPartidoFragment fragment = new CrearPartidoFragment();
            getFragmentManager().beginTransaction().replace(R.id.content_sport_activity,fragment).commit();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
        }else {
            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this.getApplicationContext());
            dialogo1.setTitle("Desea salir?");
            dialogo1.setMessage("Se cancelará toda la transacción.");
            dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            finish();
                        }});

        }

    }


   /* public void createSports(){
        Drawable footballDrawable  = ContextCompat.getDrawable(getApplicationContext(), R.drawable.logo_inicial);
        SportItem football = new SportItem("Football",footballDrawable);


        this.sportItemArrayList.add(football);

    }*/

}
