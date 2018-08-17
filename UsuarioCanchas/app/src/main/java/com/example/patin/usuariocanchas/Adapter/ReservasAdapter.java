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
import com.example.patin.usuariocanchas.Model.Reserva;
import com.example.patin.usuariocanchas.R;
import com.example.patin.usuariocanchas.Values.FireBaseReferences;
import com.example.patin.usuariocanchas.Values.SingletonUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ReservasAdapter extends RecyclerView.Adapter<ReservasAdapter.ReservasViewHolder>{
    List<Reserva> reservas;
    View v;

    public ReservasAdapter(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @NonNull
    @Override
    public ReservasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_reserva,parent,false);
        ReservasViewHolder holder=new ReservasViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ReservasViewHolder holder, final int position) {
        final Reserva reserva= reservas.get(position);
        String nombreCancha=reserva.getIdCancha();
        String fechaEvento=reserva.getFecha_evento();
        String horaInicio=reserva.getHora_inicio();
        String horaTermino=reserva.getHora_termino();
        String montoCancelado="5mil";
        holder.nombreCancha.setText(nombreCancha);
        holder.fechaEvento.setText(fechaEvento);
        holder.horaInicio.setText(horaInicio);
        holder.horaTermino.setText(horaTermino);
        holder.montoCancelado.setText(montoCancelado);
    }

    @Override
    public int getItemCount() {
        return reservas.size();
    }

    public static class ReservasViewHolder extends RecyclerView.ViewHolder{
        TextView nombreCancha,fechaEvento,horaInicio,horaTermino,montoCancelado;

        public ReservasViewHolder(View itemView) {
            super(itemView);
            nombreCancha=itemView.findViewById(R.id.textView_nombre_cancha);
            fechaEvento=itemView.findViewById(R.id.textView_fecha_evento_st);
            horaInicio=itemView.findViewById(R.id.textView_hora_inicio_st);
            horaTermino=itemView.findViewById(R.id.textView_hora_termino_st);
            montoCancelado=itemView.findViewById(R.id.textView_valor_cancelado_st);

        }
    }
}