package com.example.dniapp.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dniapp.R;
import com.example.dniapp.adapter.DniAdapter;
import com.example.dniapp.beans.Dni;
import com.example.dniapp.dao.BaseDatosDni;
import com.example.dniapp.util.Comparador;
import com.example.dniapp.util.Preferencias;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListaDnisActivity extends AppCompatActivity {

    List<Dni> dniList;
    DniAdapter dniAdapter;
    BaseDatosDni baseDatosDni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dnis);

        //dniList = Preferencias.cargarFicheroDni(this);


        //creo el objeto de la base de datos
        this.baseDatosDni = new BaseDatosDni(this, "MiDB", null, 1);


        dniList = baseDatosDni.buscarDnis();

        if (dniList!=null && dniList.size() > 0)
        {
            findViewById(R.id.caja_no_resultado).setVisibility(View.GONE);
            dniAdapter = new DniAdapter(dniList);
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setAdapter(dniAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }

    }

    public void ordenarPorNumero(View view) {
        Log.d("MIAPP", "EN ordenarPorNumero");
        Collections.sort(dniList);
        dniAdapter.setDniList(dniList);
        dniAdapter.notifyDataSetChanged();
        Toast toast = Toast.makeText(this, "Lista ordenada por número", Toast.LENGTH_LONG);
        toast.show();

    }

    public void ordenarPorLetra(View view) {
        Log.d("MIAPP", "EN ordenarPorLetra");
        Collections.sort(dniList,new Comparador());
        dniAdapter.setDniList(dniList);
        dniAdapter.notifyDataSetChanged();

        Toast toast = Toast.makeText(this, "Lista ordenada por letra", Toast.LENGTH_LONG);
        toast.show();
    }
}
