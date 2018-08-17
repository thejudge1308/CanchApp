package com.example.patin.usuariocanchas.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
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
import com.example.patin.usuariocanchas.Adapter.ReservasAdapter;
import com.example.patin.usuariocanchas.Model.NotificacionAmistad;
import com.example.patin.usuariocanchas.Model.Reserva;
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
import java.util.List;

public class MisReservasFragment extends Fragment {
    RecyclerView rv;
    View view;
    String idUser= SingletonUser.getInstance().getId();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference reserva= database.getReference(FireBaseReferences.RESERVA_REFERENCE);
    List reservas=new ArrayList<Reserva>();
    ReservasAdapter reservasAdapter;
    private OnFragmentInteractionListener mListener;

    public MisReservasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        container.removeAllViews();
        view=inflater.inflate(R.layout.fragment_mis_reservas, container, false);


        rv = (RecyclerView) view.findViewById(R.id.recycler_reserva);
        FragmentActivity c = (FragmentActivity) getActivity();
        rv.setLayoutManager(new LinearLayoutManager(c));
        reservasAdapter =new ReservasAdapter(reservas);
        rv.setAdapter(reservasAdapter);



        Query q=reserva.child(idUser);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                reservas.removeAll(reservas);
                int i=0;
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    for (DataSnapshot snapshott:snapshot.getChildren()) {
                        Log.v("que",snapshott.getRef().toString());
                        i++;
                        Reserva reserva=snapshott.getValue(Reserva.class);
                        reservas.add(reserva);
                    }
                }
                if (reservas.size()<=0){
                    TextView textView=view.findViewById(R.id.textView_reserva);
                    textView.setText("No tiene Reservas");
                }
                Log.v("que",Integer.toString(i));
                reservasAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;
    }



    public interface OnFragmentInteractionListener {

    }
}
