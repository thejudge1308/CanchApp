package com.example.patin.usuariocanchas.Activities;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Adapter.AdapterService;
import com.example.patin.usuariocanchas.Item.ServiceItem;
import com.example.patin.usuariocanchas.Model.Service;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MyServiceActivity extends AppCompatActivity {

    private ListView servicesListView;
    private HashMap<String,ServiceDB> services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

        this.servicesListView = findViewById(R.id.services_listview_myserviceactivity);

        this.services = new HashMap<>();
        loadserviceconfiguration();



    }

    private void loadserviceconfiguration(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userReference = database.getReference(); //Obtiene la referencia de la bd
        Query query = userReference.child(FireBaseReferences.SERVICE_REFEREMCE).orderByChild("nombre");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        //Get services from database
                        ServiceDB seDb = new ServiceDB();
                        seDb.setNombre(issue.child("nombre").getValue().toString());

                        MyServiceActivity.this.services.put(issue.getKey().toString(),seDb);

                        Log.v("__SERVICE",MyServiceActivity.this.services.get(issue.getKey().toString()).getNombre()+" key:"+issue.getKey().toString());
                        //Log.v("Data",issue.getKey().toString());
                        //Log.v("Data2",issue.child("nombre").getValue().toString());

                       //servicesDB.add(issue.child("nombre").getValue().toString());
                        //serviceDBS.add(serviceDB);

                    }

                    AdapterService adapterService = new AdapterService(MyServiceActivity.this,getServicesForAdapter());
                    MyServiceActivity.this.servicesListView.setAdapter(adapterService);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MyServiceActivity.this.getApplicationContext(),"Problemas de conexion con el servidor.",Toast.LENGTH_LONG).show();
            }
        });
    }

    private ArrayList<ServiceItem> getServicesForAdapter(){
        Drawable image  = ContextCompat.getDrawable(MyServiceActivity.this.getApplicationContext(), R.drawable.ic_menu_share);
        ArrayList<ServiceItem> serviceItems = new ArrayList<>();
        Log.v("__SERVICE",services.size()+" mPA");
        for (HashMap.Entry<String, ServiceDB> value : services.entrySet()) {
            ServiceDB aux = (ServiceDB)value.getValue();
            ServiceItem item = new ServiceItem(aux.nombre,aux.value,aux.estado,image);
            serviceItems.add(item);

        }
    Log.v("__SERVICE",serviceItems.size()+"");
    return serviceItems;
    }

    public class ServiceDB{
        private String nombre;
        private int value;
        private int estado;

        public ServiceDB(String nombre, int value, int estado) {
            this.nombre = nombre;
            this.value = value;
            this.estado = estado;
        }
        public ServiceDB(){
            this.value = 0;
            this.estado=1;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getValor() {
            return value;
        }

        public void setValor(int value) {
            this.value = value;
        }

        public int getEstado() {
            return estado;
        }

        public void setEstado(int estado) {
            this.estado = estado;
        }
    }

}
