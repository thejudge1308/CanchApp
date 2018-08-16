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

    private EditText emailEditText;
    private Button sendRequestButton;
    DatabaseReference base;
    DatabaseReference baseNotificacion;
    DatabaseReference basedato;
    String nombreSolicitante = SingletonUser.getInstance().getName(); //soy yo
    String apellidoSolicitante = SingletonUser.getInstance().getSurname();
    String correoSolicitante = SingletonUser.getInstance().getEmail();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable  ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

            container.removeAllViews();
            final View rootView = inflater.inflate(R.layout.activity_add_friend, container,false);

            Log.v("Nombre mio = ",nombreSolicitante + apellidoSolicitante);
            Log.v(" Mi correo = ",correoSolicitante);
            super.onCreate(savedInstanceState);
            //setContentView(R.layout.activity_add_friend);
            base = FirebaseDatabase.getInstance().getReference("usuario");
            baseNotificacion = FirebaseDatabase.getInstance().getReference("Notificacion");
            basedato = FirebaseDatabase.getInstance().getReference(FireBaseReferences.NOTIFICACIONAMISTAD_REFEREMCE);
            this.emailEditText = rootView.findViewById(R.id.email_addfriend_activity);
            this.sendRequestButton = rootView.findViewById(R.id.sendrequest_addfriend_activity1);

            this.sendRequestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //buscar el usuario en la base de datos
                    final String correoIngresado=ContactFragment.this.emailEditText.getText().toString();
                    final Query q =  base.orderByChild("email").equalTo(correoIngresado);
                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int cont=0;
                            User user = new User();
                            String keyUser="";
                            for (DataSnapshot datasnapshot : dataSnapshot.getChildren()){
                                keyUser=datasnapshot.getKey();
                                if (!correoIngresado.equalsIgnoreCase(SingletonUser.getInstance().getEmail())) {
                                    user = datasnapshot.getValue(User.class);
                                    Log.v("Nombre: ", keyUser);
                                    Log.v("Nombre: ", dataSnapshot.getKey() + " key");
                                    cont++;
                                }
                            }
                            if (cont>0){
                                Log.v("encontre => ", String.valueOf(+ cont));
                                NotificacionAmistad notificacionAmistad = new NotificacionAmistad(nombreSolicitante,apellidoSolicitante,user.getName(),user.getSurname(), user.getEmail(),correoSolicitante);
                                Log.v("nombre Solicitante ", nombreSolicitante);
                                Log.v("apellido Solicitante ", apellidoSolicitante);
                                Log.v("nombre Solicitado ", user.getName());
                                Log.v("apellido Solicitado ", user.getSurname());
                                Log.v("email Solicitado ", user.getEmail());
                                //asi se inserta una solicitud de amistad
                                basedato.child(keyUser).push().setValue(notificacionAmistad);
                                Toast.makeText(rootView.getContext(), "Solicitud Enviada" , Toast.LENGTH_LONG ).show();
                            }
                            else if(cont==0){
                                Log.v("encontre nada => ", String.valueOf(+ cont));
                                Toast.makeText(rootView.getContext(), "Usuario no Encontrado" , Toast.LENGTH_LONG ).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            });



        return rootView;

    }

}
