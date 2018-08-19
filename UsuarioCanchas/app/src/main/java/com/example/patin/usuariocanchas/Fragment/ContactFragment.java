package com.example.patin.usuariocanchas.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Activities.AddFriendActivity;
import com.example.patin.usuariocanchas.Activities.CreateMatchActivity;
import com.example.patin.usuariocanchas.Activities.HomeActivity;
import com.example.patin.usuariocanchas.Activities.LoginActivity;
import com.example.patin.usuariocanchas.Activities.MyPerfilActivity;
import com.example.patin.usuariocanchas.Adapter.AdapterContact;
import com.example.patin.usuariocanchas.Item.ContactItem;
import com.example.patin.usuariocanchas.Model.Amigo;
import com.example.patin.usuariocanchas.Model.NotificacionAmistad;
import com.example.patin.usuariocanchas.Model.User;
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

public class ContactFragment extends Fragment{
    private  FloatingActionButton floatingActionButton;
    private ListView contactListView;
    private ArrayList<ContactItem> contactItems;
    private String miID = SingletonUser.getInstance().getId();
    private String miCorreo = SingletonUser.getInstance().getEmail();
    private ArrayList<Amigo> misAmigos = new ArrayList<Amigo>();
    DatabaseReference bdAmigos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable  ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

            container.removeAllViews();
            final View rootView = inflater.inflate(R.layout.fragment_contact, container,false);

            FloatingActionButton floatingActionButton=rootView.findViewById(R.id.addcontact_fragment_contact);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BuscarAmigo buscarAmigo=new BuscarAmigo();
                    getFragmentManager().beginTransaction().replace(R.id.frangment_content,buscarAmigo).commit();
                }
            });


            bdAmigos= FirebaseDatabase.getInstance().getReference("Amigos").child(miID);

        final Query q =  bdAmigos.orderByChild("miCorreo").equalTo(miCorreo);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int cont=0;
                Amigo amigo = new Amigo();

                for (DataSnapshot datasnapshot : dataSnapshot.getChildren()){
                    Log.v("hola", "holamundo");
                    if (dataSnapshot.exists()){
                        amigo = datasnapshot.getValue(Amigo.class);
                        misAmigos.add(amigo);
                        cont++;
                    }
                }

                if (misAmigos.size()>0){
                    /*for (int i=0; i<misAmigos.size();i++){
                        Log.v("Nombre", "nombre:"+ misAmigos.get(i).getNombreAmigo());

                    }*/

                    contactListView = (ListView)rootView.findViewById(R.id.contact_listview_fragment_contact);

                    Drawable image  = ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.ic_group);

                    contactItems = new ArrayList<>();
                    String nombreAmigo;
                    String correoAmigo;
                    for (int i=0;i<misAmigos.size();i++){
                        nombreAmigo = misAmigos.get(i).getNombreAmigo() + " "+misAmigos.get(i).getApellidoAmigo();
                        correoAmigo = misAmigos.get(i).getCorrreoAmigo();
                        contactItems.add(new ContactItem(nombreAmigo, correoAmigo,image));
                    }
                    //contactItems.add(new ContactItem("2", "2",image));
                    //this.contactItems.add(new ContactItem("3", "2",image));
                    AdapterContact adapterContact = new AdapterContact(getActivity(),contactItems);
                    contactListView.setAdapter(adapterContact);

                   /* contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            final int pos = position;
                            Toast.makeText(getActivity().getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                        }
                    });*/

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       






        return rootView;

    }

}
