package com.example.patin.usuariocanchas.Model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.patin.usuariocanchas.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.NotificacionAmistadViewHolder>{
    List<NotificacionAmistad> notificacionAmistads;

    public Adapter(List<NotificacionAmistad> notificacionAmistads) {
        this.notificacionAmistads = notificacionAmistads;
    }

    @NonNull
    @Override
    public NotificacionAmistadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler,parent,false);
        NotificacionAmistadViewHolder holder=new NotificacionAmistadViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificacionAmistadViewHolder holder, int position) {
        NotificacionAmistad notificacionAmistad=notificacionAmistads.get(position);
        holder.nombre.setText(notificacionAmistad.getNombreSolicitante());
        holder.apellido.setText(notificacionAmistad.getApellidoSolicitante());
        holder.correo.setText(notificacionAmistad.getApellidoSolicitante());
        holder.nick.setText(notificacionAmistad.getApellidoSolicitante());
    }

    @Override
    public int getItemCount() {
        return notificacionAmistads.size();
    }

    public static class NotificacionAmistadViewHolder extends RecyclerView.ViewHolder{
    TextView nombre,apellido,correo,nick;

    public NotificacionAmistadViewHolder(View itemView) {
        super(itemView);
        nombre=itemView.findViewById(R.id.textView_nombre);
        apellido=itemView.findViewById(R.id.textView_apellido);
        correo=itemView.findViewById(R.id.textView_correo);
        nick=itemView.findViewById(R.id.textView_nick);
    }
}


}
