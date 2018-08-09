package com.example.patin.usuariocanchas.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.patin.usuariocanchas.Model.Adapter;
import com.example.patin.usuariocanchas.Model.NotificacionAmistad;
import com.example.patin.usuariocanchas.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotifiFragment  extends Fragment {
    int a;
    ArrayList<NotificacionAmistad> notificaciones;
    DatabaseReference dbNotificaciones;
    RecyclerView rv;
    View view;
    Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.v("logf","Entro en notify");
        view=inflater.inflate(R.layout.fragment_notifi, container, false);
        rv = (RecyclerView) view.findViewById(R.id.recycler);
        FragmentActivity c = (FragmentActivity) getActivity();
        rv.setLayoutManager(new LinearLayoutManager (c));
        dbNotificaciones = FirebaseDatabase.getInstance().getReference("NotificacionAmistad");
        notificaciones=new ArrayList<>();
        adapter=new Adapter(notificaciones);
        rv.setAdapter(adapter);
        dbNotificaciones.getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notificaciones.removeAll(notificaciones);
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    NotificacionAmistad notificacionAmistad=snapshot.getValue(NotificacionAmistad.class);
                    notificaciones.add(notificacionAmistad);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
