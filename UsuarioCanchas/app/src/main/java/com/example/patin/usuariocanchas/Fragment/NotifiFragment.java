package com.example.patin.usuariocanchas.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patin.usuariocanchas.Model.NotificacionAmistad;
import com.example.patin.usuariocanchas.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NotifiFragment  extends Fragment {
    ArrayList<NotificacionAmistad> notificaciones = new ArrayList<NotificacionAmistad>;
    DatabaseReference dbNotificaciones;
    RecyclerView rv;
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rv = (RecyclerView) view.findViewById(R.id.recycler);

        dbNotificaciones = FirebaseDatabase.getInstance().getReference("NotificacionAmistad");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.v("logf","Entro en notify");
        view=inflater.inflate(R.layout.fragment_notifi, container, false)
        return view;

    }
}
