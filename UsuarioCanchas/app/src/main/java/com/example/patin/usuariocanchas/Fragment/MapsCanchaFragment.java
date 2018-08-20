package com.example.patin.usuariocanchas.Fragment;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.SupportActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Fragment.CanchaPrincipalFragment;
import com.example.patin.usuariocanchas.Fragment.SeleccionDeCanchaFragment;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;


import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Map;


public class MapsCanchaFragment extends DialogFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, DialogInterface.OnClickListener{
    View view;
    //private GoogleMap mMap;
    private GoogleMap mMap;
    private static final int LOCATION_REQUEST = 500;
    private EditText searchEditText;
    LocationManager locationManager;
    String lattitude, longitude;
    private static final int REQUEST_LOCATION = 1;
    private double lat, lgt;
    private ArrayList<LatLng> curico;
    private ArrayList<String> direccion;
    private int contadorClickMarcador;
    private String tituloMarcador;
    private long idAdmin;
    private MapFragment mapFragment;
    private OnDialogResult dialogoResult = null;
    private String[] items = { "Español", "Inglés", "Francés" };
    public MapsCanchaFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        container.removeAllViews();
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_maps_cancha, container, false);

        this.searchEditText = view.findViewById(R.id.search_edittext_maps);

        lat = 0.0;
        lgt = 0.0;

        curico = new ArrayList<LatLng>();

        direccion = new ArrayList<String>();

        contadorClickMarcador = 0;
        tituloMarcador = "";

        idAdmin = 0;

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(view.getContext());

        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            mapFragment.getMapAsync(this);
        }

        // R.id.map is a FrameLayout, not a Fragment
        getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();

        return view;
    }





    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();

        if(contadorClickMarcador ==0)
        {
            tituloMarcador = marker.getTitle();
            contadorClickMarcador++;
        }
        else if(contadorClickMarcador==1 && tituloMarcador.equals(marker.getTitle()))//Si hace doble click en un marcador ingresa a fragment CanchaPrincipal
        {

            capturarIdAdmin(tituloMarcador);



            Log.d("click","Entro al if");
            tituloMarcador = "";
            contadorClickMarcador = 0;



            //setContactViewFragment();

        }
        else if(contadorClickMarcador==1 && !tituloMarcador.equals(marker.getTitle()))
        {
            Log.d("click","Entro al else if");
            tituloMarcador = marker.getTitle();;
            //contadorClickMarcador = 0;
        }

        Log.d("click","titulo: "+marker.getTitle());
        Log.d("click","contador: "+contadorClickMarcador);

        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getLocalitation();
        //// Add a marker in Sydney and move the camera

        LatLng curicoAux = new LatLng(lat, lgt);

        curico.add(curicoAux);

        String direccionAux= conversorDireccion(lat, lgt);
        direccion.add(direccionAux);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference clubCanchaReference = database.getReference(); //Obtiene la referencia de la bd

        Query query = clubCanchaReference.child(FireBaseReferences.CLUB_CANCHA_REFERENCE).orderByChild("idAdministrador");

        //Log.d("_Firebase","Query: "+query.toString()+"");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //Log.v("_Firebase","Entro");
                    //User user = SingletonUser.getInstance();
                    //Log.v("_Firebase","cantidad: "+dataSnapshot.getChildrenCount());
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        //Log.d("_Firebase","Id: "+issue.getKey());
                        //Cancha cancha = issue.getValue(Cancha.class);
                        Double latitud=0.0;
                        Double longitud=0.0;
                        for(DataSnapshot values : issue.getChildren()){
                            //Log.v("_Firebase",values.getKey()+":"+values.getValue());
                            if(values.getKey().contains("latitud")){
                                latitud = (Double)values.getValue();
                            }else if(values.getKey().contains("longitud")){
                                longitud = (Double)values.getValue();
                            }
                            else if(values.getKey().contains("nombre"))
                            {
                                //String direccionAux2= conversorDireccion(latitud, longitud);
                                String direccionAux2=(String)values.getValue();
                                direccion.add(direccionAux2);
                            }

                        }

                        LatLng curicoAux = new LatLng(latitud, longitud);

                        curico.add(curicoAux);



                        //Log.d("_Firebase","lat: "+cancha.getLatitud()+"");



                    }
                    Marcadores();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mMap.setOnMarkerClickListener(this);
    }
    public void Marcadores()
    {
        Log.d("Largo curico", curico.size()+"");

        //mMap.addMarker(new MarkerOptions().position(curico.get(0)).title(direccion.get(0)));
        for(int i=1; i<curico.size(); i++)
        {
            Log.d("direccion",direccion.get(i));
            mMap.addMarker(new MarkerOptions().position(curico.get(i)).title(direccion.get(i)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curico.get(0), 18));
        if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);

    }
    public String conversorDireccion(double latitud, double longitud)
    {
        String direccion = "";
        if (latitud != 0.0 && longitud != 0.0)
        {
            try
            {
                Geocoder geocoder = new Geocoder(view.getContext(), Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(latitud, longitud, 5);

                if (!list.isEmpty())
                {
                    Address DirCalle = list.get(0);
                    direccion = String.valueOf(DirCalle.getAddressLine(0));
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return direccion;
    }

    void getLocalitation(){
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (view.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

            if (location != null) {
                lat = location.getLatitude();
                lgt = location.getLongitude();
                lattitude = String.valueOf(lat);
                longitude = String.valueOf(lgt);


            } else  if (location1 != null) {
                lat = location1.getLatitude();
                lgt = location1.getLongitude();
                lattitude = String.valueOf(lat);
                longitude = String.valueOf(lgt);


            } else  if (location2 != null) {
                lat = location2.getLatitude();
                lgt = location2.getLongitude();
                lattitude = String.valueOf(lat);
                longitude = String.valueOf(lgt);



            }else{

                Toast.makeText(view.getContext(),"No se puede obtener la localizacion", Toast.LENGTH_SHORT).show();

            }
        }
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("Activa el GPS")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    private void capturarIdAdmin(final String nombreClub)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference clubCanchaReference = database.getReference(); //Obtiene la referencia de la bd

        Query query = clubCanchaReference.child(FireBaseReferences.CLUB_CANCHA_REFERENCE).orderByChild("nombre").equalTo(nombreClub);

        Log.d("_Firebase","Query: "+query.toString()+"");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.v("_Firebase","Entro");
                    //User user = SingletonUser.getInstance();
                    Log.v("_Firebase","cantidad: "+dataSnapshot.getChildrenCount());
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Log.d("_Firebase","Id: "+issue.getKey());
                        //Cancha cancha = issue.getValue(Cancha.class);
                        //Double latitud=0.0;
                        //Double longitud=0.0;
                        String nombreC="";
                        long id =0;
                        for(DataSnapshot values : issue.getChildren()){
                            Log.v("_Firebase",values.getKey()+":"+values.getValue());
                            if(values.getKey().contains("idAdministrador")){
                                idAdmin = (Long) values.getValue();
                            }else if(values.getKey().contains("nombre")){
                                nombreC = (String)values.getValue();
                            }
                            /*else if(values.getKey().contains("nombre"))
                            {
                                //String direccionAux2= conversorDireccion(latitud, longitud);
                                String direccionAux2=(String)values.getValue();
                                direccion.add(direccionAux2);
                            }*/

                        }
                        //Log.d("IdAdmin",idAdmin+"");

                        if(idAdmin!=0)
                        {
                            setContactViewFragment(idAdmin, nombreC);
                        }


                        //LatLng curicoAux = new LatLng(latitud, longitud);

                        //curico.add(curicoAux);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setContactViewFragment(long id, String nombreClub){

        idAdmin = id;

        /**
         * dilog fragment que abre Seleccionar cancha
         */
        //FragmentTransaction ft = getFragmentManager().beginTransaction();
        SeleccionDeCanchaFragment dialogFragment = new SeleccionDeCanchaFragment();

        Bundle bundle=new Bundle();
        bundle.putLong("idAdmin",idAdmin);
        bundle.putString("nombreClub",nombreClub);
        bundle.putLong("idAdmin",idAdmin);
        dialogFragment.setArguments(bundle);

       //dialogFragment.show(ft, "dialog");

        //MapsCanchaFragment maps = new MapsCanchaFragment();
        getFragmentManager().beginTransaction().replace(R.id.content_sport_activity,dialogFragment).commit();

        FragmentTransaction ft = getFragmentManager().beginTransaction();

    }

    @Override
    public void onClick(DialogInterface dialog, int item) {
        if (getDialog() != null) {
            dialogoResult.finish(items[item]);
        }
        this.dismiss();
    }

    public interface OnDialogResult {
        void finish(String result);
    }

    public void setDialogoResult(OnDialogResult dialogoResult) {
        this.dialogoResult = dialogoResult;
    }




}
