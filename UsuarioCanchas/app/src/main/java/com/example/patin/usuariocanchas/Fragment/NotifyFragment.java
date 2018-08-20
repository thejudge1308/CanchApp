package com.example.patin.usuariocanchas.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.patin.usuariocanchas.Adapter.AdapterNotificacion;
import com.example.patin.usuariocanchas.Model.NotificacionAmistad;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotifyFragment extends Fragment {
    ArrayList<NotificacionAmistad> notificaciones;
    DatabaseReference dbNotificaciones;
    RecyclerView rv;
    View view;
    AdapterNotificacion adapterNotificacion;
    String keyUser;
    String correoUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        correoUser = getArguments().getString("correoUser");//Obtengo el correo y la llave del usuario
        keyUser = getArguments().getString("keyUser");// estos datos vienen en un bundle desde una activity a un fragment
        Log.v("iii",correoUser);
        Log.v("iii",keyUser);
        view=inflater.inflate(R.layout.fragment_notifi, container, false);
        rv = (RecyclerView) view.findViewById(R.id.recycler);
        FragmentActivity c = (FragmentActivity) getActivity();
        rv.setLayoutManager(new LinearLayoutManager(c));
        dbNotificaciones = FirebaseDatabase.getInstance().getReference(FireBaseReferences.NOTIFICACIONAMISTAD_REFEREMCE).child(keyUser);
        notificaciones=new ArrayList<>();
        adapterNotificacion =new AdapterNotificacion(notificaciones);
        rv.setAdapter(adapterNotificacion);
        Query q=dbNotificaciones.orderByChild(FireBaseReferences.CORREO_SOLICITADO).equalTo(correoUser);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notificaciones.removeAll(notificaciones);
                int i=0;
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    i++;

                    NotificacionAmistad notificacionAmistad=snapshot.getValue(NotificacionAmistad.class);
                    notificaciones.add(notificacionAmistad);

                }
                if (notificaciones.size()<=0){
                    TextView textView=view.findViewById(R.id.nohaynotificaciones);

                }
                adapterNotificacion.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }


}
