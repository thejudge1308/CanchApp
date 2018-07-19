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
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.example.patin.usuariocanchas.Values.SingletonUser;
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

                    setServicesForUser();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MyServiceActivity.this.getApplicationContext(),"Problemas de conexion con el servidor.",Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     *
     * @return arraylist with all services of Services_reference
     */
    private ArrayList<ServiceItem> getServicesForAdapter(){
        Drawable image  = ContextCompat.getDrawable(MyServiceActivity.this.getApplicationContext(), R.drawable.ic_menu_share);
        ArrayList<ServiceItem> serviceItems = new ArrayList<>();
        Log.v("__SERVICE",services.size()+" mPA");
        for (HashMap.Entry<String, ServiceDB> value : services.entrySet()) {
            ServiceDB aux = (ServiceDB)value.getValue();
            ServiceItem item = new ServiceItem(aux.nombre,aux.valor,aux.estado,image);
            Log.v("__SERVICE",item.getName()+" : "+item.getPrice() +" : "+item.getState());
            serviceItems.add(item);

        }
    Log.v("__SERVICE",serviceItems.size()+"");
    return serviceItems;
    }

    private void setServicesForUser(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userReference = database.getReference(); //Obtiene la referencia de la bd
        Query query = userReference.child(FireBaseReferences.USER_SERVICE_REFERENCE).equalTo(SingletonUser.getInstance().getId());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //User user = SingletonUser.getInstance();
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Log.v("__Service","Cantidad "+issue.getChildrenCount());
                        Log.v("__Service","key "+issue.getKey());
                        for(DataSnapshot child : issue.getChildren()){
                            Log.v("__Service","Child: "+child.getKey());
                            ServiceDB service = MyServiceActivity.this.services.get(child.getKey().toString());
                            String valueDB = child.child("valor").exists()?child.child("valor").getValue().toString():"0";
                            String stateDB = child.child("estado").exists()?child.child("estado").getValue().toString():"0";
                            Log.v("__Service",valueDB);
                            Log.v("__Service",stateDB);
                            //Log.v("__Service",service.getNombre() +" : "+service.getEstado()+" : "+service.getValor());

                            service.setEstado(Integer.parseInt(stateDB));
                            service.setValor(Integer.parseInt(valueDB));
                            Log.v("__Service",service.getNombre() +" : "+service.getEstado()+" : "+service.getValor());


                            MyServiceActivity.this.services.put(child.getKey().toString(),service);
                        }

                        AdapterService adapterService = new AdapterService(MyServiceActivity.this,getServicesForAdapter());
                        MyServiceActivity.this.servicesListView.setAdapter(adapterService);
                    }
                }else{
                    Log.v("__Service","Not found");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public class ServiceDB{
        private String nombre;
        private int valor;
        private int estado;

        public ServiceDB(String nombre, int value, int estado) {
            this.nombre = nombre;
            this.valor = value;
            this.estado = estado;
        }
        public ServiceDB(){
            this.valor = 0;
            this.estado=0;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getValor() {
            return valor;
        }

        public void setValor(int value) {
            this.valor = value;
        }

        public int getEstado() {
            return estado;
        }

        public void setEstado(int estado) {
            this.estado = estado;
        }
    }

}
