package com.example.patin.usuariocanchas.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Model.Cancha;
import com.example.patin.usuariocanchas.Model.User;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.example.patin.usuariocanchas.Values.SingletonUser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
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

public class MapsCanchaActivity extends FragmentActivity implements OnMapReadyCallback {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_cancha);
        this.searchEditText = findViewById(R.id.search_edittext_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);*/

        lat = 0.0;
        lgt = 0.0;

        curico = new ArrayList<LatLng>();

        direccion = new ArrayList<String>();

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (status == ConnectionResult.SUCCESS) //si es que encuentra el mapa
        {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else // en caso contrario si hay error
        {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, (Activity) getApplicationContext(), 10);
            dialog.show();
        }

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
        DatabaseReference userReference = database.getReference(); //Obtiene la referencia de la bd
        Query query = userReference.child(FireBaseReferences.CANCHA_REFERENCE).orderByChild("idAdministrador").equalTo(1);

        Log.d("_Firebase","Query"+query.toString()+"");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.v("_Firebase","Entro");
                    //User user = SingletonUser.getInstance();
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Log.d("_Firebase","Id: "+issue.getKey());
                        //Cancha cancha = issue.getValue(Cancha.class);
                        Double latitud=0.0;
                        Double longitud=0.0;
                        for(DataSnapshot values : issue.getChildren()){
                            Log.v("_Firebase",values.getKey()+":"+values.getValue());
                           if(values.getKey().contains("latitud")){
                               latitud = (Double)values.getValue();
                           }else if(values.getKey().contains("longitud")){
                               longitud = (Double)values.getValue();
                           }
                        }
                        LatLng curicoAux = new LatLng(latitud, longitud);

                        curico.add(curicoAux);

                        String direccionAux2= conversorDireccion(latitud, longitud);
                        direccion.add(direccionAux2);
                        //Log.d("_Firebase","lat: "+cancha.getLatitud()+"");

                        Marcadores();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Log.d("lat",lat+"");
        //Log.d("lgt",lgt+"");







        /*mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }

        LocationManager locman = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        double latitud;
        double longitud;
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Location location;

        if (!gpsEnabled)
        {
            location = locman.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            latitud = location.getLatitude();
            longitud = location.getLongitude();
        }
        else
        {
            location = locman.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            latitud = location.getLatitude();
            longitud = location.getLongitude();
        }

        LatLng hcmus = new LatLng(latitud, longitud);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 18));
        mMap.setMyLocationEnabled(true);*/

     }

     public void Marcadores()
     {
         Log.d("Largo curico", curico.size()+"");
         mMap.addMarker(new MarkerOptions().position(curico.get(0)).title(direccion.get(0)));
         mMap.addMarker(new MarkerOptions().position(curico.get(1)).title(direccion.get(1)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
         mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curico.get(0), 18));
         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
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
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(MapsCanchaActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (MapsCanchaActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MapsCanchaActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

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

                Toast.makeText(this,"No se puede obtener la localizacion", Toast.LENGTH_SHORT).show();

            }
        }
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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



}
