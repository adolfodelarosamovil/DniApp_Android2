package com.example.dniapp.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dniapp.R;
import com.example.dniapp.adapter.DniAdapter;
import com.example.dniapp.beans.Dni;
import com.example.dniapp.util.Comparador;
import com.example.dniapp.util.Preferencias;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListaDnisActivity extends AppCompatActivity {

    List<Dni> dniList;
    DniAdapter dniAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dnis);

        dniList = Preferencias.cargarFicheroDni(this);

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

    }

    public void ordenarPorLetra(View view) {
        Log.d("MIAPP", "EN ordenarPorLetra");
        Collections.sort(dniList,new Comparador());
        dniAdapter.setDniList(dniList);
        dniAdapter.notifyDataSetChanged();
    }
}
