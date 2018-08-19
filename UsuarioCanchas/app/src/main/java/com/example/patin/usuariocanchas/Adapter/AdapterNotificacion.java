package com.example.patin.usuariocanchas.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.patin.usuariocanchas.Model.Amigo;
import com.example.patin.usuariocanchas.Model.NotificacionAmistad;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.example.patin.usuariocanchas.Values.SingletonUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterNotificacion extends RecyclerView.Adapter<AdapterNotificacion.NotificacionAmistadViewHolder>{
    List<NotificacionAmistad> notificacionAmistads;
    View v;

    public AdapterNotificacion(List<NotificacionAmistad> notificacionAmistads) {
        this.notificacionAmistads = notificacionAmistads;
    }

    @NonNull
    @Override
    public NotificacionAmistadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_notificacion,parent,false);
        NotificacionAmistadViewHolder holder=new NotificacionAmistadViewHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final NotificacionAmistadViewHolder holder, final int position) {
        final NotificacionAmistad notificacionAmistad=notificacionAmistads.get(position);
        String nombreApellido=notificacionAmistad.getNombreSolicitante()+" "+notificacionAmistad.getApellidoSolicitante();
        holder.nombre.setText(nombreApellido);
        holder.tituloNotificaion.setText("Notificacion de Amistad");
        final String keyUser = SingletonUser.getInstance().getId();
        final String correoMio = SingletonUser.getInstance().getEmail();
        holder.correo.setText(notificacionAmistad.getCorreoSolicitante());
        Button aceptar= (Button) v.findViewById(R.id.button_aceptar_amigo);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference base = FirebaseDatabase.getInstance().getReference("Amigos/"+keyUser);//esta intancia agrega amigo para una persona
                DatabaseReference base1 = FirebaseDatabase.getInstance().getReference("Amigos/"+notificacionAmistad.getIdSolicitado());//esta instancia agrega amigo para la otra persona
                String apellidoAmigo=notificacionAmistad.getApellidoSolicitante();
                String corrreoAmigo=notificacionAmistad.getCorreoSolicitante();
                String nombreAmigo=notificacionAmistad.getNombreSolicitante();
                final String miCorreo=notificacionAmistad.getCorreoSolicitado();
                Amigo amigo=new Amigo(apellidoAmigo,corrreoAmigo, nombreAmigo, miCorreo);

                String miApellido=SingletonUser.getInstance().getSurname();
                String miCorrreo=SingletonUser.getInstance().getEmail();
                String miNombre=SingletonUser.getInstance().getName();
                String CorreoAmigo=notificacionAmistad.getCorreoSolicitante();
                Amigo amigo2=new Amigo(miApellido,miCorrreo, miNombre, CorreoAmigo);

                if(!base.push().setValue(amigo).isSuccessful() && !base1.push().setValue(amigo2).isSuccessful()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Felicidades");
                    builder.setMessage("Ya Son Amigos");
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
                    builder.setMessage("No se Pudo Realizar la Solicitud");
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
        });
        Button ignorar= (Button) v.findViewById(R.id.button_ignorar_amigo);
        ignorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference(FireBaseReferences.NOTIFICACIONAMISTAD_REFEREMCE+"/"+keyUser).removeValue();
                notificacionAmistads.remove(position);

            }
        });
    }



    @Override
    public int getItemCount() {
        return notificacionAmistads.size();
    }

    public static class NotificacionAmistadViewHolder extends RecyclerView.ViewHolder{
    TextView nombre,apellido,correo,tituloNotificaion;

    public NotificacionAmistadViewHolder(View itemView) {
        super(itemView);
        tituloNotificaion=itemView.findViewById(R.id.titulo_notificacion);
        nombre=itemView.findViewById(R.id.textView_nombre);
        correo=itemView.findViewById(R.id.textView_correo);

    }
}
}
