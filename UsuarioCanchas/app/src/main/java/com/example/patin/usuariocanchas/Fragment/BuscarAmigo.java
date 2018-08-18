package com.example.patin.usuariocanchas.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Activities.AddFriendActivity;
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

public class BuscarAmigo extends Fragment {
    View view;
    private FloatingActionButton floatingActionButton;
    private ListView contactListView;
    private ArrayList<ContactItem> contactItems;

    private EditText emailEditText;
    private Button sendRequestButton;
    DatabaseReference base;
    DatabaseReference noDuplicate;
    DatabaseReference baseNotificacion;
    DatabaseReference basedato;
    String nombreSolicitante = SingletonUser.getInstance().getName(); //soy yo
    String apellidoSolicitante = SingletonUser.getInstance().getSurname();
    String correoSolicitante = SingletonUser.getInstance().getEmail();
    int contador=0;
    private OnFragmentInteractionListener mListener;

    public BuscarAmigo() {
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
        view=inflater.inflate(R.layout.fragment_buscar_amigo, container, false);
        Log.v("Nombre mio = ",nombreSolicitante + apellidoSolicitante);
        Log.v(" Mi correo = ",correoSolicitante);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_friend);
        base = FirebaseDatabase.getInstance().getReference("usuario");
        baseNotificacion = FirebaseDatabase.getInstance().getReference("Notificacion");
        basedato = FirebaseDatabase.getInstance().getReference(FireBaseReferences.NOTIFICACIONAMISTAD_REFEREMCE);
        this.emailEditText = view.findViewById(R.id.email_addfriend_activity);
        this.sendRequestButton = view.findViewById(R.id.sendrequest_addfriend_activity1);

        this.sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //buscar el usuario en la base de datos
                final String correoIngresado=BuscarAmigo.this.emailEditText.getText().toString();
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
                            final NotificacionAmistad notificacionAmistad = new NotificacionAmistad(nombreSolicitante,apellidoSolicitante,user.getName(),user.getSurname(), user.getEmail(),correoSolicitante);
                            Log.v("nombre Solicitante ", nombreSolicitante);
                            Log.v("apellido Solicitante ", apellidoSolicitante);
                            Log.v("nombre Solicitado ", user.getName());
                            Log.v("apellido Solicitado ", user.getSurname());
                            Log.v("email Solicitado ", user.getEmail());
                            //asi se inserta una solicitud de amistad

                            final DatabaseReference addNotificacion = FirebaseDatabase.getInstance().getReference("NotificacionAmistad/"+user.getId());
                            DatabaseReference bdNotificacion= FirebaseDatabase.getInstance().getReference("NotificacionAmistad").child(user.getId());
                            bdNotificacion.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot datasnapshot : dataSnapshot.getChildren()){
                                        contador++;

                                    }

                                    if(contador==0){
                                        if(!addNotificacion.push().setValue(notificacionAmistad).isSuccessful()){
                                            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                            builder.setTitle("Solicitud");
                                            builder.setMessage("Solicitud Enviada");
                                            builder.setIcon(R.drawable.ic_info_black_24dp);
                                            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.dismiss();
                                                }
                                            });
                                            AlertDialog alert = builder.create();
                                            alert.show();

                                        }else{
                                            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                            builder.setTitle("Error");
                                            builder.setMessage("Error, No se Envio la Solicitud");
                                            builder.setIcon(R.drawable.ic_cancel_black_24dp);
                                            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.dismiss();
                                                }
                                            });
                                            AlertDialog alert = builder.create();
                                            alert.show();
                                        }
                                    }
                                    else if(contador>0){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                        builder.setTitle("Solicitud");
                                        builder.setMessage("Ya Existe una Solicitud");
                                        builder.setIcon(R.drawable.ic_info_black_24dp);
                                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.dismiss();
                                            }
                                        });
                                        AlertDialog alert = builder.create();
                                        alert.show();
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });





                            //noDuplicate = FirebaseDatabase.getInstance().getReference("NotificacionAmistad");

                            /*Query query=  noDuplicate.orderByChild("correoSolicitante").equalTo(correoSolicitante);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    NotificacionAmistad notificacionAmistad = new NotificacionAmistad();
                                    for (DataSnapshot datasnapshot : dataSnapshot.getChildren()){
                                        notificacionAmistad = datasnapshot.getValue(NotificacionAmistad.class);
                                        if(notificacionAmistad.getCorreoSolicitante().equalsIgnoreCase(correoSolicitante)){
                                            contador ++;

                                        }

                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            if (contador==0){
                                basedato.child(keyUser).push().setValue(notificacionAmistad);
                                Toast.makeText(view.getContext(), "Solicitud Enviada" , Toast.LENGTH_LONG ).show();
                            }
                            }
                            else if(contador>0){
                                Toast.makeText(view.getContext(), "El Usuario ya Tiene una Tolicitud de Usted", Toast.LENGTH_LONG ).show();
                                //Toast.makeText(AddFriendActivity.this, "El Usuario ya Tiene una Tolicitud de Usted" , Toast.LENGTH_LONG ).show();
                            }*/
                        }
                        else if(cont==0){
                            Log.v("encontre nada => ", String.valueOf(+ cont));
                            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                            builder.setTitle("Solicitud");
                            builder.setMessage("Usuario no Encontrado");
                            builder.setIcon(R.drawable.ic_info_black_24dp);
                            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        return view;
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
