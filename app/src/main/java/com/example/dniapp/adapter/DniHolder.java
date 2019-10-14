package com.example.dniapp.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dniapp.R;
import com.example.dniapp.beans.Dni;

public class DniHolder extends RecyclerView.ViewHolder {


    private TextView textViewDni;
    private TextView textViewLetra;

    public DniHolder(@NonNull View itemView) {
        super(itemView);
        textViewDni = (TextView)itemView.findViewById(R.id.num_dni);
        textViewLetra = (TextView)itemView.findViewById(R.id.letra_dni);
    }


    public void cargarDniEnHolder(Dni dni) {
        textViewDni.setText(String.valueOf(dni.getNumero()));
        textViewLetra.setText(String.valueOf(dni.getLetra()));
    }
}
