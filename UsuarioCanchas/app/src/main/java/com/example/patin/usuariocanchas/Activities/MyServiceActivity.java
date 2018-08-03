package com.example.patin.usuariocanchas.Activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Adapter.AdapterService;
import com.example.patin.usuariocanchas.Item.ServiceItem;
import com.example.patin.usuariocanchas.Model.Service;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.example.patin.usuariocanchas.Values.SingletonUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class MyServiceActivity extends AppCompatActivity {

    private ListView servicesListView;
    public HashMap<String,Service> services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);
        //Log.v("_FIREBASE",SingletonUser.getInstance().getId()+"");
        this.servicesListView = findViewById(R.id.services_listview_myserviceactivity);

        this.services = new HashMap<>();
        loadserviceconfiguration();
    }

    public void onResume() {

        super.onResume();
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
                        Service seDb = new Service();
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
        for (HashMap.Entry<String, Service> value : services.entrySet()) {
            Service aux = (Service)value.getValue();
            ServiceItem item = new ServiceItem(aux.getNombre(),aux.getValor(),aux.getEstado(),image);
            Log.v("__SERVICE",item.getName()+" : "+item.getPrice() +" : "+item.getState());
            serviceItems.add(item);

        }
    Log.v("__SERVICE",serviceItems.size()+"");
    return serviceItems;
    }

    private void setServicesForUser(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userReference = database.getReference(); //Obtiene la referencia de la bd
        Log.v("__Service","Id "+SingletonUser.getInstance().getId());

        Query query = userReference.child(FireBaseReferences.USER_SERVICE_REFERENCE).child(SingletonUser.getInstance().getId());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference userReference = database.getReference(); //Obtiene la referencia de la bd
                    userReference.child(FireBaseReferences.USER_SERVICE_REFERENCE).child(SingletonUser.getInstance().getId()).push();
                    userReference.child(FireBaseReferences.USER_SERVICE_REFERENCE).child(SingletonUser.getInstance().getId()).setValue(MyServiceActivity.this.services);
                }


                if (true) {
                    //User user = SingletonUser.getInstance();
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Log.v("__Service","Cantidad "+issue.getChildrenCount());
                        Log.v("__Service","key "+issue.getKey());
                        Log.v("__Service","dara"+issue);
                        String valueDB="0";
                        String stateDB="0";
                        String key = "";
                        for(DataSnapshot child : issue.getChildren()){

                            if(child.getKey().equals("valor")){
                                valueDB = child.getValue().toString();
                            }else if(child.getKey().equals("estado")){
                                stateDB = child.getValue().toString();
                            }
                            Log.v("__Service","Child: "+child.getKey());
                            Log.v("__Service",child.getValue()+"");
                        }
                        Service service = MyServiceActivity.this.services.get(issue.getKey().toString());
                        service.setEstado(Integer.parseInt(stateDB));
                        service.setValor(Integer.parseInt(valueDB));
                        MyServiceActivity.this.services.put(issue.getKey().toString(),service);

                    }
                    AdapterService adapterService = new AdapterService(MyServiceActivity.this,getServicesForAdapter());
                        MyServiceActivity.this.servicesListView.setAdapter(adapterService);

                        MyServiceActivity.this.servicesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ServiceItem item = (ServiceItem) MyServiceActivity.this.servicesListView.getAdapter().getItem(position);
                                Intent  i =  new Intent(MyServiceActivity.this.getApplicationContext(),ServiceValuesActivity.class);
                                i.putExtra("name",item.getName());
                                i.putExtra("price",item.getPrice()+"");
                                i.putExtra("avaliable",item.getState()+"");
                                i.putExtra("array",MyServiceActivity.this.services);


                                startActivity(i);
                                //Toast.makeText(getApplicationContext(), "Obj"+item.getState()+" : "+item.getName()+" : "+item.getPrice(), Toast.LENGTH_SHORT).show();

                            }
                        });
                }else{
                    Log.v("__Service","Not found");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public HashMap<String, Service> getServices() {
        return services;
    }

    public void setServices(HashMap<String, Service> services) {
        this.services = services;
    }

    /*public class ServiceDB{
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
    }*/

}
